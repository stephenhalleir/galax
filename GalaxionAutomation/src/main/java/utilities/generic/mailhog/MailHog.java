package utilities.generic.mailhog;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.config.ConfigReader;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.mailhog.objects.Items;
import utilities.generic.mailhog.objects.MailhogResponse;
import utilities.test.config_readers.IONEmailManager;

public class MailHog {
	
	public MailHog() {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public static String getDeeplinkFromMailhog(String emailAddress,String emailID) {
		
		IONEmailManager emailManager = new IONEmailManager();
		String startString=emailManager.get(emailID, "BEFORESTRING");
		String endString=emailManager.get(emailID, "AFTERSTRING");
		
		String emailBody = getEmailBodyText(emailAddress, emailManager.get(emailID, "SUBJECT"), emailManager.get(emailID, "CONTAINS"));
		
		if(emailBody!=null) {
			return getLinkFromString(emailBody,startString,endString);
		}

		return null;		
	}
	
	// helper method to read a deeplink from a large string based on start and end strings
	public static String getLinkFromString(String s, String startString, String endString) {
		s = s.substring(s.indexOf(startString)).trim();
		return s.substring(s.indexOf("http"),s.indexOf(endString));
	}
	
	public static String getEmailBodyText(String email, String subject,String searchString) {
		/*
		 * Create a 60 second Loop. Every 10 seconds check for keycloak email in
		 * mailhog. If email is found, get the deeplink from email body and break the
		 * loop.
		 */
		
		System.out.println("Calling getEmailBodyText(): " + email + ", " + subject + ", " + searchString);
		
		long loopStart = System.currentTimeMillis();
		long loopEnd = loopStart + 20 * 1000;
		String messageBody=null;
		
		while (System.currentTimeMillis() < loopEnd) {
			
			// Get Emails
			List<Items> emailsList = null;
			emailsList = MailHog.getEmailsContainingString(email);

			System.out.println("MailHog: " + emailsList.size() + " email(s) found. Now iterating through each email and looking for a deeplink.");
			
			// for each email found
			for (Items emailItem : emailsList) {	
				
				// Find email sent from 'Customer Keycloak'. This is the domain of keycloak emails
				if (emailItem.getContent().getHeaders().getSubject().get(0).equals(subject) && emailItem.getContent().getHeaders().getTo().get(0).equalsIgnoreCase(email) && emailItem.getContent().getBody().contains(searchString)) {
					
					// return the image body without the new line characters
					messageBody = emailItem.getContent().getBody().replaceAll("=\r\n", "").replace("=3D", "=");
					return messageBody;
				}
			}
			
			try {
				if (messageBody != null) {
					break;
				}
				System.out.println("No emails found containing \nSleeping for 10 seconds");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		return null;
	}
	

	public static String getEmailJsonArrayString(String email) {
		
		// Set eir proxy properties
		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));

		RestAssured.useRelaxedHTTPSValidation();

		RestAssured.baseURI = EnvironmentDirectory.getMailhogURL();
		LoginCredentials credentials=EnvironmentDirectory.getMailhogLoginCredentials();
		
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given().auth().basic(credentials.getUsername(), credentials.getPassword());

		// Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("/api/v2/search?kind=containing&query=" + email.trim());

		// Response.asString method will directly return the content of the body
		// as String.
		String jsonString = response.asString();
		return jsonString;
	}

	public static List<Items> getEmailsContainingString(String string) {
		String emailJsonArray = MailHog.getEmailJsonArrayString(string);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MailhogResponse mailhogResponse = null;
		try {
			mailhogResponse = objectMapper.readValue(emailJsonArray, MailhogResponse.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<Items> emailsList = mailhogResponse.getItems();
		return emailsList;
	}
}
package utilities.generic.mailhog;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.config.ConfigReader;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.mailhog2.Email;

public class MailHog2 {
	
	public MailHog2() {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public static ArrayList<Email> getEmails(String emailAddress) {
		
		ArrayList<Email> emails = new ArrayList<Email>();
		
		Response response = MailHog2.getEmailJsonArrayString(emailAddress);
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		int numEmails = jsonPathEvaluator.getList("items").size();
		
		// for each email returned
		for(int i=0;i<numEmails;i++) {
			
			Email email = new Email();
			
			email.setDate(jsonPathEvaluator.getString("items[" + i + "].Content.Headers.Date[0]"));
			email.setFrom(jsonPathEvaluator.getString("items[" + i + "].Content.Headers.From[0]"));
			email.setSubject(jsonPathEvaluator.getString("items[" + i + "].Content.Headers.Subject[0]"));
			email.setTo(jsonPathEvaluator.getString("items[" + i + "].Content.Headers.To[0]"));
			email.setData(jsonPathEvaluator.getString("items[" + i + "].Raw.Data"));
			emails.add(email);
		}
		
		return emails;
	}
	
	
	public static Response getEmailJsonArrayString(String email) {
		
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

		System.err.println(RestAssured.baseURI + "/api/v2/search?kind=to&query=" + email.trim());
		
		// Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("/api/v2/search?kind=to&query=" + email.trim());

		// Response.asString method will directly return the content of the body
		// as String.
		return response;
	}
}
package microservices.backend.eir_elavon_facade_backend.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import utilities.api.RestAssuredUtil;
import utilities.config.ConfigReader;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class ElavonFacadeAPI {

	public static APITransaction generateHPP(String prospectUuid) {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.defaultParser = Parser.JSON;

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));
		
		String url=EnvironmentDirectory.getAPIElavonFacade() + "/hpp/payment/generate";
		GenerateHppRequestDTO dto = new GenerateHppRequestDTO(prospectUuid);
		
		Response r = given().header("requestor-id","payment-center").contentType(ContentType.JSON).body(PojoToJsonConverter.getJSON(dto)).post(url).then().extract().response();

		return new APITransaction(url,r);
	}
	
	public static APITransaction processPayment(String hppResponse) {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.defaultParser = Parser.JSON;

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));
		
		String url=EnvironmentDirectory.getAPIElavonFacade() + "/hpp/payment/process";
		ProcessPaymentDTO dto = new ProcessPaymentDTO(hppResponse);
		System.out.println("Posting: " + PojoToJsonConverter.getJSON(dto));
		Response r = given().header("requestor-id","payment-center").contentType(ContentType.JSON).body(PojoToJsonConverter.getJSON(dto)).post(url).then().extract().response();

		return new APITransaction(url,r);
	}
	
	
}

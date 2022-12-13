package utilities.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import utilities.config.ConfigReader;

public class GraphQLUtil {

	//private static Matcher<Integer> statusOk = anyOf(is(200), is(201), is(204));

	/**
	 * Set up the REST-Assured and proxy settings
	 */
	private static void setup() {

		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.defaultParser = Parser.JSON;

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));
	}

	/**
	 * New GET request
	 * 
	 * @return Response object
	 */
	public static Response galaxionGet(String url, String token) {
		System.err.println("API GET " + url);
		setup();
		
		if (token == null) {
			return given().header("Content-type","application/graphql").get(url).then().extract().response();
		} else {
			return given().contentType(ContentType.JSON).auth().oauth2(token).get(url).then().extract().response();
		}
	}

	public static void main(String[] args) {

	}
}

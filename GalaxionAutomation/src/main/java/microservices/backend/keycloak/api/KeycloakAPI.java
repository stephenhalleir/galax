package microservices.backend.keycloak.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import microservices.backend.keycloak.enums.Realm;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;

public class KeycloakAPI {

	/**
	 * Generate a token using grant type "password"
	 * 
	 * @param clientID
	 * @param clientSecret
	 * @param realmID
	 * @param username
	 * @param password
	 * @return token string
	 */
	public static String getToken(String clientID, String clientSecret, Realm realmID, String username, String password) {

		// set RestAssured properties
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.defaultParser = Parser.JSON;

		// construct the URL
		String url = EnvironmentDirectory.getKeycloakURL() + "/auth/realms/$realm/protocol/openid-connect/token";
		url = url.replace("$realm", realmID.toString());

		// generate the response
		Response r = given().headers("Content-Type", "application/x-www-form-urlencoded").param("client_id", clientID).param("grant_type", "password")
				.param("username", username).param("password", password).param("client_secret", clientSecret).when().post(url).then()
				.contentType(ContentType.JSON).extract().response();
		System.err.println("KeycloakAPI.getToken():" + url + ", " + r.statusCode());

		// return the field "access_token"
		return r.jsonPath().get("access_token");
	}

	public static APITransaction getAccount(String token) {
		String url = "https://keycloak.perf.ion.comhar.local/auth/realms/gomo/account";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}
}

package utilities.api;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class RestAssuredUtil {

	/**
	 * Set up the REST-Assured and proxy settings
	 */
	private static void setup() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.defaultParser = Parser.JSON;
	}

	/**
	 * GET request
	 * 
	 * @return Response object
	 */
	public static Response galaxionGet(String url, String token) {
		System.err.println("API GET " + url);
		setup();

		Response r = null;

		if (token == null) {
			r = given().contentType(ContentType.JSON).get(url).then().extract().response();
		} else {
			r = given().contentType(ContentType.JSON).auth().oauth2(token).get(url).then().extract().response();
		}

		System.err.println("Response time: " + r.getTime() + " milliseconds");
		return r;
	}

	/**
	 * GET request with no URL encoding
	 * 
	 * @return Response object
	 */
	public static Response galaxionGetNoUrlEncoding(String url, String token) {
		System.err.println("API GET " + url);
		setup();

		Response r = null;

		if (token == null) {
			r = given().contentType(ContentType.JSON).urlEncodingEnabled(false).get(url).then().extract().response();
		} else {
			r = given().contentType(ContentType.JSON).urlEncodingEnabled(false).auth().oauth2(token).get(url).then().extract().response();
		}

		System.err.println("Response time: " + r.getTime() + " milliseconds");
		return r;
	}

	/**
	 * OPTIONS request
	 * 
	 * @return Response object
	 */
	public static Response galaxionOptions(String url, String token) {
		System.err.println("API OPTIONS " + url);
		setup();

		Response r = null;

		if (token == null) {
			r = given().contentType(ContentType.JSON).options(url).then().extract().response();
		} else {
			r = given().contentType(ContentType.JSON).auth().oauth2(token).options(url).then().extract().response();
		}

		System.err.println("Response time: " + r.getTime() + " milliseconds");
		return r;
	}

	/**
	 * PUT request
	 * 
	 * @return Response object
	 */
	public static Response galaxionPut(String url, String payload, String token) {
		setup();
		System.err.println("API PUT " + url + ":\n" + payload);

		if (token == null && payload == null) {
			return given().contentType(ContentType.JSON).put(url).then().extract().response();
		} else if (token == null && payload != null) {
			return given().contentType(ContentType.JSON).body(payload).put(url).then().extract().response();
		} else if (token != null && payload == null) {
			return given().contentType(ContentType.JSON).auth().oauth2(token).put(url).then().extract().response();
		} else if (token != null && payload != null) {
			return given().contentType(ContentType.JSON).auth().oauth2(token).body(payload).put(url).then().extract().response();
		}
		return null;
	}

	/**
	 * POST request
	 * 
	 * @return
	 */
	public static Response galaxionPost(String url, String payload, String token) {
		setup();

		System.err.println("API POST " + url + ":\n" + payload);

		if (token == null && payload == null) {
			return given().contentType(ContentType.JSON).post(url).then().extract().response();
		} else if (token == null && payload != null) {
			return given().contentType(ContentType.JSON).body(payload).post(url).then().extract().response();
		} else if (token != null && payload == null) {
			return given().contentType(ContentType.JSON).auth().oauth2(token).post(url).then().extract().response();
		} else if (token != null && payload != null) {
			return given().contentType(ContentType.JSON).auth().oauth2(token).body(payload).post(url).then().extract().response();
		}
		return null;
	}

	/**
	 * DELETE request
	 * 
	 * @return
	 */
	public static Response galaxionDelete(String url, String token) {
		setup();

		System.err.println("API DELETE " + url);

		if (token == null) {
			return given().contentType(ContentType.JSON).delete(url).then().extract().response();
		} else {
			return given().contentType(ContentType.JSON).auth().oauth2(token).delete(url).then().extract().response();
		}
	}

	public static Response galaxionPostFile(String url, String token, File file) {
		return given().auth().oauth2(token).multiPart("file", file).accept(ContentType.JSON).when().post(url).then().extract().response();
	}

	public static Response galaxionPatch(String url, String payload, String token) {
		setup();

		System.err.println("API PATCH " + url + ":\n" + payload);

		if (token == null && payload == null) {
			return given().contentType(ContentType.JSON).patch(url).then().extract().response();
		} else if (token == null && payload != null) {
			return given().contentType(ContentType.JSON).body(payload).patch(url).then().extract().response();
		} else if (token != null && payload == null) {
			return given().contentType(ContentType.JSON).auth().oauth2(token).patch(url).then().extract().response();
		} else if (token != null && payload != null) {
			return given().contentType(ContentType.JSON).auth().oauth2(token).body(payload).patch(url).then().extract().response();
		}
		return null;
	}

	public static Response galaxionMergePatch(String url, String payload, String token) {
		setup();

		System.err.println("API PATCH " + url + ":\n" + payload);

		if (token == null && payload == null) {
			return given().header("Content-Type", "application/merge-patch+json").patch(url).then().extract().response();
		} else if (token == null && payload != null) {
			return given().header("Content-Type", "application/merge-patch+json").body(payload).patch(url).then().extract().response();
		} else if (token != null && payload == null) {
			return given().header("Content-Type", "application/merge-patch+json").auth().oauth2(token).patch(url).then().extract().response();
		} else if (token != null && payload != null) {
			return given().header("Content-Type", "application/merge-patch+json").auth().oauth2(token).body(payload).patch(url).then().extract().response();
		}
		return null;
	}

	/**
	 * This GET request is used specifically for calls to MT's customer history
	 * service
	 * 
	 * @param url
	 * @param token
	 * @return
	 */
	public static Response galaxionGetMT(String url, String token) {
		System.err.println("API GET " + url);
		setup();

		String mtContentType="application/vnd.monacotelecom.v2+json;charset=UTF-8";
		
		Response r = null;

		if (token == null) {
			r = given().header("accept", mtContentType)
					.header("content-type", mtContentType).urlEncodingEnabled(false).get(url).then().extract()
					.response();
		} else {
			r = given().header("accept", mtContentType)
					.header("content-type", mtContentType).urlEncodingEnabled(false).auth().oauth2(token).get(url)
					.then().extract().response();
		}

		System.err.println("Response time: " + r.getTime() + " milliseconds");
		return r;
	}

	/**
	 * Send a SOAP POST request to an an endpoint using RestAssured
	 * 
	 * @param url
	 * @param payload
	 * @return Response object
	 */
	public static Response galaxionSoapPost(String url, String payload) {
		setup();

		System.err.println("RestAssured: SOAP - POST " + url + ":\n" + payload);

		return given().header("Content-type", "text/html;charset=utf-8").body(payload).post(url).then().extract().response();
	}

	/**
	 * New SOAP POST request, specifically for the HLR-FE
	 * 
	 * @return
	 */
	public static Response HLRFESoapPost(String url, String payload, String action) {
		setup();

		System.err.println("SOAP POST " + url + ":\n" + payload);

		return given().header("Content-type", "text/xml; charset=utf-8").header("SOAPAction", "CAI3G#" + action).body(payload).post(url).then().extract()
				.response();
	}

	public static void main(String[] args) {

	}
}

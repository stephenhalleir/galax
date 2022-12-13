package external_systems.device_enrollment.api;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DepAPI {
	public static Response postDEPFile(String url, String filepath) {
		RestAssured.useRelaxedHTTPSValidation();
		File file = new File(filepath);
		System.out.println("Uploading file " + file.getName());

		return given().headers("orderType", "OR").headers("deviceType", "apple").multiPart("file", file).accept(ContentType.JSON).when().post(url).then()
				.extract().response();
	}
}

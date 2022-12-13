package microservices.backend.eir_bulk_backend.api;

import java.io.File;

import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;

public class BulkAPI {

	/**
	 * Use the Bulk API to upload a file to the bulk microservice
	 * @param flow
	 * @param filepath
	 * @return the API response object
	 */
	public static Response uploadBulkFile(String token, BulkRefFlow flow, String filepath) {
		
		// construct the URL
		String endpoint = EnvironmentDirectory.getAPIBulkKey() + "/api/v1/private/auth/flows/" + flow.toString();
		System.out.println("BulkFileUploader posting file " + filepath + " to \n --> " + endpoint);
		
		// generate token
		String agentEmailAddress = EnvironmentDirectory.getB2BAgentEmailAddress();
		LoginCredentials login = EnvironmentDirectory.getB2BAgentLogin();
		
		// create a file object
		File file = new File(filepath);
		
		// call the API to post the file
		return RestAssuredUtil.galaxionPostFile(endpoint, token, file);
	}
}

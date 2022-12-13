package microservices.backend.eir_document_management_backend.api;

import java.io.File;

import io.restassured.response.Response;
import microservices.backend.eir_document_management_backend.enums.DocumentType;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;

public class DocumentAPI {

	/**
	 * Upload a document via the document-management-backend REST API
	 * 
	 * @param accountID    - the 4 digit account.id
	 * @param documentType - the document type - e.g. CONTRACT, MANIFEST, etc
	 * @param filepath     - path on the local machine where the file exists
	 * @return JSON response
	 */
	public static Response uploadDocument(String token, int accountID, DocumentType documentType, String filepath) {

		// create a file object
		File file = new File(filepath);
		
		// construct the URL
		String endpoint = EnvironmentDirectory.getAPIDocuments()
				+ "/private/auth/documents/v2?accountId=$accountID&documentFileName=$filename&domain=B2B&documentStatus=DRAFT&documentName=$documentType&documentVersion=1.0";
		endpoint = endpoint.replace("$accountID", Integer.toString(accountID));
		endpoint = endpoint.replace("$filename", file.getName());
		endpoint = endpoint.replace("$documentType", documentType.toString());

		System.out.println("DocumentUploader posting file " + filepath + " to \n --> " + endpoint);

		// generate token
		String agentEmailAddress = EnvironmentDirectory.getB2BAgentEmailAddress();
		LoginCredentials login = EnvironmentDirectory.getB2BAgentLogin();

		// call the API to post the file
		return RestAssuredUtil.galaxionPostFile(endpoint, token, file);
	}
}

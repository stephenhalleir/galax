package microservices.backend.eir_bulk_backend.utilities;

import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.bulk_files.base_file.BulkFile;

/**
 * This class represents a result from a BULK action and is used for reporting
 * 
 * @author stephenhall
 *
 */
public class BulkResult {
	
	private Response response;
	private BulkFile file;
	
	public BulkResult(Response response, BulkFile file) {
		super();
		this.response = response;
		this.file = file;
	}
	
	public BulkResult() {
		response=null;
		file=null;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public BulkFile getFile() {
		return file;
	}

	public void setFile(BulkFile file) {
		this.file = file;
	}
	
	public String toString() {
		return response.statusCode() + ", " + response.asString();
	}
}

package utilities.generic.api;

import io.restassured.response.Response;

/**
 * This class represents an API transaction and stores the request, response and URL
 * Useful for logging
 * 
 * @author stephenhall
 *
 */
public class APITransaction {

	private String requestBody;
	private String url;
	private Response response;
	private HTTPMethod method;
	
	public APITransaction() {
		
	}
	
	public APITransaction(String url, Response response) {
		this.url=url;
		this.response=response;
	}

	public APITransaction(String url, String requestBody, Response response) {
		super();
		this.requestBody = requestBody;
		this.url = url;
		this.response = response;
	}
	
	public APITransaction(HTTPMethod method,String url, String requestBody, Response response) {
		super();
		this.requestBody = requestBody;
		this.url = url;
		this.response = response;
		this.method=method;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	public HTTPMethod getMethod() {
		return method;
	}

	public void setMethod(HTTPMethod method) {
		this.method = method;
	}

	public String toString() {
		return "Method: " + method + "\nEndpoint: "+ url + "\nPayload: " + requestBody + "\nResponse: " + response.statusCode() + ", " + response.asString() + "\nResponse time: " + response.getTime() + " milliseconds";
	}
}

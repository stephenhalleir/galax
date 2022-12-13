package testCases.environment_acceptance.test_objects;

public class ApiCheck {
	private String system;
	private String description;
	private String callingService;
	private String method;
	private String url;
	private String payload;
	private int expectedStatusCode;
	private String actualStatusCode;
	private String responseBody;
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCallingService() {
		return callingService;
	}
	public void setCallingService(String callingService) {
		this.callingService = callingService;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public int getExpectedStatusCode() {
		return expectedStatusCode;
	}
	public void setExpectedStatusCode(int expectedStatusCode) {
		this.expectedStatusCode = expectedStatusCode;
	}
	public String getActualStatusCode() {
		return actualStatusCode;
	}
	public void setActualStatusCode(String actualStatusCode) {
		this.actualStatusCode = actualStatusCode;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
}

package microservices.backend.eir_provisioning_facade_backend.objects;

public class ProvisioningError {
	
	private String errorCode;
	private String errorDescription;
	
	public ProvisioningError() {
		errorCode=null;
		errorDescription=null;
	}
	
	public ProvisioningError(String errorCode, String errorDescription) {
		this.errorCode=errorCode;
		this.errorDescription=errorDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}

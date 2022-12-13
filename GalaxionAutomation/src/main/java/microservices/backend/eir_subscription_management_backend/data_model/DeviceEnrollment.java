package microservices.backend.eir_subscription_management_backend.data_model;

public class DeviceEnrollment {
	
	private int id;
	private String deviceEnrollmentRef;
	private String deviceEnrollmentProvider;
	private int b2bAccountID;

	public DeviceEnrollment() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceEnrollmentRef() {
		return deviceEnrollmentRef;
	}

	public void setDeviceEnrollmentRef(String deviceEnrollmentRef) {
		this.deviceEnrollmentRef = deviceEnrollmentRef;
	}

	public String getDeviceEnrollmentProvider() {
		return deviceEnrollmentProvider;
	}

	public void setDeviceEnrollmentProvider(String deviceEnrollmentProvider) {
		this.deviceEnrollmentProvider = deviceEnrollmentProvider;
	}

	public int getB2bAccountID() {
		return b2bAccountID;
	}

	public void setB2bAccountID(int b2bAccountID) {
		this.b2bAccountID = b2bAccountID;
	}	
}

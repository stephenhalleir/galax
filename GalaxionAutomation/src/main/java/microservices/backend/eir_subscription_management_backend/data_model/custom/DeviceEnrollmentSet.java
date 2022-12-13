package microservices.backend.eir_subscription_management_backend.data_model.custom;

public class DeviceEnrollmentSet {

	private String appleEnrollment;
	private String samsungEnrollment;
	private String androidEnrollment;

	public DeviceEnrollmentSet() {
		super();
	}

	public String getAppleEnrollment() {
		return appleEnrollment;
	}

	public void setAppleEnrollment(String appleEnrollment) {
		this.appleEnrollment = appleEnrollment;
	}

	public String getSamsungEnrollment() {
		return samsungEnrollment;
	}

	public void setSamsungEnrollment(String samsungEnrollment) {
		this.samsungEnrollment = samsungEnrollment;
	}

	public String getAndroidEnrollment() {
		return androidEnrollment;
	}

	public void setAndroidEnrollment(String androidEnrollment) {
		this.androidEnrollment = androidEnrollment;
	}
}

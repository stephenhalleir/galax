package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_device_enrollment;

public class DeviceEnrollmentDTO {

	private String deviceEnrollmentProvider;
	private String deviceEnrollmentRef;

	public DeviceEnrollmentDTO(String deviceEnrollmentProvider, String deviceEnrollmentRef) {
		super();
		this.deviceEnrollmentProvider = deviceEnrollmentProvider;
		this.deviceEnrollmentRef = deviceEnrollmentRef;
	}

	public DeviceEnrollmentDTO() {
		super();
	}

	public String getDeviceEnrollmentProvider() {
		return deviceEnrollmentProvider;
	}

	public void setDeviceEnrollmentProvider(String deviceEnrollmentProvider) {
		this.deviceEnrollmentProvider = deviceEnrollmentProvider;
	}

	public String getDeviceEnrollmentRef() {
		return deviceEnrollmentRef;
	}

	public void setDeviceEnrollmentRef(String deviceEnrollmentRef) {
		this.deviceEnrollmentRef = deviceEnrollmentRef;
	}
}

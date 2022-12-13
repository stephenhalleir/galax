package external_systems.device_enrollment.data_model;

/**
 * This class represents an item in the device enrollment platform's ANDROID_DEVICES table
 * @author stephenhall
 *
 */

public class AndroidDevice {
	private int id;
	private String resellerId;
	private String companyId;
	private String deviceId;
	private String orderNumber;

	public AndroidDevice() {
		id = -1;
		resellerId = "";
		companyId = "";
		deviceId = "";
		orderNumber = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResellerId() {
		return resellerId;
	}

	public void setResellerId(String resellerId) {
		this.resellerId = resellerId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}

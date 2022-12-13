package external_systems.device_enrollment.data_model;

import java.sql.Blob;

public class AppleResponse {
	private int id;
	private String deviceEnrollmentTransactionId;
	private Blob jsonRequest;
	private Blob jsonResponse;
	private String orderNumber;
	private String deviceId;
	private String statusCode;
	private String devicePostStatus;
	private String devicePostStatusMessage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blob getJsonRequest() {
		return jsonRequest;
	}

	public void setJsonRequest(Blob jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	public Blob getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(Blob jsonResponse) {
		this.jsonResponse = jsonResponse;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDeviceEnrollmentTransactionId() {
		return deviceEnrollmentTransactionId;
	}

	public void setDeviceEnrollmentTransactionId(String deviceEnrollmentTransactionId) {
		this.deviceEnrollmentTransactionId = deviceEnrollmentTransactionId;
	}

	public String getDevicePostStatus() {
		return devicePostStatus;
	}

	public void setDevicePostStatus(String devicePostStatus) {
		this.devicePostStatus = devicePostStatus;
	}

	public String getDevicePostStatusMessage() {
		return devicePostStatusMessage;
	}

	public void setDevicePostStatusMessage(String devicePostStatusMessage) {
		this.devicePostStatusMessage = devicePostStatusMessage;
	}
}

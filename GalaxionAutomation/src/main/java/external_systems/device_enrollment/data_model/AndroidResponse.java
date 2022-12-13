package external_systems.device_enrollment.data_model;

import java.sql.Blob;

public class AndroidResponse {
	
	private int id;
	private String operationId;
	private Blob jsonRequest;
	private Blob jsonResponse;
	private String orderNumber;
	private String deviceId;
	private String serialNumber;
	private String operationType;
	private String statusCode;
	private String manufacturerType;
	private String statusDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getManufacturerType() {
		return manufacturerType;
	}

	public void setManufacturerType(String manufacturerType) {
		this.manufacturerType = manufacturerType;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
}

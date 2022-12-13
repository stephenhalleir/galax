package pojo_repo.eir_topup_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopUp {

	private String uuid;
	private String status;
	private int amount;
	private String currency;
	private String payerReference;
	private String ownerUuid;
	private String voucherSerialNumber;
	private int serviceId;
	private int networkId;
	private int adjustmentId;
	private String msisdn;
	private String errorCode;
	private String errorMessage;
	private Date createdAt;
	private Date updatedAt;
	private String orderReference;

	public TopUp() {

	}

	public TopUp(ResultSet rs) {
		try {
			uuid = rs.getString("uuid");
			status = rs.getString("status");
			amount = rs.getInt("amount");
			currency = rs.getString("currency");
			payerReference = rs.getString("payer_reference");
			ownerUuid = rs.getString("owner_uuid");
			voucherSerialNumber = rs.getString("voucher_serial_number");
			serviceId = rs.getInt("service_id");
			networkId = rs.getInt("network_id");
			adjustmentId = rs.getInt("adjustment_id");
			msisdn = rs.getString("msisdn");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			orderReference = rs.getString("order_reference");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayerReference() {
		return payerReference;
	}

	public void setPayerReference(String payerReference) {
		this.payerReference = payerReference;
	}

	public String getOwnerUuid() {
		return ownerUuid;
	}

	public void setOwnerUuid(String ownerUuid) {
		this.ownerUuid = ownerUuid;
	}

	public String getVoucherSerialNumber() {
		return voucherSerialNumber;
	}

	public void setVoucherSerialNumber(String voucherSerialNumber) {
		this.voucherSerialNumber = voucherSerialNumber;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public int getAdjustmentId() {
		return adjustmentId;
	}

	public void setAdjustmentId(int adjustmentId) {
		this.adjustmentId = adjustmentId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

}
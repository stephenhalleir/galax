package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChargedUsagePostpay {

	private int id;
	private Date lastModifiedDateTime;
	private int mobileUsagePostpayId;
	private int amount;
	private int billingAccountId;
	private int subscriptionId;
	private int serviceId;
	private String serviceName;
	private Date chargeStartDateTime;
	private int duration;
	private int quantity;
	private String originNumber;
	private String destinationNumber;
	private String vatCode;
	private String measuringUnit;
	private String invoiceText;
	private String originCountryCode;
	private String destinationCountryCode;
	private int partitionNumber;

	public ChargedUsagePostpay() {

	}

	public ChargedUsagePostpay(ResultSet rs) {
		try {
			id = rs.getInt("id");
			lastModifiedDateTime = rs.getDate("last_modified_date_time");
			mobileUsagePostpayId = rs.getInt("mobile_usage_postpay_id");
			amount = rs.getInt("amount");
			billingAccountId = rs.getInt("billing_account_id");
			subscriptionId = rs.getInt("subscription_id");
			serviceId = rs.getInt("service_id");
			serviceName = rs.getString("service_name");
			chargeStartDateTime = rs.getDate("charge_start_date_time");
			duration = rs.getInt("duration");
			quantity = rs.getInt("quantity");
			originNumber = rs.getString("origin_number");
			destinationNumber = rs.getString("destination_number");
			vatCode = rs.getString("vat_code");
			measuringUnit = rs.getString("measuring_unit");
			invoiceText = rs.getString("invoice_text");
			originCountryCode = rs.getString("origin_country_code");
			destinationCountryCode = rs.getString("destination_country_code");
			partitionNumber = rs.getInt("partition_number");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public int getMobileUsagePostpayId() {
		return mobileUsagePostpayId;
	}

	public void setMobileUsagePostpayId(int mobileUsagePostpayId) {
		this.mobileUsagePostpayId = mobileUsagePostpayId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getChargeStartDateTime() {
		return chargeStartDateTime;
	}

	public void setChargeStartDateTime(Date chargeStartDateTime) {
		this.chargeStartDateTime = chargeStartDateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOriginNumber() {
		return originNumber;
	}

	public void setOriginNumber(String originNumber) {
		this.originNumber = originNumber;
	}

	public String getDestinationNumber() {
		return destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public String getInvoiceText() {
		return invoiceText;
	}

	public void setInvoiceText(String invoiceText) {
		this.invoiceText = invoiceText;
	}

	public String getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}

	public void setDestinationCountryCode(String destinationCountryCode) {
		this.destinationCountryCode = destinationCountryCode;
	}

	public int getPartitionNumber() {
		return partitionNumber;
	}

	public void setPartitionNumber(int partitionNumber) {
		this.partitionNumber = partitionNumber;
	}

}
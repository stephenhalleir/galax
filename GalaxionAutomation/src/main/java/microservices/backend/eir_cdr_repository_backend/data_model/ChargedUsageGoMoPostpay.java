package microservices.backend.eir_cdr_repository_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import framework.test_data.generic.StringUtil;

public class ChargedUsageGoMoPostpay {

	private int id;
	private Date lastModifiedDateTime;
	private int mobileUsageGoMoPostpayID;
	private int amount;
	private int billingAccountID;
	private int subscriptionID;
	private int serviceID;
	private int duration;
	private int quantity;
	private String serviceName;
	private Date chargeStartDateTime;
	private String originNumber;
	private String destinationNumber;
	private String vatCode;
	private String measuringUnit;
	private String invoiceText;
	private String originCountryCode;
	private String destinationCountryCode;
	private int partitionNumber;
	private int usageCounterTypeID;
	
	public ChargedUsageGoMoPostpay() {
		
	}
	
	public ChargedUsageGoMoPostpay(ResultSet rs) {
		try {
			id=rs.getInt("id");
			lastModifiedDateTime=rs.getDate("last_modified_date_time");
			mobileUsageGoMoPostpayID=rs.getInt("mobile_usage_gomo_postpay_id");
			amount=rs.getInt("amount");
			billingAccountID=rs.getInt("billing_account_id");
			subscriptionID=rs.getInt("subscription_id");
			serviceID=rs.getInt("service_id");
			duration=rs.getInt("duration");
			quantity=rs.getInt("quantity");
			serviceName=rs.getString("service_name");
			chargeStartDateTime=rs.getDate("charge_start_date_time");
			originNumber=rs.getString("origin_number");
			destinationNumber=rs.getString("destination_number");
			vatCode=rs.getString("vat_code");
			measuringUnit=rs.getString("measuring_unit");
			invoiceText=rs.getString("invoice_text");
			originCountryCode=rs.getString("origin_country_code");
			destinationCountryCode=rs.getString("destination_country_code");
			partitionNumber=rs.getInt("partition_number");
			usageCounterTypeID=rs.getInt("usage_counter_type_id");
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

	public int getMobileUsagePostpayID() {
		return mobileUsageGoMoPostpayID;
	}

	public void setMobileUsagePostpayID(int mobileUsagePostpayID) {
		this.mobileUsageGoMoPostpayID = mobileUsagePostpayID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(int subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
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

	public int getUsageCounterTypeID() {
		return usageCounterTypeID;
	}

	public void setUsageCounterTypeID(int usageCounterTypeID) {
		this.usageCounterTypeID = usageCounterTypeID;
	}
	
	// GET methods for MyGoMo usage display page
	public String getDurationStr() {
		if(invoiceText.equals("CALL")) {
			return StringUtil.getHoursMinutesSeconds(duration);
		}
		else if(invoiceText.equals("TEXT")){
			return "1";
		}
		else if(invoiceText.equals("DATA")){
			double bytes=(double)quantity/1024/1024/1024;
			return String.format("%.3f", bytes) + "GB";
		}
		return null;
	}
	
	public String getChargeTimeStr() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateString = simpleDateFormat.format(chargeStartDateTime);
		return "On " + dateString.replace(" ", " at ");
	}
	
	public String getAmountStr() {
		if(amount != 0) {
			return StringUtil.toCurrency(amount, "€");
		}
		else {
			return "€0";
		}
	}
}

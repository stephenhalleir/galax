package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeOffer {

	private int id;
	private int accountId;
	private String msisdn;
	private String externalOrderRef;
	private int reContract;
	private String changeToOfferCode;
	private String changeToOfferTariffPlanCode;
	private int changeToOfferTariffPlanCharge;
	private int earlyCeaseCharge;
	private String approvedBy;
	private String reason;
	private String deviceCode;
	private int deviceCharge;
	private int deductDevChargeFromHwf;
	private int deviceEnrollment;
	private String deliveryContactType;
	private String deliveryContactEmailAddress;
	private String orderReference;
	private String deliveryFirstName;
	private String deliveryLastName;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String deliveryAddressLine3;
	private String deliveryCity;
	private String deliveryCounty;
	private String deliveryCountry;
	private String deliveryPostalCode;
	private String deliveryEmail;

	public ChangeOffer() {

	}

	public ChangeOffer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			msisdn = rs.getString("msisdn");
			externalOrderRef = rs.getString("external_order_ref");
			reContract = rs.getInt("re_contract");
			changeToOfferCode = rs.getString("change_to_offer_code");
			changeToOfferTariffPlanCode = rs.getString("change_to_offer_tariff_plan_code");
			changeToOfferTariffPlanCharge = rs.getInt("change_to_offer_tariff_plan_charge");
			earlyCeaseCharge = rs.getInt("early_cease_charge");
			approvedBy = rs.getString("approved_by");
			reason = rs.getString("reason");
			deviceCode = rs.getString("device_code");
			deviceCharge = rs.getInt("device_charge");
			deductDevChargeFromHwf = rs.getInt("deduct_dev_charge_from_hwf");
			deviceEnrollment = rs.getInt("device_enrollment");
			deliveryContactType = rs.getString("delivery_contact_type");
			deliveryContactEmailAddress = rs.getString("delivery_contact_email_address");
			orderReference = rs.getString("order_reference");
			deliveryFirstName = rs.getString("delivery_first_name");
			deliveryLastName = rs.getString("delivery_last_name");
			deliveryAddressLine1 = rs.getString("delivery_address_line_1");
			deliveryAddressLine2 = rs.getString("delivery_address_line_2");
			deliveryAddressLine3 = rs.getString("delivery_address_line_3");
			deliveryCity = rs.getString("delivery_city");
			deliveryCounty = rs.getString("delivery_county");
			deliveryCountry = rs.getString("delivery_country");
			deliveryPostalCode = rs.getString("delivery_postal_code");
			deliveryEmail = rs.getString("delivery_email");
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getExternalOrderRef() {
		return externalOrderRef;
	}

	public void setExternalOrderRef(String externalOrderRef) {
		this.externalOrderRef = externalOrderRef;
	}

	public int getReContract() {
		return reContract;
	}

	public void setReContract(int reContract) {
		this.reContract = reContract;
	}

	public String getChangeToOfferCode() {
		return changeToOfferCode;
	}

	public void setChangeToOfferCode(String changeToOfferCode) {
		this.changeToOfferCode = changeToOfferCode;
	}

	public String getChangeToOfferTariffPlanCode() {
		return changeToOfferTariffPlanCode;
	}

	public void setChangeToOfferTariffPlanCode(String changeToOfferTariffPlanCode) {
		this.changeToOfferTariffPlanCode = changeToOfferTariffPlanCode;
	}

	public int getChangeToOfferTariffPlanCharge() {
		return changeToOfferTariffPlanCharge;
	}

	public void setChangeToOfferTariffPlanCharge(int changeToOfferTariffPlanCharge) {
		this.changeToOfferTariffPlanCharge = changeToOfferTariffPlanCharge;
	}

	public int getEarlyCeaseCharge() {
		return earlyCeaseCharge;
	}

	public void setEarlyCeaseCharge(int earlyCeaseCharge) {
		this.earlyCeaseCharge = earlyCeaseCharge;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public int getDeviceCharge() {
		return deviceCharge;
	}

	public void setDeviceCharge(int deviceCharge) {
		this.deviceCharge = deviceCharge;
	}

	public int getDeductDevChargeFromHwf() {
		return deductDevChargeFromHwf;
	}

	public void setDeductDevChargeFromHwf(int deductDevChargeFromHwf) {
		this.deductDevChargeFromHwf = deductDevChargeFromHwf;
	}

	public int getDeviceEnrollment() {
		return deviceEnrollment;
	}

	public void setDeviceEnrollment(int deviceEnrollment) {
		this.deviceEnrollment = deviceEnrollment;
	}

	public String getDeliveryContactType() {
		return deliveryContactType;
	}

	public void setDeliveryContactType(String deliveryContactType) {
		this.deliveryContactType = deliveryContactType;
	}

	public String getDeliveryContactEmailAddress() {
		return deliveryContactEmailAddress;
	}

	public void setDeliveryContactEmailAddress(String deliveryContactEmailAddress) {
		this.deliveryContactEmailAddress = deliveryContactEmailAddress;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getDeliveryFirstName() {
		return deliveryFirstName;
	}

	public void setDeliveryFirstName(String deliveryFirstName) {
		this.deliveryFirstName = deliveryFirstName;
	}

	public String getDeliveryLastName() {
		return deliveryLastName;
	}

	public void setDeliveryLastName(String deliveryLastName) {
		this.deliveryLastName = deliveryLastName;
	}

	public String getDeliveryAddressLine1() {
		return deliveryAddressLine1;
	}

	public void setDeliveryAddressLine1(String deliveryAddressLine1) {
		this.deliveryAddressLine1 = deliveryAddressLine1;
	}

	public String getDeliveryAddressLine2() {
		return deliveryAddressLine2;
	}

	public void setDeliveryAddressLine2(String deliveryAddressLine2) {
		this.deliveryAddressLine2 = deliveryAddressLine2;
	}

	public String getDeliveryAddressLine3() {
		return deliveryAddressLine3;
	}

	public void setDeliveryAddressLine3(String deliveryAddressLine3) {
		this.deliveryAddressLine3 = deliveryAddressLine3;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryCounty() {
		return deliveryCounty;
	}

	public void setDeliveryCounty(String deliveryCounty) {
		this.deliveryCounty = deliveryCounty;
	}

	public String getDeliveryCountry() {
		return deliveryCountry;
	}

	public void setDeliveryCountry(String deliveryCountry) {
		this.deliveryCountry = deliveryCountry;
	}

	public String getDeliveryPostalCode() {
		return deliveryPostalCode;
	}

	public void setDeliveryPostalCode(String deliveryPostalCode) {
		this.deliveryPostalCode = deliveryPostalCode;
	}

	public String getDeliveryEmail() {
		return deliveryEmail;
	}

	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}

}
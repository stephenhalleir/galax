package microservices.backend.eir_bulk_backend.bulk_files.change_offer_file;

public class ChangeOfferRow {

	private String row;
	private String billingAccountID;
	private String msisdn;
	private String externalOrderRef;
	private boolean reContract;
	private String changeToOfferCode;
	private String changeToOfferTariffPlanCode;
	private String changeToOfferTariffPlanCharge;
	private String earlyCeaseCharge;
	private String approvedBy;
	private String reason;
	private String deviceCode;
	private String deviceCharge;
	private String deductDevChargeFromHW;
	private String deviceEnrollment;
	private String deliveryContactType;
	private String deliveryContactEmailAddress;
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
	private String deliveryPhoneNumber;

	public ChangeOfferRow(String s) {

		// hard fix here
		s = s + " ";

		String[] parts = s.split(",", -1);
		for (int i = 0; i < parts.length; i++) {
			System.err.println(i + " - " + parts[i]);
		}

		row = parts[0].trim();
		billingAccountID = parts[1].trim();
		msisdn = parts[2].trim();
		externalOrderRef = parts[3].trim();
		reContract = Boolean.valueOf(parts[4].trim());
		changeToOfferCode = parts[6].trim();
		changeToOfferTariffPlanCode = parts[7].trim();
		changeToOfferTariffPlanCharge = parts[8].trim();
		earlyCeaseCharge = parts[9].trim();
		approvedBy = parts[10].trim();
		reason = parts[11].trim();
		deviceCode = parts[12].trim();
		deviceCharge = parts[13].trim();
		deductDevChargeFromHW = parts[14].trim();
		deviceEnrollment = parts[15].trim();
		deliveryContactType = parts[16].trim();
		deliveryContactEmailAddress = parts[17].trim();

	}

	public ChangeOfferRow() {
		row = "";
		billingAccountID = "";
		msisdn = "";
		externalOrderRef = "CHANGE_OFFER_" + System.currentTimeMillis();
		reContract = false;
		changeToOfferCode = "";
		changeToOfferTariffPlanCode = "";
		changeToOfferTariffPlanCharge = "";
		earlyCeaseCharge = "";
		approvedBy = "Steve Test";
		reason = "Upgrade";
		deviceCode = "";
		deviceCharge = "";
		deductDevChargeFromHW = "";
		deviceEnrollment = "";
		deliveryContactType = "Primary Contact";
		deliveryContactEmailAddress = "";
		deliveryFirstName = "";
		deliveryLastName = "";
		deliveryAddressLine1 = "";
		deliveryAddressLine2 = "";
		deliveryAddressLine3 = "";
		deliveryCity = "";
		deliveryCounty = "";
		deliveryCountry = "";
		deliveryPostalCode = "";
		deliveryEmail = "";
		deliveryPhoneNumber = "";
	}
	
	public ChangeOfferRow(int billingAccountID, String msisdn, String changeToOfferCode, String changeToOfferTariffPlanCode) {
		this();
		this.billingAccountID=Integer.toString(billingAccountID);
		this.msisdn=msisdn;
		this.changeToOfferCode=changeToOfferCode;
		this.changeToOfferTariffPlanCode=changeToOfferTariffPlanCode;
	}

	public void randomize() {

	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCustomerAccountNumber() {
		return billingAccountID;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.billingAccountID = customerAccountNumber;
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

	public boolean getReContract() {
		return reContract;
	}

	public void setReContract(boolean reContract) {
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

	public String getChangeToOfferTariffPlanCharge() {
		return changeToOfferTariffPlanCharge;
	}

	public void setChangeToOfferTariffPlanCharge(String changeToOfferTariffPlanCharge) {
		this.changeToOfferTariffPlanCharge = changeToOfferTariffPlanCharge;
	}

	public String getEarlyCeaseCharge() {
		return earlyCeaseCharge;
	}

	public void setEarlyCeaseCharge(String earlyCeaseCharge) {
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

	public String getDeviceCharge() {
		return deviceCharge;
	}

	public void setDeviceCharge(String deviceCharge) {
		this.deviceCharge = deviceCharge;
	}

	public String getDeductDevChargeFromHW() {
		return deductDevChargeFromHW;
	}

	public void setDeductDevChargeFromHW(String deductDevChargeFromHW) {
		this.deductDevChargeFromHW = deductDevChargeFromHW;
	}

	public String getDeviceEnrollment() {
		return deviceEnrollment;
	}

	public void setDeviceEnrollment(String deviceEnrollment) {
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

	public String toString() {
		String[] fields = { row, billingAccountID, msisdn, externalOrderRef, Boolean.toString(reContract).toUpperCase(), changeToOfferCode, changeToOfferTariffPlanCode,
				changeToOfferTariffPlanCharge, earlyCeaseCharge, approvedBy, reason, deviceCode, deviceCharge, deductDevChargeFromHW, deviceEnrollment,
				deliveryContactType, deliveryContactEmailAddress, deliveryFirstName, deliveryLastName, deliveryAddressLine1, deliveryAddressLine2,
				deliveryAddressLine3, deliveryCity, deliveryCounty, deliveryCountry, deliveryPostalCode, deliveryEmail, deliveryPhoneNumber };
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}
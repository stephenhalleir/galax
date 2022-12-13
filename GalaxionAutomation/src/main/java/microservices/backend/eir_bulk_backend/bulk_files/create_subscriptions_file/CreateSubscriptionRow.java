package microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file;

import java.util.ArrayList;

import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.Handset;

public class CreateSubscriptionRow {

	private String row;
	private String customerAccountNumber;
	private String externalOrderRef;
	private String subscriptionName;
	private String subsCostCenter;
	private String vipSubscription;
	private String offer;
	private String offerPricePlan;
	private String offerPricePlanCharge;
	private String deviceCode;
	private String deductChargeFromHWFund;
	private String deviceCharge;
	private String deviceEnrollment;
	private String directoryPreference;
	private String deliveryContactType;
	private String deliveryContactEmailAddress;
	private String addon1;
	private String addon1Charge;
	private String addon2;
	private String addon2Charge;
	private String addon3;
	private String addon3Charge;
	private String addon4;
	private String addon4Charge;
	private String addon5;
	private String addon5Charge;
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

	private EquipmentPack linkedSIMPack;

	public CreateSubscriptionRow(String s) {

		String[] parts = s.split(",", -1);

		row = parts[0].trim();
		customerAccountNumber = parts[1].trim();
		externalOrderRef = parts[2].trim();
		subscriptionName = parts[3].trim();
		subsCostCenter = parts[4].trim();
		vipSubscription = parts[5].trim();
		offer = parts[6].trim();
		offerPricePlan = parts[7].trim();
		offerPricePlanCharge = parts[8].trim();
		deviceCode = parts[9].trim();
		deductChargeFromHWFund = parts[10].trim();
		deviceCharge = parts[11].trim();
		deviceEnrollment = parts[12].trim();
		directoryPreference = parts[13].trim();
		deliveryContactType = parts[14].trim();
		deliveryContactEmailAddress = parts[15].trim();
		addon1 = parts[16].trim();
		addon1Charge = parts[17].trim();
		addon2 = parts[18].trim();
		addon2Charge = parts[19].trim();
		addon3 = parts[20].trim();
		addon3Charge = parts[21].trim();
		addon4 = parts[22].trim();
		addon4Charge = parts[23].trim();
		addon5 = parts[24].trim();
		addon5Charge = parts[25].trim();
		deliveryFirstName=parts[26].trim();
		deliveryLastName=parts[27].trim();
		deliveryAddressLine1=parts[28].trim();
		deliveryAddressLine2=parts[29].trim();
		deliveryAddressLine3=parts[30].trim();
		deliveryCity=parts[31].trim();
		deliveryCounty=parts[32].trim();
		deliveryCountry=parts[33].trim();
		deliveryPostalCode=parts[34].trim();
		deliveryEmail=parts[35].trim();
		deliveryPhoneNumber=parts[36].trim();
	}

	public CreateSubscriptionRow() {

		row = "";
		customerAccountNumber = "";
		externalOrderRef = "";
		subscriptionName = "";
		subsCostCenter = "";
		vipSubscription = "";
		offer = "";
		offerPricePlan = "";
		offerPricePlanCharge = "";
		deviceCode = "";
		deductChargeFromHWFund = "";
		deviceCharge = "";
		deviceEnrollment = "";
		directoryPreference = "";
		deliveryContactType = "";
		deliveryContactEmailAddress = "";
		addon1 = "";
		addon1Charge = "";
		addon2 = "";
		addon2Charge = "";
		addon3 = "";
		addon3Charge = "";
		addon4 = "";
		addon4Charge = "";
		addon5 = "";
		addon5Charge = "";
		deliveryFirstName="";
		deliveryLastName="";
		deliveryAddressLine1="";
		deliveryAddressLine2="";
		deliveryAddressLine3="";
		deliveryCity="";
		deliveryCounty="";
		deliveryCountry="";
		deliveryPostalCode="";
		deliveryEmail="";
		deliveryPhoneNumber="";
	}

	public void randomize() {

		externalOrderRef = Long.toString(System.currentTimeMillis());
		subscriptionName = RandomStringGenerator.getRandomFirstName() + " " + RandomStringGenerator.getRandomLastName();
		subsCostCenter = RandomStringGenerator.getRandomString("0123456789", 8);
		vipSubscription = Boolean.toString(RandomStringGenerator.getRandomBoolean()).toUpperCase();
		offerPricePlanCharge = "";
		// following fields should only be blank if deviceCode is blank
		deviceCode = "";
		deviceEnrollment = ""; // true/false
		deductChargeFromHWFund = ""; // true/false
		deviceCharge = "";

		/*
		 * if(includeHandset) { // set random equipment
		 * setDeviceName(CatalogCoreDAO.getRandomEquipmentCode()); }
		 */

		directoryPreference = RandomStringGenerator.getRandomValueFromArray(new String[] { "EXDIRECTORY", "LISTED", "UNLISTED" });
		deliveryContactType = "Primary Contact";
		deliveryContactEmailAddress = "";
		addon1 = "";
		addon1Charge = "";
		addon2 = "";
		addon2Charge = "";
		addon3 = "";
		addon3Charge = "";
		addon4 = "";
		addon4Charge = "";
		addon5 = "";
		addon5Charge = "";
		deliveryFirstName="";
		deliveryLastName="";
		deliveryAddressLine1="";
		deliveryAddressLine2="";
		deliveryAddressLine3="";
		deliveryCity="";
		deliveryCounty="";
		deliveryCountry="";
		deliveryPostalCode="";
		deliveryEmail="";
		deliveryPhoneNumber="";
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getExternalOrderRef() {
		return externalOrderRef;
	}

	public void setExternalOrderRef(String externalOrderRef) {
		this.externalOrderRef = externalOrderRef;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	public String getSubsCostCenter() {
		return subsCostCenter;
	}

	public void setSubsCostCenter(String subsCostCenter) {
		this.subsCostCenter = subsCostCenter;
	}

	public String getVipSubscription() {
		return vipSubscription;
	}

	public void setVipSubscription(String vipSubscription) {
		this.vipSubscription = vipSubscription;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOfferPricePlan() {
		return offerPricePlan;
	}

	public void setOfferPricePlan(String offerPricePlan) {
		this.offerPricePlan = offerPricePlan;
	}

	public String getOfferPricePlanCharge() {
		return offerPricePlanCharge;
	}

	public void setOfferPricePlanCharge(String offerPricePlanCharge) {
		this.offerPricePlanCharge = offerPricePlanCharge;
	}

	public String getDeviceName() {
		return deviceCode;
	}

	public String getDeductChargeFromHWFund() {
		return deductChargeFromHWFund;
	}

	public void setDeductChargeFromHWFund(String deductChargeFromHWFund) {
		this.deductChargeFromHWFund = deductChargeFromHWFund;
	}

	public String getDeviceCharge() {
		return deviceCharge;
	}

	public void setDeviceCharge(String deviceCharge) {
		this.deviceCharge = deviceCharge;
	}

	public String getDeviceEnrollment() {
		return deviceEnrollment;
	}

	public void setDeviceEnrollment(String deviceEnrollment) {
		this.deviceEnrollment = deviceEnrollment;
	}

	public String getDirectoryPreference() {
		return directoryPreference;
	}

	public void setDirectoryPreference(String directoryPreference) {
		this.directoryPreference = directoryPreference;
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

	public String getAddon1() {
		return addon1;
	}

	public void setAddon1(String addon1) {
		this.addon1 = addon1;
	}

	public String getAddon1Charge() {
		return addon1Charge;
	}

	public void setAddon1Charge(String addon1Charge) {
		this.addon1Charge = addon1Charge;
	}

	public String getAddon2() {
		return addon2;
	}

	public void setAddon2(String addon2) {
		this.addon2 = addon2;
	}

	public String getAddon2Charge() {
		return addon2Charge;
	}

	public void setAddon2Charge(String addon2Charge) {
		this.addon2Charge = addon2Charge;
	}

	public String getAddon3() {
		return addon3;
	}

	public void setAddon3(String addon3) {
		this.addon3 = addon3;
	}

	public String getAddon3Charge() {
		return addon3Charge;
	}

	public void setAddon3Charge(String addon3Charge) {
		this.addon3Charge = addon3Charge;
	}

	public String getAddon4() {
		return addon4;
	}

	public void setAddon4(String addon4) {
		this.addon4 = addon4;
	}

	public String getAddon4Charge() {
		return addon4Charge;
	}

	public void setAddon4Charge(String addon4Charge) {
		this.addon4Charge = addon4Charge;
	}

	public String getAddon5() {
		return addon5;
	}

	public void setAddon5(String addon5) {
		this.addon5 = addon5;
	}

	public String getAddon5Charge() {
		return addon5Charge;
	}

	public void setAddon5Charge(String addon5Charge) {
		this.addon5Charge = addon5Charge;
	}

	public EquipmentPack getLinkedSIMPack() {
		return linkedSIMPack;
	}

	public void setLinkedSIMPack(EquipmentPack linkedSIMPack) {
		this.linkedSIMPack = linkedSIMPack;
	}
	

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
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

	public String getDeliveryPhoneNumber() {
		return deliveryPhoneNumber;
	}

	public void setDeliveryPhoneNumber(String deliveryPhoneNumber) {
		this.deliveryPhoneNumber = deliveryPhoneNumber;
	}

	public void setHandset(Handset handset) {
		if (handset != null) {
			this.deductChargeFromHWFund = Boolean.toString(handset.isDeductFromHwFund()).toUpperCase();
			this.deviceEnrollment = Boolean.toString(handset.isDeviceEnrollment()).toUpperCase();
			this.deviceCode = handset.getHandsetCode();
		} else {
			this.deductChargeFromHWFund = "";
			this.deviceEnrollment = "";
			this.deviceCode = "";
		}

	}

	/**
	 * Set a list of addons into the file
	 * 
	 * @param addons
	 */
	public void setAddons(ArrayList<String> addons) {
		for (String addon : addons) {
			this.addAddon(addon);
		}
	}

	/**
	 * Add the addon into the next available addon field - addon1, addon2, addon3,
	 * etc
	 */
	public void addAddon(String addon) {
		if (addon1.equals("")) {
			addon1 = addon;
		} else if (addon2.equals("")) {
			addon2 = addon;
		} else if (addon3.equals("")) {
			addon3 = addon;
		} else if (addon4.equals("")) {
			addon4 = addon;
		} else if (addon5.equals("")) {
			addon5 = addon;
		}
	}


	public String toString() {
		String[] fields = { row, customerAccountNumber, externalOrderRef, subscriptionName, subsCostCenter, vipSubscription, offer, offerPricePlan,
				offerPricePlanCharge, deviceCode, deviceCharge, deductChargeFromHWFund, deviceEnrollment, directoryPreference, deliveryContactType,
				deliveryContactEmailAddress, addon1, addon1Charge, addon2, addon2Charge, addon3, addon3Charge, addon4, addon4Charge, addon5, addon5Charge,
				deliveryFirstName, deliveryLastName, deliveryAddressLine1, deliveryAddressLine2, deliveryAddressLine3, deliveryCity, deliveryCounty,
				deliveryCountry, deliveryPostalCode, deliveryEmail, deliveryPhoneNumber};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}

}
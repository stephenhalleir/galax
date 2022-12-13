package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateSubscription {

	private int id;
	private int isVip;
	private String name;
	private String costCenter;
	private String offerCode;
	private String tariffPlanCode;
	private int tariffPlanChargeAmount;
	private String externalOrderReference;
	private String directoryPreference;
	private int deviceEnrolled;
	private String deviceCode;
	private int deviceChargeAmount;
	private String deliveryContactType;
	private String deliveryContactEmail;
	private int isDeductedFromHwf;
	private int accountId;
	private String firstAddOnCode;
	private int firstAddOnChargeAmount;
	private String secondAddOnCode;
	private int secondAddOnChargeAmount;
	private String thirdAddOnCode;
	private int thirdAddOnChargeAmount;
	private String fourthAddOnCode;
	private int fourthAddOnChargeAmount;
	private String fifthAddOnCode;
	private int fifthAddOnChargeAmount;
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

	public CreateSubscription() {

	}

	public CreateSubscription(ResultSet rs) {
		try {
			id = rs.getInt("id");
			isVip = rs.getInt("is_vip");
			name = rs.getString("name");
			costCenter = rs.getString("cost_center");
			offerCode = rs.getString("offer_code");
			tariffPlanCode = rs.getString("tariff_plan_code");
			tariffPlanChargeAmount = rs.getInt("tariff_plan_charge_amount");
			externalOrderReference = rs.getString("external_order_reference");
			directoryPreference = rs.getString("directory_preference");
			deviceEnrolled = rs.getInt("device_enrolled");
			deviceCode = rs.getString("device_code");
			deviceChargeAmount = rs.getInt("device_charge_amount");
			deliveryContactType = rs.getString("delivery_contact_type");
			deliveryContactEmail = rs.getString("delivery_contact_email");
			isDeductedFromHwf = rs.getInt("is_deducted_from_hwf");
			accountId = rs.getInt("account_id");
			firstAddOnCode = rs.getString("first_add_on_code");
			firstAddOnChargeAmount = rs.getInt("first_add_on_charge_amount");
			secondAddOnCode = rs.getString("second_add_on_code");
			secondAddOnChargeAmount = rs.getInt("second_add_on_charge_amount");
			thirdAddOnCode = rs.getString("third_add_on_code");
			thirdAddOnChargeAmount = rs.getInt("third_add_on_charge_amount");
			fourthAddOnCode = rs.getString("fourth_add_on_code");
			fourthAddOnChargeAmount = rs.getInt("fourth_add_on_charge_amount");
			fifthAddOnCode = rs.getString("fifth_add_on_code");
			fifthAddOnChargeAmount = rs.getInt("fifth_add_on_charge_amount");
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

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}

	public int getTariffPlanChargeAmount() {
		return tariffPlanChargeAmount;
	}

	public void setTariffPlanChargeAmount(int tariffPlanChargeAmount) {
		this.tariffPlanChargeAmount = tariffPlanChargeAmount;
	}

	public String getExternalOrderReference() {
		return externalOrderReference;
	}

	public void setExternalOrderReference(String externalOrderReference) {
		this.externalOrderReference = externalOrderReference;
	}

	public String getDirectoryPreference() {
		return directoryPreference;
	}

	public void setDirectoryPreference(String directoryPreference) {
		this.directoryPreference = directoryPreference;
	}

	public int getDeviceEnrolled() {
		return deviceEnrolled;
	}

	public void setDeviceEnrolled(int deviceEnrolled) {
		this.deviceEnrolled = deviceEnrolled;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public int getDeviceChargeAmount() {
		return deviceChargeAmount;
	}

	public void setDeviceChargeAmount(int deviceChargeAmount) {
		this.deviceChargeAmount = deviceChargeAmount;
	}

	public String getDeliveryContactType() {
		return deliveryContactType;
	}

	public void setDeliveryContactType(String deliveryContactType) {
		this.deliveryContactType = deliveryContactType;
	}

	public String getDeliveryContactEmail() {
		return deliveryContactEmail;
	}

	public void setDeliveryContactEmail(String deliveryContactEmail) {
		this.deliveryContactEmail = deliveryContactEmail;
	}

	public int getIsDeductedFromHwf() {
		return isDeductedFromHwf;
	}

	public void setIsDeductedFromHwf(int isDeductedFromHwf) {
		this.isDeductedFromHwf = isDeductedFromHwf;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFirstAddOnCode() {
		return firstAddOnCode;
	}

	public void setFirstAddOnCode(String firstAddOnCode) {
		this.firstAddOnCode = firstAddOnCode;
	}

	public int getFirstAddOnChargeAmount() {
		return firstAddOnChargeAmount;
	}

	public void setFirstAddOnChargeAmount(int firstAddOnChargeAmount) {
		this.firstAddOnChargeAmount = firstAddOnChargeAmount;
	}

	public String getSecondAddOnCode() {
		return secondAddOnCode;
	}

	public void setSecondAddOnCode(String secondAddOnCode) {
		this.secondAddOnCode = secondAddOnCode;
	}

	public int getSecondAddOnChargeAmount() {
		return secondAddOnChargeAmount;
	}

	public void setSecondAddOnChargeAmount(int secondAddOnChargeAmount) {
		this.secondAddOnChargeAmount = secondAddOnChargeAmount;
	}

	public String getThirdAddOnCode() {
		return thirdAddOnCode;
	}

	public void setThirdAddOnCode(String thirdAddOnCode) {
		this.thirdAddOnCode = thirdAddOnCode;
	}

	public int getThirdAddOnChargeAmount() {
		return thirdAddOnChargeAmount;
	}

	public void setThirdAddOnChargeAmount(int thirdAddOnChargeAmount) {
		this.thirdAddOnChargeAmount = thirdAddOnChargeAmount;
	}

	public String getFourthAddOnCode() {
		return fourthAddOnCode;
	}

	public void setFourthAddOnCode(String fourthAddOnCode) {
		this.fourthAddOnCode = fourthAddOnCode;
	}

	public int getFourthAddOnChargeAmount() {
		return fourthAddOnChargeAmount;
	}

	public void setFourthAddOnChargeAmount(int fourthAddOnChargeAmount) {
		this.fourthAddOnChargeAmount = fourthAddOnChargeAmount;
	}

	public String getFifthAddOnCode() {
		return fifthAddOnCode;
	}

	public void setFifthAddOnCode(String fifthAddOnCode) {
		this.fifthAddOnCode = fifthAddOnCode;
	}

	public int getFifthAddOnChargeAmount() {
		return fifthAddOnChargeAmount;
	}

	public void setFifthAddOnChargeAmount(int fifthAddOnChargeAmount) {
		this.fifthAddOnChargeAmount = fifthAddOnChargeAmount;
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
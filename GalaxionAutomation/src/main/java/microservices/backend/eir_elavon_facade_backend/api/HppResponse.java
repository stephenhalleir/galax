package microservices.backend.eir_elavon_facade_backend.api;

/**
 * This class represents the "hppResponse" string that gets sent to Prospect as part of the 'validate' step
 * 
 */
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.global.api.entities.Customer;
import com.global.api.entities.Transaction;

import framework.test_data.generic.StringUtil;
import microservices.frontend.eir_eshop_frontend.dto.HppRequest;

public class HppResponse {

	private String pas_uuid;

	@JsonProperty("CAVV")
	private String cavv;
	@JsonProperty("SRD")
	private String srd;
	@JsonProperty("MERCHANT_RESPONSE_URL")
	private String merchantResponseUrl;
	@JsonProperty("SAVED_PMT_REF")
	private String savedPmtRef;
	@JsonProperty("REALWALLET_CHOSEN")
	private String realwalletChosen;
	@JsonProperty("CVNRESULT")
	private String cvnResult;
	@JsonProperty("HPP_CUSTOMER_PHONENUMBER_MOBILE")
	private String hppCustomerPhoneNumberMobile;
	@JsonProperty("PASREF")
	private String pasref;
	@JsonProperty("PMT_SETUP_MSG")
	private String pmtSetupMsg;
	@JsonProperty("MESSAGE")
	private String message;
	@JsonProperty("DS_TRANS_ID")
	private String dsTransID;
	@JsonProperty("SAVED_PMT_NAME")
	private String savedPmtName;
	@JsonProperty("ACCOUNT")
	private String account;
	@JsonProperty("AVSPOSTCODERESULT")
	private String avsPostCodeResult;
	@JsonProperty("AMOUNT")
	private String amount;
	@JsonProperty("TIMESTAMP")
	private String timestamp;
	@JsonProperty("HPP_BILLING_STREET1")
	private String hppBillingStreet1;
	@JsonProperty("HPP_BILLING_STREET2")
	private String hppBillingStreet2;
	@JsonProperty("HPP_BILLING_STREET3")
	private String hppBillingStreet3;
	@JsonProperty("AUTHCODE")
	private String authcode;
	@JsonProperty("HPP_BILLING_CITY")
	private String hppBillingCity;
	@JsonProperty("PMT_SETUP")
	private String pmtSetup;
	@JsonProperty("SAVED_PMT_TYPE")
	private String savedPmtType;
	@JsonProperty("AVSADDRESSRESULT")
	private String avsAddressResult;
	@JsonProperty("AUTHENTICATION_VALUE")
	private String authenticationValue;
	@JsonProperty("HPP_BILLING_POSTALCODE")
	private String hppBillingPostalCode;
	@JsonProperty("ECI")
	private String eci;
	@JsonProperty("SAVED_PMT_DIGITS")
	private String savedPmtDigits;
	@JsonProperty("PAYER_SETUP_MSG")
	private String payerSetupMsg;
	@JsonProperty("HPP_BILLING_COUNTRY")
	private String hppBillingCountry;
	@JsonProperty("MESSAGE_VERSION")
	private String messageVersion;
	@JsonProperty("SAVED_PMT_EXPDATE")
	private String savedPmtExpDate;
	@JsonProperty("CARD_STORAGE_ENABLE")
	private String cardStorageEnable;
	@JsonProperty("SAVED_PAYER_REF")
	private String savedPayerRef;
	@JsonProperty("BATCHID")
	private String batchID;
	@JsonProperty("XID")
	private String xid;
	@JsonProperty("SHA1HASH")
	private String sha1Hash;
	@JsonProperty("ORDER_ID")
	private String orderID;
	@JsonProperty("PAYER_SETUP")
	private String payerSetup;
	@JsonProperty("HPP_CUSTOMER_EMAIL")
	private String hppCustomerEmail;
	@JsonProperty("HPP_FRAUDFILTER_RESULT")
	private String hppFraudFilterResult;
	@JsonProperty("RESULT")
	private String result;
	@JsonProperty("MERCHANT_ID")
	private String merchantID;

	/**
	 * Populate the HPP response object based on HPPRequest, payer and transaction
	 * information
	 * 
	 * @param hppRequest
	 * @param payer
	 * @param payment
	 */
	public HppResponse(HppRequest hppRequest, Customer payer, Transaction payment) {
		cavv = "";
		srd = payment.getSchemeId();
		merchantResponseUrl = hppRequest.getMerchantResponseUrl();
		savedPmtRef = payer.getKey();
		realwalletChosen = "1";
		cvnResult = payment.getCvnResponseCode();
		hppCustomerPhoneNumberMobile = hppRequest.getHppCustomerPhoneNumberMobile();
		pasref = payment.getTransactionId();
		pmtSetupMsg = "Successful";
		message = payment.getResponseMessage();
		dsTransID = UUID.randomUUID().toString();
		savedPmtName = "Steve Test";
		account = hppRequest.getAccount();
		avsPostCodeResult = payment.getAvsResponseCode();
		amount = hppRequest.getAmount();
		timestamp = payment.getTimestamp();
		pas_uuid = UUID.randomUUID().toString();
		hppBillingStreet1 = hppRequest.getHppBillingStreet1();
		hppBillingStreet2 = hppRequest.getHppBillingStreet2();
		hppBillingStreet3 = hppRequest.getHppBillingStreet3();
		authcode = payment.getAuthorizationCode();
		hppBillingCity = hppRequest.getHppBillingCity();
		pmtSetup = "00";
		savedPmtType = "Visa";
		avsAddressResult = "M";
		authenticationValue = "AJkBCVNCKAAAAAXbl4NEdAAAAAA=";
		hppBillingPostalCode = hppRequest.getHppBillingPostalCode();
		eci = "05";
		savedPmtDigits = "426397xxxx5262";
		payerSetupMsg = "Successful";
		hppBillingCountry = hppRequest.getHppBillingCountry();
		messageVersion = "2.1.0";
		savedPmtExpDate = "1222";
		cardStorageEnable = hppRequest.getCardStorageEnable();
		savedPayerRef = payer.getKey();
		batchID = "";
		xid = "";
		orderID = hppRequest.getOrderID();
		payerSetup = "00";
		hppCustomerEmail = hppRequest.getHppCustomerEmail();
		hppFraudFilterResult = "NOT_EXECUTED";
		result = "00";
		merchantID = hppRequest.getMerchantID();
		sha1Hash = StringUtil.getHppSHA1HASH(timestamp, merchantID, orderID, result, message, pasref, authcode, "secret");
	}

	/**
	 * Encode all of the values to base64 format
	 */
	public void encode() {
		cavv = StringUtil.base64Encode(cavv).replace("\r\n", "");
		srd = StringUtil.base64Encode(srd).replace("\r\n", "");
		merchantResponseUrl = StringUtil.base64Encode(merchantResponseUrl).replace("\r\n", "");
		savedPmtRef = StringUtil.base64Encode(savedPmtRef).replace("\r\n", "");
		realwalletChosen = StringUtil.base64Encode(realwalletChosen).replace("\r\n", "");
		cvnResult = StringUtil.base64Encode(cvnResult).replace("\r\n", "");
		hppCustomerPhoneNumberMobile = StringUtil.base64Encode(hppCustomerPhoneNumberMobile).replace("\r\n", "");
		pasref = StringUtil.base64Encode(pasref).replace("\r\n", "");
		pmtSetupMsg = StringUtil.base64Encode(pmtSetupMsg).replace("\r\n", "");
		message = StringUtil.base64Encode(message).replace("\r\n", "");
		dsTransID = StringUtil.base64Encode(dsTransID).replace("\r\n", "");
		savedPmtName = StringUtil.base64Encode(savedPmtName).replace("\r\n", "");
		account = StringUtil.base64Encode(account).replace("\r\n", "");
		avsPostCodeResult = StringUtil.base64Encode(avsPostCodeResult).replace("\r\n", "");
		amount = StringUtil.base64Encode(amount).replace("\r\n", "");
		timestamp = StringUtil.base64Encode(timestamp).replace("\r\n", "");
		pas_uuid = StringUtil.base64Encode(pas_uuid).replace("\r\n", "");
		hppBillingStreet1 = StringUtil.base64Encode(hppBillingStreet1).replace("\r\n", "");
		hppBillingStreet2 = StringUtil.base64Encode(hppBillingStreet2).replace("\r\n", "");
		hppBillingStreet3 = StringUtil.base64Encode(hppBillingStreet3).replace("\r\n", "");
		authcode = StringUtil.base64Encode(authcode).replace("\r\n", "");
		hppBillingCity = StringUtil.base64Encode(hppBillingCity).replace("\r\n", "");
		pmtSetup = StringUtil.base64Encode(pmtSetup).replace("\r\n", "");
		savedPmtType = StringUtil.base64Encode(savedPmtType).replace("\r\n", "");
		avsAddressResult = StringUtil.base64Encode(avsAddressResult).replace("\r\n", "");
		authenticationValue = StringUtil.base64Encode(authenticationValue).replace("\r\n", "");
		hppBillingPostalCode = StringUtil.base64Encode(hppBillingPostalCode).replace("\r\n", "");
		eci = StringUtil.base64Encode(eci).replace("\r\n", "");
		savedPmtDigits = StringUtil.base64Encode(savedPmtDigits).replace("\r\n", "");
		payerSetupMsg = StringUtil.base64Encode(payerSetupMsg).replace("\r\n", "");
		hppBillingCountry = StringUtil.base64Encode(hppBillingCountry).replace("\r\n", "");
		messageVersion = StringUtil.base64Encode(messageVersion).replace("\r\n", "");
		savedPmtExpDate = StringUtil.base64Encode(savedPmtExpDate).replace("\r\n", "");
		cardStorageEnable = StringUtil.base64Encode(cardStorageEnable).replace("\r\n", "");
		savedPayerRef = StringUtil.base64Encode(savedPayerRef).replace("\r\n", "");
		batchID = StringUtil.base64Encode(batchID).replace("\r\n", "");
		xid = StringUtil.base64Encode(xid).replace("\r\n", "");
		sha1Hash = StringUtil.base64Encode(sha1Hash).replace("\r\n", "");
		orderID = StringUtil.base64Encode(orderID).replace("\r\n", "");
		payerSetup = StringUtil.base64Encode(payerSetup).replace("\r\n", "");
		hppCustomerEmail = StringUtil.base64Encode(hppCustomerEmail).replace("\r\n", "");
		hppFraudFilterResult = StringUtil.base64Encode(hppFraudFilterResult).replace("\r\n", "");
		result = StringUtil.base64Encode(result).replace("\r\n", "");
		merchantID = StringUtil.base64Encode(merchantID).replace("\r\n", "");
	}

	public String getPas_uuid() {
		return pas_uuid;
	}

	public void setPas_uuid(String pas_uuid) {
		this.pas_uuid = pas_uuid;
	}

	public String getCavv() {
		return cavv;
	}

	public void setCavv(String cavv) {
		this.cavv = cavv;
	}

	public String getSrd() {
		return srd;
	}

	public void setSrd(String srd) {
		this.srd = srd;
	}

	public String getMerchantResponseUrl() {
		return merchantResponseUrl;
	}

	public void setMerchantResponseUrl(String merchantResponseUrl) {
		this.merchantResponseUrl = merchantResponseUrl;
	}

	public String getSavedPmtRef() {
		return savedPmtRef;
	}

	public void setSavedPmtRef(String savedPmtRef) {
		this.savedPmtRef = savedPmtRef;
	}

	public String getRealwalletChosen() {
		return realwalletChosen;
	}

	public void setRealwalletChosen(String realwalletChosen) {
		this.realwalletChosen = realwalletChosen;
	}

	public String getCvnResult() {
		return cvnResult;
	}

	public void setCvnResult(String cvnResult) {
		this.cvnResult = cvnResult;
	}

	public String getHppCustomerPhoneNumberMobile() {
		return hppCustomerPhoneNumberMobile;
	}

	public void setHppCustomerPhoneNumberMobile(String hppCustomerPhoneNumberMobile) {
		this.hppCustomerPhoneNumberMobile = hppCustomerPhoneNumberMobile;
	}

	public String getPasref() {
		return pasref;
	}

	public void setPasref(String pasref) {
		this.pasref = pasref;
	}

	public String getPmtSetupMsg() {
		return pmtSetupMsg;
	}

	public void setPmtSetupMsg(String pmtSetupMsg) {
		this.pmtSetupMsg = pmtSetupMsg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.recalculateSha1Hash();
	}

	public String getDsTransID() {
		return dsTransID;
	}

	public void setDsTransID(String dsTransID) {
		this.dsTransID = dsTransID;
	}

	public String getSavedPmtName() {
		return savedPmtName;
	}

	public void setSavedPmtName(String savedPmtName) {
		this.savedPmtName = savedPmtName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAvsPostCodeResult() {
		return avsPostCodeResult;
	}

	public void setAvsPostCodeResult(String avsPostCodeResult) {
		this.avsPostCodeResult = avsPostCodeResult;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
		this.recalculateSha1Hash();
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		this.recalculateSha1Hash();
	}

	public String getHppBillingStreet1() {
		return hppBillingStreet1;
	}

	public void setHppBillingStreet1(String hppBillingStreet1) {
		this.hppBillingStreet1 = hppBillingStreet1;
	}

	public String getHppBillingStreet2() {
		return hppBillingStreet2;
	}

	public void setHppBillingStreet2(String hppBillingStreet2) {
		this.hppBillingStreet2 = hppBillingStreet2;
	}

	public String getHppBillingStreet3() {
		return hppBillingStreet3;
	}

	public void setHppBillingStreet3(String hppBillingStreet3) {
		this.hppBillingStreet3 = hppBillingStreet3;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
		this.recalculateSha1Hash();
	}

	public String getHppBillingCity() {
		return hppBillingCity;
	}

	public void setHppBillingCity(String hppBillingCity) {
		this.hppBillingCity = hppBillingCity;
	}

	public String getPmtSetup() {
		return pmtSetup;
	}

	public void setPmtSetup(String pmtSetup) {
		this.pmtSetup = pmtSetup;
	}

	public String getSavedPmtType() {
		return savedPmtType;
	}

	public void setSavedPmtType(String savedPmtType) {
		this.savedPmtType = savedPmtType;
	}

	public String getAvsAddressResult() {
		return avsAddressResult;
	}

	public void setAvsAddressResult(String avsAddressResult) {
		this.avsAddressResult = avsAddressResult;
	}

	public String getAuthenticationValue() {
		return authenticationValue;
	}

	public void setAuthenticationValue(String authenticationValue) {
		this.authenticationValue = authenticationValue;
	}

	public String getHppBillingPostalCode() {
		return hppBillingPostalCode;
	}

	public void setHppBillingPostalCode(String hppBillingPostalCode) {
		this.hppBillingPostalCode = hppBillingPostalCode;
	}

	public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
	}

	public String getSavedPmtDigits() {
		return savedPmtDigits;
	}

	public void setSavedPmtDigits(String savedPmtDigits) {
		this.savedPmtDigits = savedPmtDigits;
	}

	public String getPayerSetupMsg() {
		return payerSetupMsg;
	}

	public void setPayerSetupMsg(String payerSetupMsg) {
		this.payerSetupMsg = payerSetupMsg;
	}

	public String getHppBillingCountry() {
		return hppBillingCountry;
	}

	public void setHppBillingCountry(String hppBillingCountry) {
		this.hppBillingCountry = hppBillingCountry;
	}

	public String getMessageVersion() {
		return messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	public String getSavedPmtExpDate() {
		return savedPmtExpDate;
	}

	public void setSavedPmtExpDate(String savedPmtExpDate) {
		this.savedPmtExpDate = savedPmtExpDate;
	}

	public String getCardStorageEnable() {
		return cardStorageEnable;
	}

	public void setCardStorageEnable(String cardStorageEnable) {
		this.cardStorageEnable = cardStorageEnable;
	}

	public String getSavedPayerRef() {
		return savedPayerRef;
	}

	public void setSavedPayerRef(String savedPayerRef) {
		this.savedPayerRef = savedPayerRef;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	public String getXid() {
		return xid;
	}

	public void setXid(String xid) {
		this.xid = xid;
	}

	public String getSha1Hash() {
		return sha1Hash;
	}

	public void setSha1Hash(String sha1Hash) {
		this.sha1Hash = sha1Hash;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
		this.recalculateSha1Hash();
	}

	public String getPayerSetup() {
		return payerSetup;
	}

	public void setPayerSetup(String payerSetup) {
		this.payerSetup = payerSetup;
	}

	public String getHppCustomerEmail() {
		return hppCustomerEmail;
	}

	public void setHppCustomerEmail(String hppCustomerEmail) {
		this.hppCustomerEmail = hppCustomerEmail;
	}

	public String getHppFraudFilterResult() {
		return hppFraudFilterResult;
	}

	public void setHppFraudFilterResult(String hppFraudFilterResult) {
		this.hppFraudFilterResult = hppFraudFilterResult;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
		this.recalculateSha1Hash();
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
		this.recalculateSha1Hash();
	}

	// calculate/recalculate the sha1hash value based on the relevant fields
	private void recalculateSha1Hash() {
		sha1Hash = StringUtil.getHppSHA1HASH(timestamp, merchantID, orderID, result, message, pasref, authcode, "secret");
	}
}

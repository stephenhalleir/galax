package microservices.frontend.eir_eshop_frontend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HppRequest {

	@JsonProperty("MERCHANT_ID")
	private String merchantID;
	@JsonProperty("ACCOUNT")
	private String account;
	@JsonProperty("ORDER_ID")
	private String orderID;
	@JsonProperty("AMOUNT")
	private String amount;
	@JsonProperty("CURRENCY")
	private String currency;
	@JsonProperty("TIMESTAMP")
	private String timestamp;
	@JsonProperty("SHA1HASH")
	private String sha1Hash;
	@JsonProperty("AUTO_SETTLE_FLAG")
	private String autoSettleFlag;
	@JsonProperty("CARD_STORAGE_ENABLE")
	private String cardStorageEnable;
	@JsonProperty("OFFER_SAVE_CARD")
	private String offerSaveCard;
	@JsonProperty("PMT_REF")
	private String pmtRef;
	@JsonProperty("PAYER_EXIST")
	private String payerExist;
	@JsonProperty("HPP_VERSION")
	private String hppVersion;
	@JsonProperty("HPP_BILLING_CITY")
	private String hppBillingCity;
	@JsonProperty("MERCHANT_RESPONSE_URL")
	private String merchantResponseUrl;
	@JsonProperty("HPP_BILLING_STREET3")
	private String hppBillingStreet3;
	@JsonProperty("HPP_CUSTOMER_EMAIL")
	private String hppCustomerEmail;
	@JsonProperty("HPP_BILLING_STREET2")
	private String hppBillingStreet2;
	@JsonProperty("HPP_BILLING_POSTALCODE")
	private String hppBillingPostalCode;
	@JsonProperty("HPP_BILLING_COUNTRY")
	private String hppBillingCountry;
	@JsonProperty("HPP_CUSTOMER_PHONENUMBER_MOBILE")
	private String hppCustomerPhoneNumberMobile;
	@JsonProperty("HPP_BILLING_STREET1")
	private String hppBillingStreet1;
	@JsonProperty("HPP_CHALLENGE_REQUEST_INDICATOR")
	private String hppChallengeRequesstIndicator;
	@JsonProperty("HPP_STORED_CREDENTIAL_INITIATOR")
	private String hppChallengeCredentialInitiator;
	@JsonProperty("HPP_STORED_CREDENTIAL_TYPE")
	private String hppStoredCredentialType;
	@JsonProperty("PAYMENT_REASON")
	private String paymentReason;
	@JsonProperty("HPP_STORED_CREDENTIAL_SEQUENCE")
	private String hppStoredCredentialSequence;
	@JsonProperty("PAYMENT_SOURCE")
	private String paymentSource;
	@JsonProperty("CHANNEL")
	private String channel;

	public HppRequest() {
		super();
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSha1Hash() {
		return sha1Hash;
	}

	public void setSha1Hash(String sha1Hash) {
		this.sha1Hash = sha1Hash;
	}

	public String getAutoSettleFlag() {
		return autoSettleFlag;
	}

	public void setAutoSettleFlag(String autoSettleFlag) {
		this.autoSettleFlag = autoSettleFlag;
	}

	public String getCardStorageEnable() {
		return cardStorageEnable;
	}

	public void setCardStorageEnable(String cardStorageEnable) {
		this.cardStorageEnable = cardStorageEnable;
	}

	public String getOfferSaveCard() {
		return offerSaveCard;
	}

	public void setOfferSaveCard(String offerSaveCard) {
		this.offerSaveCard = offerSaveCard;
	}

	public String getPmtRef() {
		return pmtRef;
	}

	public void setPmtRef(String pmtRef) {
		this.pmtRef = pmtRef;
	}

	public String getPayerExist() {
		return payerExist;
	}

	public void setPayerExist(String payerExist) {
		this.payerExist = payerExist;
	}

	public String getHppVersion() {
		return hppVersion;
	}

	public void setHppVersion(String hppVersion) {
		this.hppVersion = hppVersion;
	}

	public String getHppBillingCity() {
		return hppBillingCity;
	}

	public void setHppBillingCity(String hppBillingCity) {
		this.hppBillingCity = hppBillingCity;
	}

	public String getMerchantResponseUrl() {
		return merchantResponseUrl;
	}

	public void setMerchantResponseUrl(String merchantResponseUrl) {
		this.merchantResponseUrl = merchantResponseUrl;
	}

	public String getHppBillingStreet3() {
		return hppBillingStreet3;
	}

	public void setHppBillingStreet3(String hppBillingStreet3) {
		this.hppBillingStreet3 = hppBillingStreet3;
	}

	public String getHppCustomerEmail() {
		return hppCustomerEmail;
	}

	public void setHppCustomerEmail(String hppCustomerEmail) {
		this.hppCustomerEmail = hppCustomerEmail;
	}

	public String getHppBillingStreet2() {
		return hppBillingStreet2;
	}

	public void setHppBillingStreet2(String hppBillingStreet2) {
		this.hppBillingStreet2 = hppBillingStreet2;
	}

	public String getHppBillingPostalCode() {
		return hppBillingPostalCode;
	}

	public void setHppBillingPostalCode(String hppBillingPostalCode) {
		this.hppBillingPostalCode = hppBillingPostalCode;
	}

	public String getHppBillingCountry() {
		return hppBillingCountry;
	}

	public void setHppBillingCountry(String hppBillingCountry) {
		this.hppBillingCountry = hppBillingCountry;
	}

	public String getHppCustomerPhoneNumberMobile() {
		return hppCustomerPhoneNumberMobile;
	}

	public void setHppCustomerPhoneNumberMobile(String hppCustomerPhoneNumberMobile) {
		this.hppCustomerPhoneNumberMobile = hppCustomerPhoneNumberMobile;
	}

	public String getHppBillingStreet1() {
		return hppBillingStreet1;
	}

	public void setHppBillingStreet1(String hppBillingStreet1) {
		this.hppBillingStreet1 = hppBillingStreet1;
	}

	public String getHppChallengeRequesstIndicator() {
		return hppChallengeRequesstIndicator;
	}

	public void setHppChallengeRequesstIndicator(String hppChallengeRequesstIndicator) {
		this.hppChallengeRequesstIndicator = hppChallengeRequesstIndicator;
	}

	public String getHppChallengeCredentialInitiator() {
		return hppChallengeCredentialInitiator;
	}

	public void setHppChallengeCredentialInitiator(String hppChallengeCredentialInitiator) {
		this.hppChallengeCredentialInitiator = hppChallengeCredentialInitiator;
	}

	public String getHppStoredCredentialType() {
		return hppStoredCredentialType;
	}

	public void setHppStoredCredentialType(String hppStoredCredentialType) {
		this.hppStoredCredentialType = hppStoredCredentialType;
	}

	public String getPaymentReason() {
		return paymentReason;
	}

	public void setPaymentReason(String paymentReason) {
		this.paymentReason = paymentReason;
	}

	public String getHppStoredCredentialSequence() {
		return hppStoredCredentialSequence;
	}

	public void setHppStoredCredentialSequence(String hppStoredCredentialSequence) {
		this.hppStoredCredentialSequence = hppStoredCredentialSequence;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}

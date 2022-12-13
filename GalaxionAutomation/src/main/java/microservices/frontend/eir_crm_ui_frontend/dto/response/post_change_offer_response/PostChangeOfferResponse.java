package microservices.frontend.eir_crm_ui_frontend.dto.response.post_change_offer_response;

public class PostChangeOfferResponse {
	private String uuid;
	private String subscriptionId;
	private String channel;
	private String channelGroup;
	private String brand;
	private boolean contractRenewal;
	private String contractFileUuid;
	private String contractEmailId;
	private String contractSentAt;
	private String contractRenewalStartDate;
	private String contractRenewalEndDate;
	private String status;
	private String ePosReference;
	private boolean valid;
	private String paymentUuid;
	private int amount;
	private String customerDetails;
	private String paymentDetails;
	private String waiveEarlyCeaseCharge;

	public PostChangeOfferResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelGroup() {
		return channelGroup;
	}

	public void setChannelGroup(String channelGroup) {
		this.channelGroup = channelGroup;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isContractRenewal() {
		return contractRenewal;
	}

	public void setContractRenewal(boolean contractRenewal) {
		this.contractRenewal = contractRenewal;
	}

	public String getContractFileUuid() {
		return contractFileUuid;
	}

	public void setContractFileUuid(String contractFileUuid) {
		this.contractFileUuid = contractFileUuid;
	}

	public String getContractEmailId() {
		return contractEmailId;
	}

	public void setContractEmailId(String contractEmailId) {
		this.contractEmailId = contractEmailId;
	}

	public String getContractSentAt() {
		return contractSentAt;
	}

	public void setContractSentAt(String contractSentAt) {
		this.contractSentAt = contractSentAt;
	}

	public String getContractRenewalStartDate() {
		return contractRenewalStartDate;
	}

	public void setContractRenewalStartDate(String contractRenewalStartDate) {
		this.contractRenewalStartDate = contractRenewalStartDate;
	}

	public String getContractRenewalEndDate() {
		return contractRenewalEndDate;
	}

	public void setContractRenewalEndDate(String contractRenewalEndDate) {
		this.contractRenewalEndDate = contractRenewalEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getePosReference() {
		return ePosReference;
	}

	public void setePosReference(String ePosReference) {
		this.ePosReference = ePosReference;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getPaymentUuid() {
		return paymentUuid;
	}

	public void setPaymentUuid(String paymentUuid) {
		this.paymentUuid = paymentUuid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(String customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getWaiveEarlyCeaseCharge() {
		return waiveEarlyCeaseCharge;
	}

	public void setWaiveEarlyCeaseCharge(String waiveEarlyCeaseCharge) {
		this.waiveEarlyCeaseCharge = waiveEarlyCeaseCharge;
	}
}

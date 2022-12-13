package testCases.testObjects.orders;

public class TestObjectCRMOrderService {

	private String offerName;
	private String subscriptionOffer;
	private String topupOffer;
	private String handset;
	private String discount;
	private String topupAmount;
	private String description;
	private String addon;
	private String nddSetting;
	private String orderName;
	private int serviceId;
	private String msisdn;

	public TestObjectCRMOrderService() {
		offerName = null;
		subscriptionOffer = null;
		topupOffer = null;
		handset = null;
		discount = null;
		topupAmount = null;
		description = null;
		addon = null;
		nddSetting = null;
		orderName = null;
		serviceId = -1;
		msisdn = "";
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		if (offerName.trim().equals("")) {
			this.offerName = null;
		} else {
			this.offerName = offerName;
		}
	}

	public String getSubscriptionOffer() {
		return subscriptionOffer;
	}

	public void setSubscriptionOffer(String subscriptionOffer) {

		if (subscriptionOffer.trim().equals("")) {
			this.subscriptionOffer = null;
		} else {
			this.subscriptionOffer = subscriptionOffer;
		}
	}

	public String getTopupOffer() {
		return topupOffer;
	}

	public void setTopupOffer(String topupOffer) {
		if (topupOffer.trim().equals("")) {
			this.topupOffer = null;
		} else {
			this.topupOffer = topupOffer;
		}
	}

	public String getHandset() {
		return handset;
	}

	public void setHandset(String handset) {

		if (handset.trim().equals("")) {
			this.handset = null;
		} else {
			this.handset = handset;
		}
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		if (discount.trim().equals("")) {
			this.discount = null;
		} else {
			this.discount = discount;
		}
	}

	public String getTopupAmount() {
		return topupAmount;
	}

	public String getTopupAmountAsNumber() {
		return topupAmount.replace("€", "").replace(".", "");
	}

	public void setTopupAmount(String topupAmount) {
		// if (topupAmount.trim().equals("")) {
		// this.topupAmount = "€20.00";
		// } else {
		this.topupAmount = topupAmount;
		// }
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description.trim().equals("")) {
			this.description = null;
		} else {
			this.description = description;
		}
	}

	public String getAddon() {
		return addon;
	}

	public void setAddon(String addon) {
		if (addon.trim().equals("")) {
			this.addon = null;
		} else {
			this.addon = addon;
		}
	}

	public String getNddSetting() {
		return nddSetting;
	}

	public void setNddSetting(String nddSetting) {
		if (nddSetting.trim().equals("")) {
			this.nddSetting = null;
		} else {
			this.nddSetting = nddSetting;
		}
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String toString() {
		return description + ": " + offerName + ", " + subscriptionOffer + ", " + addon + ", " + handset + ", " + discount;
	}

}

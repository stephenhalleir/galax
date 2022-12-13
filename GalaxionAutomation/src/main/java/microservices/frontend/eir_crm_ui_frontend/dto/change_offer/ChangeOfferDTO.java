package microservices.frontend.eir_crm_ui_frontend.dto.change_offer;

public class ChangeOfferDTO {
	private boolean contractRenewal;
	private int subscriptionId;
	private String channel;

	public ChangeOfferDTO(boolean contractRenewal, int subscriptionId, String channel) {
		super();
		this.contractRenewal = contractRenewal;
		this.subscriptionId = subscriptionId;
		this.channel = channel;
	}

	public boolean isContractRenewal() {
		return contractRenewal;
	}

	public void setContractRenewal(boolean contractRenewal) {
		this.contractRenewal = contractRenewal;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
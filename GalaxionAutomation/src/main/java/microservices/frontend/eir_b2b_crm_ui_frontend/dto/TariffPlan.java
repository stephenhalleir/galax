package microservices.frontend.eir_b2b_crm_ui_frontend.dto;

public class TariffPlan {
	private String offerCode;
	private int amount;

	public TariffPlan() {

	}

	public TariffPlan(String offerCode, int amount) {
		this.offerCode = offerCode;
		this.amount = amount;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

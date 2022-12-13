package microservices.frontend.eir_crm_ui_frontend.dto.acquisitions;

public class AddTopupDTO {
	private int topUpAmount;

	public AddTopupDTO(int topUpAmount) {
		super();
		this.topUpAmount = topUpAmount;
	}

	public int getTopUpAmount() {
		return topUpAmount;
	}

	public void setTopUpAmount(int topUpAmount) {
		this.topUpAmount = topUpAmount;
	}
}

package microservices.frontend.eir_b2b_crm_ui_frontend.dto.renew_hardware_fund;

public class RenewHardwareFundDTO {
	private int amount;
	private int term;
	private String comment;
	private int hardwareFundId;

	public RenewHardwareFundDTO(int amount, int term, String comment, int hardwareFundId) {
		super();
		this.amount = amount;
		this.term = term;
		this.comment = comment;
		this.hardwareFundId=hardwareFundId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getHardwareFundId() {
		return hardwareFundId;
	}

	public void setHardwareFundId(int hardwareFundId) {
		this.hardwareFundId = hardwareFundId;
	}
}

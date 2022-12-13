package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_hardware_fund;

public class CreateHardwareFundDTO {
	private int amount;
	private int term;
	private String comment;

	public CreateHardwareFundDTO(int amount, int term, String comment) {
		super();
		this.amount = amount;
		this.term = term;
		this.comment = comment;
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
}

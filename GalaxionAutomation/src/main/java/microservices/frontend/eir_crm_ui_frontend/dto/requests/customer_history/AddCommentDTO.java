package microservices.frontend.eir_crm_ui_frontend.dto.requests.customer_history;

public class AddCommentDTO {
	private int historyId;
	private String comment;

	public AddCommentDTO(int historyId, String comment) {
		super();
		this.historyId = historyId;
		this.comment = comment;
	}

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

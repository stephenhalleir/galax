package microservices.frontend.eir_crm_ui_frontend.dto.response.get_ref_security_questions;

public class GetRefSecurityQuestionsResponse {
	private String code;
	private String question;

	public GetRefSecurityQuestionsResponse() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}

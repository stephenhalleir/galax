package microservices.frontend.eir_crm_ui_frontend.dto.response.get_security_question;

public class GetSecurityQuestionResponse {
	
	private String code;
	private String question;
	private String response;
	public GetSecurityQuestionResponse() {
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}

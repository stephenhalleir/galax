package microservices.frontend.eir_crm_ui_frontend.dto.requests.update_security_question;

import microservices.backend.eir_security_question_backend.enums.SecurityQuestionCode;

public class UpdateSecurityQuestionDTO {
	
	private SecurityQuestionCode code;
	private String response;

	public UpdateSecurityQuestionDTO() {
		super();
	}

	public UpdateSecurityQuestionDTO(SecurityQuestionCode code, String response) {
		super();
		this.code = code;
		this.response = response;
	}

	public SecurityQuestionCode getCode() {
		return code;
	}

	public void setCode(SecurityQuestionCode code) {
		this.code = code;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}

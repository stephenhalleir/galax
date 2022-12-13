package microservices.backend.eir_security_question_backend.dto;

import microservices.backend.eir_security_question_backend.enums.VerificationStatusEnum;

public class SecurityVerificationDTO {
	
	private String verificationResultStatusEnum;

	public SecurityVerificationDTO(VerificationStatusEnum status) {
		this.verificationResultStatusEnum=status.toString();
	}
	
	public SecurityVerificationDTO() {
	}

	public String getVerificationResultStatusEnum() {
		return verificationResultStatusEnum;
	}

	public void setVerificationResultStatusEnum(String verificationResultStatusEnum) {
		this.verificationResultStatusEnum = verificationResultStatusEnum;
	}
}

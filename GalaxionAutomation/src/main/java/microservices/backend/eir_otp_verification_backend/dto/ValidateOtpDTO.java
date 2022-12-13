package microservices.backend.eir_otp_verification_backend.dto;

public class ValidateOtpDTO {
	
	private String otp;
	
	public ValidateOtpDTO(String otp) {
		super();
		this.otp = otp;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}

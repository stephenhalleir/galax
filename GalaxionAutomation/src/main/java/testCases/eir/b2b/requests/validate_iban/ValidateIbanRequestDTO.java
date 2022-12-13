package testCases.eir.b2b.requests.validate_iban;

public class ValidateIbanRequestDTO {
	
	private String iban;

	public ValidateIbanRequestDTO(String iban) {
		super();
		this.iban = iban;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
}

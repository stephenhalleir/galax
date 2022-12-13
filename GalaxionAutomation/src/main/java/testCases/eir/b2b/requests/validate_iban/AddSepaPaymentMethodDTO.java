package testCases.eir.b2b.requests.validate_iban;

public class AddSepaPaymentMethodDTO {
	private String accountOwner;
	private String bankName;
	private String bic;
	private String branchName;
	private String iban;
	private String mandateSignedAt;

	public AddSepaPaymentMethodDTO() {
		super();
	}

	public AddSepaPaymentMethodDTO(String accountOwner, String bankName, String bic, String branchName, String iban, String mandateSignedAt) {
		super();
		this.accountOwner = accountOwner;
		this.bankName = bankName;
		this.bic = bic;
		this.branchName = branchName;
		this.iban = iban;
		this.mandateSignedAt = mandateSignedAt;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getMandateSignedAt() {
		return mandateSignedAt;
	}

	public void setMandateSignedAt(String mandateSignedAt) {
		this.mandateSignedAt = mandateSignedAt;
	}
}

package microservices.backend.eir_payment_center_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectDebitPaymentMethod {

	private int id;
	private String iban;
	private String bic;
	private String type;
	private String uniqueMandateReference;
	private String bankName;
	private String accountOwner;
	private String mandateSignedAt;
	private String branchName;

	public DirectDebitPaymentMethod() {

	}

	public DirectDebitPaymentMethod(ResultSet rs) {
		try {
			id = rs.getInt("id");
			iban = rs.getString("iban");
			bic = rs.getString("bic");
			type = rs.getString("type");
			uniqueMandateReference = rs.getString("unique_mandate_reference");
			bankName = rs.getString("bank_name");
			accountOwner = rs.getString("account_owner");
			mandateSignedAt = rs.getString("mandate_signed_at");
			branchName=rs.getString("branch_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUniqueMandateReference() {
		return uniqueMandateReference;
	}

	public void setUniqueMandateReference(String uniqueMandateReference) {
		this.uniqueMandateReference = uniqueMandateReference;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getMandateSignedAt() {
		return mandateSignedAt;
	}

	public void setMandateSignedAt(String mandateSignedAt) {
		this.mandateSignedAt = mandateSignedAt;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
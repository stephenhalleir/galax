package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigSepaMethod {

	private int billingAccountId;
	private String bankName;
	private String accountOwner;
	private String bic;
	private String iban;
	private String uniqueMandateReference;

	public MigSepaMethod() {

	}

	public MigSepaMethod(ResultSet rs) {
		try {
			billingAccountId = rs.getInt("billing_account_id");
			bankName = rs.getString("bank_name");
			accountOwner = rs.getString("account_owner");
			bic = rs.getString("bic");
			iban = rs.getString("iban");
			uniqueMandateReference = rs.getString("unique_mandate_reference");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
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

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getUniqueMandateReference() {
		return uniqueMandateReference;
	}

	public void setUniqueMandateReference(String uniqueMandateReference) {
		this.uniqueMandateReference = uniqueMandateReference;
	}

}
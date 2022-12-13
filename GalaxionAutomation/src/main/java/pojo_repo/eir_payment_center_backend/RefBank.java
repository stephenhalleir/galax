package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefBank {

	private String countryCode;
	private String bankCode;
	private String bankName;
	private String bic;
	private String branchName;
	private Date activatedAt;
	private Date terminatedAt;
	private int id;

	public RefBank() {

	}

	public RefBank(ResultSet rs) {
		try {
			countryCode = rs.getString("country_code");
			bankCode = rs.getString("bank_code");
			bankName = rs.getString("bank_name");
			bic = rs.getString("bic");
			branchName = rs.getString("branch_name");
			activatedAt = rs.getDate("activated_at");
			terminatedAt = rs.getDate("terminated_at");
			id = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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

	public Date getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(Date activatedAt) {
		this.activatedAt = activatedAt;
	}

	public Date getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(Date terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
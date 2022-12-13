package microservices.backend.eir_payment_center_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefBank {

	private int id;
	private String countryCode;
	private String bankCode;
	private String bankName;
	private String bic;
	private String branchName;
	private String branchCode;
	
	public RefBank(){
		
	}
	
	public RefBank(ResultSet rs) {
		try {
			id=rs.getInt("id");
			countryCode=rs.getString("country_code");
			bankCode=rs.getString("bank_code");
			bankName=rs.getString("bank_name");
			bic=rs.getString("bic");
			branchName=rs.getString("branch_name");
			branchCode=rs.getString("branch_code");
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

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

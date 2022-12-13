package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAccountStatus {

	private int accountNo;

	public MigAccountStatus() {

	}

	public MigAccountStatus(ResultSet rs) {
		try {
			accountNo = rs.getInt("account_no");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

}
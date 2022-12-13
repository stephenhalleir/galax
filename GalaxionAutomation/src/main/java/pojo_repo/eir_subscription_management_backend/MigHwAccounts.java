package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigHwAccounts {

	private int accountNumber;
	private String accountUsage;
	private String corp;
	private String group;

	public MigHwAccounts() {

	}

	public MigHwAccounts(ResultSet rs) {
		try {
			accountNumber = rs.getInt("account_number");
			accountUsage = rs.getString("account_usage");
			corp = rs.getString("corp");
			group = rs.getString("group");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountUsage() {
		return accountUsage;
	}

	public void setAccountUsage(String accountUsage) {
		this.accountUsage = accountUsage;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigHierTmp {

	private String accountNumber;
	private String groupCode;
	private String groupPostingAccount;
	private String corpName;
	private String corpAccountNumber;

	public MigHierTmp() {

	}

	public MigHierTmp(ResultSet rs) {
		try {
			accountNumber = rs.getString("account_Number");
			groupCode = rs.getString("group_code");
			groupPostingAccount = rs.getString("group_posting_account");
			corpName = rs.getString("corp_name");
			corpAccountNumber = rs.getString("corp_account_number");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupPostingAccount() {
		return groupPostingAccount;
	}

	public void setGroupPostingAccount(String groupPostingAccount) {
		this.groupPostingAccount = groupPostingAccount;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpAccountNumber() {
		return corpAccountNumber;
	}

	public void setCorpAccountNumber(String corpAccountNumber) {
		this.corpAccountNumber = corpAccountNumber;
	}

}
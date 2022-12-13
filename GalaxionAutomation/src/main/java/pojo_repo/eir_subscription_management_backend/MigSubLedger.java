package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigSubLedger {

	private String subLedger;
	private String group;
	private String corp;
	private int account;
	private String agreement;
	private int billCycle;
	private String consolidationLevel;

	public MigSubLedger() {

	}

	public MigSubLedger(ResultSet rs) {
		try {
			subLedger = rs.getString("sub_ledger");
			group = rs.getString("group");
			corp = rs.getString("corp");
			account = rs.getInt("account");
			agreement = rs.getString("agreement");
			billCycle = rs.getInt("bill_cycle");
			consolidationLevel = rs.getString("consolidation_level");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSubLedger() {
		return subLedger;
	}

	public void setSubLedger(String subLedger) {
		this.subLedger = subLedger;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public int getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(int billCycle) {
		this.billCycle = billCycle;
	}

	public String getConsolidationLevel() {
		return consolidationLevel;
	}

	public void setConsolidationLevel(String consolidationLevel) {
		this.consolidationLevel = consolidationLevel;
	}

}
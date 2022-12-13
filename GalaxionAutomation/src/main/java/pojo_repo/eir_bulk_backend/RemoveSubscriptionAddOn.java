package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveSubscriptionAddOn {

	private int id;
	private int accountId;
	private String msisdn;
	private String addOn1;
	private String addOn2;
	private String addOn3;
	private String addOn4;

	public RemoveSubscriptionAddOn() {

	}

	public RemoveSubscriptionAddOn(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			msisdn = rs.getString("msisdn");
			addOn1 = rs.getString("add_on_1");
			addOn2 = rs.getString("add_on_2");
			addOn3 = rs.getString("add_on_3");
			addOn4 = rs.getString("add_on_4");
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAddOn1() {
		return addOn1;
	}

	public void setAddOn1(String addOn1) {
		this.addOn1 = addOn1;
	}

	public String getAddOn2() {
		return addOn2;
	}

	public void setAddOn2(String addOn2) {
		this.addOn2 = addOn2;
	}

	public String getAddOn3() {
		return addOn3;
	}

	public void setAddOn3(String addOn3) {
		this.addOn3 = addOn3;
	}

	public String getAddOn4() {
		return addOn4;
	}

	public void setAddOn4(String addOn4) {
		this.addOn4 = addOn4;
	}

}
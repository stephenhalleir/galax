package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateServiceAddOn {

	private int id;
	private int accountId;
	private String msisdn;
	private String addOn1;
	private int addOnCharge1;
	private String addOn2;
	private int addOnCharge2;
	private String addOn3;
	private int addOnCharge3;
	private String addOn4;
	private int addOnCharge4;
	private String addOn5;

	public CreateServiceAddOn() {

	}

	public CreateServiceAddOn(ResultSet rs) {
		try {
			id = rs.getInt("id");
			accountId = rs.getInt("account_id");
			msisdn = rs.getString("msisdn");
			addOn1 = rs.getString("add_on_1");
			addOnCharge1 = rs.getInt("add_on_charge_1");
			addOn2 = rs.getString("add_on_2");
			addOnCharge2 = rs.getInt("add_on_charge_2");
			addOn3 = rs.getString("add_on_3");
			addOnCharge3 = rs.getInt("add_on_charge_3");
			addOn4 = rs.getString("add_on_4");
			addOnCharge4 = rs.getInt("add_on_charge_4");
			addOn5 = rs.getString("add_on_5");
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

	public int getAddOnCharge1() {
		return addOnCharge1;
	}

	public void setAddOnCharge1(int addOnCharge1) {
		this.addOnCharge1 = addOnCharge1;
	}

	public String getAddOn2() {
		return addOn2;
	}

	public void setAddOn2(String addOn2) {
		this.addOn2 = addOn2;
	}

	public int getAddOnCharge2() {
		return addOnCharge2;
	}

	public void setAddOnCharge2(int addOnCharge2) {
		this.addOnCharge2 = addOnCharge2;
	}

	public String getAddOn3() {
		return addOn3;
	}

	public void setAddOn3(String addOn3) {
		this.addOn3 = addOn3;
	}

	public int getAddOnCharge3() {
		return addOnCharge3;
	}

	public void setAddOnCharge3(int addOnCharge3) {
		this.addOnCharge3 = addOnCharge3;
	}

	public String getAddOn4() {
		return addOn4;
	}

	public void setAddOn4(String addOn4) {
		this.addOn4 = addOn4;
	}

	public int getAddOnCharge4() {
		return addOnCharge4;
	}

	public void setAddOnCharge4(int addOnCharge4) {
		this.addOnCharge4 = addOnCharge4;
	}

	public String getAddOn5() {
		return addOn5;
	}

	public void setAddOn5(String addOn5) {
		this.addOn5 = addOn5;
	}

}
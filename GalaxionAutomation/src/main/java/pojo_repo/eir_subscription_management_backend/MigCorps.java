package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigCorps {

	private String corpName;
	private String corpDesc;
	private String corpPostingAccount;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String deliveryAddressLine3;
	private String deliveryAddressLine4;
	private String deliveryAddressLine5;
	private String deliveryAddressPostcode;
	private String corpAccountNumber;

	public MigCorps() {

	}

	public MigCorps(ResultSet rs) {
		try {
			corpName = rs.getString("corp_name");
			corpDesc = rs.getString("corp_desc");
			corpPostingAccount = rs.getString("corp_posting_account");
			deliveryAddressLine1 = rs.getString("delivery_address_line_1");
			deliveryAddressLine2 = rs.getString("delivery_address_line_2");
			deliveryAddressLine3 = rs.getString("delivery_address_line_3");
			deliveryAddressLine4 = rs.getString("delivery_address_line_4");
			deliveryAddressLine5 = rs.getString("delivery_address_line_5");
			deliveryAddressPostcode = rs.getString("delivery_address_postcode");
			corpAccountNumber = rs.getString("corp_account_number");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpDesc() {
		return corpDesc;
	}

	public void setCorpDesc(String corpDesc) {
		this.corpDesc = corpDesc;
	}

	public String getCorpPostingAccount() {
		return corpPostingAccount;
	}

	public void setCorpPostingAccount(String corpPostingAccount) {
		this.corpPostingAccount = corpPostingAccount;
	}

	public String getDeliveryAddressLine1() {
		return deliveryAddressLine1;
	}

	public void setDeliveryAddressLine1(String deliveryAddressLine1) {
		this.deliveryAddressLine1 = deliveryAddressLine1;
	}

	public String getDeliveryAddressLine2() {
		return deliveryAddressLine2;
	}

	public void setDeliveryAddressLine2(String deliveryAddressLine2) {
		this.deliveryAddressLine2 = deliveryAddressLine2;
	}

	public String getDeliveryAddressLine3() {
		return deliveryAddressLine3;
	}

	public void setDeliveryAddressLine3(String deliveryAddressLine3) {
		this.deliveryAddressLine3 = deliveryAddressLine3;
	}

	public String getDeliveryAddressLine4() {
		return deliveryAddressLine4;
	}

	public void setDeliveryAddressLine4(String deliveryAddressLine4) {
		this.deliveryAddressLine4 = deliveryAddressLine4;
	}

	public String getDeliveryAddressLine5() {
		return deliveryAddressLine5;
	}

	public void setDeliveryAddressLine5(String deliveryAddressLine5) {
		this.deliveryAddressLine5 = deliveryAddressLine5;
	}

	public String getDeliveryAddressPostcode() {
		return deliveryAddressPostcode;
	}

	public void setDeliveryAddressPostcode(String deliveryAddressPostcode) {
		this.deliveryAddressPostcode = deliveryAddressPostcode;
	}

	public String getCorpAccountNumber() {
		return corpAccountNumber;
	}

	public void setCorpAccountNumber(String corpAccountNumber) {
		this.corpAccountNumber = corpAccountNumber;
	}

}
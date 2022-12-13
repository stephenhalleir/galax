package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigGroups {

	private String groupName;
	private String groupDesc;
	private String groupPostingAccount;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String deliveryAddressLine3;
	private String deliveryAddressLine4;
	private String deliveryAddressLine5;
	private String deliveryAddressPostcode;

	public MigGroups() {

	}

	public MigGroups(ResultSet rs) {
		try {
			groupName = rs.getString("group_name");
			groupDesc = rs.getString("group_desc");
			groupPostingAccount = rs.getString("group_posting_account");
			deliveryAddressLine1 = rs.getString("delivery_address_line_1");
			deliveryAddressLine2 = rs.getString("delivery_address_line_2");
			deliveryAddressLine3 = rs.getString("delivery_address_line_3");
			deliveryAddressLine4 = rs.getString("delivery_address_line_4");
			deliveryAddressLine5 = rs.getString("delivery_address_line_5");
			deliveryAddressPostcode = rs.getString("delivery_address_postcode");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getGroupPostingAccount() {
		return groupPostingAccount;
	}

	public void setGroupPostingAccount(String groupPostingAccount) {
		this.groupPostingAccount = groupPostingAccount;
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

}
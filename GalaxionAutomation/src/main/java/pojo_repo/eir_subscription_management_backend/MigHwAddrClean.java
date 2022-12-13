package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigHwAddrClean {

	private String accountNumber;
	private String firstName;
	private String lastName;
	private String billingAddressLine1;
	private String billingAddressLine2;
	private String billingAddressLine3;
	private String billingAddressLine4;
	private String billingAddressLine5;

	public MigHwAddrClean() {

	}

	public MigHwAddrClean(ResultSet rs) {
		try {
			accountNumber = rs.getString("account_number");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			billingAddressLine1 = rs.getString("billing_address_line_1");
			billingAddressLine2 = rs.getString("billing_address_line_2");
			billingAddressLine3 = rs.getString("billing_address_line_3");
			billingAddressLine4 = rs.getString("billing_address_line_4");
			billingAddressLine5 = rs.getString("billing_address_line_5");
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBillingAddressLine1() {
		return billingAddressLine1;
	}

	public void setBillingAddressLine1(String billingAddressLine1) {
		this.billingAddressLine1 = billingAddressLine1;
	}

	public String getBillingAddressLine2() {
		return billingAddressLine2;
	}

	public void setBillingAddressLine2(String billingAddressLine2) {
		this.billingAddressLine2 = billingAddressLine2;
	}

	public String getBillingAddressLine3() {
		return billingAddressLine3;
	}

	public void setBillingAddressLine3(String billingAddressLine3) {
		this.billingAddressLine3 = billingAddressLine3;
	}

	public String getBillingAddressLine4() {
		return billingAddressLine4;
	}

	public void setBillingAddressLine4(String billingAddressLine4) {
		this.billingAddressLine4 = billingAddressLine4;
	}

	public String getBillingAddressLine5() {
		return billingAddressLine5;
	}

	public void setBillingAddressLine5(String billingAddressLine5) {
		this.billingAddressLine5 = billingAddressLine5;
	}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigEmail {

	private String emailAddress;
	private String customerLevel;

	public MigEmail() {

	}

	public MigEmail(ResultSet rs) {
		try {
			emailAddress = rs.getString("email_address");
			customerLevel = rs.getString("customer_level");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

}
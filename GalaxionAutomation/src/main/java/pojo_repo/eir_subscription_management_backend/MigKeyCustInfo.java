package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigKeyCustInfo {

	private String keyCustomerSequence;
	private String keyCustomerReference;
	private String lineNumber;
	private String description;
	private String customerLevel;
	private String reference;
	private String keyCustomerText;

	public MigKeyCustInfo() {

	}

	public MigKeyCustInfo(ResultSet rs) {
		try {
			keyCustomerSequence = rs.getString("key_customer_sequence");
			keyCustomerReference = rs.getString("key_customer_reference");
			lineNumber = rs.getString("line_number");
			description = rs.getString("description");
			customerLevel = rs.getString("customer_level");
			reference = rs.getString("reference");
			keyCustomerText = rs.getString("key_customer_text");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getKeyCustomerSequence() {
		return keyCustomerSequence;
	}

	public void setKeyCustomerSequence(String keyCustomerSequence) {
		this.keyCustomerSequence = keyCustomerSequence;
	}

	public String getKeyCustomerReference() {
		return keyCustomerReference;
	}

	public void setKeyCustomerReference(String keyCustomerReference) {
		this.keyCustomerReference = keyCustomerReference;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getKeyCustomerText() {
		return keyCustomerText;
	}

	public void setKeyCustomerText(String keyCustomerText) {
		this.keyCustomerText = keyCustomerText;
	}

}
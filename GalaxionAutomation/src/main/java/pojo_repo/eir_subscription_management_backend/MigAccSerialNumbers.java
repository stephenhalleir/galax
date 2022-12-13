package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAccSerialNumbers {

	private int accountNumber;
	private int accountSerialId;
	private int rawEffectiveDate;
	private Date effectiveDate;
	private String value;
	private String accountSerialIdDesc;

	public MigAccSerialNumbers() {

	}

	public MigAccSerialNumbers(ResultSet rs) {
		try {
			accountNumber = rs.getInt("account_number");
			accountSerialId = rs.getInt("account_serial_id");
			rawEffectiveDate = rs.getInt("raw_effective_date");
			effectiveDate = rs.getDate("effective_date");
			value = rs.getString("value");
			accountSerialIdDesc = rs.getString("account_serial_id_desc");
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

	public int getAccountSerialId() {
		return accountSerialId;
	}

	public void setAccountSerialId(int accountSerialId) {
		this.accountSerialId = accountSerialId;
	}

	public int getRawEffectiveDate() {
		return rawEffectiveDate;
	}

	public void setRawEffectiveDate(int rawEffectiveDate) {
		this.rawEffectiveDate = rawEffectiveDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAccountSerialIdDesc() {
		return accountSerialIdDesc;
	}

	public void setAccountSerialIdDesc(String accountSerialIdDesc) {
		this.accountSerialIdDesc = accountSerialIdDesc;
	}

}
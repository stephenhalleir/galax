package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subsidy {

	private String code;
	private String description;
	private int amount;
	private Date validFrom;
	private Date validTo;

	public Subsidy() {

	}

	public Subsidy(ResultSet rs) {
		try {
			code = rs.getString("code");
			description = rs.getString("description");
			amount = rs.getInt("amount");
			validFrom = rs.getDate("valid_from");
			validTo = rs.getDate("valid_to");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}
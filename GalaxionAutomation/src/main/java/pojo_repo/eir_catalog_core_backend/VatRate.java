package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VatRate {

	private int id;
	private Date validFrom;
	private Date validTo;
	private int value;

	public VatRate() {

	}

	public VatRate(ResultSet rs) {
		try {
			id = rs.getInt("id");
			validFrom = rs.getDate("valid_from");
			validTo = rs.getDate("valid_to");
			value = rs.getInt("value");
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
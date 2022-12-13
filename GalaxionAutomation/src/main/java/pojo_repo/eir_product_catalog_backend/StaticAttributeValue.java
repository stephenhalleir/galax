package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaticAttributeValue {

	private int id;
	private int staticAttributeId;
	private String stringValue;
	private int booleanValue;
	private Date datetimeValue;

	public StaticAttributeValue() {

	}

	public StaticAttributeValue(ResultSet rs) {
		try {
			id = rs.getInt("id");
			staticAttributeId = rs.getInt("static_attribute_id");
			stringValue = rs.getString("string_value");
			booleanValue = rs.getInt("boolean_value");
			datetimeValue = rs.getDate("datetime_value");
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

	public int getStaticAttributeId() {
		return staticAttributeId;
	}

	public void setStaticAttributeId(int staticAttributeId) {
		this.staticAttributeId = staticAttributeId;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(int booleanValue) {
		this.booleanValue = booleanValue;
	}

	public Date getDatetimeValue() {
		return datetimeValue;
	}

	public void setDatetimeValue(Date datetimeValue) {
		this.datetimeValue = datetimeValue;
	}

}
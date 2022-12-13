package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DynamicAttributeDefaultValue {

	private int id;
	private int dynamicAttributeId;
	private String stringValue;
	private int booleanValue;
	private Date datetimeValue;

	public DynamicAttributeDefaultValue() {

	}

	public DynamicAttributeDefaultValue(ResultSet rs) {
		try {
			id = rs.getInt("id");
			dynamicAttributeId = rs.getInt("dynamic_attribute_id");
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

	public int getDynamicAttributeId() {
		return dynamicAttributeId;
	}

	public void setDynamicAttributeId(int dynamicAttributeId) {
		this.dynamicAttributeId = dynamicAttributeId;
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
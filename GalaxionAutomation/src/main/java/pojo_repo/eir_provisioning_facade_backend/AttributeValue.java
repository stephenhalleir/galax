package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeValue {

	private int id;
	private int attributeId;
	private String stringValue;
	private int integerValue;
	private int booleanValue;
	private Date datetimeValue;

	public AttributeValue() {

	}

	public AttributeValue(ResultSet rs) {
		try {
			id = rs.getInt("id");
			attributeId = rs.getInt("attribute_id");
			stringValue = rs.getString("string_value");
			integerValue = rs.getInt("integer_value");
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

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(int integerValue) {
		this.integerValue = integerValue;
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
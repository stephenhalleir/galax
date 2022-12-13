package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DynamicAttributeValue {

	private int id;
	private int orderDynamicAttributeId;
	private String stringValue;
	private int longValue;
	private int booleanValue;

	public DynamicAttributeValue() {

	}

	public DynamicAttributeValue(ResultSet rs) {
		try {
			id = rs.getInt("id");
			orderDynamicAttributeId = rs.getInt("order_dynamic_attribute_id");
			stringValue = rs.getString("string_value");
			longValue = rs.getInt("long_value");
			booleanValue = rs.getInt("boolean_value");
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

	public int getOrderDynamicAttributeId() {
		return orderDynamicAttributeId;
	}

	public void setOrderDynamicAttributeId(int orderDynamicAttributeId) {
		this.orderDynamicAttributeId = orderDynamicAttributeId;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getLongValue() {
		return longValue;
	}

	public void setLongValue(int longValue) {
		this.longValue = longValue;
	}

	public int getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(int booleanValue) {
		this.booleanValue = booleanValue;
	}

}
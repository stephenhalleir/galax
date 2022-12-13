package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeValueOld {

	private int id;
	private Date creationDateTime;
	private Date startDateTime;
	private int booleanValue;
	private Date datetimeValue;
	private int longValue;
	private String stringValue;

	public AttributeValueOld() {

	}

	public AttributeValueOld(ResultSet rs) {
		try {
			id = rs.getInt("id");
			creationDateTime = rs.getDate("creation_date_time");
			startDateTime = rs.getDate("start_date_time");
			booleanValue = rs.getInt("boolean_value");
			datetimeValue = rs.getDate("datetime_value");
			longValue = rs.getInt("long_value");
			stringValue = rs.getString("string_value");
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

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
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

	public int getLongValue() {
		return longValue;
	}

	public void setLongValue(int longValue) {
		this.longValue = longValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

}
package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Process {

	private int id;
	private String type;
	private int sequence;
	private int eventId;
	private String status;
	private Date createdDateTime;

	public Process() {

	}

	public Process(ResultSet rs) {
		try {
			id = rs.getInt("id");
			type = rs.getString("type");
			sequence = rs.getInt("sequence");
			eventId = rs.getInt("event_id");
			status = rs.getString("status");
			createdDateTime = rs.getDate("created_date_time");
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
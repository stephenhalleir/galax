package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;
import java.sql.SQLException;

import java.sql.ResultSet;

public class Contract {
	
	private int id;
	private Date startAt;
	private Date endAt;
	
	public Contract() {
		
	}

	public Contract(ResultSet rs) {
		try {
			id=rs.getInt("id");
			startAt=rs.getDate("start_at");
			endAt=rs.getDate("end_at");
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

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
}

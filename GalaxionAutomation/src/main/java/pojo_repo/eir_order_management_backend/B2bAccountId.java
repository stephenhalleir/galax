package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class B2bAccountId {

	private int id;
	private int b2bAccountId;
	private String status;
	private Date createdDateTime;

	public B2bAccountId() {

	}

	public B2bAccountId(ResultSet rs) {
		try {
			id = rs.getInt("id");
			b2bAccountId = rs.getInt("b2b_account_id");
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

	public int getB2bAccountId() {
		return b2bAccountId;
	}

	public void setB2bAccountId(int b2bAccountId) {
		this.b2bAccountId = b2bAccountId;
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
package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefProcess {

	private int id;
	private String type;
	private int sequence;

	public RefProcess() {

	}

	public RefProcess(ResultSet rs) {
		try {
			id = rs.getInt("id");
			type = rs.getString("type");
			sequence = rs.getInt("sequence");
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

}
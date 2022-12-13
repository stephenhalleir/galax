package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {

	private int id;
	private String comment;
	private String type;

	public Comment() {

	}

	public Comment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			comment = rs.getString("comment");
			type = rs.getString("type");
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
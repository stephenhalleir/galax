package pojo_repo.eir_security_question_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Answer {

	private int id;
	private String uuid;
	private int refQuestionId;

	public Answer() {

	}

	public Answer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			uuid = rs.getString("uuid");
			refQuestionId = rs.getInt("ref_question_id");
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getRefQuestionId() {
		return refQuestionId;
	}

	public void setRefQuestionId(int refQuestionId) {
		this.refQuestionId = refQuestionId;
	}

}
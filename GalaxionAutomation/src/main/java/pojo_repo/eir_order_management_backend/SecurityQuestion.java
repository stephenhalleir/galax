package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecurityQuestion {

	private int id;
	private String questionId;
	private String response;

	public SecurityQuestion() {

	}

	public SecurityQuestion(ResultSet rs) {
		try {
			id = rs.getInt("id");
			questionId = rs.getString("question_id");
			response = rs.getString("response");
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

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
package microservices.backend.eir_security_question_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefQuestion {
	private int id;
	private String code;
	private String question;
	
	public RefQuestion() {
		
	}
	
	public RefQuestion(ResultSet rs) {
		try {
			id=rs.getInt("id");
			code=rs.getString("code");
			question=rs.getString("question");
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}

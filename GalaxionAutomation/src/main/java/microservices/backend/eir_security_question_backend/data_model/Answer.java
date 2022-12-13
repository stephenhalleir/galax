package microservices.backend.eir_security_question_backend.data_model;

import java.sql.SQLException;

import java.sql.ResultSet;

public class Answer {
	private int id;
	private String uuid;
	private int refQuestionID;
	private String response;
	
	public Answer() {
		
	}
	
	public Answer(ResultSet rs) {
		try {
			id=rs.getInt("id");
			uuid=rs.getString("uuid");
			refQuestionID=rs.getInt("ref_question_id");
			response=rs.getString("response");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getRefQuestionID() {
		return refQuestionID;
	}

	public void setRefQuestionID(int refQuestionID) {
		this.refQuestionID = refQuestionID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}

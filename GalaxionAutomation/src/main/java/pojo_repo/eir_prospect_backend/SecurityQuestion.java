package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecurityQuestion {

private int oldId;
private String questionCode;
private String response;

public SecurityQuestion() {

}

public SecurityQuestion(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	questionCode = rs.getString("question_code");
	response = rs.getString("response");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getOldId() {
 	return oldId;
}
public void setOldId(int oldId) {
 	 this.oldId=oldId;
}
public String getQuestionCode() {
 	return questionCode;
}
public void setQuestionCode(String questionCode) {
 	 this.questionCode=questionCode;
}
public String getResponse() {
 	return response;
}
public void setResponse(String response) {
 	 this.response=response;
}

}
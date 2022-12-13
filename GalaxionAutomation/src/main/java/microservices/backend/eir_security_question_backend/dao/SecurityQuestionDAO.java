package microservices.backend.eir_security_question_backend.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import microservices.backend.eir_security_question_backend.data_model.Answer;
import microservices.backend.eir_security_question_backend.data_model.RefQuestion;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class SecurityQuestionDAO {
	
	public static Answer getAnswer(String contactUuid) {
		String query = ExcelSQLManager.getSQLQuery("SECURITY_QUESTION", "GET_ANSWER");
		query = query.replace("$contactUuid", contactUuid);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new Answer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static RefQuestion getQuestion(int id) {
		String query = ExcelSQLManager.getSQLQuery("SECURITY_QUESTION", "GET_REF_QUESTION");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new RefQuestion(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String [] args) {
		Answer answer = getAnswer("8997ae65-b5c7-4c30-96bc-38920b7dbc17");
		RefQuestion question=getQuestion(answer.getRefQuestionID());

		System.out.println(answer.getResponse() + ", " + question.getQuestion());
	}
}

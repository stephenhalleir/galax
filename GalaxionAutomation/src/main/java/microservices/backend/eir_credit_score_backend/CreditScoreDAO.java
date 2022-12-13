package microservices.backend.eir_credit_score_backend;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class CreditScoreDAO {

	private static String tabName = "CREDIT_SCORE";

	/**
	 * Create an entry in the credit score table
	 * 
	 * @param creditScore
	 * @param occupation
	 * @param residentialStatus
	 * @param prospectUuid
	 * @param contactUuid
	 */
	public static void createCreditScore(int creditScore, String occupation, String residentialStatus, String prospectUuid, String contactUuid) {
		String query = ExcelSQLManager.getSQLQuery(tabName, "CREATE_CREDIT_SCORE");
		query = query.replace("$creditScore", Integer.toString(creditScore));
		query = query.replace("$occupation", occupation);
		query = query.replace("$residentialStatus", residentialStatus);
		query = query.replace("$prospectUuid", prospectUuid);
		query = query.replace("$contactUuid", contactUuid);
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static CreditScore getCreditScore(String contactUuid) {
		
		System.err.println("Contactuuid = " + contactUuid);
		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_CREDIT_SCORE");
		System.err.println("Query = " + query);
		query = query.replace("$contactUuid", contactUuid);
		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new CreditScore(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

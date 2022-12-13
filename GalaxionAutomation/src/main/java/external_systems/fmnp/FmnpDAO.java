package external_systems.fmnp;

import java.sql.SQLException;

import external_systems.fmnp.enums.MobileOperator;
import utilities.generic.time.Timestamp;
import utilities.test.config_readers.ExcelSQLManager;

public class FmnpDAO {

	private static String tabName = "FMNP";

	/**
	 * Enter a row into the table MNP.npdb_msisdn_status
	 * 
	 * @param operator
	 * @param msisdn
	 * @param startTime - dd-MM-yyyy
	 */
	public static int insertMSISDN(MobileOperator operator, String msisdn, String startTime) {

		// retrieve the query
		String query = ExcelSQLManager.getSQLQuery(tabName, "INSERT_MSISDN");

		// determine the operator name string from the enum value
		String operatorName = getOperatorName(operator);

		// build the query
		query = query.replace("$operator", operatorName);
		query = query.replace("$msisdn", msisdn);
		query = query.replace("$startTime", startTime);

		// run the update
		try {
			return FmnpDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int insertMSISDN(MobileOperator operator, String msisdn) {
		return insertMSISDN(operator, msisdn, Timestamp.getCurrentTimestamp("dd-MM-yyyy"));
	}

	public static void updateCurrentOperator(String msisdn, MobileOperator operator) {

		// retrieve the query
		String query = ExcelSQLManager.getSQLQuery(tabName, "UPDATE_OPERATOR");

		// determine the operator name string from the enum value
		String operatorName = getOperatorName(operator);

		// build the query
		query = query.replace("$operator", operatorName);
		query = query.replace("$msisdn", msisdn);

		// run the update
		try {
			FmnpDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getOperatorName(MobileOperator operator) {
		if (operator == MobileOperator.EIR) {
			return "Eircom Mobile";
		} else if (operator == MobileOperator.LYCA) {
			return "LycaMobile";
		} else if (operator == MobileOperator.METEOR) {
			return "Meteor";
		} else if (operator == MobileOperator.TESCO) {
			return "Tesco";
		} else if (operator == MobileOperator.THREE) {
			return "3";
		} else if (operator == MobileOperator.UPC) {
			return "UPC Mobile";
		} else if (operator == MobileOperator.VODAFONE) {
			return "Vodafone";
		} else {
			System.err.println("Unable to determine operator name for enum " + operator);
			return null;
		}
	}

}

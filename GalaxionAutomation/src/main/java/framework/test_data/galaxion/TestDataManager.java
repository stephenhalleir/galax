/**
 * This class can be used to manage test data for the Galaxion Automation framework
 * 
 * It contains a number of static methods to retrieve test data from the Galaxion database
 * 
 * Created By: Stephen Hall (stephen.hall@eir.ie)
 * Last Reviewed: 15/03/2021
 */

package framework.test_data.galaxion;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class TestDataManager {
	
	/**
	 * Retrieve a billing account ID from the environment sheet - GOMO.MULTILINE
	 * @return billing account ID
	 */
	public static int getGoMoMultilineSubscriber() {
		return Integer.parseInt(EnvironmentExcelConfigReader.getGalaxionConfigValue("GOMO.MULTILINE"));
	}
	
	/**
	 * Retrieve a billing account ID from the environment sheet - B2B.ACCOUNT.CORPORATE
	 * @return billing account ID
	 */
	public static int getB2BCorporateAccount() {
		return Integer.parseInt(EnvironmentExcelConfigReader.getGalaxionConfigValue("B2B.ACCOUNT.CORPORATE"));
	}
	
	/**
	 * Retrieve a billing account ID from the environment sheet - B2B.ACCOUNT.GOVERNMENT
	 * @return billing account ID
	 */
	public static int getB2BGovernmentAccount() {
		return Integer.parseInt(EnvironmentExcelConfigReader.getGalaxionConfigValue("B2B.ACCOUNT.GOVERNMENT"));
	}
	
	/**
	 * Retrieve a billing account ID from the environment sheet - PAYG.SINGLELINE
	 * @return billing account ID
	 */
	public static int getEirPAYGSubscriber() {
		return Integer.parseInt(EnvironmentExcelConfigReader.getGalaxionConfigValue("PAYG.SINGLELINE"));
	}
	
	/**
	 * Retrieve a billing account ID from the environment sheet - EIR.B2C
	 * @return billing account ID
	 */
	public static int getEirPostpaySubscriber() {
		return Integer.parseInt(EnvironmentExcelConfigReader.getGalaxionConfigValue("EIR.B2C"));
	}
	
	/**
	 * Retrieve a piece of test data from the ION database based on a billing account ID
	 * @param billingAccountID
	 * @return test data object
	 */
	public static TestData getTestData(int billingAccountID) {
		
		// build the query
		String query = ExcelSQLManager.getSQLQuery("TEST_DATA", "GET_TEST_DATA");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;

		// make the DB call
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				// return the TestData object
				return new TestData(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}	
}

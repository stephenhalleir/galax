package external_systems.fmnp;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.database.MariaDBConnection;

public class FmnpDBUtil {
	
	/**
	 * Get an object to interface with the Galaxion database
	 * @return
	 */
	public static MariaDBConnection getDBConnection() {
		String DB_URL="10.237.4.154";
		String DB_PORT = "3306";
		String USER = "fmnp-admin";
		String PASS = "MX4BmMwk";
		return new MariaDBConnection(DB_URL,DB_PORT,USER,PASS);
	}
	
	public static ResultSet getQueryResultSet(String query) throws SQLException {
		MariaDBConnection db = getDBConnection();
		return db.getQueryResultSet(query);
	}
	
	public static int runUpdateQuery(String query) throws SQLException {
		MariaDBConnection db = getDBConnection();
		return db.runUpdateQuery(query);
	}
}

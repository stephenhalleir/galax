package utilities.generic.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.galaxion.environments.EnvironmentDirectory;

public class GalaxionDBUtil {

	/**
	 * Get an object to interface with the Galaxion database
	 * @return
	 */
	private static MariaDBConnection getDBConnection() {
		String DB_URL=EnvironmentDirectory.getMariaDBHost();
		String DB_PORT = EnvironmentDirectory.getMariaDBPort();
		String USER = EnvironmentDirectory.getMariaDBUsername();
		String PASS = EnvironmentDirectory.getMariaDBPassword();
		return new MariaDBConnection(DB_URL,DB_PORT,USER,PASS);
	}
	
	/**
	 * return a query result set
	 * @param query
	 * @return
	 * @throws SQLException 
	 */
	public static ResultSet getQueryResultSet(String query) throws SQLException {
		MariaDBConnection db = getDBConnection();
		return db.getQueryResultSet(query);
	}
	
	public static int runUpdateQuery(String query) throws SQLException {
		MariaDBConnection db = getDBConnection();
		return db.runUpdateQuery(query);
	}
}

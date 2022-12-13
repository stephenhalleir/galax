package utilities.generic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDBUtil {

	public Connection con = null;
	public String connectionString;
	public String username;
	public String password;

	// constructor, taking connection string, username and password
	public OracleDBUtil(String host, String port,String sid, String username,
			String password) {
		this.connectionString = "jdbc:oracle:thin:@" + host + ":" + port + "/" + sid;
		this.username = username;
		this.password = password;
		openDBConnection();
		System.out.println("Connected to database");
	}

	public void runUpdateQuery(String query) {

		System.out.println("Running update query: " + query);

		try {

			// create the statement
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// execute the java prepared statement
			preparedStmt.executeUpdate();

			preparedStmt.close();
			// commit changes
			//con.commit();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// open a connection to the ECS config database
	public boolean openDBConnection() {
		try {
			// look for the JDBC driver
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.err.println("Where is your Oracle JDBC Driver?");
				e.printStackTrace();
				return false;
			}

			// establish a connection to the database
			con = DriverManager.getConnection(connectionString, username,
					password);
		} catch (SQLException e1) {
			System.err
					.println("Connection to database failed! Check output console");
			e1.printStackTrace();
		}

		return true;
	}

	// close the connection to the database
	public boolean closeConnections() throws SQLException {
		con.close();
		System.out.println("Closing database connection");
		return true;
	}

	// execute a SQL query against the database and return a ResultSet
	public ResultSet executeQuery(String query) throws SQLException {
		Statement statement = null;
		ResultSet resultset = null;
		statement = con.createStatement();
		resultset = statement.executeQuery(query);
		return resultset;
	}
	
	
	public static void main (String [] args){

		OracleDBUtil dbutil = new OracleDBUtil("10.118.118.69","1521","ISVTST01","dwh_etl","dwh_etl1");
		dbutil.openDBConnection();
				
		try{
			ResultSet r = dbutil.executeQuery("select * from task_queue_result");
			//r.next();
			//System.out.println(r.getString(1));
			dbutil.closeConnections();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
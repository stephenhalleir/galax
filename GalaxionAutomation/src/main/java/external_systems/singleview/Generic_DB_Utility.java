package external_systems.singleview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Generic_DB_Utility {

	public Connection con = null;
	public String connectionString;
	public String username;
	public String password;


	// constructor, taking connection string, username and password
	public Generic_DB_Utility(String host, String port,String sid, String username,
			String password) {
		this.connectionString = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
		this.username = username;
		this.password = password;
		openDBConnection();
		System.out.println("Connected to database");
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

		Generic_DB_Utility dbutil = new Generic_DB_Utility("hpbillts.intmet.ie","1521","ISVTST01","dwh_etl","dwh_etl1");
		dbutil.openDBConnection();
				
		try{
			ResultSet r = dbutil.executeQuery("select * from equipment_history " + 
					"where serial_number between '893530305243667828' and '893530305243668329' " + 
					"and sysdate between effective_start_date and effective_end_date");
			r.next();
			System.out.println(r.getString("subscription_key"));
			dbutil.closeConnections();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
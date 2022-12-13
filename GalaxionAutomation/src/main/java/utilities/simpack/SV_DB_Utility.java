package utilities.simpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SV_DB_Utility {

	public Connection svCon = null;
	public String connectionString;
	public String username;
	public String password;

	// constructor - hard code Test05 details for testing purposes
	public SV_DB_Utility(String user) {

		if (user.toUpperCase().equals("ISVTST04")
				|| user.toUpperCase().equals("ISVTST05")
				|| user.toUpperCase().equals("ISVTST07")) {
			connectionString = "jdbc:oracle:thin:@10.105.22.14:1521:" + user;
		} else if (user.toUpperCase().equals("ISVTST01")
				|| user.toUpperCase().equals("ISVTST02")
				|| user.toUpperCase().equals("ISVTST03")) {
			connectionString = "jdbc:oracle:thin:@10.118.118.69:1521:" + user;
		}
		username = "dwh_etl";
		password = "dwh_etl1";
		//openDBConnection();
		//System.out.println("Connected to Singl.eView database");
	}

	// constructor, taking connection string, username and password
	public SV_DB_Utility(String connectionString, String username,
			String password) {
		this.connectionString = connectionString;
		this.username = username;
		this.password = password;
		//openDBConnection();
		//System.out.println("Connected to Singl.eView database");
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

			// establish a connection to the Singl.eView database on your chosen
			// environment
			svCon = DriverManager.getConnection(connectionString, username,
					password);
			System.out.println("Connected to Singl.eView database");
		} catch (SQLException e1) {
			System.err
					.println("Connection to Singl.eView database failed! Check output console");
			e1.printStackTrace();
			return false;
		}

		return true;
	}

	// close the connection to the Singl.eView database
	public boolean closeConnections() throws SQLException {
		svCon.close();
		System.out.println("Closing Singl.eView database connection");
		return true;
	}

	// execute a SQL query against the SV database and return a ResultSet
	public ResultSet executeQuery(String query) throws SQLException {
		Statement statement = null;
		ResultSet resultset = null;
		System.out.println("svCon=Null: " + svCon==null);
		statement = svCon.createStatement();
		resultset = statement.executeQuery(query);
		return resultset;
	}
	
	// execute a SQL query against the SV database and return a ResultSet
		public void executeUpdateQuery(String query) throws SQLException {
			openDBConnection();
			Statement statement = null;
			statement = svCon.createStatement();
			statement.executeQuery(query);
			closeConnections();
		}
	
	public String getResultsForTask(String taskID){
		
		openDBConnection();
		
		String results="";
		String query = "select output_text from task_queue_result where task_queue_id='"+ taskID.trim();
		
		System.out.println(query);
		try {
			// read the results of the query
			ResultSet r = executeQuery(query);

			// while there are rows in the result set...
			while (r.next()) {
				results=results+ r.getString("OUTPUT_TEXT")+"\n";
			}
			closeConnections();
		} catch (SQLException e) {
			System.err.println("Exception occurred");
			e.printStackTrace();
		}
		
		return results;
	}
	
	// retrieve the schedule ID for a given "Allot SIMs" task ID
	public String getScheduleIdForAllotSIMs(String taskID) {
		
		String query = "select output_text from task_queue_result where task_queue_id='"
				+ taskID.trim() + "' and output_text like '%Task schedule%'";
		System.out.println(query);
		String outputText = null;
		try {
			// read the results of the query
			ResultSet r = executeQuery(query);

			// while there are rows in the result set...
			if (r.next()) {
				outputText = r.getString("OUTPUT_TEXT");
			}

			// if the output text was found, return the schedule ID
			if (outputText != null) {
				return outputText.substring(56).replace(" created.", "").trim();
			}
		} catch (SQLException e) {
			System.err.println("Exception occurred");
			e.printStackTrace();
		}
		return null;
	}

	// retrieve the .int file name for a given task ID
	public String getIntFilenameforTaskID(String taskID) {
		String query = "select output_text from task_queue_result where task_queue_id='"
				+ taskID.trim() + "' and output_text like '%File%.int%'";
		// + "' and output_text like '%Prepaid%Provisioning%File =%int%'";
		System.out.println(query);
		String outputText = "";
		
		openDBConnection();
		try {
			// read the results of the query
			ResultSet r = executeQuery(query);

			// while there are rows in the result set...
			if (r.next()) {
				outputText = r.getString("OUTPUT_TEXT");
				System.err.println(outputText);
				
				String splitChar = null;
				if (!outputText.equals("")) {
					if (outputText.contains("=")) {
						splitChar = "=";
					} else if (outputText.contains(":")) {
						splitChar = ":";
					}
					else{
						System.err.println(outputText + " contains no = or :");
					}

					System.err.println("getIntFilenameforTaskID Returning "
							+ outputText.split(splitChar)[1].trim());
					closeConnections();
					return outputText.split(splitChar)[1].trim();
				}
			} else {
				System.err.println("getIntFilenameforTaskID Returning empty string");
			}
		} catch (Exception e) {
			
		}
		return null;
	}

	// retrieve the .int file name for a given Allot Sims task ID
	public String getIntFilenameforAllotSimsTaskID(String taskID) {
		openDBConnection();
		String query = "select output_text from task_queue_result where task_queue_id='"
				+ taskID.trim()
				+ "' and output_text like '%Allot SIM Cards File Name%'";
		System.out.println(query);
		String outputText = "";
		try {
			// read the results of the query
			ResultSet r = executeQuery(query);

			// while there are rows in the result set...
			if (r.next()) {
				outputText = r.getString("OUTPUT_TEXT");
			}
			
			closeConnections();

			if (!outputText.equals("")) {
				System.err.println("Returning "
						+ outputText.split(":")[1].trim());
				return outputText.split(":")[1].trim();
			} else {
				System.err.println("Returning null string");
			}
		} catch (Exception e) {

		}
		return null;
	}

	// retrieve the task ID for a given schedule ID
	public String getTaskIDforScheduleID(String scheduleID) {
		
		openDBConnection();
		
		String query = "select task_queue_id from task_queue where schedule_id="
				+ scheduleID;
		System.out.println(query);
		String taskID = "";
		try {
			// read the results of the query
			ResultSet r = executeQuery(query);

			// while there are rows in the result set...
			if (r.next()) {
				taskID = r.getString("task_queue_id");
				System.out.println("Task_ID=" + taskID);
			}
			
			closeConnections();

			if (!taskID.equals("")) {
				System.err.println("Returning " + taskID);
				return taskID;
			} else {
				return null;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Returning null");
			return null;
		}
	}

	// get the created batch ID for a task
	public String getBatchIDForTask(String taskID) {

		openDBConnection();
		
		String query = "select output_text from task_queue_result where task_queue_id='"
				+ taskID.replace("\n", "")
				+ "' and output_text like '%Batch%ID%'";
		System.out.println(query);
		String outputText = "";
		try {
			// read the results of the query
			ResultSet r = executeQuery(query);

			// while there are rows in the result set...
			if (r.next()) {
				outputText = r.getString("OUTPUT_TEXT");
				System.err.println("Full string found = "+outputText);
			}
			
			closeConnections();

			if (!outputText.equals("")) {
				
				System.err.println("Returning "
						+ outputText.split(":")[1].trim());
				return outputText.split(":")[1].trim();
			} else {
				return null;
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Returning null");
			return null;
		}
	}

	public static void main(String[] args) {
		SV_DB_Utility dao = new SV_DB_Utility("isvtst05");
		dao.getIntFilenameforTaskID(dao.getTaskIDforScheduleID(dao
				.getScheduleIdForAllotSIMs("14092888")));
	}
}

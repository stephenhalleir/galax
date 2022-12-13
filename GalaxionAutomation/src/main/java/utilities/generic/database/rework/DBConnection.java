package utilities.generic.database.rework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
	
	public String JDBC_DRIVER;
	public String DB_URL;
	public String DB_PORT;
	public String USER;
	public String PASS;
	
	public DBConnection(String dB_URL, String dB_PORT, String uSER, String pASS) {
		super();
		DB_URL = dB_URL;
		DB_PORT = dB_PORT;
		USER = uSER;
		PASS = pASS;
	}
	
	// =================================================================================================================
		// Return the result of a SELECT query on the database
		// =================================================================================================================
		public ResultSet getQueryResultSet(String query) throws SQLException {

			System.out.println("Running select query: " + query);

			// create the connection and statement objects
			Connection conn = null;
			Statement stmt = null;

			// open a connection
			conn = DriverManager.getConnection(DB_URL + ":" + DB_PORT, USER, PASS);

			// execute a query
			stmt = conn.createStatement();

			// Execute the SQL Query. Store results in ResultSet
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);

			// close the statement object
			if (stmt != null) {
				stmt.close();
			}

			// close the connection
			if (conn != null) {
				conn.close();
			}

			// return the resultset
			return rs;
		}

		// =================================================================================================================
		// Return the number of rows in a result set
		// =================================================================================================================
		public int getNumberRows(ResultSet rs) {
			try {
				if (rs.last()) {
					return rs.getRow();
				} else {
					return 0;
				}
			} catch (Exception e) {
				System.out.println("Error getting row count");
				e.printStackTrace();
			}
			return 0;
		}

		// =================================================================================================================
		// Run the query and return the value of a particular String field
		// =================================================================================================================
		public String checkDBFieldStr(String query, String field) {
			ResultSet rs;
			try {
				rs = getQueryResultSet(query);
				while (rs.next()) {
					try {
						return rs.getString(field);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return null;
		}

		public Date checkDBFieldDate(String query, String field) {
			ResultSet rs;
			try {
				rs = getQueryResultSet(query);
				while (rs.next()) {
					try {
						return rs.getDate(field);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return null;
		}

		// =================================================================================================================
		// Poll the database every second until a field matches an expected value
		// =================================================================================================================
		public boolean waitForString(String query, String field, String value, int timeout) {

			int counter = 0;

			// while we have not yet hit the timeout and the field does not match
			while (counter < timeout && !checkDBFieldStr(query, field).equalsIgnoreCase(value)) {
				System.out.println("After " + counter + " seconds, " + field + " = " + checkDBFieldStr(query, field));
				// wait 1 second
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				counter++;
			}

			return checkDBFieldStr(query, field).equalsIgnoreCase(value);
		}

		// =================================================================================================================
		// Poll the database every second until a query returns a result
		// =================================================================================================================
		public ResultSet waitForResult(String query, int timeout) {

			long endTime = System.currentTimeMillis() + (timeout * 1000);
			ResultSet rs = null;

			// while we have not yet hit the timeout and the resultset is still null
			while (System.currentTimeMillis() < endTime && rs == null) {

				try {
					rs = this.getQueryResultSet(query);

					if (!rs.next()) {
						rs = null;
					} else {
						rs.beforeFirst();
						System.out.println("returning rs as it has a result");
						return rs;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// wait 1 second before checking again
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			return rs;
		}

		// run a sql query and return a specific integer field
		public int checkDBFieldInt(String query, String field) {
			ResultSet rs;
			try {
				rs = getQueryResultSet(query);
				while (rs.next()) {
					try {
						return rs.getInt(field);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return -1;
		}

		public boolean resultSetContains(ResultSet rs, String key, String value) {

			try {
				while (rs.next()) {
					try {
						if (rs.getString(key).equals(value)) {
							return true;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}

		public String getStrFromRS(ResultSet rs, String key) {

			try {
				while (rs.next()) {
					try {
						String s = rs.getString(key);
						rs.beforeFirst();
						return s;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}

		public Blob getBlobFromRS(ResultSet rs, String key) {

			try {
				while (rs.next()) {
					try {
						Blob blob = rs.getBlob(key);
						rs.beforeFirst();
						return blob;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}

		public int getIntFromRS(ResultSet rs, String key) {

			try {
				while (rs.next()) {
					try {
						int i = rs.getInt(key);
						rs.beforeFirst();
						return i;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		}

		public Date getDateFromRS(ResultSet rs, String key) {

			try {
				while (rs.next()) {
					try {
						Date d = rs.getDate(key);
						rs.beforeFirst();
						return d;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}

		// determine whether a result is found
		public boolean isResultFound(String query) {
			ResultSet rs;
			try {
				rs = getQueryResultSet(query);
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
		}

		public ArrayList<String> getList(String query, String field) {

			ArrayList<String> results = new ArrayList<String>();

			ResultSet rs;
			try {
				rs = getQueryResultSet(query);
				while (rs.next()) {
					try {
						results.add(rs.getString(field));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return results;
		}

		public String blobAsString(Blob blob) {
			String s = "";
			String temp;
			try {
				BufferedReader bufReader = new BufferedReader(new InputStreamReader(blob.getBinaryStream()));

				while ((temp = bufReader.readLine()) != null) {

					if (!s.equals("")) {
						s = s + "\n";
					}
					s = s + temp;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return s;
		}

		public String resultsetAsHtml(ResultSet rs) throws SQLException {

			// create the opening table tag
			String htmlString = "<table style='border: 1px solid black'>";

			// do the header row
			htmlString = htmlString + "<th>";
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				htmlString = htmlString + "<td>" + rsmd.getColumnName(i) + "</td>";
			}
			htmlString = htmlString + "</th>";

			// now add the contents
			while (rs.next()) {
				htmlString = htmlString + "<tr>";

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					htmlString = htmlString + "<td>" + rs.getString(i) + "</td>";
				}

				htmlString = htmlString + "</tr>";
			}

			htmlString = htmlString + "</table>";

			return htmlString;
		}

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}
	public void setJDBC_DRIVER(String jDBC_DRIVER) {
		JDBC_DRIVER = jDBC_DRIVER;
	}
	public String getDB_URL() {
		return DB_URL;
	}
	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}
	public String getDB_PORT() {
		return DB_PORT;
	}
	public void setDB_PORT(String dB_PORT) {
		DB_PORT = dB_PORT;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}	
}

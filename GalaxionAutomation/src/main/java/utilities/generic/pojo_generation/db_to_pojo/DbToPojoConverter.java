package utilities.generic.pojo_generation.db_to_pojo;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.files.TextReader;
import utilities.generic.pojo_generation.db_to_pojo.supporting.PojoStringUtility;
import utilities.generic.pojo_generation.db_to_pojo.supporting.TableProfile;

public class DbToPojoConverter {

	/**
	 * Generate a .java file for each table in a named database
	 * 
	 * @param db - e.g. "eir-subscription-management-backend"
	 */
	public static void generatePojosFor(String db) {

		// query to read the list of tables from the database
		String listTablesQuery = "SHOW TABLES FROM `$db_name`;";
		listTablesQuery = listTablesQuery.replace("$db_name", db);

		// execute the call to read the list of tables

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(listTablesQuery);
			
			// for each table found in the schema....
			while (rs.next()) {
				
				// determine the table name
				String tableName = rs.getString(1);

				// build the query for reading the table
				String getTableQuery = "select * from `$db`.`$table` limit 1";
				getTableQuery = getTableQuery.replace("$db", db);
				getTableQuery = getTableQuery.replace("$table", tableName);

				// query the table to read the metadata
				if (!tableName.startsWith("_") && !tableName.startsWith("DATABASE")) {
					ResultSet tableResultSet = GalaxionDBUtil.getQueryResultSet(getTableQuery);
					ResultSetMetaData metadata = tableResultSet.getMetaData();

					// build a profile of the table using the metadata and table name
					TableProfile profile = new TableProfile(rs.getString(1), metadata);

					// specify the package name and location based on the microservice name
					String packageName = "pojo_repo." + db.replace("-", "_").toLowerCase();
					String location = "src\\main\\java\\pojo_repo\\" + db.replace("-", "_").toLowerCase();
					
					// create the directory if it doesn't exist
					File directory = new File(location);
					if (!directory.exists()) {
						directory.mkdir();
					}
					
					// write the .java file to the directory
					TextReader.writeFile(PojoStringUtility.getClassCode(profile, packageName), location + "\\" + profile.getClassName() + ".java");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

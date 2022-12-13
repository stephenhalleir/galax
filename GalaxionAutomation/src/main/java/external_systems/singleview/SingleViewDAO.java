package external_systems.singleview;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.database.OracleDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class SingleViewDAO {

	public static void updateEquipmentStatus(String msisdn, String iccid, int statusCode) {
		
		// build the query
		String query = ExcelSQLManager.getSQLQuery("SINGLEVIEW", "UPDATE_EQUIPMENT_STATUS");
		query=query.replace("$msisdn", msisdn);
		query=query.replace("$iccid", iccid);
		query=query.replace("$code", Integer.toString(statusCode));
		System.out.println(query);
		
		// read the environment details for the SingleView database
		String host=EnvironmentExcelConfigReader.getMMWConfigValue("DB.HOST");
		String port=EnvironmentExcelConfigReader.getMMWConfigValue("DB.PORT");
		String serviceName=EnvironmentExcelConfigReader.getMMWConfigValue("DB.SERVICENAME");
		String username=EnvironmentExcelConfigReader.getMMWConfigValue("DB.USERNAME");
		String password=EnvironmentExcelConfigReader.getMMWConfigValue("DB.PASSWORD");
		
		// open the connection
		OracleDBUtil dbutil = new OracleDBUtil(host,port,serviceName,username,password);
		dbutil.openDBConnection();
				
		// execute the query
		try{
			ResultSet r = dbutil.executeQuery(query);
			dbutil.closeConnections();
		}
		catch(SQLException e){
			try {
				dbutil.closeConnections();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}

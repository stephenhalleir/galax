package microservices.backend.tecrep.resource_management.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import microservices.backend.tecrep.resource_management.data_model.Number;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class ResourceManagementDAO {

	public static Number getNumber(String number) {
		String query = ExcelSQLManager.getSQLQuery("RESOURCE_MANAGEMENT", "GET_NUMBER");
		query=query.replace("$number", number);
		
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				if(rs.next()) {
					return new Number(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public static void updateNumberStatus(String number, String newStatus) {
		String query = ExcelSQLManager.getSQLQuery("RESOURCE_MANAGEMENT", "UPDATE_NUMBER");
		query=query.replace("$number", number);
		query=query.replace("$newStatus", newStatus);
		
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getHighestMsisdnInRange(String range) {
		String query = ExcelSQLManager.getSQLQuery("RESOURCE_MANAGEMENT", "GET_LAST_NUMBER_IN_RANGE");
		query=query.replace("$range", range);
		
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				if(rs.next()) {
					return rs.getString("max");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
}

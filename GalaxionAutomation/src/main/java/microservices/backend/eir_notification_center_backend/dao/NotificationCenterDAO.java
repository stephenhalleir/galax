package microservices.backend.eir_notification_center_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_notification_center_backend.data_model.NCWebMessage;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class NotificationCenterDAO {
	
	public static ArrayList<NCWebMessage> getWebMessages(String contact_uuid){
		ArrayList<NCWebMessage> webMessages = new ArrayList<NCWebMessage>();
		
		String query = ExcelSQLManager.getSQLQuery("NOTIFICATION_CENTER", "GET_WEB_MESSAGES");
		query = query.replace("$contact_uuid", contact_uuid);
		
		ResultSet rs;
		
		try {
			rs=GalaxionDBUtil.getQueryResultSet(query);
			
			while(rs.next()) {
				webMessages.add(new NCWebMessage(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return webMessages;
	}
	
	public static NCWebMessage getWebMessage(int id) {
		
		String query = ExcelSQLManager.getSQLQuery("NOTIFICATION_CENTER", "GET_WEB_MESSAGE");
		query = query.replace("$id", Integer.toString(id));
		
		ResultSet rs;
		
		try {
			rs=GalaxionDBUtil.getQueryResultSet(query);
			
			while(rs.next()) {
				return new NCWebMessage(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<NCWebMessage> getDisplayableWebMessages(String contact_uuid){
		ArrayList<NCWebMessage> allWebMessages = getWebMessages(contact_uuid);
		ArrayList<NCWebMessage> displayableWebMessages = new ArrayList<NCWebMessage>();
		
		for(NCWebMessage message:allWebMessages) {
			if(message.getIsDisplayable()==1) {
				displayableWebMessages.add(message);
			}
		}
		
		return displayableWebMessages;
	}
}

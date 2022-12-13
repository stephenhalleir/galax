package microservices.backend.eir_bulk_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionFile;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionRow;
import microservices.backend.eir_bulk_backend.data_model.Bulk;
import microservices.backend.eir_bulk_backend.data_model.ChangeOffer;
import microservices.backend.eir_bulk_backend.data_model.Flow;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

/**
 * BulKDAO provides an interface into
 * the bulk-backend database
 * 
 * @author stephenhall
 *
 */
public class BulkDAO {

	/**
	 * Retrieve the details from the eir-bulk-backend BULK table
	 * for a particular file name
	 * 
	 * @param filename 
	 * @return the Bulk object
	 */
	public static Bulk getBulkFile(String filename) {

		String query = ExcelSQLManager.getSQLQuery("BULK", "GET_BULK_FILE");
		query = query.replace("$filename", filename);
		
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			return new Bulk(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<String> getOrderNumbersForCreateSubscriptionFile(String filename){
		String query = ExcelSQLManager.getSQLQuery("BULK", "GET_ORDER_REFERENCES_FROM_BULK_FILE");
		query = query.replace("$filename", filename);
		
		ArrayList<String> references = new ArrayList<String>();
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while(rs.next()) {
				references.add(rs.getString("order_reference"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return references;
	}
	
	public static ArrayList<Flow> getFlows(int bulkID){
		
		ArrayList<Flow> flows = new ArrayList<Flow>();
		
		String query = ExcelSQLManager.getSQLQuery("BULK","GET_FLOWS");
		query = query.replace("$bulkID", Integer.toString(bulkID));


		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			
			while(rs.next()) {
				flows.add(new Flow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flows;
	}
	
	public static ChangeOffer getChangeOffer(String externalReference) {
		String query = ExcelSQLManager.getSQLQuery("BULK","GET_CHANGE_OFFER");
		query = query.replace("$externalReference", externalReference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			
			while(rs.next()) {
				return new ChangeOffer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static CreateSubscriptionFile getCreateSubscriptionFile(String filename) {
		CreateSubscriptionFile file = new CreateSubscriptionFile();
		Bulk bulk=getBulkFile(filename);
		
		String[] lines=bulk.getCsvString().split("\n");
		System.out.println(lines.length);
		
		file.setFilename(lines[0]);
		for(int i=1;i<lines.length;i++) {
			file.addRow(new CreateSubscriptionRow(lines[i]));
		}
		
		return file;
	}
}

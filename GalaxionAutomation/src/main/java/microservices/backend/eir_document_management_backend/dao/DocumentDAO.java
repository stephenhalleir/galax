package microservices.backend.eir_document_management_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_document_management_backend.data_model.Document;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class DocumentDAO {

	public static ArrayList<String> getDocumentTypes(){
		ArrayList<String> names = new ArrayList<String>();
		String query = "SELECT * FROM `eir-document-management-backend`.document_name;";
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while(rs.next()) {
				names.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return names;
	}
	
	public static Document getDocumentByFilename(String filename) {
		String query = ExcelSQLManager.getSQLQuery("DOCUMENT", "GET_DOCUMENT_BY_FILENAME");
		query=query.replace("$filename",filename);
		
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new Document(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<Document> getDocuments(int accountID) {
		
		ArrayList<Document> documents = new ArrayList<Document>();
		String query = ExcelSQLManager.getSQLQuery("DOCUMENT", "GET_DOCUMENTS_BY_ACCOUNT_ID");
		query=query.replace("$accountId",Integer.toString(accountID));
		
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while(rs.next()) {
				documents.add(new Document(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return documents;
	}
	
}

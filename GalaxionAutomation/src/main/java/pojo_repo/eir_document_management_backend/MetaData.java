package pojo_repo.eir_document_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaData {

	private int documentId;
	private String value;

	public MetaData() {

	}

	public MetaData(ResultSet rs) {
		try {
			documentId = rs.getInt("document_id");
			value = rs.getString("value");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
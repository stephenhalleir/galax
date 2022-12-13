package microservices.backend.eir_document_management_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Document {
	
	private int id;
	private String customerReference;
	private String documentFilename;
	private int documentFilesize;
	private String documentStatus;
	private String documentType;
	private String documentVersion;
	private int numberOfDownloads;
	private String accountId;
	
	public Document() {
		
	}
	
	public Document(ResultSet rs) {
		try {
			id=rs.getInt("id");
			customerReference=rs.getString("customer_reference");
			documentFilename=rs.getString("document_file_name");
			documentFilesize=rs.getInt("document_file_size");
			documentStatus=rs.getString("document_status");
			documentType=rs.getString("document_type");
			documentVersion=rs.getString("document_version");
			numberOfDownloads=rs.getInt("number_of_download");
			accountId=rs.getString("account_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getDocumentFilename() {
		return documentFilename;
	}

	public void setDocumentFilename(String documentFilename) {
		this.documentFilename = documentFilename;
	}

	public int getDocumentFilesize() {
		return documentFilesize;
	}

	public void setDocumentFilesize(int documentFilesize) {
		this.documentFilesize = documentFilesize;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentVersion() {
		return documentVersion;
	}

	public void setDocumentVersion(String documentVersion) {
		this.documentVersion = documentVersion;
	}

	public int getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(int numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}

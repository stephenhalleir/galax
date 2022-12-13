package pojo_repo.eir_document_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentAud {

	private int id;
	private int revisionId;
	private int revisionType;
	private String customerReference;
	private Date dateOfLastDownload;
	private String documentFileName;
	private int documentFileSize;
	private String documentName;
	private String documentStatus;
	private String documentType;
	private String documentVersion;
	private int numberOfDownload;
	private int serviceId;
	private Date updateDate;
	private Date uploadDate;
	private String customerUuid;
	private String user;

	public DocumentAud() {

	}

	public DocumentAud(ResultSet rs) {
		try {
			id = rs.getInt("id");
			revisionId = rs.getInt("revision_id");
			revisionType = rs.getInt("revision_type");
			customerReference = rs.getString("customer_reference");
			dateOfLastDownload = rs.getDate("date_of_last_download");
			documentFileName = rs.getString("document_file_name");
			documentFileSize = rs.getInt("document_file_size");
			documentName = rs.getString("document_name");
			documentStatus = rs.getString("document_status");
			documentType = rs.getString("document_type");
			documentVersion = rs.getString("document_version");
			numberOfDownload = rs.getInt("number_of_download");
			serviceId = rs.getInt("service_id");
			updateDate = rs.getDate("update_date");
			uploadDate = rs.getDate("upload_date");
			customerUuid = rs.getString("customer_uuid");
			user = rs.getString("user");
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

	public int getRevisionId() {
		return revisionId;
	}

	public void setRevisionId(int revisionId) {
		this.revisionId = revisionId;
	}

	public int getRevisionType() {
		return revisionType;
	}

	public void setRevisionType(int revisionType) {
		this.revisionType = revisionType;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public Date getDateOfLastDownload() {
		return dateOfLastDownload;
	}

	public void setDateOfLastDownload(Date dateOfLastDownload) {
		this.dateOfLastDownload = dateOfLastDownload;
	}

	public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	public int getDocumentFileSize() {
		return documentFileSize;
	}

	public void setDocumentFileSize(int documentFileSize) {
		this.documentFileSize = documentFileSize;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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

	public int getNumberOfDownload() {
		return numberOfDownload;
	}

	public void setNumberOfDownload(int numberOfDownload) {
		this.numberOfDownload = numberOfDownload;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
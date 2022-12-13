package pojo_repo.eir_document_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Document {

	private int id;
	private String customerReference;
	private Date dateOfLastDownload;
	private String documentFileName;
	private int documentFileSize;
	private String documentStatus;
	private String documentType;
	private String documentVersion;
	private int numberOfDownload;
	private int serviceId;
	private Date updateDate;
	private Date uploadDate;
	private String customerUuid;
	private String user;
	private String accountId;

	public Document() {

	}

	public Document(ResultSet rs) {
		try {
			id = rs.getInt("id");
			customerReference = rs.getString("customer_reference");
			dateOfLastDownload = rs.getDate("date_of_last_download");
			documentFileName = rs.getString("document_file_name");
			documentFileSize = rs.getInt("document_file_size");
			documentStatus = rs.getString("document_status");
			documentType = rs.getString("document_type");
			documentVersion = rs.getString("document_version");
			numberOfDownload = rs.getInt("number_of_download");
			serviceId = rs.getInt("service_id");
			updateDate = rs.getDate("update_date");
			uploadDate = rs.getDate("upload_date");
			customerUuid = rs.getString("customer_uuid");
			user = rs.getString("user");
			accountId = rs.getString("account_id");
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
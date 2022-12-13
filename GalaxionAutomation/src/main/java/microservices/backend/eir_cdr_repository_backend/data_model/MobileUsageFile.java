package microservices.backend.eir_cdr_repository_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileUsageFile {
	private int id;
	private String serviceType;
	private String filename;
	private int recordCount;
	private int successCount;
	private int validationErrorCount;
	private int processingErrorCount;
	private String fileStatus;
	private String errorCode;
	private String errorText;
	
	public MobileUsageFile() {
		
	}
	
	public MobileUsageFile(ResultSet rs) {
		try {
			id=rs.getInt("id");
			serviceType=rs.getString("service_type");
			filename=rs.getString("filename");
			recordCount=rs.getInt("record_count");
			successCount=rs.getInt("success_count");
			validationErrorCount=rs.getInt("validation_error_count");
			processingErrorCount=rs.getInt("processing_error_count");
			fileStatus=rs.getString("file_status");
			errorCode=rs.getString("error_code");
			errorText=rs.getString("error_text");
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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public int getValidationErrorCount() {
		return validationErrorCount;
	}

	public void setValidationErrorCount(int validationErrorCount) {
		this.validationErrorCount = validationErrorCount;
	}

	public int getProcessingErrorCount() {
		return processingErrorCount;
	}

	public void setProcessingErrorCount(int processingErrorCount) {
		this.processingErrorCount = processingErrorCount;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}

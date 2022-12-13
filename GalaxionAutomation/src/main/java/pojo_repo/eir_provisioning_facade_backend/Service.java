package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {

	private int id;
	private int sequence;
	private int requestId;
	private int serviceId;
	private String serviceName;
	private int catalogOfferId;
	private String catalogOfferDomain;
	private String serviceAction;
	private String serviceStatus;
	private String errorCode;
	private String errorDescription;
	private int retryCount;
	private Date createdDateTime;

	public Service() {

	}

	public Service(ResultSet rs) {
		try {
			id = rs.getInt("id");
			sequence = rs.getInt("sequence");
			requestId = rs.getInt("request_id");
			serviceId = rs.getInt("service_id");
			serviceName = rs.getString("service_name");
			catalogOfferId = rs.getInt("catalog_offer_id");
			catalogOfferDomain = rs.getString("catalog_offer_domain");
			serviceAction = rs.getString("service_action");
			serviceStatus = rs.getString("service_status");
			errorCode = rs.getString("error_code");
			errorDescription = rs.getString("error_description");
			retryCount = rs.getInt("retry_count");
			createdDateTime = rs.getDate("created_date_time");
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}

	public String getCatalogOfferDomain() {
		return catalogOfferDomain;
	}

	public void setCatalogOfferDomain(String catalogOfferDomain) {
		this.catalogOfferDomain = catalogOfferDomain;
	}

	public String getServiceAction() {
		return serviceAction;
	}

	public void setServiceAction(String serviceAction) {
		this.serviceAction = serviceAction;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
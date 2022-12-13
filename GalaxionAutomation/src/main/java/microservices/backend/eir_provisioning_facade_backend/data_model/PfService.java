package microservices.backend.eir_provisioning_facade_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PfService {

	private int id;
	private int sequence;
	private int requestID;
	private int serviceID;
	private String catalogOfferCode;
	private String catalogOfferDomain;
	private String serviceName;
	private String serviceAction;
	private String serviceStatus;
	private String errorCode;
	private String errorDescription;
	private int retryCount;

	public PfService() {

	}
	
	public PfService(ResultSet rs){
		try {
			id=rs.getInt("id");
			sequence=rs.getInt("sequence");
			requestID=rs.getInt("request_id");
			serviceID=rs.getInt("service_id");
			catalogOfferCode=rs.getString("catalog_offer_code");
			catalogOfferDomain=rs.getString("catalog_offer_domain");
			serviceName=rs.getString("service_name");
			serviceAction=rs.getString("service_action");
			serviceStatus=rs.getString("service_status");
			errorCode=rs.getString("error_code");
			errorDescription=rs.getString("error_description");
			retryCount=rs.getInt("retry_count");
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

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getCatalogOfferCode() {
		return catalogOfferCode;
	}

	public void setCatalogOfferCode(String catalogOfferCode) {
		this.catalogOfferCode = catalogOfferCode;
	}

	public String getCatalogOfferDomain() {
		return catalogOfferDomain;
	}

	public void setCatalogOfferDomain(String catalogOfferDomain) {
		this.catalogOfferDomain = catalogOfferDomain;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
}

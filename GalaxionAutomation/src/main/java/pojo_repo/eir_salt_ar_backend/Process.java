package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Process {

	private String changeType;
	private int id;
	private Date creationDatetime;
	private String errorCode;
	private String errorMessage;
	private Date expiryDatetime;
	private Date lastProcessingDatetime;
	private int lastProcessingDuration;
	private Date nextProcessingTime;
	private int postponedCount;
	private String containerId;
	private String hostId;
	private String processingUnitId;
	private String correlationId;
	private String requestorId;
	private String rootCorrelationId;
	private int retryCount;
	private int status;
	private int tentativeCount;
	private int amount;
	private int billingAccountId;
	private Date changeDate;
	private int currentBatch;
	private Date dueDate;
	private Date invoiceDate;
	private String pollerName;
	private int totalBatch;
	private Date overdueDate;
	private int rootEvent;
	private Date createTs;
	private Date modifTs;

	public Process() {

	}

	public Process(ResultSet rs) {
		try {
			changeType = rs.getString("change_type");
			id = rs.getInt("id");
			creationDatetime = rs.getDate("creation_datetime");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			expiryDatetime = rs.getDate("expiry_datetime");
			lastProcessingDatetime = rs.getDate("last_processing_datetime");
			lastProcessingDuration = rs.getInt("last_processing_duration");
			nextProcessingTime = rs.getDate("next_processing_time");
			postponedCount = rs.getInt("postponed_count");
			containerId = rs.getString("container_id");
			hostId = rs.getString("host_id");
			processingUnitId = rs.getString("processing_unit_id");
			correlationId = rs.getString("correlation_id");
			requestorId = rs.getString("requestor_id");
			rootCorrelationId = rs.getString("root_correlation_id");
			retryCount = rs.getInt("retry_count");
			status = rs.getInt("status");
			tentativeCount = rs.getInt("tentative_count");
			amount = rs.getInt("amount");
			billingAccountId = rs.getInt("billing_account_id");
			changeDate = rs.getDate("change_date");
			currentBatch = rs.getInt("current_batch");
			dueDate = rs.getDate("due_date");
			invoiceDate = rs.getDate("invoice_date");
			pollerName = rs.getString("poller_name");
			totalBatch = rs.getInt("total_batch");
			overdueDate = rs.getDate("overdue_date");
			rootEvent = rs.getInt("root_event");
			createTs = rs.getDate("create_ts");
			modifTs = rs.getDate("modif_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDatetime() {
		return creationDatetime;
	}

	public void setCreationDatetime(Date creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getExpiryDatetime() {
		return expiryDatetime;
	}

	public void setExpiryDatetime(Date expiryDatetime) {
		this.expiryDatetime = expiryDatetime;
	}

	public Date getLastProcessingDatetime() {
		return lastProcessingDatetime;
	}

	public void setLastProcessingDatetime(Date lastProcessingDatetime) {
		this.lastProcessingDatetime = lastProcessingDatetime;
	}

	public int getLastProcessingDuration() {
		return lastProcessingDuration;
	}

	public void setLastProcessingDuration(int lastProcessingDuration) {
		this.lastProcessingDuration = lastProcessingDuration;
	}

	public Date getNextProcessingTime() {
		return nextProcessingTime;
	}

	public void setNextProcessingTime(Date nextProcessingTime) {
		this.nextProcessingTime = nextProcessingTime;
	}

	public int getPostponedCount() {
		return postponedCount;
	}

	public void setPostponedCount(int postponedCount) {
		this.postponedCount = postponedCount;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getProcessingUnitId() {
		return processingUnitId;
	}

	public void setProcessingUnitId(String processingUnitId) {
		this.processingUnitId = processingUnitId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getRootCorrelationId() {
		return rootCorrelationId;
	}

	public void setRootCorrelationId(String rootCorrelationId) {
		this.rootCorrelationId = rootCorrelationId;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTentativeCount() {
		return tentativeCount;
	}

	public void setTentativeCount(int tentativeCount) {
		this.tentativeCount = tentativeCount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public int getCurrentBatch() {
		return currentBatch;
	}

	public void setCurrentBatch(int currentBatch) {
		this.currentBatch = currentBatch;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPollerName() {
		return pollerName;
	}

	public void setPollerName(String pollerName) {
		this.pollerName = pollerName;
	}

	public int getTotalBatch() {
		return totalBatch;
	}

	public void setTotalBatch(int totalBatch) {
		this.totalBatch = totalBatch;
	}

	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	public int getRootEvent() {
		return rootEvent;
	}

	public void setRootEvent(int rootEvent) {
		this.rootEvent = rootEvent;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getModifTs() {
		return modifTs;
	}

	public void setModifTs(Date modifTs) {
		this.modifTs = modifTs;
	}

}
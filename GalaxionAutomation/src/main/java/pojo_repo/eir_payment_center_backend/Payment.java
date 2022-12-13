package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

	private int id;
	private int paymentMethodId;
	private int amount;
	private Date transactionDatetime;
	private String status;
	private String providerTransactionRef;
	private String type;
	private String correlationTransactionUuid;
	private String providerAuthCode;
	private String providerRebateTransactionRef;
	private String rebateReason;
	private Date lastModifiedDate;
	private String source;
	private String lastModifiedBy;
	private int oldPaymentRunId;
	private String errorCode;
	private String errorMessage;
	private Date rebateDatetime;
	private String rejectReason;
	private int paymentRunId;
	private int paymentRunGroupId;
	private Date createdAt;
	private Date updatedAt;
	private String fileName;
	private int transferFrom;
	private int transferTo;
	private Date transferredAt;

	public Payment() {

	}

	public Payment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			paymentMethodId = rs.getInt("payment_method_id");
			amount = rs.getInt("amount");
			transactionDatetime = rs.getDate("transaction_datetime");
			status = rs.getString("status");
			providerTransactionRef = rs.getString("provider_transaction_ref");
			type = rs.getString("type");
			correlationTransactionUuid = rs.getString("correlation_transaction_uuid");
			providerAuthCode = rs.getString("provider_auth_code");
			providerRebateTransactionRef = rs.getString("provider_rebate_transaction_ref");
			rebateReason = rs.getString("rebate_reason");
			lastModifiedDate = rs.getDate("last_modified_date");
			source = rs.getString("source");
			lastModifiedBy = rs.getString("last_modified_by");
			oldPaymentRunId = rs.getInt("old_payment_run_id");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			rebateDatetime = rs.getDate("rebate_datetime");
			rejectReason = rs.getString("reject_reason");
			paymentRunId = rs.getInt("payment_run_id");
			paymentRunGroupId = rs.getInt("payment_run_group_id");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			fileName = rs.getString("file_name");
			transferFrom = rs.getInt("transfer_from");
			transferTo = rs.getInt("transfer_to");
			transferredAt = rs.getDate("transferred_at");
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

	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Date transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProviderTransactionRef() {
		return providerTransactionRef;
	}

	public void setProviderTransactionRef(String providerTransactionRef) {
		this.providerTransactionRef = providerTransactionRef;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCorrelationTransactionUuid() {
		return correlationTransactionUuid;
	}

	public void setCorrelationTransactionUuid(String correlationTransactionUuid) {
		this.correlationTransactionUuid = correlationTransactionUuid;
	}

	public String getProviderAuthCode() {
		return providerAuthCode;
	}

	public void setProviderAuthCode(String providerAuthCode) {
		this.providerAuthCode = providerAuthCode;
	}

	public String getProviderRebateTransactionRef() {
		return providerRebateTransactionRef;
	}

	public void setProviderRebateTransactionRef(String providerRebateTransactionRef) {
		this.providerRebateTransactionRef = providerRebateTransactionRef;
	}

	public String getRebateReason() {
		return rebateReason;
	}

	public void setRebateReason(String rebateReason) {
		this.rebateReason = rebateReason;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public int getOldPaymentRunId() {
		return oldPaymentRunId;
	}

	public void setOldPaymentRunId(int oldPaymentRunId) {
		this.oldPaymentRunId = oldPaymentRunId;
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

	public Date getRebateDatetime() {
		return rebateDatetime;
	}

	public void setRebateDatetime(Date rebateDatetime) {
		this.rebateDatetime = rebateDatetime;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public int getPaymentRunId() {
		return paymentRunId;
	}

	public void setPaymentRunId(int paymentRunId) {
		this.paymentRunId = paymentRunId;
	}

	public int getPaymentRunGroupId() {
		return paymentRunGroupId;
	}

	public void setPaymentRunGroupId(int paymentRunGroupId) {
		this.paymentRunGroupId = paymentRunGroupId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getTransferFrom() {
		return transferFrom;
	}

	public void setTransferFrom(int transferFrom) {
		this.transferFrom = transferFrom;
	}

	public int getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(int transferTo) {
		this.transferTo = transferTo;
	}

	public Date getTransferredAt() {
		return transferredAt;
	}

	public void setTransferredAt(Date transferredAt) {
		this.transferredAt = transferredAt;
	}

}
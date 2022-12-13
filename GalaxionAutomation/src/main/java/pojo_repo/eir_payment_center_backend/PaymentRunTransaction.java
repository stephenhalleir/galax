package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRunTransaction {

	private int id;
	private int amount;
	private int billingAccountId;
	private int paymentRunId;
	private int paymentRunGroupId;
	private String paymentMethod;
	private String status;
	private String errorCode;
	private String errorMessage;
	private Date createdAt;
	private Date updatedAt;
	private String debtorName;
	private String debtorIban;
	private String debtorBic;
	private String currency;
	private String rejectReason;
	private String creditorName;
	private String creditorIban;
	private String creditorBic;

	public PaymentRunTransaction() {

	}

	public PaymentRunTransaction(ResultSet rs) {
		try {
			id = rs.getInt("id");
			amount = rs.getInt("amount");
			billingAccountId = rs.getInt("billing_account_id");
			paymentRunId = rs.getInt("payment_run_id");
			paymentRunGroupId = rs.getInt("payment_run_group_id");
			paymentMethod = rs.getString("payment_method");
			status = rs.getString("status");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			debtorName = rs.getString("debtor_name");
			debtorIban = rs.getString("debtor_iban");
			debtorBic = rs.getString("debtor_bic");
			currency = rs.getString("currency");
			rejectReason = rs.getString("reject_reason");
			creditorName = rs.getString("creditor_name");
			creditorIban = rs.getString("creditor_iban");
			creditorBic = rs.getString("creditor_bic");
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDebtorName() {
		return debtorName;
	}

	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}

	public String getDebtorIban() {
		return debtorIban;
	}

	public void setDebtorIban(String debtorIban) {
		this.debtorIban = debtorIban;
	}

	public String getDebtorBic() {
		return debtorBic;
	}

	public void setDebtorBic(String debtorBic) {
		this.debtorBic = debtorBic;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getCreditorName() {
		return creditorName;
	}

	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}

	public String getCreditorIban() {
		return creditorIban;
	}

	public void setCreditorIban(String creditorIban) {
		this.creditorIban = creditorIban;
	}

	public String getCreditorBic() {
		return creditorBic;
	}

	public void setCreditorBic(String creditorBic) {
		this.creditorBic = creditorBic;
	}

}
package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileNonUsage {

	private int id;
	private int partitionNumber;
	private int mobileNonUsageFileId;
	private int recordType;
	private int billingAccountId;
	private int subscriptionId;
	private int serviceId;
	private int accountId;
	private String serviceName;
	private Date eventDateTime;
	private int adjustmentCode;
	private String adjustmentReason;
	private String transactionId;
	private int amount;
	private int balanceType;
	private int unitType;
	private int balance;
	private int confirmationNumber;
	private String voucherNumber;
	private int status;
	private String serviceStatus;
	private int pricePlanId;
	private int reserved;

	public MobileNonUsage() {

	}

	public MobileNonUsage(ResultSet rs) {
		try {
			id = rs.getInt("id");
			partitionNumber = rs.getInt("partition_number");
			mobileNonUsageFileId = rs.getInt("mobile_non_usage_file_id");
			recordType = rs.getInt("record_type");
			billingAccountId = rs.getInt("billing_account_id");
			subscriptionId = rs.getInt("subscription_id");
			serviceId = rs.getInt("service_id");
			accountId = rs.getInt("account_id");
			serviceName = rs.getString("service_name");
			eventDateTime = rs.getDate("event_date_time");
			adjustmentCode = rs.getInt("adjustment_code");
			adjustmentReason = rs.getString("adjustment_reason");
			transactionId = rs.getString("transaction_id");
			amount = rs.getInt("amount");
			balanceType = rs.getInt("balance_type");
			unitType = rs.getInt("unit_type");
			balance = rs.getInt("balance");
			confirmationNumber = rs.getInt("confirmation_number");
			voucherNumber = rs.getString("voucher_number");
			status = rs.getInt("status");
			serviceStatus = rs.getString("service_status");
			pricePlanId = rs.getInt("price_plan_id");
			reserved = rs.getInt("reserved");
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

	public int getPartitionNumber() {
		return partitionNumber;
	}

	public void setPartitionNumber(int partitionNumber) {
		this.partitionNumber = partitionNumber;
	}

	public int getMobileNonUsageFileId() {
		return mobileNonUsageFileId;
	}

	public void setMobileNonUsageFileId(int mobileNonUsageFileId) {
		this.mobileNonUsageFileId = mobileNonUsageFileId;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public int getAdjustmentCode() {
		return adjustmentCode;
	}

	public void setAdjustmentCode(int adjustmentCode) {
		this.adjustmentCode = adjustmentCode;
	}

	public String getAdjustmentReason() {
		return adjustmentReason;
	}

	public void setAdjustmentReason(String adjustmentReason) {
		this.adjustmentReason = adjustmentReason;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(int balanceType) {
		this.balanceType = balanceType;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public int getPricePlanId() {
		return pricePlanId;
	}

	public void setPricePlanId(int pricePlanId) {
		this.pricePlanId = pricePlanId;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

}
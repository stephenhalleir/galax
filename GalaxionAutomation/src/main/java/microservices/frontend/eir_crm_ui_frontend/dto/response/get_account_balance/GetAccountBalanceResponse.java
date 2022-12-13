package microservices.frontend.eir_crm_ui_frontend.dto.response.get_account_balance;

public class GetAccountBalanceResponse {
	private int billingAccountId;
	private String domain;
	private int nonOverdueAmount;
	private String lastBillOverdueDate;
	private int overdueAmount;
	private String overdueDate;
	private String delinquentDate;
	private boolean delinquentState;
	private boolean receiverFlag;
	private String date;
	private int delinquentStartAmount;
	private int delinquentEndAmount;

	public GetAccountBalanceResponse() {
		super();
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getNonOverdueAmount() {
		return nonOverdueAmount;
	}

	public void setNonOverdueAmount(int nonOverdueAmount) {
		this.nonOverdueAmount = nonOverdueAmount;
	}

	public String getLastBillOverdueDate() {
		return lastBillOverdueDate;
	}

	public void setLastBillOverdueDate(String lastBillOverdueDate) {
		this.lastBillOverdueDate = lastBillOverdueDate;
	}

	public int getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(int overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public String getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(String overdueDate) {
		this.overdueDate = overdueDate;
	}

	public String getDelinquentDate() {
		return delinquentDate;
	}

	public void setDelinquentDate(String delinquentDate) {
		this.delinquentDate = delinquentDate;
	}

	public boolean isDelinquentState() {
		return delinquentState;
	}

	public void setDelinquentState(boolean delinquentState) {
		this.delinquentState = delinquentState;
	}

	public boolean isReceiverFlag() {
		return receiverFlag;
	}

	public void setReceiverFlag(boolean receiverFlag) {
		this.receiverFlag = receiverFlag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDelinquentStartAmount() {
		return delinquentStartAmount;
	}

	public void setDelinquentStartAmount(int delinquentStartAmount) {
		this.delinquentStartAmount = delinquentStartAmount;
	}

	public int getDelinquentEndAmount() {
		return delinquentEndAmount;
	}

	public void setDelinquentEndAmount(int delinquentEndAmount) {
		this.delinquentEndAmount = delinquentEndAmount;
	}
}

package microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_hardware_fund;

import java.util.List;

public class GetHardwareFundDTO {
	private String creationDate;
	private int hardwareFundAmount;
	private int currentTotalBalance;
	private List<HardwareFundTransaction> transactionList;

	public GetHardwareFundDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getHardwareFundAmount() {
		return hardwareFundAmount;
	}

	public void setHardwareFundAmount(int hardwareFundAmount) {
		this.hardwareFundAmount = hardwareFundAmount;
	}

	public int getCurrentTotalBalance() {
		return currentTotalBalance;
	}

	public void setCurrentTotalBalance(int currentTotalBalance) {
		this.currentTotalBalance = currentTotalBalance;
	}

	public List<HardwareFundTransaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<HardwareFundTransaction> transactionList) {
		this.transactionList = transactionList;
	}

}

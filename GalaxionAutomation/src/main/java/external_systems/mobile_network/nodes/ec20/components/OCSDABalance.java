package external_systems.mobile_network.nodes.ec20.components;

//=================================================================================================================
// This class represents a Dedicated Account (DA) balance on CS5
// =================================================================================================================

public class OCSDABalance {

	private String eventType;
	private String balance;
	private String balanceCategory;
	private String expiryDate;
	
	// =================================================================================================================
    // Constructors
    // =================================================================================================================
	public OCSDABalance() {
		super();
		this.eventType = "";
		this.balance = "";
		this.balanceCategory = "";
		this.expiryDate = "";
	}

	public OCSDABalance(String eventType, String balance, String balanceCategory, String expiryDate) {
		super();
		this.eventType = eventType;
		this.balance = balance;
		this.balanceCategory = balanceCategory;
		this.expiryDate = expiryDate;
	}

	// =================================================================================================================
    // Getters and setters
    // =================================================================================================================
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBalanceCategory() {
		return balanceCategory;
	}

	public void setBalanceCategory(String balanceCategory) {
		this.balanceCategory = balanceCategory;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expirydate) {
		this.expiryDate = expirydate;
	}
	
	// =================================================================================================================
    // toString()
    // =================================================================================================================
	
	public String toString() {
		return "Balance: " + eventType + "\t" + balance + "\t" + balanceCategory + "\t" + expiryDate;
	}
}

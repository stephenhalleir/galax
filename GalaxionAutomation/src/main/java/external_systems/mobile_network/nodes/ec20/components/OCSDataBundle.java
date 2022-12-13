package external_systems.mobile_network.nodes.ec20.components;

//=================================================================================================================
// This class represents a Dedicated Account (DA) balance on CS5
// =================================================================================================================

public class OCSDataBundle {

	private String balanceId;
	private String balanceType;
	private String balanceAmount;
	private String balanceAvailable;
	private String effectiveDate;
	private String expiryDate;
	private String category;
	
	// =================================================================================================================
    // Constructors
    // =================================================================================================================
	public OCSDataBundle() {
		super();
		
	}

	public OCSDataBundle(String eventType, String balance, String balanceCategory, String expiryDate) {
		super();
		
	}

	// =================================================================================================================
    // Getters and setters
    // =================================================================================================================
	
	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBalanceAvailable() {
		return balanceAvailable;
	}

	public void setBalanceAvailable(String balanceAvailable) {
		this.balanceAvailable = balanceAvailable;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	// =================================================================================================================
    // toString()
    // =================================================================================================================
	public String toString() {
		return balanceId + ", " + balanceType + ", " + balanceAmount + ", " + balanceAvailable + ", " + effectiveDate + ", " + expiryDate + ", " + category;
	}
	
}

package external_systems.mobile_network.nodes.ec20.components;

//=================================================================================================================
// This class represents an ECS bundle component - e.g. a FUP or main balance
//=================================================================================================================

public class ECSBundleComponent {

	private String balanceID;
	private String balanceType;
	private String balanceAmount;
	private String balanceAvailable;
	private String effectiveDate;
	private String expiryDate;
	private String category;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	public ECSBundleComponent() {
		super();
	}

	// =================================================================================================================
	// Getters and Setters
	// =================================================================================================================

	public String getBalanceID() {
		return balanceID;
	}

	public void setBalanceID(String balanceID) {
		this.balanceID = balanceID;
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
	// Methods
	// =================================================================================================================

	public String toString() {
		return "Component: " + balanceID + "\t" + balanceType + "\t" + balanceAmount + "\t" + balanceAvailable + "\t"
				+ effectiveDate + "\t" + expiryDate + "\t" + category;
	}
}

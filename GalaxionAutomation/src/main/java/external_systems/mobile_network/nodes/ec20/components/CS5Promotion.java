package external_systems.mobile_network.nodes.ec20.components;

//=================================================================================================================
// This class represents a Promotion (PSO) on CS5
//=================================================================================================================

public class CS5Promotion {
	
	private String promotionName;
	private String endDate;
	
	// =================================================================================================================
    // Constructors
    // =================================================================================================================
	
	public CS5Promotion() {
		super();
		promotionName="";
		endDate="";
	}

	public CS5Promotion(String promotionName, String endDate) {
		super();
		this.promotionName = promotionName;
		this.endDate = endDate;
	}
	
	// =================================================================================================================
    // Getters and setters
    // =================================================================================================================

	public String getPromotionName() {
		return promotionName;
	}
	
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	// =================================================================================================================
    // toString()
    // =================================================================================================================
	
	public String toString() {
		return "Promotion: " + promotionName + "\t" + endDate;
	}
}

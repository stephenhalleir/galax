package microservices.frontend.common_ui.response_objects.cdr_repo;

public class RatingSubtotalType {
	private int id;
	private String key;
	private String ratingSubtotalUsageType;
	private String ratingSubtotalDisplayName;
	private String ratingSubtotalLevel;
	private String lastModifiedDateTime;
	private boolean billingSubtotal;

	public RatingSubtotalType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRatingSubtotalUsageType() {
		return ratingSubtotalUsageType;
	}

	public void setRatingSubtotalUsageType(String ratingSubtotalUsageType) {
		this.ratingSubtotalUsageType = ratingSubtotalUsageType;
	}

	public String getRatingSubtotalDisplayName() {
		return ratingSubtotalDisplayName;
	}

	public void setRatingSubtotalDisplayName(String ratingSubtotalDisplayName) {
		this.ratingSubtotalDisplayName = ratingSubtotalDisplayName;
	}

	public String getRatingSubtotalLevel() {
		return ratingSubtotalLevel;
	}

	public void setRatingSubtotalLevel(String ratingSubtotalLevel) {
		this.ratingSubtotalLevel = ratingSubtotalLevel;
	}

	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public boolean isBillingSubtotal() {
		return billingSubtotal;
	}

	public void setBillingSubtotal(boolean billingSubtotal) {
		this.billingSubtotal = billingSubtotal;
	}
}

package microservices.frontend.common_ui.response_objects.cdr_repo;

public class UsageSummary {

	private int ratingSubtotalId;
	private String measuringUnit;
	private int duration;
	private int quantity;
	private int eventCount;
	private int amount;
	private int ratingSubtotalTypeId;
	private String ratingSubtotalTypeKey;
	private String ratingSubtotalTypeLevel;
	private String ratingSubtotalTypeDisplayName;

	public UsageSummary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRatingSubtotalId() {
		return ratingSubtotalId;
	}

	public void setRatingSubtotalId(int ratingSubtotalId) {
		this.ratingSubtotalId = ratingSubtotalId;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRatingSubtotalTypeId() {
		return ratingSubtotalTypeId;
	}

	public void setRatingSubtotalTypeId(int ratingSubtotalTypeId) {
		this.ratingSubtotalTypeId = ratingSubtotalTypeId;
	}

	public String getRatingSubtotalTypeKey() {
		return ratingSubtotalTypeKey;
	}

	public void setRatingSubtotalTypeKey(String ratingSubtotalTypeKey) {
		this.ratingSubtotalTypeKey = ratingSubtotalTypeKey;
	}

	public String getRatingSubtotalTypeLevel() {
		return ratingSubtotalTypeLevel;
	}

	public void setRatingSubtotalTypeLevel(String ratingSubtotalTypeLevel) {
		this.ratingSubtotalTypeLevel = ratingSubtotalTypeLevel;
	}

	public String getRatingSubtotalTypeDisplayName() {
		return ratingSubtotalTypeDisplayName;
	}

	public void setRatingSubtotalTypeDisplayName(String ratingSubtotalTypeDisplayName) {
		this.ratingSubtotalTypeDisplayName = ratingSubtotalTypeDisplayName;
	}

}

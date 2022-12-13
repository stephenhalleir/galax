package microservices.frontend.common_ui.response_objects.cdr_repo;

public class Usage {

	private int chargedUsageId;
	private int amount;
	private String chargeStartDateTime;
	private int duration;
	private int quantity;
	private String chargedNumber;
	private String originNumber;
	private String destinationNumber;
	private String vatCode;
	private String measuringUnit;
	private String invoiceText;
	private String destinationCountryCode;
	private String originCountryCode;
	private int usageCounterTypeId;

	public Usage() {
		super();
	}

	public int getChargedUsageId() {
		return chargedUsageId;
	}

	public void setChargedUsageId(int chargedUsageId) {
		this.chargedUsageId = chargedUsageId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getChargeStartDateTime() {
		return chargeStartDateTime;
	}

	public void setChargeStartDateTime(String chargeStartDateTime) {
		this.chargeStartDateTime = chargeStartDateTime;
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

	public String getChargedNumber() {
		return chargedNumber;
	}

	public void setChargedNumber(String chargedNumber) {
		this.chargedNumber = chargedNumber;
	}

	public String getOriginNumber() {
		return originNumber;
	}

	public void setOriginNumber(String originNumber) {
		this.originNumber = originNumber;
	}

	public String getDestinationNumber() {
		return destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public String getInvoiceText() {
		return invoiceText;
	}

	public void setInvoiceText(String invoiceText) {
		this.invoiceText = invoiceText;
	}

	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}

	public void setDestinationCountryCode(String destinationCountryCode) {
		this.destinationCountryCode = destinationCountryCode;
	}

	public String getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public int getUsageCounterTypeId() {
		return usageCounterTypeId;
	}

	public void setUsageCounterTypeId(int usageCounterTypeId) {
		this.usageCounterTypeId = usageCounterTypeId;
	}
}

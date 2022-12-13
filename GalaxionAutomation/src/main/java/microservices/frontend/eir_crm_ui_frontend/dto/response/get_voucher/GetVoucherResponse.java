package microservices.frontend.eir_crm_ui_frontend.dto.response.get_voucher;

public class GetVoucherResponse {
	
	private String voucherSerialNumber;
	private int amount;
	private String currency;
	private String subscriberMSISDN;
	private String status;
	private String usedDateTime;

	public GetVoucherResponse() {
		super();
	}

	public String getVoucherSerialNumber() {
		return voucherSerialNumber;
	}

	public void setVoucherSerialNumber(String voucherSerialNumber) {
		this.voucherSerialNumber = voucherSerialNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSubscriberMSISDN() {
		return subscriberMSISDN;
	}

	public void setSubscriberMSISDN(String subscriberMSISDN) {
		this.subscriberMSISDN = subscriberMSISDN;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsedDateTime() {
		return usedDateTime;
	}

	public void setUsedDateTime(String usedDateTime) {
		this.usedDateTime = usedDateTime;
	}
}

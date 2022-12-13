package microservices.frontend.eir_crm_ui_frontend.dto.requests.process_topup;

public class ProcessTopupDTO {
	private String voucherActivationCode;
	private String voucherSerialNumber;

	public ProcessTopupDTO(String voucherSerialNumber, String voucherActivationCode) {
		super();
		this.voucherActivationCode = voucherActivationCode;
		this.voucherSerialNumber = voucherSerialNumber;
	}

	public String getVoucherActivationCode() {
		return voucherActivationCode;
	}

	public void setVoucherActivationCode(String voucherActivationCode) {
		this.voucherActivationCode = voucherActivationCode;
	}

	public String getVoucherSerialNumber() {
		return voucherSerialNumber;
	}

	public void setVoucherSerialNumber(String voucherSerialNumber) {
		this.voucherSerialNumber = voucherSerialNumber;
	}
}

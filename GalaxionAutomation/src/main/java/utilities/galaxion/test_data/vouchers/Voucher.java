package utilities.galaxion.test_data.vouchers;

public class Voucher {
	private String voucherNumber;
	private String activationCode;
	private double amount;
	
	public Voucher() {
		
	}
	
	public Voucher(String voucherNumber, String activationCode, double amount) {
		super();
		this.voucherNumber = voucherNumber;
		this.activationCode = activationCode;
		this.amount=amount;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}

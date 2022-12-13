package testCases.eir.b2b.bulk.create_subscriptions.test_objects;

public class Handset {

	private String handsetCode;
	private String deviceCharge;
	private boolean deductFromHwFund;
	private boolean deviceEnrollment;
	
	public Handset(String handsetCode) {
		this.handsetCode=handsetCode;
		this.deviceCharge="";
		this.deductFromHwFund=false;
		this.deviceEnrollment=false;
	}
	 
	public Handset(String handsetCode, String deviceCharge, boolean deductFromHwFund, boolean deviceEnrollment) {
		this.handsetCode=handsetCode;
		this.deviceCharge=deviceCharge;
		this.deductFromHwFund=deductFromHwFund;
		this.deviceEnrollment=deviceEnrollment;
	}
	
	public String getHandsetCode() {
		return handsetCode;
	}
	public void setHandsetCode(String handsetCode) {
		this.handsetCode = handsetCode;
	}
	public String getDeviceCharge() {
		return deviceCharge;
	}
	public void setDeviceCharge(String deviceCharge) {
		this.deviceCharge = deviceCharge;
	}
	public boolean isDeductFromHwFund() {
		return deductFromHwFund;
	}
	public void setDeductFromHwFund(boolean deductFromHwFund) {
		this.deductFromHwFund = deductFromHwFund;
	}
	public boolean isDeviceEnrollment() {
		return deviceEnrollment;
	}
	public void setDeviceEnrollment(boolean deviceEnrollment) {
		this.deviceEnrollment = deviceEnrollment;
	}
}

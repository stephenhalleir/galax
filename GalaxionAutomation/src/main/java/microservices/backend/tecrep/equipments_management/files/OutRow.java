package microservices.backend.tecrep.equipments_management.files;

import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.tecrep.equipments_management.utility.IccidCheckDigitGenerator;

public class OutRow {
	
	private String imsi;
	private String iccid;
	private String msisdn;
	private String ki;
	private String pin1;
	private String puk1;
	private String pin2;
	private String puk2;
	private String adm;
	
	public OutRow(String imsi, String iccid, String msisdn) {
		super();
		this.imsi=imsi;
		this.msisdn=msisdn;
		this.ki=RandomStringGenerator.getRandomHexString(32);
		this.adm=RandomStringGenerator.getRandomHexString(16);
		this.pin1=RandomStringGenerator.getRandomNumericString(4);
		this.pin2=RandomStringGenerator.getRandomNumericString(4);
		this.puk1=RandomStringGenerator.getRandomNumericString(8);
		this.puk2=RandomStringGenerator.getRandomNumericString(8);
		
		// set the iccid complete with the check digit
		int checkDigit = IccidCheckDigitGenerator.getCheckDigit(iccid);
		this.iccid=iccid.replace("L", Integer.toString(checkDigit));
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getKi() {
		return ki;
	}
	public void setKi(String ki) {
		this.ki = ki;
	}
	public String getPin1() {
		return pin1;
	}
	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}
	public String getPuk1() {
		return puk1;
	}
	public void setPuk1(String puk1) {
		this.puk1 = puk1;
	}
	public String getPin2() {
		return pin2;
	}
	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}
	public String getPuk2() {
		return puk2;
	}
	public void setPuk2(String puk2) {
		this.puk2 = puk2;
	}
	public String getAdm() {
		return adm;
	}
	public void setAdm(String adm) {
		this.adm = adm;
	}
	
	public String toString() {
		return imsi + " " + iccid + " " + msisdn + " " + ki + " " + pin1 + " " + puk1 + " " + pin2 + " " + puk2 + " " + adm; 
	}
}

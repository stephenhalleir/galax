package microservices.backend.eir_inventory_management_backend.objects;

public class EquipmentPack {

	private String packID;
	private String msisdn;
	private String iccid;
	private String imsi;
	private String pin;
	private String puk;

	public EquipmentPack(String msisdn, String packID, String iccid, String imsi) {
		this.msisdn = msisdn;
		this.packID = packID;
		this.iccid = iccid;
		this.imsi = imsi;
		this.pin = null;
		this.puk = null;
	}

	public EquipmentPack(String packString) {
		
		// split the string by comma
		String[] packComponents = packString.split(",");

		// create a pack object based on the fields in the line
		setPackID(packComponents[0]);
		setMsisdn(packComponents[1]);
		setIccid(packComponents[2]);
		setImsi(packComponents[3]);
	}

	public EquipmentPack() {

	}

	public String toString() {
		return iccid + ", " + imsi + ", " + msisdn;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public String getPackID() {
		return packID;
	}

	public String getIccid() {
		return iccid;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public void setPackID(String packID) {
		this.packID = packID;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}
}

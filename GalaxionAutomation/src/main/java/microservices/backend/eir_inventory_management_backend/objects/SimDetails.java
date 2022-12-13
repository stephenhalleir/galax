package microservices.backend.eir_inventory_management_backend.objects;

/**
 * This class represents the SIM details as retrieved by the
 * inventory-management-backend route "/inventory/sim"
 * 
 * @author stephenhall
 *
 */

public class SimDetails {
	
	private String pin1;
	private String puk1;
	private String pin2;
	private String puk2;
	private String imsi;
	private String iccid;

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
}

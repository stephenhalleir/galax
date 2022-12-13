package microservices.backend.eir_inventory_management_backend.objects;

public class EquipmentUpdateFile {
	private String msisdn;
	private String iccid;
	private int id;

	public EquipmentUpdateFile(String msisdn, String iccid, int id) {
		super();
		this.msisdn = msisdn;
		this.iccid = iccid;
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return msisdn + "," + iccid + "," + id + ",";
	}
}

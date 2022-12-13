package microservices.backend.tecrep.equipments_management.files;

public class InpRow {
	
	private String imsi;
	private String iccid;

	public InpRow(String imsi, String iccid) {
		super();
		this.imsi = imsi;
		this.iccid = iccid;
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

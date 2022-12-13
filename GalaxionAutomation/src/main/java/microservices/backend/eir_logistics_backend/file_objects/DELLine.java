package microservices.backend.eir_logistics_backend.file_objects;

public class DELLine {

	private String packId;
	private String imei;

	public DELLine(String packId, String imei) {
		super();
		this.packId = packId;
		this.imei = imei;
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String toString() {
		String template = "00000 000 0000000000000000000 0000000000 0000 00000000 $packId 400ETSS3ETSR6506 $imei 7558630";
		return template.replace("$packId", packId).replace("$imei",imei);
	}
}

package microservices.backend.tecrep.files.import_number_file;

import microservices.frontend.tecrep_monitor.enums.InventoryPool;

public class ImportNumberRow {
	private String msisdn;
	private String status;
	private String goldenNumber;
	private String nature;
	private InventoryPool inventoryPool;
	private String comment;

	public ImportNumberRow() {
		super();
	}

	public ImportNumberRow(String msisdn, String status, String goldenNumber, String nature, InventoryPool inventoryPool, String comment) {
		super();
		this.msisdn = msisdn;
		this.status = status;
		this.goldenNumber = goldenNumber;
		this.nature = nature;
		this.inventoryPool = inventoryPool;
		this.comment = comment;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoldenNumber() {
		return goldenNumber;
	}

	public void setGoldenNumber(String goldenNumber) {
		this.goldenNumber = goldenNumber;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public InventoryPool getInventoryPool() {
		return inventoryPool;
	}

	public void setInventoryPool(InventoryPool inventoryPool) {
		this.inventoryPool = inventoryPool;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

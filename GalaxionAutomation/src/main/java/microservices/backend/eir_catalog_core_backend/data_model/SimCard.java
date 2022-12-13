package microservices.backend.eir_catalog_core_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimCard {

	private String simCardSize;
	private int id;
	private String status;
	private int printable;
	private String invoiceDescription;
	private String accountingCode;
	private String vatCode;
	private String description;
	private String manufacturerCode;
	private String model;
	private String inventoryCode;
	private String oldChargeCode;

	public SimCard() {

	}

	public SimCard(ResultSet rs) {
		try {
			simCardSize = rs.getString("sim_card_size");
			id = rs.getInt("id");
			status = rs.getString("status");
			printable = rs.getInt("printable");
			invoiceDescription = rs.getString("invoice_description");
			accountingCode = rs.getString("accounting_code");
			vatCode = rs.getString("vat_code");
			description = rs.getString("description");
			manufacturerCode = rs.getString("manufacturer_code");
			model = rs.getString("model");
			inventoryCode = rs.getString("inventory_code");
			oldChargeCode = rs.getString("old_charge_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSimCardSize() {
		return simCardSize;
	}

	public void setSimCardSize(String simCardSize) {
		this.simCardSize = simCardSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrintable() {
		return printable;
	}

	public void setPrintable(int printable) {
		this.printable = printable;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public String getOldChargeCode() {
		return oldChargeCode;
	}

	public void setOldChargeCode(String oldChargeCode) {
		this.oldChargeCode = oldChargeCode;
	}

}
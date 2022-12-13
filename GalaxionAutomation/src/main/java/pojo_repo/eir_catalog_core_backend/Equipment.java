package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipment {

	private String description;
	private String model;
	private String inventoryCode;
	private int manufacturerId;
	private int id;
	private String chargeCode;
	private String vatCode;
	private String accountingCode;
	private String invoiceDescription;
	private int printable;
	private String status;
	private String code;
	private String color;
	private String colorCode;
	private Date availableAt;
	private Date preOrderAt;
	private Date discontinuedAt;
	private int suspended;

	public Equipment() {

	}

	public Equipment(ResultSet rs) {
		try {
			description = rs.getString("description");
			model = rs.getString("model");
			inventoryCode = rs.getString("inventory_code");
			manufacturerId = rs.getInt("manufacturer_id");
			id = rs.getInt("id");
			chargeCode = rs.getString("charge_code");
			vatCode = rs.getString("vat_code");
			accountingCode = rs.getString("accounting_code");
			invoiceDescription = rs.getString("invoice_description");
			printable = rs.getInt("printable");
			status = rs.getString("status");
			code = rs.getString("code");
			color = rs.getString("color");
			colorCode = rs.getString("color_code");
			availableAt = rs.getDate("available_at");
			preOrderAt = rs.getDate("pre_order_at");
			discontinuedAt = rs.getDate("discontinued_at");
			suspended = rs.getInt("suspended");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	public int getPrintable() {
		return printable;
	}

	public void setPrintable(int printable) {
		this.printable = printable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Date getAvailableAt() {
		return availableAt;
	}

	public void setAvailableAt(Date availableAt) {
		this.availableAt = availableAt;
	}

	public Date getPreOrderAt() {
		return preOrderAt;
	}

	public void setPreOrderAt(Date preOrderAt) {
		this.preOrderAt = preOrderAt;
	}

	public Date getDiscontinuedAt() {
		return discontinuedAt;
	}

	public void setDiscontinuedAt(Date discontinuedAt) {
		this.discontinuedAt = discontinuedAt;
	}

	public int getSuspended() {
		return suspended;
	}

	public void setSuspended(int suspended) {
		this.suspended = suspended;
	}

}
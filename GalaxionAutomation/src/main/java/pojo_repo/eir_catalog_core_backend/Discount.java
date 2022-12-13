package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {

	private String code;
	private String description;
	private String frequency;
	private String invoiceDescription;
	private int occurrence;
	private int printable;
	private String type;
	private int value;
	private String status;
	private int id;
	private int vatIncluded;
	private String vatCode;
	private String acquisitionType;
	private String itemType;
	private String level;
	private String billingType;
	private int displayOrder;
	private int prorataIn;

	public Discount() {

	}

	public Discount(ResultSet rs) {
		try {
			code = rs.getString("code");
			description = rs.getString("description");
			frequency = rs.getString("frequency");
			invoiceDescription = rs.getString("invoice_description");
			occurrence = rs.getInt("occurrence");
			printable = rs.getInt("printable");
			type = rs.getString("type");
			value = rs.getInt("value");
			status = rs.getString("status");
			id = rs.getInt("id");
			vatIncluded = rs.getInt("vat_included");
			vatCode = rs.getString("vat_code");
			acquisitionType = rs.getString("acquisition_type");
			itemType = rs.getString("item_type");
			level = rs.getString("level");
			billingType = rs.getString("billing_type");
			displayOrder = rs.getInt("display_order");
			prorataIn = rs.getInt("prorata_in");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	public int getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}

	public int getPrintable() {
		return printable;
	}

	public void setPrintable(int printable) {
		this.printable = printable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVatIncluded() {
		return vatIncluded;
	}

	public void setVatIncluded(int vatIncluded) {
		this.vatIncluded = vatIncluded;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

	public String getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(String acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public int getProrataIn() {
		return prorataIn;
	}

	public void setProrataIn(int prorataIn) {
		this.prorataIn = prorataIn;
	}

}
package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageQuota {

	private String code;
	private String vatCode;
	private String accountingCode;
	private String invoiceDescription;
	private int printable;
	private String status;
	private String description;
	private String frequency;
	private int occurrence;
	private String usageRange;
	private String type;
	private String unit;
	private int unlimited;
	private int value;

	public UsageQuota() {

	}

	public UsageQuota(ResultSet rs) {
		try {
			code = rs.getString("code");
			vatCode = rs.getString("vat_code");
			accountingCode = rs.getString("accounting_code");
			invoiceDescription = rs.getString("invoice_description");
			printable = rs.getInt("printable");
			status = rs.getString("status");
			description = rs.getString("description");
			frequency = rs.getString("frequency");
			occurrence = rs.getInt("occurrence");
			usageRange = rs.getString("usage_range");
			type = rs.getString("type");
			unit = rs.getString("unit");
			unlimited = rs.getInt("unlimited");
			value = rs.getInt("value");
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

	public int getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}

	public String getUsageRange() {
		return usageRange;
	}

	public void setUsageRange(String usageRange) {
		this.usageRange = usageRange;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getUnlimited() {
		return unlimited;
	}

	public void setUnlimited(int unlimited) {
		this.unlimited = unlimited;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
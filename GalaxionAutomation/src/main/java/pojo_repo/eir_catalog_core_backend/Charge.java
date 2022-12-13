package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Charge {

	private String advanceArrears;
	private String billingType;
	private String code;
	private String description;
	private int commitmentDuration;
	private int coolingOff;
	private String frequency;
	private String name;
	private int occurrence;
	private int prorataIn;
	private int prorataOut;
	private int validityDurationInDays;
	private String vatCode;
	private String accountingCode;
	private String invoiceDescription;
	private int printable;
	private String status;
	private int id;

	public Charge() {

	}

	public Charge(ResultSet rs) {
		try {
			advanceArrears = rs.getString("advance_arrears");
			billingType = rs.getString("billing_type");
			code = rs.getString("code");
			description = rs.getString("description");
			commitmentDuration = rs.getInt("commitment_duration");
			coolingOff = rs.getInt("cooling_off");
			frequency = rs.getString("frequency");
			name = rs.getString("name");
			occurrence = rs.getInt("occurrence");
			prorataIn = rs.getInt("prorata_in");
			prorataOut = rs.getInt("prorata_out");
			validityDurationInDays = rs.getInt("validity_duration_in_days");
			vatCode = rs.getString("vat_code");
			accountingCode = rs.getString("accounting_code");
			invoiceDescription = rs.getString("invoice_description");
			printable = rs.getInt("printable");
			status = rs.getString("status");
			id = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAdvanceArrears() {
		return advanceArrears;
	}

	public void setAdvanceArrears(String advanceArrears) {
		this.advanceArrears = advanceArrears;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
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

	public int getCommitmentDuration() {
		return commitmentDuration;
	}

	public void setCommitmentDuration(int commitmentDuration) {
		this.commitmentDuration = commitmentDuration;
	}

	public int getCoolingOff() {
		return coolingOff;
	}

	public void setCoolingOff(int coolingOff) {
		this.coolingOff = coolingOff;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}

	public int getProrataIn() {
		return prorataIn;
	}

	public void setProrataIn(int prorataIn) {
		this.prorataIn = prorataIn;
	}

	public int getProrataOut() {
		return prorataOut;
	}

	public void setProrataOut(int prorataOut) {
		this.prorataOut = prorataOut;
	}

	public int getValidityDurationInDays() {
		return validityDurationInDays;
	}

	public void setValidityDurationInDays(int validityDurationInDays) {
		this.validityDurationInDays = validityDurationInDays;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
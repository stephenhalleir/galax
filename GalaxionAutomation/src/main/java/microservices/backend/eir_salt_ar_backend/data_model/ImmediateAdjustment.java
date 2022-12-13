package microservices.backend.eir_salt_ar_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImmediateAdjustment {

	private String agentUsername;
	private String billItem;
	private String billingAccountVatCode;
	private String freeText;
	private int id;
	private int biVatPercentage;
	private int vatAmount;
	private String reasonCode;
	private String reasonTextFr;
	private String reasonTextDe;
	private String reasonTextEn;
	private String reasonTextIt;
	private Date createTs;
	private Date modifTs;
	private String reference;

	public ImmediateAdjustment() {

	}

	public ImmediateAdjustment(ResultSet rs) {
		try {
			agentUsername = rs.getString("agent_username");
			billItem = rs.getString("bill_item");
			billingAccountVatCode = rs.getString("billing_account_vat_code");
			freeText = rs.getString("free_text");
			id = rs.getInt("id");
			biVatPercentage = rs.getInt("bi_vat_percentage");
			vatAmount = rs.getInt("vat_amount");
			reasonCode = rs.getString("reason_code");
			reasonTextFr = rs.getString("reason_text_fr");
			reasonTextDe = rs.getString("reason_text_de");
			reasonTextEn = rs.getString("reason_text_en");
			reasonTextIt = rs.getString("reason_text_it");
			createTs = rs.getDate("create_ts");
			modifTs = rs.getDate("modif_ts");
			reference=rs.getString("reference_uuid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAgentUsername() {
		return agentUsername;
	}

	public void setAgentUsername(String agentUsername) {
		this.agentUsername = agentUsername;
	}

	public String getBillItem() {
		return billItem;
	}

	public void setBillItem(String billItem) {
		this.billItem = billItem;
	}

	public String getBillingAccountVatCode() {
		return billingAccountVatCode;
	}

	public void setBillingAccountVatCode(String billingAccountVatCode) {
		this.billingAccountVatCode = billingAccountVatCode;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBiVatPercentage() {
		return biVatPercentage;
	}

	public void setBiVatPercentage(int biVatPercentage) {
		this.biVatPercentage = biVatPercentage;
	}

	public int getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(int vatAmount) {
		this.vatAmount = vatAmount;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonTextFr() {
		return reasonTextFr;
	}

	public void setReasonTextFr(String reasonTextFr) {
		this.reasonTextFr = reasonTextFr;
	}

	public String getReasonTextDe() {
		return reasonTextDe;
	}

	public void setReasonTextDe(String reasonTextDe) {
		this.reasonTextDe = reasonTextDe;
	}

	public String getReasonTextEn() {
		return reasonTextEn;
	}

	public void setReasonTextEn(String reasonTextEn) {
		this.reasonTextEn = reasonTextEn;
	}

	public String getReasonTextIt() {
		return reasonTextIt;
	}

	public void setReasonTextIt(String reasonTextIt) {
		this.reasonTextIt = reasonTextIt;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getModifTs() {
		return modifTs;
	}

	public void setModifTs(Date modifTs) {
		this.modifTs = modifTs;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;

public class TaxDetails {

	private int id;
	private String category;
	private String number;
	private String certNumber;
	private String exemptionDocumentType;
	private Date exemptionStartDate;
	private Date exemptionEndDate;
	
	public TaxDetails() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}

	public String getExemptionDocumentType() {
		return exemptionDocumentType;
	}

	public void setExemptionDocumentType(String exemptionDocumentType) {
		this.exemptionDocumentType = exemptionDocumentType;
	}

	public Date getExemptionStartDate() {
		return exemptionStartDate;
	}

	public void setExemptionStartDate(Date exemptionStartDate) {
		this.exemptionStartDate = exemptionStartDate;
	}

	public Date getExemptionEndDate() {
		return exemptionEndDate;
	}

	public void setExemptionEndDate(Date exemptionEndDate) {
		this.exemptionEndDate = exemptionEndDate;
	}	
}

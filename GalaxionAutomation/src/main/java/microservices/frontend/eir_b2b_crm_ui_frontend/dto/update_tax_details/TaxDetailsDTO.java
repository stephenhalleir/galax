package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_tax_details;

public class TaxDetailsDTO {

	private String taxNumber;
	private String taxCertificateNumber;
	private String taxCategory;
	private String taxExemptionDocumentType;
	private String taxExemptionStartDate;
	private String taxExemptionEndDate;

	public TaxDetailsDTO(String taxNumber, String taxCertificateNumber, String taxCategory, String taxExemptionDocumentType, String taxExemptionStartDate,
			String taxExemptionEndDate) {
		super();
		this.taxNumber = taxNumber;
		this.taxCertificateNumber = taxCertificateNumber;
		this.taxCategory = taxCategory;
		this.taxExemptionDocumentType = taxExemptionDocumentType;
		this.taxExemptionStartDate = taxExemptionStartDate;
		this.taxExemptionEndDate = taxExemptionEndDate;
	}

	public TaxDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getTaxCertificateNumber() {
		return taxCertificateNumber;
	}

	public void setTaxCertificateNumber(String taxCertificateNumber) {
		this.taxCertificateNumber = taxCertificateNumber;
	}

	public String getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
	}

	public String getTaxExemptionDocumentType() {
		return taxExemptionDocumentType;
	}

	public void setTaxExemptionDocumentType(String taxExemptionDocumentType) {
		this.taxExemptionDocumentType = taxExemptionDocumentType;
	}

	public String getTaxExemptionStartDate() {
		return taxExemptionStartDate;
	}

	public void setTaxExemptionStartDate(String taxExemptionStartDate) {
		this.taxExemptionStartDate = taxExemptionStartDate;
	}

	public String getTaxExemptionEndDate() {
		return taxExemptionEndDate;
	}

	public void setTaxExemptionEndDate(String taxExemptionEndDate) {
		this.taxExemptionEndDate = taxExemptionEndDate;
	}
}

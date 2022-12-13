package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tax {

	private int id;
	private String companyName;
	private String companyRegistrationNumber;
	private String taxCategory;
	private String taxNumber;
	private String taxCertificateNumber;
	private String taxExemptionDocumentType;
	private Date taxExemptionStartDate;
	private Date taxExemptionEndDate;

	public Tax() {

	}

	public Tax(ResultSet rs) {
		try {
			id = rs.getInt("id");
			companyName = rs.getString("company_name");
			companyRegistrationNumber = rs.getString("company_registration_number");
			taxCategory = rs.getString("tax_category");
			taxNumber = rs.getString("tax_number");
			taxCertificateNumber = rs.getString("tax_certificate_number");
			taxExemptionDocumentType = rs.getString("tax_exemption_document_type");
			taxExemptionStartDate = rs.getDate("tax_exemption_start_date");
			taxExemptionEndDate = rs.getDate("tax_exemption_end_date");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyRegistrationNumber() {
		return companyRegistrationNumber;
	}

	public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
		this.companyRegistrationNumber = companyRegistrationNumber;
	}

	public String getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
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

	public String getTaxExemptionDocumentType() {
		return taxExemptionDocumentType;
	}

	public void setTaxExemptionDocumentType(String taxExemptionDocumentType) {
		this.taxExemptionDocumentType = taxExemptionDocumentType;
	}

	public Date getTaxExemptionStartDate() {
		return taxExemptionStartDate;
	}

	public void setTaxExemptionStartDate(Date taxExemptionStartDate) {
		this.taxExemptionStartDate = taxExemptionStartDate;
	}

	public Date getTaxExemptionEndDate() {
		return taxExemptionEndDate;
	}

	public void setTaxExemptionEndDate(Date taxExemptionEndDate) {
		this.taxExemptionEndDate = taxExemptionEndDate;
	}

}
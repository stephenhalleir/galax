package microservices.backend.eir_catalog_core_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {

	private int id;
	private Date validFrom;
	private Date validTo;
	private String code;
	private String description;
	private String status;
	private int displayOrder;
	private String invoiceDescription;
	private String invoiceType;
	private String brandCode;
	private String offerTypeCode;
	private String comment;

	public Offer() {

	}

	public Offer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			validFrom = rs.getDate("valid_from");
			validTo = rs.getDate("valid_to");
			code = rs.getString("code");
			description = rs.getString("description");
			status = rs.getString("status");
			displayOrder = rs.getInt("display_order");
			invoiceDescription = rs.getString("invoice_description");
			invoiceType = rs.getString("invoice_type");
			brandCode = rs.getString("brand_code");
			offerTypeCode = rs.getString("offer_type_code");
			comment = rs.getString("comment");
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

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getOfferTypeCode() {
		return offerTypeCode;
	}

	public void setOfferTypeCode(String offerTypeCode) {
		this.offerTypeCode = offerTypeCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
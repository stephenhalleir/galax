package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductOrder {

	private int id;
	private String externalId;
	private int hasAgreedTermsAndConditions;
	private int isAnonymous;
	private String reference;
	private int ownerId;
	private int payerId;
	private int channelId;
	private String pointOfSaleCode;
	private int documentId;
	private String status;
	private String orderType;
	private String salesType;
	private String ownerUuid;
	private String payerUuid;
	private int eventId;
	private int debitHardwareFund;

	public ProductOrder() {

	}

	public ProductOrder(ResultSet rs) {
		try {
			id = rs.getInt("id");
			externalId = rs.getString("external_id");
			hasAgreedTermsAndConditions = rs.getInt("has_agreed_terms_and_conditions");
			isAnonymous = rs.getInt("is_anonymous");
			reference = rs.getString("reference");
			ownerId = rs.getInt("owner_id");
			payerId = rs.getInt("payer_id");
			channelId = rs.getInt("channel_id");
			pointOfSaleCode = rs.getString("point_of_sale_code");
			documentId = rs.getInt("document_id");
			status = rs.getString("status");
			orderType = rs.getString("order_type");
			salesType = rs.getString("sales_type");
			ownerUuid = rs.getString("owner_uuid");
			payerUuid = rs.getString("payer_uuid");
			eventId = rs.getInt("event_id");
			debitHardwareFund = rs.getInt("debit_hardware_fund");
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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public int getHasAgreedTermsAndConditions() {
		return hasAgreedTermsAndConditions;
	}

	public void setHasAgreedTermsAndConditions(int hasAgreedTermsAndConditions) {
		this.hasAgreedTermsAndConditions = hasAgreedTermsAndConditions;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getPayerId() {
		return payerId;
	}

	public void setPayerId(int payerId) {
		this.payerId = payerId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getPointOfSaleCode() {
		return pointOfSaleCode;
	}

	public void setPointOfSaleCode(String pointOfSaleCode) {
		this.pointOfSaleCode = pointOfSaleCode;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public String getOwnerUuid() {
		return ownerUuid;
	}

	public void setOwnerUuid(String ownerUuid) {
		this.ownerUuid = ownerUuid;
	}

	public String getPayerUuid() {
		return payerUuid;
	}

	public void setPayerUuid(String payerUuid) {
		this.payerUuid = payerUuid;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getDebitHardwareFund() {
		return debitHardwareFund;
	}

	public void setDebitHardwareFund(int debitHardwareFund) {
		this.debitHardwareFund = debitHardwareFund;
	}

}
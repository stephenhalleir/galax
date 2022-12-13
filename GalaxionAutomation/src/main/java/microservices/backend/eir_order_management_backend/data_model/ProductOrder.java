package microservices.backend.eir_order_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import utilities.generic.database.MariaDBConnection;
import utilities.generic.time.Timestamp;

// this class represents a row in order management's product_order table
public class ProductOrder {

	private int id;
	private String externalId;
	private int agreedToTermsAndConditions;
	private int anonymous;
	private String reference;
	private int ownerId;
	private int payerId;
	private int channelId;
	private String pointOfSaleCode;
	private int documentId;
	private String status;
	private String order_type;
	private String salesType;
	private String ownerUuid;
	private String payerUuid;
	private int eventId;
	private int debitHardwareFund;
	private Date createdDate;

	// build an order based on a resultset
	// 	- select * from product_order where....
	public ProductOrder(ResultSet rs) {
		try {
			id = rs.getInt("id");
			externalId=rs.getString("external_id");
			agreedToTermsAndConditions = rs.getInt("has_agreed_terms_and_conditions");
			anonymous=rs.getInt("is_anonymous");
			reference=rs.getString("reference");
			ownerId=rs.getInt("owner_id");
			payerId=rs.getInt("payer_id");
			pointOfSaleCode=rs.getString("point_of_sale_code");
			documentId=rs.getInt("document_id");
			channelId=rs.getInt("channel_id");
			status=rs.getString("status");
			order_type=rs.getString("order_type");
			salesType=rs.getString("sales_type");
			ownerUuid=rs.getString("owner_uuid");
			payerUuid=rs.getString("payer_uuid");
			eventId=rs.getInt("event_id");
			debitHardwareFund=rs.getInt("debit_hardware_fund");
			createdDate=rs.getDate("created_date");
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

	public int getAgreedToTermsAndConditions() {
		return agreedToTermsAndConditions;
	}

	public void setAgreedToTermsAndConditions(int agreedToTermsAndConditions) {
		this.agreedToTermsAndConditions = agreedToTermsAndConditions;
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
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
	
	public String getExternalId() {
		return externalId;
	}


	public void setExternalId(String externalId) {
		this.externalId = externalId;
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


	public String getSalesType() {
		return salesType;
	}


	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}


	public int getDebitHardwareFund() {
		return debitHardwareFund;
	}


	public void setDebitHardwareFund(int debitHardwareFund) {
		this.debitHardwareFund = debitHardwareFund;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
	public String getCreatedDate(String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(createdDate);
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public static void main(String [] args) {
		
	}
	
}

package microservices.backend.eir_prospect_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prospect {

	private int id;
	private String status;
	private String uuid;
	private String pointOfSaleCode;
	private int anonymous;
	private String subscriptionAccountId;
	private String orderNumber;
	private String brand;
	private String channelCode;
	private String offerType;
	private int hasAgreedTermsAndConditions;
	private int maximumCartSubscriptions;
	private Date createdAt;
	private Date updatedAt;
	private int allowThirdParty;
	private String paymentUuid;
	private int amount;

	public Prospect() {

	}

	public Prospect(ResultSet rs) {
		try {
			id = rs.getInt("id");
			status = rs.getString("status");
			uuid = rs.getString("uuid");
			pointOfSaleCode = rs.getString("point_of_sale_code");
			anonymous = rs.getInt("anonymous");
			subscriptionAccountId = rs.getString("subscription_account_id");
			orderNumber = rs.getString("order_number");
			brand = rs.getString("brand");
			channelCode = rs.getString("channel_code");
			offerType = rs.getString("offer_type");
			hasAgreedTermsAndConditions = rs.getInt("has_agreed_terms_and_conditions");
			maximumCartSubscriptions = rs.getInt("maximum_cart_subscriptions");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			allowThirdParty = rs.getInt("allow_third_party");
			paymentUuid = rs.getString("payment_uuid");
			amount = rs.getInt("amount");
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPointOfSaleCode() {
		return pointOfSaleCode;
	}

	public void setPointOfSaleCode(String pointOfSaleCode) {
		this.pointOfSaleCode = pointOfSaleCode;
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public String getSubscriptionAccountId() {
		return subscriptionAccountId;
	}

	public void setSubscriptionAccountId(String subscriptionAccountId) {
		this.subscriptionAccountId = subscriptionAccountId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public int getHasAgreedTermsAndConditions() {
		return hasAgreedTermsAndConditions;
	}

	public void setHasAgreedTermsAndConditions(int hasAgreedTermsAndConditions) {
		this.hasAgreedTermsAndConditions = hasAgreedTermsAndConditions;
	}

	public int getMaximumCartSubscriptions() {
		return maximumCartSubscriptions;
	}

	public void setMaximumCartSubscriptions(int maximumCartSubscriptions) {
		this.maximumCartSubscriptions = maximumCartSubscriptions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getAllowThirdParty() {
		return allowThirdParty;
	}

	public void setAllowThirdParty(int allowThirdParty) {
		this.allowThirdParty = allowThirdParty;
	}

	public String getPaymentUuid() {
		return paymentUuid;
	}

	public void setPaymentUuid(String paymentUuid) {
		this.paymentUuid = paymentUuid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
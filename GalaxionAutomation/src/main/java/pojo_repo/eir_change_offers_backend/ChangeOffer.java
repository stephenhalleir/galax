package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeOffer {

	private String uuid;
	private String channel;
	private int contractRenewal;
	private int subscriptionId;
	private String brand;
	private String contractFileUuid;
	private String ePosReference;
	private String status;
	private String cartUuid;
	private int valid;
	private Date createdAt;
	private String createdBy;
	private Date updatedAt;
	private String channelGroup;
	private String paymentUuid;
	private int amount;
	private Date contractSentAt;

	public ChangeOffer() {

	}

	public ChangeOffer(ResultSet rs) {
		try {
			uuid = rs.getString("uuid");
			channel = rs.getString("channel");
			contractRenewal = rs.getInt("contract_renewal");
			subscriptionId = rs.getInt("subscription_id");
			brand = rs.getString("brand");
			contractFileUuid = rs.getString("contract_file_uuid");
			ePosReference = rs.getString("e_pos_reference");
			status = rs.getString("status");
			cartUuid = rs.getString("cart_uuid");
			valid = rs.getInt("valid");
			createdAt = rs.getDate("created_at");
			createdBy = rs.getString("created_by");
			updatedAt = rs.getDate("updated_at");
			channelGroup = rs.getString("channel_group");
			paymentUuid = rs.getString("payment_uuid");
			amount = rs.getInt("amount");
			contractSentAt = rs.getDate("contract_sent_at");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getContractRenewal() {
		return contractRenewal;
	}

	public void setContractRenewal(int contractRenewal) {
		this.contractRenewal = contractRenewal;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getContractFileUuid() {
		return contractFileUuid;
	}

	public void setContractFileUuid(String contractFileUuid) {
		this.contractFileUuid = contractFileUuid;
	}

	public String getEPosReference() {
		return ePosReference;
	}

	public void setEPosReference(String ePosReference) {
		this.ePosReference = ePosReference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCartUuid() {
		return cartUuid;
	}

	public void setCartUuid(String cartUuid) {
		this.cartUuid = cartUuid;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getChannelGroup() {
		return channelGroup;
	}

	public void setChannelGroup(String channelGroup) {
		this.channelGroup = channelGroup;
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

	public Date getContractSentAt() {
		return contractSentAt;
	}

	public void setContractSentAt(Date contractSentAt) {
		this.contractSentAt = contractSentAt;
	}

}
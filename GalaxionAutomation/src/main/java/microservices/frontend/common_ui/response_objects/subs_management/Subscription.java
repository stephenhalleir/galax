package microservices.frontend.common_ui.response_objects.subs_management;

import java.util.ArrayList;

/**
 * This class represents a subscription as returned by the GET subscriptions
 * call in the UI API
 * 
 * @author stephenhall
 *
 */

public class Subscription {
	private int id;
	private int orderId;
	private String orderReference;
	private String status;
	private String catalogOfferCode;
	private String userUuid;
	private String type;
	private String activatedAt;
	private String terminatedAt;
	private String createdAt;
	private String updatedAt;
	private String costCenter;
	private String tariffPlanCode;
	private String name;
	private String vip;
	private String nextBillingDateTime;
	private String coolOffAt;
	private int accountId;
	private ArrayList<String> discounts;
	private ArrayList<String> charges;

	public Subscription() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCatalogOfferCode() {
		return catalogOfferCode;
	}

	public void setCatalogOfferCode(String catalogOfferCode) {
		this.catalogOfferCode = catalogOfferCode;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(String activatedAt) {
		this.activatedAt = activatedAt;
	}

	public String getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(String terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getNextBillingDateTime() {
		return nextBillingDateTime;
	}

	public void setNextBillingDateTime(String nextBillingDateTime) {
		this.nextBillingDateTime = nextBillingDateTime;
	}

	public String getCoolOffAt() {
		return coolOffAt;
	}

	public void setCoolOffAt(String coolOffAt) {
		this.coolOffAt = coolOffAt;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public ArrayList<String> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(ArrayList<String> discounts) {
		this.discounts = discounts;
	}

	public ArrayList<String> getCharges() {
		return charges;
	}

	public void setCharges(ArrayList<String> charges) {
		this.charges = charges;
	}
}

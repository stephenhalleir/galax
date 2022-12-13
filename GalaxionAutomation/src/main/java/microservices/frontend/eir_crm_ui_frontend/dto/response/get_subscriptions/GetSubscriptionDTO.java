package microservices.frontend.eir_crm_ui_frontend.dto.response.get_subscriptions;

public class GetSubscriptionDTO {
	private int id;
	private int orderId;
	private String orderReference;
	private String status;
	private String catalogOfferCode;
	private String userUuid;
	private String type;
	private String activatedAt;
	private String createdAt;
	private String terminatedAt;
	private String updatedAt;
	private String costCenter;
	private String tariffPlanCode;
	private String name;
	private boolean vip;
	private String nextBillingDateTime;
	private String coolOffAt;
	private int accountId;
	private String[] discounts;
	private String[] charges;

	public GetSubscriptionDTO() {
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(String terminatedAt) {
		this.terminatedAt = terminatedAt;
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

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
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

	public String[] getDiscounts() {
		return discounts;
	}

	public void setDiscounts(String[] discounts) {
		this.discounts = discounts;
	}

	public String[] getCharges() {
		return charges;
	}

	public void setCharges(String[] charges) {
		this.charges = charges;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}

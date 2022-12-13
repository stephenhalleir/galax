package microservices.frontend.eir_crm_ui_frontend.dto.response.customer_search;

import java.util.List;

/**
 * This class represents a search result returned to the Eir CRM UI customer search
 * 
 * These are currently returned in a list to the UI
 * 
 * @author stephenhall 25/11/21
 *
 */
public class EirB2CSearchResultDTO {

	private int id;
	private String type;
	private String status;
	private int billingAccountId;
	private String brand;
	private String marketSegment;
	private String customerType;
	private String b2BAccountName;
	private String groupICID;
	private Owner owner;
	private String legacyReference;
	private String legacySystemName;
	private List<PrepaySubscriptionDTO> subscriptions;

	public EirB2CSearchResultDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBillingAccountId() {
		return billingAccountId;
	}

	public void setBillingAccountId(int billingAccountId) {
		this.billingAccountId = billingAccountId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMarketSegment() {
		return marketSegment;
	}

	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getB2BAccountName() {
		return b2BAccountName;
	}

	public void setB2BAccountName(String b2bAccountName) {
		b2BAccountName = b2bAccountName;
	}

	public String getGroupICID() {
		return groupICID;
	}

	public void setGroupICID(String groupICID) {
		this.groupICID = groupICID;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<PrepaySubscriptionDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<PrepaySubscriptionDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getLegacyReference() {
		return legacyReference;
	}

	public void setLegacyReference(String legacyReference) {
		this.legacyReference = legacyReference;
	}

	public String getLegacySystemName() {
		return legacySystemName;
	}

	public void setLegacySystemName(String legacySystemName) {
		this.legacySystemName = legacySystemName;
	}
}

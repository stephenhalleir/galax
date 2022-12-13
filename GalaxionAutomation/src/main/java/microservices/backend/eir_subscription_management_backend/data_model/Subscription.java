package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subscription {

	private int id;
	private Date creationDateTime;
	private String status;
	private String orderReference;
	private String type;
	private int catalogOfferID;
	private int orderID;
	private String userUuid;
	private Date activatedAt;
	private Date terminatedAt;
	private int contractID;
	private String tariffPlanCode;
	private String costCenter;
	private String name;
	private int isVip;
	private String catalogOfferCode;
	private int accountId;
	
	
	public Subscription() {
		
	}
	
	public Subscription(ResultSet rs) {
		try {
			id=rs.getInt("id");
			creationDateTime=rs.getDate("created_at");
			status=rs.getString("status");
			catalogOfferID=rs.getInt("catalog_offer_id");
			orderID=rs.getInt("order_id");
			type=rs.getString("type");
			userUuid=rs.getString("user_uuid");
			activatedAt=rs.getDate("activated_at");
			terminatedAt=rs.getDate("terminated_at");
			contractID=rs.getInt("contract_id");
			tariffPlanCode=rs.getString("tariff_plan_code");
			costCenter=rs.getString("cost_center");
			name=rs.getString("name");
			isVip=rs.getInt("is_vip");
			catalogOfferCode=rs.getString("catalog_offer_code");
			orderReference=rs.getString("order_reference");
			accountId=rs.getInt("account_id");
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

	public int getCatalogOfferID() {
		return catalogOfferID;
	}

	public void setCatalogOfferID(int catalogOfferID) {
		this.catalogOfferID = catalogOfferID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public Date getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(Date activatedAt) {
		this.activatedAt = activatedAt;
	}

	public Date getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(Date terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public String getCatalogOfferCode() {
		return catalogOfferCode;
	}

	public void setCatalogOfferCode(String catalogOfferCode) {
		this.catalogOfferCode = catalogOfferCode;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
}

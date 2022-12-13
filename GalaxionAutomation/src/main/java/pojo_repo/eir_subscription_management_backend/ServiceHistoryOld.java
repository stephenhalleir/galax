package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceHistoryOld {

	private int id;
	private int rev;
	private int revtype;
	private Date creationDateTime;
	private Date startDateTime;
	private String status;
	private int catalogSubOfferId;
	private String domain;
	private String name;
	private int orderServiceId;
	private int subscriptionId;

	public ServiceHistoryOld() {

	}

	public ServiceHistoryOld(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rev = rs.getInt("rev");
			revtype = rs.getInt("revtype");
			creationDateTime = rs.getDate("creation_date_time");
			startDateTime = rs.getDate("start_date_time");
			status = rs.getString("status");
			catalogSubOfferId = rs.getInt("catalog_sub_offer_id");
			domain = rs.getString("domain");
			name = rs.getString("name");
			orderServiceId = rs.getInt("order_service_id");
			subscriptionId = rs.getInt("subscription_id");
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

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public int getRevtype() {
		return revtype;
	}

	public void setRevtype(int revtype) {
		this.revtype = revtype;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCatalogSubOfferId() {
		return catalogSubOfferId;
	}

	public void setCatalogSubOfferId(int catalogSubOfferId) {
		this.catalogSubOfferId = catalogSubOfferId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(int orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}
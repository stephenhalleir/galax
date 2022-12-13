package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionHistoryOld {

	private int id;
	private int rev;
	private int revtype;
	private Date creationDateTime;
	private Date startDateTime;
	private String status;
	private int catalogOfferId;
	private int orderSubscriptionId;
	private String type;

	public SubscriptionHistoryOld() {

	}

	public SubscriptionHistoryOld(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rev = rs.getInt("rev");
			revtype = rs.getInt("revtype");
			creationDateTime = rs.getDate("creation_date_time");
			startDateTime = rs.getDate("start_date_time");
			status = rs.getString("status");
			catalogOfferId = rs.getInt("catalog_offer_id");
			orderSubscriptionId = rs.getInt("order_subscription_id");
			type = rs.getString("type");
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

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}

	public int getOrderSubscriptionId() {
		return orderSubscriptionId;
	}

	public void setOrderSubscriptionId(int orderSubscriptionId) {
		this.orderSubscriptionId = orderSubscriptionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
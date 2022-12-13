package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningReference {

	private int id;
	private String reference;
	private int offerId;
	private int serviceId;
	private int itemId;

	public ProvisioningReference() {

	}

	public ProvisioningReference(ResultSet rs) {
		try {
			id = rs.getInt("id");
			reference = rs.getString("reference");
			offerId = rs.getInt("offer_id");
			serviceId = rs.getInt("service_id");
			itemId = rs.getInt("item_id");
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
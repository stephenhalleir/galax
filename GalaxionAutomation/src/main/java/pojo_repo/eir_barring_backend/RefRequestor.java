package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefRequestor {

	private int id;
	private String brand;
	private String requestorRole;
	private String subscriptionType;

	public RefRequestor() {

	}

	public RefRequestor(ResultSet rs) {
		try {
			id = rs.getInt("id");
			brand = rs.getString("brand");
			requestorRole = rs.getString("requestor_role");
			subscriptionType = rs.getString("subscription_type");
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRequestorRole() {
		return requestorRole;
	}

	public void setRequestorRole(String requestorRole) {
		this.requestorRole = requestorRole;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

}
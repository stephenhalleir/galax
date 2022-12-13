package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarringResponse {

	private int id;
	private int barSubscriptionId;
	private int serviceId;
	private String code;

	public BarringResponse() {

	}

	public BarringResponse(ResultSet rs) {
		try {
			id = rs.getInt("id");
			barSubscriptionId = rs.getInt("bar_subscription_id");
			serviceId = rs.getInt("service_id");
			code = rs.getString("code");
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

	public int getBarSubscriptionId() {
		return barSubscriptionId;
	}

	public void setBarSubscriptionId(int barSubscriptionId) {
		this.barSubscriptionId = barSubscriptionId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
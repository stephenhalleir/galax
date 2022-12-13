package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceHistory {

	private int id;
	private int serviceId;
	private Date activatedAt;

	public ServiceHistory() {

	}

	public ServiceHistory(ResultSet rs) {
		try {
			id = rs.getInt("id");
			serviceId = rs.getInt("service_id");
			activatedAt = rs.getDate("activated_at");
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

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Date getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(Date activatedAt) {
		this.activatedAt = activatedAt;
	}

}
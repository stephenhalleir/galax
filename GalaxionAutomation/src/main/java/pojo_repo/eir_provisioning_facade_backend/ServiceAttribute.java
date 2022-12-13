package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceAttribute {

	private int id;
	private int serviceId;

	public ServiceAttribute() {

	}

	public ServiceAttribute(ResultSet rs) {
		try {
			id = rs.getInt("id");
			serviceId = rs.getInt("service_id");
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

}
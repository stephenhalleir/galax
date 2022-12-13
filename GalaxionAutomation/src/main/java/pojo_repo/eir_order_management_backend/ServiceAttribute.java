package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceAttribute {

	private int serviceId;

	public ServiceAttribute() {

	}

	public ServiceAttribute(ResultSet rs) {
		try {
			serviceId = rs.getInt("service_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

}
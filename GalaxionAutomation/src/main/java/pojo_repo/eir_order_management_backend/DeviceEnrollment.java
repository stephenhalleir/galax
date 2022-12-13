package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceEnrollment {

	private int id;
	private String manufacturer;
	private String enrollmentId;

	public DeviceEnrollment() {

	}

	public DeviceEnrollment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			manufacturer = rs.getString("manufacturer");
			enrollmentId = rs.getString("enrollment_id");
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

}
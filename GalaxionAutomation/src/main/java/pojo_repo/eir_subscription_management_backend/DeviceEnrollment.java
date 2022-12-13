package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceEnrollment {

private int id;
private String deviceEnrollmentRef;
private String deviceEnrollmentProvider;

public DeviceEnrollment() {

}

public DeviceEnrollment(ResultSet rs) {
try {
	id = rs.getInt("id");
	deviceEnrollmentRef = rs.getString("device_enrollment_ref");
	deviceEnrollmentProvider = rs.getString("device_enrollment_provider");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public String getDeviceEnrollmentRef() {
 	return deviceEnrollmentRef;
}
public void setDeviceEnrollmentRef(String deviceEnrollmentRef) {
 	 this.deviceEnrollmentRef=deviceEnrollmentRef;
}
public String getDeviceEnrollmentProvider() {
 	return deviceEnrollmentProvider;
}
public void setDeviceEnrollmentProvider(String deviceEnrollmentProvider) {
 	 this.deviceEnrollmentProvider=deviceEnrollmentProvider;
}

}
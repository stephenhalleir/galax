package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUsageQuota {

private int serviceId;

public ServiceUsageQuota() {

}

public ServiceUsageQuota(ResultSet rs) {
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
 	 this.serviceId=serviceId;
}

}
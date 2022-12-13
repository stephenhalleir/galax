package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSimCard {

private int serviceId;

public ServiceSimCard() {

}

public ServiceSimCard(ResultSet rs) {
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
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NetworkElementAttribute {

private int networkElementId;

public NetworkElementAttribute() {

}

public NetworkElementAttribute(ResultSet rs) {
try {
	networkElementId = rs.getInt("network_element_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getNetworkElementId() {
 	return networkElementId;
}
public void setNetworkElementId(int networkElementId) {
 	 this.networkElementId=networkElementId;
}

}
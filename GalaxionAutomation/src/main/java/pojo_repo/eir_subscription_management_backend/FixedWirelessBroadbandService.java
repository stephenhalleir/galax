package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FixedWirelessBroadbandService {

private int id;

public FixedWirelessBroadbandService() {

}

public FixedWirelessBroadbandService(ResultSet rs) {
try {
	id = rs.getInt("id");
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

}
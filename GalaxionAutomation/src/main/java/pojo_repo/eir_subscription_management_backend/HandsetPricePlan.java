package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandsetPricePlan {

private int handsetId;

public HandsetPricePlan() {

}

public HandsetPricePlan(ResultSet rs) {
try {
	handsetId = rs.getInt("handset_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getHandsetId() {
 	return handsetId;
}
public void setHandsetId(int handsetId) {
 	 this.handsetId=handsetId;
}

}
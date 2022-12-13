package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChargePricePlan {

private int chargeId;

public ChargePricePlan() {

}

public ChargePricePlan(ResultSet rs) {
try {
	chargeId = rs.getInt("charge_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getChargeId() {
 	return chargeId;
}
public void setChargeId(int chargeId) {
 	 this.chargeId=chargeId;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimCardPricePlan {

private int simCardId;

public SimCardPricePlan() {

}

public SimCardPricePlan(ResultSet rs) {
try {
	simCardId = rs.getInt("sim_card_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getSimCardId() {
 	return simCardId;
}
public void setSimCardId(int simCardId) {
 	 this.simCardId=simCardId;
}

}
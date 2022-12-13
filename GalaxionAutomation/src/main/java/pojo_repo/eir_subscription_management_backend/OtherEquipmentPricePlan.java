package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtherEquipmentPricePlan {

private int otherEquipmentId;

public OtherEquipmentPricePlan() {

}

public OtherEquipmentPricePlan(ResultSet rs) {
try {
	otherEquipmentId = rs.getInt("other_equipment_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getOtherEquipmentId() {
 	return otherEquipmentId;
}
public void setOtherEquipmentId(int otherEquipmentId) {
 	 this.otherEquipmentId=otherEquipmentId;
}

}
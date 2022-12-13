package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionDiscount {

private int discountId;

public SubscriptionDiscount() {

}

public SubscriptionDiscount(ResultSet rs) {
try {
	discountId = rs.getInt("discount_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getDiscountId() {
 	return discountId;
}
public void setDiscountId(int discountId) {
 	 this.discountId=discountId;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResidentialGatewayPricePlan {

private int residentialGatewayId;

public ResidentialGatewayPricePlan() {

}

public ResidentialGatewayPricePlan(ResultSet rs) {
try {
	residentialGatewayId = rs.getInt("residential_gateway_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getResidentialGatewayId() {
 	return residentialGatewayId;
}
public void setResidentialGatewayId(int residentialGatewayId) {
 	 this.residentialGatewayId=residentialGatewayId;
}

}
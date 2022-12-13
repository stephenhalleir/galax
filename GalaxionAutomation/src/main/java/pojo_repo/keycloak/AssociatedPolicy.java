package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssociatedPolicy {

private String policyId;

public AssociatedPolicy() {

}

public AssociatedPolicy(ResultSet rs) {
try {
	policyId = rs.getString("POLICY_ID");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getPolicyId() {
 	return policyId;
}
public void setPolicyId(String policyId) {
 	 this.policyId=policyId;
}

}
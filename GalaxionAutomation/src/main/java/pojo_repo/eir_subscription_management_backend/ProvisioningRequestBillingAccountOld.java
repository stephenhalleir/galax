package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningRequestBillingAccountOld {

private int id;
private int hierarchicalEntityId;
private String newStatus;

public ProvisioningRequestBillingAccountOld() {

}

public ProvisioningRequestBillingAccountOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	hierarchicalEntityId = rs.getInt("hierarchical_entity_id");
	newStatus = rs.getString("new_status");
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
public int getHierarchicalEntityId() {
 	return hierarchicalEntityId;
}
public void setHierarchicalEntityId(int hierarchicalEntityId) {
 	 this.hierarchicalEntityId=hierarchicalEntityId;
}
public String getNewStatus() {
 	return newStatus;
}
public void setNewStatus(String newStatus) {
 	 this.newStatus=newStatus;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractRenewalRequest {

private int id;
private int contractId;
private Date oldEndAt;
private Date newEndAt;
private int chargeId;
private String reason;

public ContractRenewalRequest() {

}

public ContractRenewalRequest(ResultSet rs) {
try {
	id = rs.getInt("id");
	contractId = rs.getInt("contract_id");
	oldEndAt = rs.getDate("old_end_at");
	newEndAt = rs.getDate("new_end_at");
	chargeId = rs.getInt("charge_id");
	reason = rs.getString("reason");
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
public int getContractId() {
 	return contractId;
}
public void setContractId(int contractId) {
 	 this.contractId=contractId;
}
public Date getOldEndAt() {
 	return oldEndAt;
}
public void setOldEndAt(Date oldEndAt) {
 	 this.oldEndAt=oldEndAt;
}
public Date getNewEndAt() {
 	return newEndAt;
}
public void setNewEndAt(Date newEndAt) {
 	 this.newEndAt=newEndAt;
}
public int getChargeId() {
 	return chargeId;
}
public void setChargeId(int chargeId) {
 	 this.chargeId=chargeId;
}
public String getReason() {
 	return reason;
}
public void setReason(String reason) {
 	 this.reason=reason;
}

}
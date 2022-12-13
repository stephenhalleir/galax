package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Flow {

private int id;
private int billingAccountId;
private Date debtStartDate;
private int firstDebt;
private String name;
private String status;
private int revertOnFreeze;
private String preErrorStatus;
private Date createdAt;

public Flow() {

}

public Flow(ResultSet rs) {
try {
	id = rs.getInt("id");
	billingAccountId = rs.getInt("billing_account_id");
	debtStartDate = rs.getDate("debt_start_DATE");
	firstDebt = rs.getInt("first_debt");
	name = rs.getString("name");
	status = rs.getString("status");
	revertOnFreeze = rs.getInt("revert_on_freeze");
	preErrorStatus = rs.getString("pre_error_status");
	createdAt = rs.getDate("created_at");
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
public int getBillingAccountId() {
 	return billingAccountId;
}
public void setBillingAccountId(int billingAccountId) {
 	 this.billingAccountId=billingAccountId;
}
public Date getDebtStartDate() {
 	return debtStartDate;
}
public void setDebtStartDate(Date debtStartDate) {
 	 this.debtStartDate=debtStartDate;
}
public int getFirstDebt() {
 	return firstDebt;
}
public void setFirstDebt(int firstDebt) {
 	 this.firstDebt=firstDebt;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public int getRevertOnFreeze() {
 	return revertOnFreeze;
}
public void setRevertOnFreeze(int revertOnFreeze) {
 	 this.revertOnFreeze=revertOnFreeze;
}
public String getPreErrorStatus() {
 	return preErrorStatus;
}
public void setPreErrorStatus(String preErrorStatus) {
 	 this.preErrorStatus=preErrorStatus;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
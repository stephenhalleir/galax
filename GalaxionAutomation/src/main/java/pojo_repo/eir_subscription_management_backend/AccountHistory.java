package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountHistory {

private int id;
private int rev;
private int revtype;
private String status;
private int billingCycleId;

public AccountHistory() {

}

public AccountHistory(ResultSet rs) {
try {
	id = rs.getInt("id");
	rev = rs.getInt("rev");
	revtype = rs.getInt("revtype");
	status = rs.getString("status");
	billingCycleId = rs.getInt("billing_cycle_id");
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
public int getRev() {
 	return rev;
}
public void setRev(int rev) {
 	 this.rev=rev;
}
public int getRevtype() {
 	return revtype;
}
public void setRevtype(int revtype) {
 	 this.revtype=revtype;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public int getBillingCycleId() {
 	return billingCycleId;
}
public void setBillingCycleId(int billingCycleId) {
 	 this.billingCycleId=billingCycleId;
}

}
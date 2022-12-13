package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSubscriptionHistory {

private int id;
private int rev;
private int revtype;
private int accountId;

public AccountSubscriptionHistory() {

}

public AccountSubscriptionHistory(ResultSet rs) {
try {
	id = rs.getInt("id");
	rev = rs.getInt("rev");
	revtype = rs.getInt("revtype");
	accountId = rs.getInt("account_id");
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
public int getAccountId() {
 	return accountId;
}
public void setAccountId(int accountId) {
 	 this.accountId=accountId;
}

}
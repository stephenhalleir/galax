package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountItemOld {

private int id;
private Date creationDateTime;
private Date startDateTime;
private int accountId;

public AccountItemOld() {

}

public AccountItemOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	creationDateTime = rs.getDate("creation_date_time");
	startDateTime = rs.getDate("start_date_time");
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
public Date getCreationDateTime() {
 	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
 	 this.creationDateTime=creationDateTime;
}
public Date getStartDateTime() {
 	return startDateTime;
}
public void setStartDateTime(Date startDateTime) {
 	 this.startDateTime=startDateTime;
}
public int getAccountId() {
 	return accountId;
}
public void setAccountId(int accountId) {
 	 this.accountId=accountId;
}

}
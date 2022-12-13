package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestAccountChange {

private int id;
private int accountId;
private Date scheduledAt;
private String status;
private String errorCode;

public RequestAccountChange() {

}

public RequestAccountChange(ResultSet rs) {
try {
	id = rs.getInt("id");
	accountId = rs.getInt("account_id");
	scheduledAt = rs.getDate("scheduled_at");
	status = rs.getString("status");
	errorCode = rs.getString("error_code");
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
public int getAccountId() {
 	return accountId;
}
public void setAccountId(int accountId) {
 	 this.accountId=accountId;
}
public Date getScheduledAt() {
 	return scheduledAt;
}
public void setScheduledAt(Date scheduledAt) {
 	 this.scheduledAt=scheduledAt;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public String getErrorCode() {
 	return errorCode;
}
public void setErrorCode(String errorCode) {
 	 this.errorCode=errorCode;
}

}
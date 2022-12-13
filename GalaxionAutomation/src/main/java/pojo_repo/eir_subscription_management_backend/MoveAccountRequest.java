package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveAccountRequest {

private int id;
private int accountId;
private int recipientParentId;
private int formerParentId;

public MoveAccountRequest() {

}

public MoveAccountRequest(ResultSet rs) {
try {
	id = rs.getInt("id");
	accountId = rs.getInt("account_id");
	recipientParentId = rs.getInt("recipient_parent_id");
	formerParentId = rs.getInt("former_parent_id");
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
public int getRecipientParentId() {
 	return recipientParentId;
}
public void setRecipientParentId(int recipientParentId) {
 	 this.recipientParentId=recipientParentId;
}
public int getFormerParentId() {
 	return formerParentId;
}
public void setFormerParentId(int formerParentId) {
 	 this.formerParentId=formerParentId;
}

}
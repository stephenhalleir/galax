package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountContact {

private int id;
private int accountId;
private String uuid;
private int typeId;
private Date createdAt;

public AccountContact() {

}

public AccountContact(ResultSet rs) {
try {
	id = rs.getInt("id");
	accountId = rs.getInt("account_id");
	uuid = rs.getString("uuid");
	typeId = rs.getInt("type_id");
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
public int getAccountId() {
 	return accountId;
}
public void setAccountId(int accountId) {
 	 this.accountId=accountId;
}
public String getUuid() {
 	return uuid;
}
public void setUuid(String uuid) {
 	 this.uuid=uuid;
}
public int getTypeId() {
 	return typeId;
}
public void setTypeId(int typeId) {
 	 this.typeId=typeId;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
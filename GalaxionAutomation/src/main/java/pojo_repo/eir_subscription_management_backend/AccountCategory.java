package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountCategory {

private int accountId;
private String type;

public AccountCategory() {

}

public AccountCategory(ResultSet rs) {
try {
	accountId = rs.getInt("account_id");
	type = rs.getString("type");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getAccountId() {
 	return accountId;
}
public void setAccountId(int accountId) {
 	 this.accountId=accountId;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}

}
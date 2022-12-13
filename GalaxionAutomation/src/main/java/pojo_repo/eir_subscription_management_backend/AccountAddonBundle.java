package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountAddonBundle {

private int accountId;

public AccountAddonBundle() {

}

public AccountAddonBundle(ResultSet rs) {
try {
	accountId = rs.getInt("account_id");
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

}
package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowDefinition {

private String name;
private String accountType;
private String customerType;

public FlowDefinition() {

}

public FlowDefinition(ResultSet rs) {
try {
	name = rs.getString("name");
	accountType = rs.getString("account_type");
	customerType = rs.getString("customer_type");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getAccountType() {
 	return accountType;
}
public void setAccountType(String accountType) {
 	 this.accountType=accountType;
}
public String getCustomerType() {
 	return customerType;
}
public void setCustomerType(String customerType) {
 	 this.customerType=customerType;
}

}
package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefBillingCycleBck {

private int id;
private String name;
private int dayOfMonth;
private String customerType;
private String status;

public RefBillingCycleBck() {

}

public RefBillingCycleBck(ResultSet rs) {
try {
	id = rs.getInt("id");
	name = rs.getString("name");
	dayOfMonth = rs.getInt("day_of_month");
	customerType = rs.getString("customer_type");
	status = rs.getString("status");
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
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public int getDayOfMonth() {
 	return dayOfMonth;
}
public void setDayOfMonth(int dayOfMonth) {
 	 this.dayOfMonth=dayOfMonth;
}
public String getCustomerType() {
 	return customerType;
}
public void setCustomerType(String customerType) {
 	 this.customerType=customerType;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}

}
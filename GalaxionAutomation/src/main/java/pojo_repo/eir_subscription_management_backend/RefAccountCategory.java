package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefAccountCategory {

private String type;
private String value;

public RefAccountCategory() {

}

public RefAccountCategory(ResultSet rs) {
try {
	type = rs.getString("type");
	value = rs.getString("value");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getValue() {
 	return value;
}
public void setValue(String value) {
 	 this.value=value;
}

}
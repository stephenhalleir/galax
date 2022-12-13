package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefContactType {

private int id;
private String name;

public RefContactType() {

}

public RefContactType(ResultSet rs) {
try {
	id = rs.getInt("id");
	name = rs.getString("name");
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

}
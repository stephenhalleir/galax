package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompositeRole {

private String composite;

public CompositeRole() {

}

public CompositeRole(ResultSet rs) {
try {
	composite = rs.getString("COMPOSITE");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getComposite() {
 	return composite;
}
public void setComposite(String composite) {
 	 this.composite=composite;
}

}
package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientSessionNote {

private String name;
private String value;

public ClientSessionNote() {

}

public ClientSessionNote(ResultSet rs) {
try {
	name = rs.getString("NAME");
	value = rs.getString("VALUE");
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
public String getValue() {
 	return value;
}
public void setValue(String value) {
 	 this.value=value;
}

}
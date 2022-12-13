package pojo_repo.keycloak;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentConfig {

private String id;
private String componentId;
private String name;

public ComponentConfig() {

}

public ComponentConfig(ResultSet rs) {
try {
	id = rs.getString("ID");
	componentId = rs.getString("COMPONENT_ID");
	name = rs.getString("NAME");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getId() {
 	return id;
}
public void setId(String id) {
 	 this.id=id;
}
public String getComponentId() {
 	return componentId;
}
public void setComponentId(String componentId) {
 	 this.componentId=componentId;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}

}
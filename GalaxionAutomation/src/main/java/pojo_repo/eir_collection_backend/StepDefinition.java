package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StepDefinition {

private String name;
private String description;

public StepDefinition() {

}

public StepDefinition(ResultSet rs) {
try {
	name = rs.getString("name");
	description = rs.getString("description");
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
public String getDescription() {
 	return description;
}
public void setDescription(String description) {
 	 this.description=description;
}

}
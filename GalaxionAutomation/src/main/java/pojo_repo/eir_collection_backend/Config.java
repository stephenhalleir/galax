package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Config {

private String name;

public Config() {

}

public Config(ResultSet rs) {
try {
	name = rs.getString("name");
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

}
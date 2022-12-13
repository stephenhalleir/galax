package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionBatch {

private String type;

public ActionBatch() {

}

public ActionBatch(ResultSet rs) {
try {
	type = rs.getString("type");
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

}
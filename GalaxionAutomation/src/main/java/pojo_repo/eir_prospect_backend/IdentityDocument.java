package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdentityDocument {

private int oldId;
private String number;
private String type;

public IdentityDocument() {

}

public IdentityDocument(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	number = rs.getString("number");
	type = rs.getString("type");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getOldId() {
 	return oldId;
}
public void setOldId(int oldId) {
 	 this.oldId=oldId;
}
public String getNumber() {
 	return number;
}
public void setNumber(String number) {
 	 this.number=number;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}

}
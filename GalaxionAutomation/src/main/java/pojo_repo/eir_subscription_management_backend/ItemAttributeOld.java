package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemAttributeOld {

private int attributeId;

public ItemAttributeOld() {

}

public ItemAttributeOld(ResultSet rs) {
try {
	attributeId = rs.getInt("attribute_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getAttributeId() {
 	return attributeId;
}
public void setAttributeId(int attributeId) {
 	 this.attributeId=attributeId;
}

}
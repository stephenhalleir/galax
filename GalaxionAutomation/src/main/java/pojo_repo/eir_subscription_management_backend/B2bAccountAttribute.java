package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class B2bAccountAttribute {

private int id;
private int b2bAccountId;
private String attributeKey;
private String attributeValue;
private Date createdAt;

public B2bAccountAttribute() {

}

public B2bAccountAttribute(ResultSet rs) {
try {
	id = rs.getInt("id");
	b2bAccountId = rs.getInt("b2b_account_id");
	attributeKey = rs.getString("attribute_key");
	attributeValue = rs.getString("attribute_value");
	createdAt = rs.getDate("created_at");
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
public int getB2bAccountId() {
 	return b2bAccountId;
}
public void setB2bAccountId(int b2bAccountId) {
 	 this.b2bAccountId=b2bAccountId;
}
public String getAttributeKey() {
 	return attributeKey;
}
public void setAttributeKey(String attributeKey) {
 	 this.attributeKey=attributeKey;
}
public String getAttributeValue() {
 	return attributeValue;
}
public void setAttributeValue(String attributeValue) {
 	 this.attributeValue=attributeValue;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
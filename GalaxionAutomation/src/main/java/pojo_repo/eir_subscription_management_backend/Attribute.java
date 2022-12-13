package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attribute {

private int id;
private Date createdAt;
private Date updatedAt;
private int catalogDynamicAttributeId;
private String name;
private String type;

public Attribute() {

}

public Attribute(ResultSet rs) {
try {
	id = rs.getInt("id");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	catalogDynamicAttributeId = rs.getInt("catalog_dynamic_attribute_id");
	name = rs.getString("name");
	type = rs.getString("type");
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
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}
public Date getUpdatedAt() {
 	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
 	 this.updatedAt=updatedAt;
}
public int getCatalogDynamicAttributeId() {
 	return catalogDynamicAttributeId;
}
public void setCatalogDynamicAttributeId(int catalogDynamicAttributeId) {
 	 this.catalogDynamicAttributeId=catalogDynamicAttributeId;
}
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}

}
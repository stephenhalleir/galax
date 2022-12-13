package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeOld {

private int id;
private Date creationDateTime;
private Date startDateTime;
private int catalogDynamicAttributeId;
private String name;

public AttributeOld() {

}

public AttributeOld(ResultSet rs) {
try {
	id = rs.getInt("id");
	creationDateTime = rs.getDate("creation_date_time");
	startDateTime = rs.getDate("start_date_time");
	catalogDynamicAttributeId = rs.getInt("catalog_dynamic_attribute_id");
	name = rs.getString("name");
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
public Date getCreationDateTime() {
 	return creationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
 	 this.creationDateTime=creationDateTime;
}
public Date getStartDateTime() {
 	return startDateTime;
}
public void setStartDateTime(Date startDateTime) {
 	 this.startDateTime=startDateTime;
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

}
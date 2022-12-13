package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Company {

private int id;
private String name;
private String registrationNumber;
private Date startAt;
private String type;
private String legalEntityName;
private String legalFormName;
private String industryName;

public Company() {

}

public Company(ResultSet rs) {
try {
	id = rs.getInt("id");
	name = rs.getString("name");
	registrationNumber = rs.getString("registration_number");
	startAt = rs.getDate("start_at");
	type = rs.getString("type");
	legalEntityName = rs.getString("legal_entity_name");
	legalFormName = rs.getString("legal_form_name");
	industryName = rs.getString("industry_name");
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
public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getRegistrationNumber() {
 	return registrationNumber;
}
public void setRegistrationNumber(String registrationNumber) {
 	 this.registrationNumber=registrationNumber;
}
public Date getStartAt() {
 	return startAt;
}
public void setStartAt(Date startAt) {
 	 this.startAt=startAt;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getLegalEntityName() {
 	return legalEntityName;
}
public void setLegalEntityName(String legalEntityName) {
 	 this.legalEntityName=legalEntityName;
}
public String getLegalFormName() {
 	return legalFormName;
}
public void setLegalFormName(String legalFormName) {
 	 this.legalFormName=legalFormName;
}
public String getIndustryName() {
 	return industryName;
}
public void setIndustryName(String industryName) {
 	 this.industryName=industryName;
}

}
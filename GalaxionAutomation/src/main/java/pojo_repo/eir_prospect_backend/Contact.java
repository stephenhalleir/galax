package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact {

private int oldId;
private String email;
private String mobileNumber;
private String firstName;
private String lastName;
private Date birthDate;
private String contactUuid;

public Contact() {

}

public Contact(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	email = rs.getString("email");
	mobileNumber = rs.getString("mobile_number");
	firstName = rs.getString("first_name");
	lastName = rs.getString("last_name");
	birthDate = rs.getDate("birth_date");
	contactUuid = rs.getString("contact_uuid");
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
public String getEmail() {
 	return email;
}
public void setEmail(String email) {
 	 this.email=email;
}
public String getMobileNumber() {
 	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
 	 this.mobileNumber=mobileNumber;
}
public String getFirstName() {
 	return firstName;
}
public void setFirstName(String firstName) {
 	 this.firstName=firstName;
}
public String getLastName() {
 	return lastName;
}
public void setLastName(String lastName) {
 	 this.lastName=lastName;
}
public Date getBirthDate() {
 	return birthDate;
}
public void setBirthDate(Date birthDate) {
 	 this.birthDate=birthDate;
}
public String getContactUuid() {
 	return contactUuid;
}
public void setContactUuid(String contactUuid) {
 	 this.contactUuid=contactUuid;
}

}
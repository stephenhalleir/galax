package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxDetails {

private int id;
private String category;
private String number;
private String certNumber;
private Date certStartedAt;
private Date certExpiredAt;
private String exemptionDocumentType;
private Date exemptionStartedAt;
private Date exemptionEndedAt;
private Date createdAt;

public TaxDetails() {

}

public TaxDetails(ResultSet rs) {
try {
	id = rs.getInt("id");
	category = rs.getString("category");
	number = rs.getString("number");
	certNumber = rs.getString("cert_number");
	certStartedAt = rs.getDate("cert_started_at");
	certExpiredAt = rs.getDate("cert_expired_at");
	exemptionDocumentType = rs.getString("exemption_document_type");
	exemptionStartedAt = rs.getDate("exemption_started_at");
	exemptionEndedAt = rs.getDate("exemption_ended_at");
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
public String getCategory() {
 	return category;
}
public void setCategory(String category) {
 	 this.category=category;
}
public String getNumber() {
 	return number;
}
public void setNumber(String number) {
 	 this.number=number;
}
public String getCertNumber() {
 	return certNumber;
}
public void setCertNumber(String certNumber) {
 	 this.certNumber=certNumber;
}
public Date getCertStartedAt() {
 	return certStartedAt;
}
public void setCertStartedAt(Date certStartedAt) {
 	 this.certStartedAt=certStartedAt;
}
public Date getCertExpiredAt() {
 	return certExpiredAt;
}
public void setCertExpiredAt(Date certExpiredAt) {
 	 this.certExpiredAt=certExpiredAt;
}
public String getExemptionDocumentType() {
 	return exemptionDocumentType;
}
public void setExemptionDocumentType(String exemptionDocumentType) {
 	 this.exemptionDocumentType=exemptionDocumentType;
}
public Date getExemptionStartedAt() {
 	return exemptionStartedAt;
}
public void setExemptionStartedAt(Date exemptionStartedAt) {
 	 this.exemptionStartedAt=exemptionStartedAt;
}
public Date getExemptionEndedAt() {
 	return exemptionEndedAt;
}
public void setExemptionEndedAt(Date exemptionEndedAt) {
 	 this.exemptionEndedAt=exemptionEndedAt;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}

}
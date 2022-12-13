package microservices.backend.eir_credit_score_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditScore {

private int id;
private String creditScore;
private String occupation;
private String residentialStatus;
private String prospectUuid;

public CreditScore() {

}

public CreditScore(ResultSet rs) {
try {
	id = rs.getInt("id");
	creditScore = rs.getString("credit_score");
	occupation = rs.getString("occupation");
	residentialStatus = rs.getString("residential_status");
	prospectUuid = rs.getString("prospect_uuid");
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
public String getCreditScore() {
 	return creditScore;
}
public void setCreditScore(String creditScore) {
 	 this.creditScore=creditScore;
}
public String getOccupation() {
 	return occupation;
}
public void setOccupation(String occupation) {
 	 this.occupation=occupation;
}
public String getResidentialStatus() {
 	return residentialStatus;
}
public void setResidentialStatus(String residentialStatus) {
 	 this.residentialStatus=residentialStatus;
}
public String getProspectUuid() {
 	return prospectUuid;
}
public void setProspectUuid(String prospectUuid) {
 	 this.prospectUuid=prospectUuid;
}

}
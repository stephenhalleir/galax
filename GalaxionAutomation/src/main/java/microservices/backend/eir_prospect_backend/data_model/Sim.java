package microservices.backend.eir_prospect_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sim {

private int oldId;
private int catalogId;
private String iccid;
private String msisdn;
private int id;

public Sim() {

}

public Sim(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	catalogId = rs.getInt("catalog_id");
	iccid = rs.getString("iccid");
	msisdn = rs.getString("msisdn");
	id = rs.getInt("id");
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
public int getCatalogId() {
 	return catalogId;
}
public void setCatalogId(int catalogId) {
 	 this.catalogId=catalogId;
}
public String getIccid() {
 	return iccid;
}
public void setIccid(String iccid) {
 	 this.iccid=iccid;
}
public String getMsisdn() {
 	return msisdn;
}
public void setMsisdn(String msisdn) {
 	 this.msisdn=msisdn;
}
public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}

}
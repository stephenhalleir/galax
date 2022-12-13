package microservices.backend.eir_carts_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipment {

private int id;
private String catalogCode;
private String imei;

public Equipment() {

}

public Equipment(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogCode = rs.getString("catalog_code");
	imei = rs.getString("imei");
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
public String getCatalogCode() {
 	return catalogCode;
}
public void setCatalogCode(String catalogCode) {
 	 this.catalogCode=catalogCode;
}
public String getImei() {
 	return imei;
}
public void setImei(String imei) {
 	 this.imei=imei;
}

}
package microservices.backend.eir_carts_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {

private int id;
private String catalogCode;
private int offerId;

public Discount() {

}

public Discount(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogCode = rs.getString("catalog_code");
	offerId = rs.getInt("offer_id");
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
public int getOfferId() {
 	return offerId;
}
public void setOfferId(int offerId) {
 	 this.offerId=offerId;
}

}
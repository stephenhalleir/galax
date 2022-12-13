package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {

private int id;
private int catalogOfferId;
private int prospectId;
private int topUpAmount;

public Offer() {

}

public Offer(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogOfferId = rs.getInt("catalog_offer_id");
	prospectId = rs.getInt("prospect_id");
	topUpAmount = rs.getInt("top_up_amount");
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
public int getCatalogOfferId() {
 	return catalogOfferId;
}
public void setCatalogOfferId(int catalogOfferId) {
 	 this.catalogOfferId=catalogOfferId;
}
public int getProspectId() {
 	return prospectId;
}
public void setProspectId(int prospectId) {
 	 this.prospectId=prospectId;
}
public int getTopUpAmount() {
 	return topUpAmount;
}
public void setTopUpAmount(int topUpAmount) {
 	 this.topUpAmount=topUpAmount;
}

}
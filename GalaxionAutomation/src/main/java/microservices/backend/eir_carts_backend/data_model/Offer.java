package microservices.backend.eir_carts_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {

private int id;
private String catalogOfferCode;
private String catalogTariffPlanCode;
private int portIn;
private String serviceGroup;
private String directoryPreference;
private int topUpAmount;
private String cartUuid;

public Offer() {

}

public Offer(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogOfferCode = rs.getString("catalog_offer_code");
	catalogTariffPlanCode = rs.getString("catalog_tariff_plan_code");
	portIn = rs.getInt("port_in");
	serviceGroup = rs.getString("service_group");
	directoryPreference = rs.getString("directory_preference");
	topUpAmount = rs.getInt("top_up_amount");
	cartUuid = rs.getString("cart_uuid");
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
public String getCatalogOfferCode() {
 	return catalogOfferCode;
}
public void setCatalogOfferCode(String catalogOfferCode) {
 	 this.catalogOfferCode=catalogOfferCode;
}
public String getCatalogTariffPlanCode() {
 	return catalogTariffPlanCode;
}
public void setCatalogTariffPlanCode(String catalogTariffPlanCode) {
 	 this.catalogTariffPlanCode=catalogTariffPlanCode;
}
public int getPortIn() {
 	return portIn;
}
public void setPortIn(int portIn) {
 	 this.portIn=portIn;
}
public String getServiceGroup() {
 	return serviceGroup;
}
public void setServiceGroup(String serviceGroup) {
 	 this.serviceGroup=serviceGroup;
}
public String getDirectoryPreference() {
 	return directoryPreference;
}
public void setDirectoryPreference(String directoryPreference) {
 	 this.directoryPreference=directoryPreference;
}
public int getTopUpAmount() {
 	return topUpAmount;
}
public void setTopUpAmount(int topUpAmount) {
 	 this.topUpAmount=topUpAmount;
}
public String getCartUuid() {
 	return cartUuid;
}
public void setCartUuid(String cartUuid) {
 	 this.cartUuid=cartUuid;
}

}
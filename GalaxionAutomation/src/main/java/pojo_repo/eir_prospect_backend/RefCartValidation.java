package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefCartValidation {

private String action;
private String channelGroup;
private int catalogOfferId;
private int min;

public RefCartValidation() {

}

public RefCartValidation(ResultSet rs) {
try {
	action = rs.getString("action");
	channelGroup = rs.getString("channel_group");
	catalogOfferId = rs.getInt("catalog_offer_id");
	min = rs.getInt("min");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getAction() {
 	return action;
}
public void setAction(String action) {
 	 this.action=action;
}
public String getChannelGroup() {
 	return channelGroup;
}
public void setChannelGroup(String channelGroup) {
 	 this.channelGroup=channelGroup;
}
public int getCatalogOfferId() {
 	return catalogOfferId;
}
public void setCatalogOfferId(int catalogOfferId) {
 	 this.catalogOfferId=catalogOfferId;
}
public int getMin() {
 	return min;
}
public void setMin(int min) {
 	 this.min=min;
}

}
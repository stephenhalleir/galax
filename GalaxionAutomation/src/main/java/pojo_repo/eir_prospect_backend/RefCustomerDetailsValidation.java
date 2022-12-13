package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefCustomerDetailsValidation {

private String action;
private String channelGroup;
private String offerType;
private int anonymous;

public RefCustomerDetailsValidation() {

}

public RefCustomerDetailsValidation(ResultSet rs) {
try {
	action = rs.getString("action");
	channelGroup = rs.getString("channel_group");
	offerType = rs.getString("offer_type");
	anonymous = rs.getInt("anonymous");
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
public String getOfferType() {
 	return offerType;
}
public void setOfferType(String offerType) {
 	 this.offerType=offerType;
}
public int getAnonymous() {
 	return anonymous;
}
public void setAnonymous(int anonymous) {
 	 this.anonymous=anonymous;
}

}
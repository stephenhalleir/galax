package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethod {

private int id;
private String type;
private String oldToken;
private String paymentMethodType;
private String cardholderName;
private Date expirationDate;
private String partialDigits;

public PaymentMethod() {

}

public PaymentMethod(ResultSet rs) {
try {
	id = rs.getInt("id");
	type = rs.getString("type");
	oldToken = rs.getString("old_token");
	paymentMethodType = rs.getString("payment_method_type");
	cardholderName = rs.getString("cardholder_name");
	expirationDate = rs.getDate("expiration_date");
	partialDigits = rs.getString("partial_digits");
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
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getOldToken() {
 	return oldToken;
}
public void setOldToken(String oldToken) {
 	 this.oldToken=oldToken;
}
public String getPaymentMethodType() {
 	return paymentMethodType;
}
public void setPaymentMethodType(String paymentMethodType) {
 	 this.paymentMethodType=paymentMethodType;
}
public String getCardholderName() {
 	return cardholderName;
}
public void setCardholderName(String cardholderName) {
 	 this.cardholderName=cardholderName;
}
public Date getExpirationDate() {
 	return expirationDate;
}
public void setExpirationDate(Date expirationDate) {
 	 this.expirationDate=expirationDate;
}
public String getPartialDigits() {
 	return partialDigits;
}
public void setPartialDigits(String partialDigits) {
 	 this.partialDigits=partialDigits;
}

}
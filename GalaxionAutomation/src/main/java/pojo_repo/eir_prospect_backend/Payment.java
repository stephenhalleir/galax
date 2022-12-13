package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

private int oldId;
private int id;
private Date createdAt;
private int amount;
private String paymentReference;
private String payerReference;
private String correlationTransactionUuid;
private String authCode;

public Payment() {

}

public Payment(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	id = rs.getInt("id");
	createdAt = rs.getDate("created_at");
	amount = rs.getInt("amount");
	paymentReference = rs.getString("payment_reference");
	payerReference = rs.getString("payer_reference");
	correlationTransactionUuid = rs.getString("correlation_transaction_uuid");
	authCode = rs.getString("auth_code");
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
public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}
public int getAmount() {
 	return amount;
}
public void setAmount(int amount) {
 	 this.amount=amount;
}
public String getPaymentReference() {
 	return paymentReference;
}
public void setPaymentReference(String paymentReference) {
 	 this.paymentReference=paymentReference;
}
public String getPayerReference() {
 	return payerReference;
}
public void setPayerReference(String payerReference) {
 	 this.payerReference=payerReference;
}
public String getCorrelationTransactionUuid() {
 	return correlationTransactionUuid;
}
public void setCorrelationTransactionUuid(String correlationTransactionUuid) {
 	 this.correlationTransactionUuid=correlationTransactionUuid;
}
public String getAuthCode() {
 	return authCode;
}
public void setAuthCode(String authCode) {
 	 this.authCode=authCode;
}

}
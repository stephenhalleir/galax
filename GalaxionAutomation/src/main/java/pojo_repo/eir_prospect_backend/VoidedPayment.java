package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoidedPayment {

private int prospectId;
private String transactionUuid;

public VoidedPayment() {

}

public VoidedPayment(ResultSet rs) {
try {
	prospectId = rs.getInt("prospect_id");
	transactionUuid = rs.getString("transaction_uuid");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getProspectId() {
 	return prospectId;
}
public void setProspectId(int prospectId) {
 	 this.prospectId=prospectId;
}
public String getTransactionUuid() {
 	return transactionUuid;
}
public void setTransactionUuid(String transactionUuid) {
 	 this.transactionUuid=transactionUuid;
}

}
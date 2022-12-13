package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestSubscriptionTermination {

private int id;
private int subscriptionId;
private String status;
private Date scheduledAt;
private int isInsideCoolingOffPeriod;
private String comment;
private String approvedBy;
private int reasonId;
private String errorCode;
private String errorMessage;
private Date createdAt;
private Date updatedAt;
private String source;
private int earlyCeaseChargePrice;

public RequestSubscriptionTermination() {

}

public RequestSubscriptionTermination(ResultSet rs) {
try {
	id = rs.getInt("id");
	subscriptionId = rs.getInt("subscription_id");
	status = rs.getString("status");
	scheduledAt = rs.getDate("scheduled_at");
	isInsideCoolingOffPeriod = rs.getInt("is_inside_cooling_off_period");
	comment = rs.getString("comment");
	approvedBy = rs.getString("approved_by");
	reasonId = rs.getInt("reason_id");
	errorCode = rs.getString("error_code");
	errorMessage = rs.getString("error_message");
	createdAt = rs.getDate("created_at");
	updatedAt = rs.getDate("updated_at");
	source = rs.getString("source");
	earlyCeaseChargePrice = rs.getInt("early_cease_charge_price");
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
public int getSubscriptionId() {
 	return subscriptionId;
}
public void setSubscriptionId(int subscriptionId) {
 	 this.subscriptionId=subscriptionId;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public Date getScheduledAt() {
 	return scheduledAt;
}
public void setScheduledAt(Date scheduledAt) {
 	 this.scheduledAt=scheduledAt;
}
public int getIsInsideCoolingOffPeriod() {
 	return isInsideCoolingOffPeriod;
}
public void setIsInsideCoolingOffPeriod(int isInsideCoolingOffPeriod) {
 	 this.isInsideCoolingOffPeriod=isInsideCoolingOffPeriod;
}
public String getComment() {
 	return comment;
}
public void setComment(String comment) {
 	 this.comment=comment;
}
public String getApprovedBy() {
 	return approvedBy;
}
public void setApprovedBy(String approvedBy) {
 	 this.approvedBy=approvedBy;
}
public int getReasonId() {
 	return reasonId;
}
public void setReasonId(int reasonId) {
 	 this.reasonId=reasonId;
}
public String getErrorCode() {
 	return errorCode;
}
public void setErrorCode(String errorCode) {
 	 this.errorCode=errorCode;
}
public String getErrorMessage() {
 	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
 	 this.errorMessage=errorMessage;
}
public Date getCreatedAt() {
 	return createdAt;
}
public void setCreatedAt(Date createdAt) {
 	 this.createdAt=createdAt;
}
public Date getUpdatedAt() {
 	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
 	 this.updatedAt=updatedAt;
}
public String getSource() {
 	return source;
}
public void setSource(String source) {
 	 this.source=source;
}
public int getEarlyCeaseChargePrice() {
 	return earlyCeaseChargePrice;
}
public void setEarlyCeaseChargePrice(int earlyCeaseChargePrice) {
 	 this.earlyCeaseChargePrice=earlyCeaseChargePrice;
}

}
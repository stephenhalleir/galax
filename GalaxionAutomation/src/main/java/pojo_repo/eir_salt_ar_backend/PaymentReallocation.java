package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentReallocation {

	private int id;
	private String reasonCode;
	private int paymentParentId;
	private int destinationBillingAccountId;
	private int destinationPaymentId;
	private Date createTs;

	public PaymentReallocation() {

	}

	public PaymentReallocation(ResultSet rs) {
		try {
			id = rs.getInt("id");
			reasonCode = rs.getString("reason_code");
			paymentParentId = rs.getInt("payment_parent_id");
			destinationBillingAccountId = rs.getInt("destination_billing_account_id");
			destinationPaymentId = rs.getInt("destination_payment_id");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public int getPaymentParentId() {
		return paymentParentId;
	}

	public void setPaymentParentId(int paymentParentId) {
		this.paymentParentId = paymentParentId;
	}

	public int getDestinationBillingAccountId() {
		return destinationBillingAccountId;
	}

	public void setDestinationBillingAccountId(int destinationBillingAccountId) {
		this.destinationBillingAccountId = destinationBillingAccountId;
	}

	public int getDestinationPaymentId() {
		return destinationPaymentId;
	}

	public void setDestinationPaymentId(int destinationPaymentId) {
		this.destinationPaymentId = destinationPaymentId;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}
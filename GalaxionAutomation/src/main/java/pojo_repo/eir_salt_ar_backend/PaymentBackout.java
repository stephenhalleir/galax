package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentBackout {

	private String reasonCode;
	private int id;
	private int paymentParentId;
	private String cardIssuer;
	private Date createTs;

	public PaymentBackout() {

	}

	public PaymentBackout(ResultSet rs) {
		try {
			reasonCode = rs.getString("reason_code");
			id = rs.getInt("id");
			paymentParentId = rs.getInt("payment_parent_id");
			cardIssuer = rs.getString("card_issuer");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPaymentParentId() {
		return paymentParentId;
	}

	public void setPaymentParentId(int paymentParentId) {
		this.paymentParentId = paymentParentId;
	}

	public String getCardIssuer() {
		return cardIssuer;
	}

	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}
package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

	private String changeOfferUuid;
	private String correlationTransactionUuid;
	private int amount;
	private String paymentReference;
	private String authCode;

	public Payment() {

	}

	public Payment(ResultSet rs) {
		try {
			changeOfferUuid = rs.getString("change_offer_uuid");
			correlationTransactionUuid = rs.getString("correlation_transaction_uuid");
			amount = rs.getInt("amount");
			paymentReference = rs.getString("payment_reference");
			authCode = rs.getString("auth_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChangeOfferUuid() {
		return changeOfferUuid;
	}

	public void setChangeOfferUuid(String changeOfferUuid) {
		this.changeOfferUuid = changeOfferUuid;
	}

	public String getCorrelationTransactionUuid() {
		return correlationTransactionUuid;
	}

	public void setCorrelationTransactionUuid(String correlationTransactionUuid) {
		this.correlationTransactionUuid = correlationTransactionUuid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

	private int id;
	private int orderId;
	private String payerReference;
	private String paymentReference;
	private int amount;
	private String correlationTransactionUuid;
	private String authCode;
	private String paymentMethodType;
	private int paymentTerm;

	public Payment() {

	}

	public Payment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			orderId = rs.getInt("order_id");
			payerReference = rs.getString("payer_reference");
			paymentReference = rs.getString("payment_reference");
			amount = rs.getInt("amount");
			correlationTransactionUuid = rs.getString("correlation_transaction_uuid");
			authCode = rs.getString("auth_code");
			paymentMethodType = rs.getString("payment_method_type");
			paymentTerm = rs.getInt("payment_term");
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getPayerReference() {
		return payerReference;
	}

	public void setPayerReference(String payerReference) {
		this.payerReference = payerReference;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCorrelationTransactionUuid() {
		return correlationTransactionUuid;
	}

	public void setCorrelationTransactionUuid(String correlationTransactionUuid) {
		this.correlationTransactionUuid = correlationTransactionUuid;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

}
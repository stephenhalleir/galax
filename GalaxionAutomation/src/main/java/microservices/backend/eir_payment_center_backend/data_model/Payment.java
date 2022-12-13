package microservices.backend.eir_payment_center_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

	private int id;
	private int paymentMethodID;
	private int amount;
	private Date transactionDateTime;
	private String status;
	private String providerTransactionRef;
	private String type;
	private String correlationTransactionUuid;
	private String source;

	public Payment() {

	}

	public Payment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			paymentMethodID = rs.getInt("payment_method_id");
			amount = rs.getInt("amount");
			transactionDateTime = rs.getDate("transaction_datetime");
			status = rs.getString("status");
			providerTransactionRef = rs.getString("provider_transaction_ref");
			type = rs.getString("type");
			correlationTransactionUuid = rs.getString("correlation_transaction_uuid");
			source = rs.getString("source");
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

	public int getPaymentMethodID() {
		return paymentMethodID;
	}

	public void setPaymentMethodID(int paymentMethodID) {
		this.paymentMethodID = paymentMethodID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProviderTransactionRef() {
		return providerTransactionRef;
	}

	public void setProviderTransactionRef(String providerTransactionRef) {
		this.providerTransactionRef = providerTransactionRef;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCorrelationTransactionUuid() {
		return correlationTransactionUuid;
	}

	public void setCorrelationTransactionUuid(String correlationTransactionUuid) {
		this.correlationTransactionUuid = correlationTransactionUuid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}

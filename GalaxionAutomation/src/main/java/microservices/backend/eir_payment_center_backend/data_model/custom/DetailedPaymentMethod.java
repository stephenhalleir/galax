package microservices.backend.eir_payment_center_backend.data_model.custom;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailedPaymentMethod {

	private String status;
	private int id;
	private String paymentMethodType;
	private Date canceledAt;
	private String type;
	private String cardholderName;
	private Date expirationDate;
	private String partialDigits;
	private String providerRef;

	public DetailedPaymentMethod(ResultSet rs) {
		try {
			status = rs.getString("status");
			paymentMethodType = rs.getString("payment_method_type");
			type = rs.getString("type");
			cardholderName = rs.getString("cardholder_name");
			partialDigits = rs.getString("partial_digits");
			providerRef = rs.getString("provider_ref");
			id = rs.getInt("id");
			canceledAt = rs.getDate("canceled_at");
			expirationDate = rs.getDate("expiration_date");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public Date getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPartialDigits() {
		return partialDigits;
	}

	public void setPartialDigits(String partialDigits) {
		this.partialDigits = partialDigits;
	}

	public String getProviderRef() {
		return providerRef;
	}

	public void setProviderRef(String providerRef) {
		this.providerRef = providerRef;
	}
}

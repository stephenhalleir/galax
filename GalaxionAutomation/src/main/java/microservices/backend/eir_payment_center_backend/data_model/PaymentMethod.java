package microservices.backend.eir_payment_center_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethod {
	private int id;
	private int payerId;
	private String status;
	private String paymentMethodType;
	private Date cancelledAt;

	public PaymentMethod() {
		
	}
	
	public PaymentMethod(ResultSet rs) {
		try {
			id=rs.getInt("id");
			payerId=rs.getInt("payer_id");
			status=rs.getString("status");
			paymentMethodType=rs.getString("payment_method_type");
			cancelledAt=rs.getDate("canceled_at");
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

	public int getPayerId() {
		return payerId;
	}

	public void setPayerId(int payerId) {
		this.payerId = payerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public Date getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
}

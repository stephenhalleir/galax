package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethod {

	private int id;
	private int payerId;
	private String status;
	private Date createdAt;
	private Date updatedAt;
	private Date canceledAt;
	private Date anonymizedAt;

	public PaymentMethod() {

	}

	public PaymentMethod(ResultSet rs) {
		try {
			id = rs.getInt("id");
			payerId = rs.getInt("payer_id");
			status = rs.getString("status");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			canceledAt = rs.getDate("canceled_at");
			anonymizedAt = rs.getDate("anonymized_at");
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}

	public Date getAnonymizedAt() {
		return anonymizedAt;
	}

	public void setAnonymizedAt(Date anonymizedAt) {
		this.anonymizedAt = anonymizedAt;
	}

}
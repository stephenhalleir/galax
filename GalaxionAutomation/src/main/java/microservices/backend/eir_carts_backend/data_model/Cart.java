package microservices.backend.eir_carts_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cart {

	private String uuid;
	private String brand;
	private String offerType;
	private String channel;
	private String creditScore;
	private String customerType;
	private String status;
	private int valid;
	private int agreedTermAndCondition;
	private Date createdAt;
	private Date updatedAt;
	private String acquisitionType;

	public Cart() {

	}

	public Cart(ResultSet rs) {
		try {
			uuid = rs.getString("uuid");
			brand = rs.getString("brand");
			offerType = rs.getString("offer_type");
			channel = rs.getString("channel");
			creditScore = rs.getString("credit_score");
			customerType = rs.getString("customer_type");
			status = rs.getString("status");
			valid = rs.getInt("valid");
			agreedTermAndCondition = rs.getInt("agreed_term_and_condition");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			acquisitionType = rs.getString("acquisition_type");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getAgreedTermAndCondition() {
		return agreedTermAndCondition;
	}

	public void setAgreedTermAndCondition(int agreedTermAndCondition) {
		this.agreedTermAndCondition = agreedTermAndCondition;
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

	public String getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(String acquisitionType) {
		this.acquisitionType = acquisitionType;
	}
}
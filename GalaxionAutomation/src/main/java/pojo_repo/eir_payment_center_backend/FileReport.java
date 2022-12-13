package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileReport {

	private int id;
	private String path;
	private String fileName;
	private String status;
	private int transactionCount;
	private int amount;
	private Date receivedAt;
	private Date sentAt;
	private Date createdAt;
	private Date updatedAt;
	private int paymentRunGroupId;
	private String originalFileName;

	public FileReport() {

	}

	public FileReport(ResultSet rs) {
		try {
			id = rs.getInt("id");
			path = rs.getString("path");
			fileName = rs.getString("file_name");
			status = rs.getString("status");
			transactionCount = rs.getInt("transaction_count");
			amount = rs.getInt("amount");
			receivedAt = rs.getDate("received_at");
			sentAt = rs.getDate("sent_at");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			paymentRunGroupId = rs.getInt("payment_run_group_id");
			originalFileName = rs.getString("original_file_name");
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
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

	public int getPaymentRunGroupId() {
		return paymentRunGroupId;
	}

	public void setPaymentRunGroupId(int paymentRunGroupId) {
		this.paymentRunGroupId = paymentRunGroupId;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

}
package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {

	private int id;
	private String author;
	private String comment;
	private Date createdAt;
	private String customerUuid;

	public Comment() {

	}

	public Comment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			author = rs.getString("author");
			comment = rs.getString("comment");
			createdAt = rs.getDate("created_at");
			customerUuid = rs.getString("customer_uuid");
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

}
package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class History {

	private int id;
	private String author;
	private String channel;
	private String content;
	private Date createdAt;
	private String customerUuid;
	private String orderId;
	private String orderTypeName;
	private int accountId;
	private String description;
	private String shop;

	public History() {

	}

	public History(ResultSet rs) {
		try {
			id = rs.getInt("id");
			author = rs.getString("author");
			channel = rs.getString("channel");
			content = rs.getString("content");
			createdAt = rs.getDate("created_at");
			customerUuid = rs.getString("customer_uuid");
			orderId = rs.getString("order_id");
			orderTypeName = rs.getString("order_type_name");
			accountId = rs.getInt("account_id");
			description = rs.getString("description");
			shop = rs.getString("shop");
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

}
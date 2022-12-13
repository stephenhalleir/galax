package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderType {

	private int id;
	private String author;
	private Date createdAt;
	private String name;
	private Date updatedAt;
	private int templateId;
	private int accountLevel;
	private int domainId;

	public OrderType() {

	}

	public OrderType(ResultSet rs) {
		try {
			id = rs.getInt("id");
			author = rs.getString("author");
			createdAt = rs.getDate("created_at");
			name = rs.getString("name");
			updatedAt = rs.getDate("updated_at");
			templateId = rs.getInt("template_id");
			accountLevel = rs.getInt("account_level");
			domainId = rs.getInt("domain_id");
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(int accountLevel) {
		this.accountLevel = accountLevel;
	}

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

}
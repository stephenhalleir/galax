package pojo_repo.eir_customer_history_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Template {

	private int id;
	private int active;
	private String author;
	private String content;
	private Date createdAt;
	private String description;
	private String language;
	private String name;
	private String updatedAuthor;

	public Template() {

	}

	public Template(ResultSet rs) {
		try {
			id = rs.getInt("id");
			active = rs.getInt("active");
			author = rs.getString("author");
			content = rs.getString("content");
			createdAt = rs.getDate("created_at");
			description = rs.getString("description");
			language = rs.getString("language");
			name = rs.getString("name");
			updatedAuthor = rs.getString("updated_author");
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedAuthor() {
		return updatedAuthor;
	}

	public void setUpdatedAuthor(String updatedAuthor) {
		this.updatedAuthor = updatedAuthor;
	}

}
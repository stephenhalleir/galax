package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {

	private int id;
	private Date availableFrom;
	private Date availableTo;
	private String brand;
	private String description;
	private String name;
	private int priority;

	public Offer() {

	}

	public Offer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			availableFrom = rs.getDate("available_from");
			availableTo = rs.getDate("available_to");
			brand = rs.getString("brand");
			description = rs.getString("description");
			name = rs.getString("name");
			priority = rs.getInt("priority");
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

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Date getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(Date availableTo) {
		this.availableTo = availableTo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
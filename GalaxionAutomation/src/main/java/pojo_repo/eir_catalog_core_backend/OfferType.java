package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferType {

	private int id;
	private String displayName;
	private String description;

	public OfferType() {

	}

	public OfferType(ResultSet rs) {
		try {
			id = rs.getInt("id");
			displayName = rs.getString("display_name");
			description = rs.getString("description");
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
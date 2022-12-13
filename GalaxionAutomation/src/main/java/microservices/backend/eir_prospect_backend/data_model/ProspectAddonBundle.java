package microservices.backend.eir_prospect_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProspectAddonBundle {

	private int id;
	private int catalogId;

	public ProspectAddonBundle() {

	}

	public ProspectAddonBundle(ResultSet rs) {
		try {
			id = rs.getInt("id");
			catalogId = rs.getInt("catalog_id");
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

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

}
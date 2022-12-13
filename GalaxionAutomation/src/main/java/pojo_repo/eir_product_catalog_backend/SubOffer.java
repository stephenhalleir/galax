package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubOffer {

	private int id;
	private String domain;
	private String name;

	public SubOffer() {

	}

	public SubOffer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			domain = rs.getString("domain");
			name = rs.getString("name");
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
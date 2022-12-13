package pojo_repo.eir_barring_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvisioningReference {

	private int id;
	private String reference;

	public ProvisioningReference() {

	}

	public ProvisioningReference(ResultSet rs) {
		try {
			id = rs.getInt("id");
			reference = rs.getString("reference");
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}
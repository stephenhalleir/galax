package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdentityDocument {

	private int id;
	private int validationId;
	private String idType;

	public IdentityDocument() {

	}

	public IdentityDocument(ResultSet rs) {
		try {
			id = rs.getInt("id");
			validationId = rs.getInt("validation_id");
			idType = rs.getString("id_type");
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

	public int getValidationId() {
		return validationId;
	}

	public void setValidationId(int validationId) {
		this.validationId = validationId;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

}
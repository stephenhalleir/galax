package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigErrorHandling {

	private String entity;
	private int entityId;

	public MigErrorHandling() {

	}

	public MigErrorHandling(ResultSet rs) {
		try {
			entity = rs.getString("entity");
			entityId = rs.getInt("entity_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

}
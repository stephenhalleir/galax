package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefEquipmentDynamicAttribute {

	private int id;
	private String equipmentType;

	public RefEquipmentDynamicAttribute() {

	}

	public RefEquipmentDynamicAttribute(ResultSet rs) {
		try {
			id = rs.getInt("id");
			equipmentType = rs.getString("equipment_type");
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

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

}
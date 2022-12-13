package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentElement {

	private int id;
	private String equipmentManufacturer;
	private String equipmentModel;
	private String equipmentPackId;
	private String equipmentType;

	public EquipmentElement() {

	}

	public EquipmentElement(ResultSet rs) {
		try {
			id = rs.getInt("id");
			equipmentManufacturer = rs.getString("equipment_manufacturer");
			equipmentModel = rs.getString("equipment_model");
			equipmentPackId = rs.getString("equipment_pack_id");
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

	public String getEquipmentManufacturer() {
		return equipmentManufacturer;
	}

	public void setEquipmentManufacturer(String equipmentManufacturer) {
		this.equipmentManufacturer = equipmentManufacturer;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getEquipmentPackId() {
		return equipmentPackId;
	}

	public void setEquipmentPackId(String equipmentPackId) {
		this.equipmentPackId = equipmentPackId;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

}
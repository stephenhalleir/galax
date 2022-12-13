package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accessory {

	private String code;
	private String title;
	private String description;
	private String chargeCode;
	private String inventoryCode;

	public Accessory() {

	}

	public Accessory(ResultSet rs) {
		try {
			code = rs.getString("code");
			title = rs.getString("title");
			description = rs.getString("description");
			chargeCode = rs.getString("charge_code");
			inventoryCode = rs.getString("inventory_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

}
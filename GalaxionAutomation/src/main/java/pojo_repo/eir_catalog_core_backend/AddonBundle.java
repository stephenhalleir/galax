package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundle {

	private int id;
	private Date validFrom;
	private Date validTo;
	private String code;
	private String description;
	private String status;
	private String level;
	private int maxQuantity;
	private int brandId;
	private int offerTypeId;
	private String simCardCode;

	public AddonBundle() {

	}

	public AddonBundle(ResultSet rs) {
		try {
			id = rs.getInt("id");
			validFrom = rs.getDate("valid_from");
			validTo = rs.getDate("valid_to");
			code = rs.getString("code");
			description = rs.getString("description");
			status = rs.getString("status");
			level = rs.getString("level");
			maxQuantity = rs.getInt("max_quantity");
			brandId = rs.getInt("brand_id");
			offerTypeId = rs.getInt("offer_type_id");
			simCardCode = rs.getString("sim_card_code");
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

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getOfferTypeId() {
		return offerTypeId;
	}

	public void setOfferTypeId(int offerTypeId) {
		this.offerTypeId = offerTypeId;
	}

	public String getSimCardCode() {
		return simCardCode;
	}

	public void setSimCardCode(String simCardCode) {
		this.simCardCode = simCardCode;
	}

}
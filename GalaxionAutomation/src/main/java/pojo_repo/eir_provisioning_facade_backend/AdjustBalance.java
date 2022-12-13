package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdjustBalance {

	private int id;
	private String msisdn;
	private int catalogOfferId;
	private int catalogAddonId;
	private String adjustmentType;
	private int networkId;
	private String status;
	private String errorCode;
	private String errorDescription;
	private Date createdDateTime;

	public AdjustBalance() {

	}

	public AdjustBalance(ResultSet rs) {
		try {
			id = rs.getInt("id");
			msisdn = rs.getString("msisdn");
			catalogOfferId = rs.getInt("catalog_offer_id");
			catalogAddonId = rs.getInt("catalog_addon_id");
			adjustmentType = rs.getString("adjustment_type");
			networkId = rs.getInt("network_id");
			status = rs.getString("status");
			errorCode = rs.getString("error_code");
			errorDescription = rs.getString("error_description");
			createdDateTime = rs.getDate("created_date_time");
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

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}

	public int getCatalogAddonId() {
		return catalogAddonId;
	}

	public void setCatalogAddonId(int catalogAddonId) {
		this.catalogAddonId = catalogAddonId;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
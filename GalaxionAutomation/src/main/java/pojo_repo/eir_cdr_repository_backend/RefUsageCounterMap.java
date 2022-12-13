package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefUsageCounterMap {

	private int id;
	private int usageCounterTypeId;
	private String fullPath;
	private String cascadeCarrierCode;
	private String roamingNetworkBand;
	private String destinationZone;
	private String mmsType;
	private String destinationRoamingZone;
	private Date effectiveStartDateTime;

	public RefUsageCounterMap() {

	}

	public RefUsageCounterMap(ResultSet rs) {
		try {
			id = rs.getInt("id");
			usageCounterTypeId = rs.getInt("usage_counter_type_id");
			fullPath = rs.getString("full_path");
			cascadeCarrierCode = rs.getString("cascade_carrier_code");
			roamingNetworkBand = rs.getString("roaming_network_band");
			destinationZone = rs.getString("destination_zone");
			mmsType = rs.getString("mms_type");
			destinationRoamingZone = rs.getString("destination_roaming_zone");
			effectiveStartDateTime = rs.getDate("effective_start_date_time");
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

	public int getUsageCounterTypeId() {
		return usageCounterTypeId;
	}

	public void setUsageCounterTypeId(int usageCounterTypeId) {
		this.usageCounterTypeId = usageCounterTypeId;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getCascadeCarrierCode() {
		return cascadeCarrierCode;
	}

	public void setCascadeCarrierCode(String cascadeCarrierCode) {
		this.cascadeCarrierCode = cascadeCarrierCode;
	}

	public String getRoamingNetworkBand() {
		return roamingNetworkBand;
	}

	public void setRoamingNetworkBand(String roamingNetworkBand) {
		this.roamingNetworkBand = roamingNetworkBand;
	}

	public String getDestinationZone() {
		return destinationZone;
	}

	public void setDestinationZone(String destinationZone) {
		this.destinationZone = destinationZone;
	}

	public String getMmsType() {
		return mmsType;
	}

	public void setMmsType(String mmsType) {
		this.mmsType = mmsType;
	}

	public String getDestinationRoamingZone() {
		return destinationRoamingZone;
	}

	public void setDestinationRoamingZone(String destinationRoamingZone) {
		this.destinationRoamingZone = destinationRoamingZone;
	}

	public Date getEffectiveStartDateTime() {
		return effectiveStartDateTime;
	}

	public void setEffectiveStartDateTime(Date effectiveStartDateTime) {
		this.effectiveStartDateTime = effectiveStartDateTime;
	}

}
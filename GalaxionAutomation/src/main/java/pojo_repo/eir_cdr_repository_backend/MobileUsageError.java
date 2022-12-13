package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileUsageError {

	private int id;
	private String serviceType;
	private String salesType;
	private Date lastModifiedDateTime;
	private int mobileUsageFileId;
	private int fileRecordNumber;
	private String errorCode;
	private String errorMessage;
	private String aPartyId;
	private String aPartyNetworkId;
	private String aPartyName;
	private int aPartyTonCode;
	private String aPartyCellId;
	private int aPartyCarrierCode;
	private int aPartyLocationCode;
	private String aPartyRoute;
	private String bPartyId;
	private String bPartyNetworkId;
	private String bPartyName;
	private int bPartyTonCode;
	private String bPartyCellId;
	private int bPartyCarrierCode;
	private int bPartyLocationCode;
	private String bPartyRoute;
	private String cPartyId;
	private String cPartyNetworkId;
	private String cPartyName;
	private int cPartyTonCode;
	private String cPartyCellId;
	private int cPartyCarrierCode;
	private int cPartyLocationCode;
	private String cPartyRoute;
	private int fullPath;
	private int mmsType;
	private int destinationRoamingZone;
	private int cascadeCarrierCode;
	private Date chargeStartDateTime;
	private int eventSource;
	private int duration;
	private int volume;
	private int pulses;
	private int charge;
	private String daBalance;
	private String uniqueExternalRef;

	public MobileUsageError() {

	}

	public MobileUsageError(ResultSet rs) {
		try {
			id = rs.getInt("id");
			serviceType = rs.getString("service_type");
			salesType = rs.getString("sales_type");
			lastModifiedDateTime = rs.getDate("last_modified_date_time");
			mobileUsageFileId = rs.getInt("mobile_usage_file_id");
			fileRecordNumber = rs.getInt("file_record_number");
			errorCode = rs.getString("error_code");
			errorMessage = rs.getString("error_message");
			aPartyId = rs.getString("a_party_id");
			aPartyNetworkId = rs.getString("a_party_network_id");
			aPartyName = rs.getString("a_party_name");
			aPartyTonCode = rs.getInt("a_party_ton_code");
			aPartyCellId = rs.getString("a_party_cell_id");
			aPartyCarrierCode = rs.getInt("a_party_carrier_code");
			aPartyLocationCode = rs.getInt("a_party_location_code");
			aPartyRoute = rs.getString("a_party_route");
			bPartyId = rs.getString("b_party_id");
			bPartyNetworkId = rs.getString("b_party_network_id");
			bPartyName = rs.getString("b_party_name");
			bPartyTonCode = rs.getInt("b_party_ton_code");
			bPartyCellId = rs.getString("b_party_cell_id");
			bPartyCarrierCode = rs.getInt("b_party_carrier_code");
			bPartyLocationCode = rs.getInt("b_party_location_code");
			bPartyRoute = rs.getString("b_party_route");
			cPartyId = rs.getString("c_party_id");
			cPartyNetworkId = rs.getString("c_party_network_id");
			cPartyName = rs.getString("c_party_name");
			cPartyTonCode = rs.getInt("c_party_ton_code");
			cPartyCellId = rs.getString("c_party_cell_id");
			cPartyCarrierCode = rs.getInt("c_party_carrier_code");
			cPartyLocationCode = rs.getInt("c_party_location_code");
			cPartyRoute = rs.getString("c_party_route");
			fullPath = rs.getInt("full_path");
			mmsType = rs.getInt("mms_type");
			destinationRoamingZone = rs.getInt("destination_roaming_zone");
			cascadeCarrierCode = rs.getInt("cascade_carrier_code");
			chargeStartDateTime = rs.getDate("charge_start_date_time");
			eventSource = rs.getInt("event_source");
			duration = rs.getInt("duration");
			volume = rs.getInt("volume");
			pulses = rs.getInt("pulses");
			charge = rs.getInt("charge");
			daBalance = rs.getString("da_balance");
			uniqueExternalRef = rs.getString("unique_external_ref");
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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public int getMobileUsageFileId() {
		return mobileUsageFileId;
	}

	public void setMobileUsageFileId(int mobileUsageFileId) {
		this.mobileUsageFileId = mobileUsageFileId;
	}

	public int getFileRecordNumber() {
		return fileRecordNumber;
	}

	public void setFileRecordNumber(int fileRecordNumber) {
		this.fileRecordNumber = fileRecordNumber;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getAPartyId() {
		return aPartyId;
	}

	public void setAPartyId(String aPartyId) {
		this.aPartyId = aPartyId;
	}

	public String getAPartyNetworkId() {
		return aPartyNetworkId;
	}

	public void setAPartyNetworkId(String aPartyNetworkId) {
		this.aPartyNetworkId = aPartyNetworkId;
	}

	public String getAPartyName() {
		return aPartyName;
	}

	public void setAPartyName(String aPartyName) {
		this.aPartyName = aPartyName;
	}

	public int getAPartyTonCode() {
		return aPartyTonCode;
	}

	public void setAPartyTonCode(int aPartyTonCode) {
		this.aPartyTonCode = aPartyTonCode;
	}

	public String getAPartyCellId() {
		return aPartyCellId;
	}

	public void setAPartyCellId(String aPartyCellId) {
		this.aPartyCellId = aPartyCellId;
	}

	public int getAPartyCarrierCode() {
		return aPartyCarrierCode;
	}

	public void setAPartyCarrierCode(int aPartyCarrierCode) {
		this.aPartyCarrierCode = aPartyCarrierCode;
	}

	public int getAPartyLocationCode() {
		return aPartyLocationCode;
	}

	public void setAPartyLocationCode(int aPartyLocationCode) {
		this.aPartyLocationCode = aPartyLocationCode;
	}

	public String getAPartyRoute() {
		return aPartyRoute;
	}

	public void setAPartyRoute(String aPartyRoute) {
		this.aPartyRoute = aPartyRoute;
	}

	public String getBPartyId() {
		return bPartyId;
	}

	public void setBPartyId(String bPartyId) {
		this.bPartyId = bPartyId;
	}

	public String getBPartyNetworkId() {
		return bPartyNetworkId;
	}

	public void setBPartyNetworkId(String bPartyNetworkId) {
		this.bPartyNetworkId = bPartyNetworkId;
	}

	public String getBPartyName() {
		return bPartyName;
	}

	public void setBPartyName(String bPartyName) {
		this.bPartyName = bPartyName;
	}

	public int getBPartyTonCode() {
		return bPartyTonCode;
	}

	public void setBPartyTonCode(int bPartyTonCode) {
		this.bPartyTonCode = bPartyTonCode;
	}

	public String getBPartyCellId() {
		return bPartyCellId;
	}

	public void setBPartyCellId(String bPartyCellId) {
		this.bPartyCellId = bPartyCellId;
	}

	public int getBPartyCarrierCode() {
		return bPartyCarrierCode;
	}

	public void setBPartyCarrierCode(int bPartyCarrierCode) {
		this.bPartyCarrierCode = bPartyCarrierCode;
	}

	public int getBPartyLocationCode() {
		return bPartyLocationCode;
	}

	public void setBPartyLocationCode(int bPartyLocationCode) {
		this.bPartyLocationCode = bPartyLocationCode;
	}

	public String getBPartyRoute() {
		return bPartyRoute;
	}

	public void setBPartyRoute(String bPartyRoute) {
		this.bPartyRoute = bPartyRoute;
	}

	public String getCPartyId() {
		return cPartyId;
	}

	public void setCPartyId(String cPartyId) {
		this.cPartyId = cPartyId;
	}

	public String getCPartyNetworkId() {
		return cPartyNetworkId;
	}

	public void setCPartyNetworkId(String cPartyNetworkId) {
		this.cPartyNetworkId = cPartyNetworkId;
	}

	public String getCPartyName() {
		return cPartyName;
	}

	public void setCPartyName(String cPartyName) {
		this.cPartyName = cPartyName;
	}

	public int getCPartyTonCode() {
		return cPartyTonCode;
	}

	public void setCPartyTonCode(int cPartyTonCode) {
		this.cPartyTonCode = cPartyTonCode;
	}

	public String getCPartyCellId() {
		return cPartyCellId;
	}

	public void setCPartyCellId(String cPartyCellId) {
		this.cPartyCellId = cPartyCellId;
	}

	public int getCPartyCarrierCode() {
		return cPartyCarrierCode;
	}

	public void setCPartyCarrierCode(int cPartyCarrierCode) {
		this.cPartyCarrierCode = cPartyCarrierCode;
	}

	public int getCPartyLocationCode() {
		return cPartyLocationCode;
	}

	public void setCPartyLocationCode(int cPartyLocationCode) {
		this.cPartyLocationCode = cPartyLocationCode;
	}

	public String getCPartyRoute() {
		return cPartyRoute;
	}

	public void setCPartyRoute(String cPartyRoute) {
		this.cPartyRoute = cPartyRoute;
	}

	public int getFullPath() {
		return fullPath;
	}

	public void setFullPath(int fullPath) {
		this.fullPath = fullPath;
	}

	public int getMmsType() {
		return mmsType;
	}

	public void setMmsType(int mmsType) {
		this.mmsType = mmsType;
	}

	public int getDestinationRoamingZone() {
		return destinationRoamingZone;
	}

	public void setDestinationRoamingZone(int destinationRoamingZone) {
		this.destinationRoamingZone = destinationRoamingZone;
	}

	public int getCascadeCarrierCode() {
		return cascadeCarrierCode;
	}

	public void setCascadeCarrierCode(int cascadeCarrierCode) {
		this.cascadeCarrierCode = cascadeCarrierCode;
	}

	public Date getChargeStartDateTime() {
		return chargeStartDateTime;
	}

	public void setChargeStartDateTime(Date chargeStartDateTime) {
		this.chargeStartDateTime = chargeStartDateTime;
	}

	public int getEventSource() {
		return eventSource;
	}

	public void setEventSource(int eventSource) {
		this.eventSource = eventSource;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getPulses() {
		return pulses;
	}

	public void setPulses(int pulses) {
		this.pulses = pulses;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getDaBalance() {
		return daBalance;
	}

	public void setDaBalance(String daBalance) {
		this.daBalance = daBalance;
	}

	public String getUniqueExternalRef() {
		return uniqueExternalRef;
	}

	public void setUniqueExternalRef(String uniqueExternalRef) {
		this.uniqueExternalRef = uniqueExternalRef;
	}

}
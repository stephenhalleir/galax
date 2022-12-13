package microservices.backend.eir_order_management_backend.dto;

public class LogisticsDTO {

	private int orderItemId;
	private String orderReference;
	private String equipmentType;
	private String iccId;
	private String msisdn;
	private String imei;
	private String imsi;
	private String logisticPackageId;
	private String logisticsTrackTraceNumber;
	
	public LogisticsDTO() {
		orderItemId=-1;
		orderReference="";
		equipmentType="";
		iccId="";
		msisdn="";
		imei="";
		imsi="";
		logisticPackageId="ION"+System.currentTimeMillis();
		logisticsTrackTraceNumber="SX" + System.currentTimeMillis() + "IE";
	}
	
	public LogisticsDTO(String orderReference, int itemId) {
		this.orderItemId=itemId;
		this.orderReference=orderReference;
		equipmentType="";
		iccId="";
		msisdn="";
		imei="";
		imsi="";
		logisticPackageId="ION"+System.currentTimeMillis();
		logisticsTrackTraceNumber="SX" + System.currentTimeMillis() + "IE";
	}

	public void addHandset() {
		imei="35" + System.currentTimeMillis();
		
		if(equipmentType.equals("SIM")) {
			equipmentType="SIM/Handset";
		}
		else {
			equipmentType="Handset";
		}
	}
	
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
		
		if(equipmentType.equals("")) {
			equipmentType="SIM";
		}
		else if(equipmentType.equals("Handset")){
			equipmentType="SIM/Handset";
		}
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getLogisticPackageId() {
		return logisticPackageId;
	}

	public void setLogisticPackageId(String logisticPackageId) {
		this.logisticPackageId = logisticPackageId;
	}

	public String getLogisticsTrackTraceNumber() {
		return logisticsTrackTraceNumber;
	}

	public void setLogisticsTrackTraceNumber(String logisticsTrackTraceNumber) {
		this.logisticsTrackTraceNumber = logisticsTrackTraceNumber;
	}
}

package microservices.backend.eir_logistics_backend.file_objects;

import java.text.SimpleDateFormat;
import java.util.Date;

import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;

/*
 * Author: Stephen Hall
 * Created 17/08/2018 for Project Ion
 * 
 * This class represents a line item in a CNF line item.
 * The variables mirror the fields present in a CNF line item
 */
public class CNFLineItem {
	
	private String batchID;
	private String article;
	private String simNumber;
	private String msisdn;
	private String imsi;
	private String packageId;
	private String imeiNumber;
	private String orderNumber;
	private String sigmaOrderNumber;
	private String caseNumber;
	private String itemNumber;
	private String equipmentType;
	private String accountNumber;
	private String traceNumber;
	private String dispatchDate;
	private String returnDate;
	private String condition;
	private EquipmentPack pack;
	private String pricePlan;
	
	// Add an amount of white space to the end of a string depending on its length
	public String extend(String field, int requiredLength){
		String str = field;
		for(int i = field.length();i < requiredLength; i++){
			str+=" ";
		}
		return str;
	}
	
	// assign an equipment pack item to a CNF line item
	public void setPack(EquipmentPack pack){
		simNumber=pack.getIccid();
		msisdn=pack.getMsisdn();
		packageId=pack.getPackID();
	}
	
	public void setImei(String imei){
		this.imeiNumber=imei;
	}
	
	// constructor, taking an ORD line-item and pack detail as input
	public CNFLineItem(ORDLineItem ordItem,EquipmentPack pack){
		System.out.println("Creating a CNF line Item...");
		simNumber=pack.getIccid();
		msisdn=pack.getMsisdn();
		
		
		// packageId
		String packId = pack.getPackID();
		String substring=packId.substring(0,ordItem.getProductId().length());
		packageId = packId.replace(substring, ordItem.getProductId());
		
		
		imsi = pack.getImsi();
		batchID = "0001";
		article="001";
		sigmaOrderNumber="SIG0001";
		caseNumber=ordItem.getCaseId();
		orderNumber=ordItem.getOrderNumber();
		itemNumber=ordItem.getItemNumber();
		equipmentType=ordItem.getEquipmentType();
		accountNumber=ordItem.getAccountNumber();
		traceNumber="TRA610168";
		dispatchDate="15012019";
		returnDate="";
		condition="New Order";
		
		// if a handset is needed, assign the IMEI
		if(ordItem.getEquipmentType().contains("Handset")) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("353yyMMddHHmmss");
			imeiNumber = simpleDateFormat.format(new Date());
		}
		else {
			imeiNumber="";
		}
	}
	
	// return the string value of the CNF line - use "extend" to add variable amounts of whitespace between fields
	public String toString(){
		
		//String imei = "";
		String pin1="1111";
		String puk1="111111";
		String pin2="2222";
		String puk2="222222";
		
		String cnfString="";
		cnfString = cnfString +batchID + ",";
		cnfString = cnfString +article + ",";
		cnfString = cnfString +simNumber + ",";
		cnfString = cnfString +msisdn + ",";
		cnfString = cnfString +packageId + ",";
		cnfString = cnfString +imeiNumber + ",";
		cnfString = cnfString +sigmaOrderNumber + ",";
		cnfString = cnfString +caseNumber + ",";
		cnfString = cnfString +orderNumber + ",";
		cnfString = cnfString +itemNumber + ",";
		cnfString = cnfString +equipmentType + ",";
		cnfString = cnfString +accountNumber + ",";
		cnfString = cnfString +traceNumber + ",";
		cnfString = cnfString +dispatchDate + ",";
		cnfString = cnfString +returnDate + ",";
		cnfString = cnfString +condition + ",";
		cnfString = cnfString +imsi + ",";
		cnfString = cnfString +pin1 + ",";
		cnfString = cnfString +puk1 + ",";
		cnfString = cnfString +pin2 + ",";
		cnfString = cnfString +puk2 + "\n";
			
		return cnfString;
	}
	
	
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(String pricePlan) {
		this.pricePlan = pricePlan;
	}

	public String getBatchID() {
		return batchID;
	}

	public String getArticle() {
		return article;
	}

	public String getSimNumber() {
		return simNumber;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public String getPackageId() {
		return packageId;
	}

	public String getImeiNumber() {
		return imeiNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getSigmaOrderNumber() {
		return sigmaOrderNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getTraceNumber() {
		return traceNumber;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public String getCondition() {
		return condition;
	}

	public EquipmentPack getPack() {
		return pack;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setSigmaOrderNumber(String sigmaOrderNumber) {
		this.sigmaOrderNumber = sigmaOrderNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}

package microservices.backend.eir_logistics_backend.utility;

import java.util.ArrayList;

import microservices.backend.eir_logistics_backend.file_objects.CNFFile;
import microservices.backend.eir_logistics_backend.file_objects.ORDFile;

public class LogisticsReport {

	private ORDFile ordFile;
	private CNFFile cnfFile;
	private ArrayList<String> msisdns;
	
	public LogisticsReport() {
		ordFile=null;
	}	
	
	public ORDFile getOrdFile() {
		return ordFile;
	}

	public void setOrdFile(ORDFile ordFile) {
		this.ordFile = ordFile;
	}


	public CNFFile getCnfFile() {
		return cnfFile;
	}

	public void setCnfFile(CNFFile cnfFile) {
		this.cnfFile = cnfFile;
	}

	public ArrayList<String> getMsisdns() {
		return msisdns;
	}
	public void setMsisdns(ArrayList<String> msisdns) {
		this.msisdns = msisdns;
	}
	
	public void addMsisdn(String msisdn) {
		if(msisdns==null) {
			msisdns=new ArrayList<String>();
		}
		msisdns.add(msisdn);
	}
	
	public String toString() {
		return ordFile + "\n" + cnfFile + "\n" + msisdns.size();
	}
}

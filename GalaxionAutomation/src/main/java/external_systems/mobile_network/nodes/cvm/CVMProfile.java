package external_systems.mobile_network.nodes.cvm;

import org.w3c.dom.NodeList;

import utilities.generic.files.TextReader;

public class CVMProfile {

	private String msisdn;
	private String serviceNumber;
	private String voicemailType;
	private String description;
	private boolean retrievalOk;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public CVMProfile(String msisdn) {

		super();

		// change to +35385 format
		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}
		
		this.msisdn=msisdn;
	}

	// =================================================================================================================
	// Getters and Setters
	// =================================================================================================================
	
	public String getMsisdn08x() {
		if(msisdn.startsWith("3538")) {
			return msisdn.replaceFirst("3538", "08");
		}
		return msisdn;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getVoicemailType() {
		return voicemailType;
	}

	public void setVoicemailType(String voicemailType) {
		this.voicemailType = voicemailType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 

	public boolean isRetrievalOk() {
		return retrievalOk;
	}

	public void setRetrievalOk(boolean retrievalOk) {
		this.retrievalOk = retrievalOk;
	}

	public void print() {
		System.out.println("\n***** PRINTING CVM PROFILE DETAILS *****\n");
		System.out.println("SN: " + serviceNumber);
		System.out.println("VM_TYPE: " + voicemailType);
		System.out.println("VM_DESC: " + description);
	}

	// return the HLR profile as a HTML string
	public String asHTML() {

		// template string
		String htmlString = TextReader.getContent("templates\\network_extracts\\table_cvm.html");
		htmlString=htmlString.replace("$msisdn", serviceNumber);
		htmlString=htmlString.replace("$voicemailType", voicemailType);
		htmlString=htmlString.replace("$voicemailDescription", description);
		return htmlString;
	}


	// main
	public static void main(String[] args) {
		
	}
}
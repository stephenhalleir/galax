package external_systems.mobile_network.nodes.spr;

import utilities.generic.files.TextReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.network.SOAPUtility;

public class SPRProfile {

	private String subID;
	private String accountID;
	private String billingCycleName;
	private String imsi;
	private String msisdn;
	private String pricePlan;
	private String subscriberType;
	private String operator;
	private String notificationFlag;
	private String aocOptedIn;
	private String billingSource;
	private String cfsp;
	private String lteEnabled;
	private String ecsFlag;
	private String wifiCallingEnabled;
	private boolean retrievalOk;
	private String NR5G;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	public SPRProfile() {
		super();
		this.subID = "";
		this.accountID = "";
		this.imsi = "";
		this.msisdn = "";
		this.pricePlan = "";
		this.billingCycleName="";
		this.subscriberType = "";
		this.operator = "";
		this.notificationFlag = "";
		this.aocOptedIn = "";
		this.billingSource = "";
		this.cfsp = "";
		this.lteEnabled = "";
		this.ecsFlag = "";
		this.wifiCallingEnabled = "";
		this.retrievalOk=false;
	}

	public SPRProfile(String msisdn) {

		super();

		// change to +35385 format
		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}

		this.msisdn = msisdn;

		this.subID = "";
		this.accountID = "";
		this.imsi = "";
		this.pricePlan = "";
		this.subscriberType = "";
		this.operator = "";
		this.notificationFlag = "";
		this.aocOptedIn = "";
		this.billingSource = "";
		this.cfsp = "";
		this.lteEnabled = "";
		this.ecsFlag = "";
		this.wifiCallingEnabled = "";
		this.retrievalOk=false;
	}

	// =================================================================================================================
	// Getters and Setters
	// =================================================================================================================

	public String getSubID() {
		return subID;
	}

	public boolean isRetrievalOk() {
		return retrievalOk;
	}

	public void setRetrievalOk(boolean retrievalOk) {
		this.retrievalOk = retrievalOk;
	}

	public void setSubID(String subID) {
		this.subID = subID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public String getMsisdn08x() {
		if(msisdn.startsWith("3538")) {
			return msisdn.replaceFirst("3538", "08");
		}
		return msisdn;
	}

	public String getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(String pricePlan) {
		this.pricePlan = pricePlan;
	}

	public String getSubscriberType() {
		return subscriberType;
	}

	public void setSubscriberType(String subscriberType) {
		this.subscriberType = subscriberType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getNotificationFlag() {
		return notificationFlag;
	}

	public void setNotificationFlag(String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}

	public String getAocOptedIn() {
		return aocOptedIn;
	}

	public void setAocOptedIn(String aocOptedIn) {
		this.aocOptedIn = aocOptedIn;
	}

	public String getBillingSource() {
		return billingSource;
	}

	public void setBillingSource(String billingSource) {
		this.billingSource = billingSource;
	}

	public String getCfsp() {
		return cfsp;
	}

	public void setCfsp(String cfsp) {
		this.cfsp = cfsp;
	}

	public String getLteEnabled() {
		return lteEnabled;
	}

	public void setLteEnabled(String lteEnabled) {
		this.lteEnabled = lteEnabled;
	}

	public String getEcsFlag() {
		return ecsFlag;
	}

	public void setEcsFlag(String ecsFlag) {
		this.ecsFlag = ecsFlag;
	}

	public String getWifiCallingEnabled() {
		return wifiCallingEnabled;
	}

	public void setWifiCallingEnabled(String wifiCallingEnabled) {
		this.wifiCallingEnabled = wifiCallingEnabled;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================
	
	public String getBillingCycleName() {
		return billingCycleName;
	}

	public void setBillingCycleName(String billingCycleName) {
		this.billingCycleName = billingCycleName;
	}

	// print the SPR details to the screen for debugging
	public void print() {
		System.out.println("\n***** PRINTING ECS PROFILE DETAILS *****\n");
		System.out.println("SPR SUB ID: " + subID);
		System.out.println("SPR Account ID: " + accountID);
		System.out.println("SPR MSISDN: " + msisdn);
		System.out.println("SPR IMSI: " + imsi);
		System.out.println("SPR Price Plan: " + pricePlan);
		System.out.println("SPR Subscriber Type:" + subscriberType);
		System.out.println("SPR Operator:" + operator);
		System.out.println("SPR NotificationFlag:" + notificationFlag);
		System.out.println("SPR AoC:" + aocOptedIn);
		System.out.println("SPR BillingSource: " + billingSource);
		System.out.println("SPR CSFP: " + cfsp);
		System.out.println("SPR LTE: " + lteEnabled);
		System.out.println("SPR ECS: " + ecsFlag);
		System.out.println("SPR WiFi Calling: " + wifiCallingEnabled);
	}
	
	public String asHTML() {
		
		// define the base HTML string for the table
		String htmlString=TextReader.getContent("templates\\network_extracts\\table_spr.html");
		
		// sub in the SPR profile values
		htmlString = htmlString.replace("$subID",subID);
		htmlString = htmlString.replace("$accountID",accountID);
		htmlString = htmlString.replace("$msisdn",msisdn);
		htmlString = htmlString.replace("$imsi",imsi);
		htmlString = htmlString.replace("$pricePlan",pricePlan);
		htmlString = htmlString.replace("$subscriberType",subscriberType);
		htmlString = htmlString.replace("$operator",operator);
		htmlString = htmlString.replace("$notificationFlag",notificationFlag);
		htmlString = htmlString.replace("$aocOptedIn",aocOptedIn);
		htmlString = htmlString.replace("$billingSource",billingSource);
		htmlString = htmlString.replace("$cfsp",cfsp);
		htmlString = htmlString.replace("$lte",lteEnabled);
		htmlString = htmlString.replace("$ecs",ecsFlag);
		htmlString = htmlString.replace("$wifi",wifiCallingEnabled);
				
		// return the table string
		return htmlString;
	}
	
	// populate the profile using a SOAP request to MMW
		public void populate() {

			SOAPUtility soapUtility = new SOAPUtility();

			// request to interrogate SPR
			String url = EnvironmentExcelConfigReader.getMMWConfigValue("SPR.GETPROFILE");
			String xml = TextReader.getContent("requests/soap/spr/get_subscriber_details");

			System.out.println("SPR: Sending request:\n" + xml.replace("$msisdn", msisdn) + "\nTo: " + url);
			String response = soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn));
			System.out.println("SPR Response: " + response);
			// only if the request was successful
			if(response != null) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder builder = factory.newDocumentBuilder();
					InputSource is = new InputSource(new StringReader(response));
					Document doc = builder.parse(is);

					// read in the values from the SOAP response
					subID = doc.getElementsByTagName("met:SubscriberId").item(0).getFirstChild().getNodeValue();
					accountID = doc.getElementsByTagName("met:CustomerAccountId").item(0).getFirstChild().getNodeValue();
					msisdn = doc.getElementsByTagName("met:Msisdn").item(0).getFirstChild().getNodeValue();
					imsi = doc.getElementsByTagName("met:Imsi").item(0).getFirstChild().getNodeValue();
					pricePlan = doc.getElementsByTagName("met:PricePlan").item(0).getFirstChild().getNodeValue();
					subscriberType = doc.getElementsByTagName("met:SubscriberType").item(0).getFirstChild().getNodeValue();
					operator = doc.getElementsByTagName("met:Operator").item(0).getFirstChild().getNodeValue();
					notificationFlag = doc.getElementsByTagName("met:NotificationFlag").item(0).getFirstChild().getNodeValue();
					aocOptedIn = doc.getElementsByTagName("met:AOCFlag").item(0).getFirstChild().getNodeValue();
					billingSource = doc.getElementsByTagName("met:BillingSource").item(0).getFirstChild().getNodeValue();
					cfsp = doc.getElementsByTagName("met:CFSProfile").item(0).getFirstChild().getNodeValue();
					lteEnabled = doc.getElementsByTagName("met:LTE").item(0).getFirstChild().getNodeValue();
					ecsFlag = doc.getElementsByTagName("met:ECS").item(0).getFirstChild().getNodeValue();
					wifiCallingEnabled = doc.getElementsByTagName("met:WifiCalling").item(0).getFirstChild().getNodeValue();
					NR5G=doc.getElementsByTagName("met:NR5G").item(0).getFirstChild().getNodeValue();
					this.retrievalOk=true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	public String getNR5G() {
		return NR5G;
	}

	public void setNR5G(String nR5G) {
		NR5G = nR5G;
	}
}

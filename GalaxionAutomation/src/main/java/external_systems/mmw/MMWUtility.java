package external_systems.mmw;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import external_systems.mobile_network.nodes.cvm.CVMProfile;
import external_systems.mobile_network.nodes.ec20.components.OCSDABalance;
import external_systems.mobile_network.nodes.ec20.components.OCSDataBundle;
import external_systems.mobile_network.nodes.ec20.components.OCSOffer;
import external_systems.mobile_network.nodes.ec20.profile.EC20Profile;
import external_systems.mobile_network.nodes.ec20.utility.HLRFEUtil;
import external_systems.mobile_network.nodes.hlr_fe.components.EMASession;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.files.TextReader;
import utilities.network.SOAPUtility;

public class MMWUtility {

	public static SPRProfile getSprProfile(String msisdn) {

		// convert the MSISDN into 35385 format
		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}

		// build the request to interrogate SPR
		String url = EnvironmentExcelConfigReader.getMMWConfigValue("SPR.GETPROFILE");
		String request = TextReader.getContent("requests/soap/spr/get_subscriber_details");
		request = request.replace("$msisdn", msisdn);

		SPRProfile profile = new SPRProfile(msisdn);
		profile.populate();
		
		/*
		// make the request
		Response r = RestAssuredSoapUtil.galaxionSoapPost(url, request);

		if (r.statusCode() != 200) {
			System.err.println("Error: SPR retrieval has returned " + r.statusCode() + ", " + r.asString());
			return null;
		} else {

			// create the profile
			SPRProfile profile = new SPRProfile();
			XmlPath xml = new XmlPath(r.asString());

			// read in the values from the SOAP response
			profile.setSubID(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.SubscriberId"));
			profile.setAccountID(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.SubscriberAccount.CustomerAccountId"));
			profile.setBillingCycleName(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.SubscriberAccount.CustomerBillingCycleSpecName"));
			profile.setMsisdn(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.Msisdn"));
			profile.setImsi(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.Imsi"));
			profile.setPricePlan(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.PricePlan"));
			profile.setSubscriberType(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.SubscriberType"));
			profile.setOperator(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.Operator"));
			profile.setNotificationFlag(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.NotificationFlag"));
			profile.setAocOptedIn(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.AOCFlag"));
			profile.setBillingSource(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.BillingSource"));
			profile.setCfsp(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.CFSProfile"));
			profile.setLteEnabled(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.LTE"));
			profile.setEcsFlag(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.ECS"));
			profile.setWifiCallingEnabled(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.WifiCalling"));
			profile.setNR5G(xml.getString("Envelope.Body.GetSubscriberDetailsResponse.NR5G"));
			profile.setRetrievalOk(true);
			return profile;
		}
		*/
		
		return profile;
	}

	public static String getEC20CreditBalanceStr(String msisdn) {

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.GETPROFILE");

		// read the request from the template
		String xml = TextReader.getContent("requests/soap/in/getMainBalance");

		// send the request to the endpoint and get the response
		String response = SOAPUtility.getPostResponse(endpoint, xml.replace("$msisdn", msisdn));
		System.err.println("*" + response);

		// only if the response is successful
		if (response != null) {

			// parse the response
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = builder.parse(is);

				// read in the profile level information from the response
				NodeList balanceAttributes = doc.getElementsByTagName("v12:balance").item(0).getChildNodes();

				for (int i = 0; i < balanceAttributes.getLength(); i++) {
					Node n = balanceAttributes.item(i);
					if (n.getNodeName().equals("v12:balance")) {
						return (n.getTextContent());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static ArrayList<OCSDataBundle> getEC20DataBundles(String msisdn) {

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.GETPROFILE");

		// clear the data balances list
		ArrayList<OCSDataBundle> dataBundles = new ArrayList<OCSDataBundle>();

		String xml = TextReader.getContent("requests/soap/in/queryDataBundle");

		// send the request to the endpoint and get the response
		String response = SOAPUtility.getPostResponse(endpoint, xml.replace("$msisdn", msisdn));
		System.err.println("Data Bundles: " + response);

		// only if the response is successful
		if (response != null) {

			// parse the response
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = builder.parse(is);

				// balancesList will return the list of <item> nodes inside
				// <QueryDataBundleDetails>
				NodeList itemsList = doc.getElementsByTagName("v1:QueryDataBundleDetails").item(0).getChildNodes();

				// for each balance in the list
				for (int itemsCounter = 0; itemsCounter < itemsList.getLength(); itemsCounter++) {

					Node itemNode = itemsList.item(itemsCounter);
					System.out.println(itemNode.getNodeName());

					NodeList balanceList = itemNode.getChildNodes();

					// for each child/balance in the <v1:item> node
					for (int balanceCounter = 0; balanceCounter < balanceList.getLength(); balanceCounter++) {

						// create a new balance object
						OCSDataBundle balance = new OCSDataBundle();
						Node balanceNode = balanceList.item(balanceCounter);

						// read the attribute values
						NodeList balanceAttributes = balanceNode.getChildNodes();

						// for each attribute value
						for (int j = 0; j < balanceAttributes.getLength(); j++) {
							Node attributeNode = balanceAttributes.item(j);

							// assign the relevant attributes to the balance
							switch (attributeNode.getNodeName()) {
							case "v1:balanceId":
								balance.setBalanceId(attributeNode.getTextContent());
								break;
							case "v1:balanceType":
								balance.setBalanceType(attributeNode.getTextContent());
								break;
							case "v1:balanceAmount":
								balance.setBalanceAmount(attributeNode.getTextContent());
								break;
							case "v1:balanceAvailable":
								balance.setBalanceAvailable(attributeNode.getTextContent());
								break;
							case "v1:effectiveDate":
								balance.setEffectiveDate(attributeNode.getTextContent());
								break;
							case "v1:expiryDate":
								balance.setExpiryDate(attributeNode.getTextContent());
								break;
							case "v1:category":
								balance.setCategory(attributeNode.getTextContent());
								break;
							}
						}

						// add the balance to the list
						dataBundles.add(balance);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dataBundles;
	}

	// populate the CS5 profile with details retrieved from MMW/CS5
	public static ArrayList<OCSOffer> getEC20Offers(String msisdn) {

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.GETPROFILE");

		// clear the offers list
		ArrayList<OCSOffer> offers = new ArrayList<OCSOffer>();

		// get the request template
		String xml = TextReader.getContent("requests/soap/in/getOffers");

		// send the request to the endpoint and get the response
		String response = SOAPUtility.getPostResponse(endpoint, xml.replace("$msisdn", msisdn));

		// only if the response is successful
		if (response != null) {

			// parse the response
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = builder.parse(is);

				// find all nodes with name "v1:Offer"
				NodeList list = doc.getElementsByTagName("v1:Offer");

				// for each Offer node in the list
				for (int itemsCounter = 0; itemsCounter < list.getLength(); itemsCounter++) {

					// create a new offer object
					OCSOffer offer = new OCSOffer();

					// get the current offer node
					Node itemNode = list.item(itemsCounter);

					// get the list of child nodes (attributes) from the current offer node
					NodeList attributeList = itemNode.getChildNodes();

					// for each attribute in the offer
					for (int attributeCounter = 0; attributeCounter < attributeList.getLength(); attributeCounter++) {

						// get the current attribute node
						Node attributeNode = attributeList.item(attributeCounter);

						// populate the offer object based on the node name
						switch (attributeNode.getNodeName()) {
						case "v1:OfferId":
							offer.setOfferID(Integer.parseInt(attributeNode.getTextContent()));
							break;
						case "v1:OfferName":
							offer.setOfferName(attributeNode.getTextContent());
							break;
						case "v1:ExpiryDate":
							offer.setExpiryDate(attributeNode.getTextContent());
							break;
						}
					}

					// add the offers to the list
					offers.add(offer);
				}

				return offers;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static EC20Profile getEC20SubscriberDetails(String msisdn) {

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.GETPROFILE");

		// read the request
		String xml = TextReader.getContent("requests/soap/in/getSubscriberDetails");
		xml=xml.replace("$msisdn", msisdn);

		// post the request and retrieve the response
		Response r = RestAssuredUtil.galaxionSoapPost(endpoint, xml);

		if (r.statusCode() != 200) {
			return null;
		} else {
			XmlPath xmlPath = new XmlPath(r.asString());

			EC20Profile profile = new EC20Profile(msisdn);
			profile.setTariffPlan(xmlPath.getString("Envelope.Body.getSubscriberDetailsResponse.INSubscriberDetails.Subscriber.BaseProductOfferingName"));
			profile.setStatus(xmlPath.getString("Envelope.Body.getSubscriberDetailsResponse.INSubscriberDetails.Subscriber.LifecycleStatus"));
			return profile;
		}
	}

	public static ArrayList<OCSDABalance> getEC20DABalances(String msisdn) {

		ArrayList<OCSDABalance> balances = new ArrayList<OCSDABalance>();

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.GETPROFILE");

		String xml = TextReader.getContent("requests/soap/in/getBalances");

		// send the request to the endpoint and get the response
		String response = SOAPUtility.getPostResponse(endpoint, xml.replace("$msisdn", msisdn));
		System.err.println(response);

		// only if the response is successful
		if (response != null) {

			// parse the response
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = builder.parse(is);

				// balancesList will return the list of <item> nodes inside
				// <QueryDataBundleDetails>
				NodeList daList = doc.getElementsByTagName("v12:Balances").item(0).getChildNodes();

				// for each balance in the list
				for (int promoCounter = 0; promoCounter < daList.getLength(); promoCounter++) {

					Node promotionNode = daList.item(promoCounter);

					NodeList attributeList = promotionNode.getChildNodes();

					OCSDABalance balance = new OCSDABalance();

					for (int i = 0; i < attributeList.getLength(); i++) {
						Node n = attributeList.item(i);

						// assign the relevant attributes to the balance
						switch (n.getNodeName()) {
						case "v12:eventType":
							balance.setEventType(n.getTextContent());
							break;
						case "v12:balance":
							balance.setBalance(n.getTextContent());
							break;
						case "v12:balanceCategory":
							balance.setBalanceCategory(n.getTextContent());
							break;
						case "v12:expiryDate":
							balance.setExpiryDate(n.getTextContent());
							break;
						}
					}

					// add the balance to the list
					balances.add(balance);
					System.out.println("DA " + balances.size() + " found: " + balance.getEventType());
				}
				return balances;
				// end DAs
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static EC20Profile getEC20Profile(String msisdn) {

		try {
			EC20Profile profile = getEC20SubscriberDetails(msisdn);
			profile.setDaBalances(getEC20DABalances(msisdn));
			profile.setOffers(getEC20Offers(msisdn));
			profile.setDataBundles(getEC20DataBundles(msisdn));
			profile.setAccountBalance(getEC20CreditBalanceStr(msisdn));
			return profile;
		} catch (Exception e) {
			return null;
		}
	}

	public static String provisionEC20Subscriber(String msisdn, String tariffPlan, String cfsp) {
		String request = TextReader.getContent("requests/soap/in/createSubscriberWithBalance");
		request = request.replace("$msisdn", msisdn);
		request = request.replace("$tariffPlan", tariffPlan);
		request = request.replace("$cfsp", cfsp);

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.PROVISIONINGPROXY");

		return SOAPUtility.getPostResponse(endpoint, request);
	}

	public static String deleteEC20Subscriber(String msisdn) {

		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}

		String request = TextReader.getContent("requests/soap/in/deleteSubscriber");
		request = request.replace("$msisdn", msisdn);

		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.PROVISIONINGPROXY");

		return SOAPUtility.getPostResponse(endpoint, request);
	}

	public static void reprovisionEC20Subscriber(String msisdn, String cfsp) {
		EC20Profile profile = getEC20SubscriberDetails(msisdn);
		deleteEC20Subscriber(msisdn);
		provisionEC20Subscriber(msisdn, profile.getTariffPlan(), profile.getCfsp());
	}

	public static void reprovisionEC20Subsriber(String msisdn) {
		EC20Profile profile = getEC20SubscriberDetails(msisdn);
		if (profile.getCfsp() == null) {
			SPRProfile spr = MMWUtility.getSprProfile(msisdn);
			reprovisionEC20Subscriber(msisdn, spr.getCfsp());
		}
	}

	// fire an AoC acceptance request or a particular band
	public static String sendEC20AoCAcceptanceRequest(String msisdn, String tariffPlan, String type, int zone) {

		// retrieve and build the request
		String soapRequest = TextReader.getContent("requests/soap/in/aocAcceptTerms");
		soapRequest = soapRequest.replace("$msisdn", msisdn);
		soapRequest = soapRequest.replace("$tariffPlan", tariffPlan);
		soapRequest = soapRequest.replace("$zone", Integer.toString(zone));
		soapRequest = soapRequest.replace("$notificationType", type);

		// retrieve the URL
		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("OCS.PROVISIONINGPROXY");

		// send the request and return the result
		return SOAPUtility.getPostResponse(endpoint, soapRequest);
	}

	public static CVMProfile getVoicemailProfile(String msisdn) {

		// get the endpoint from the config file
		String soapEndpoint = EnvironmentExcelConfigReader.getMMWConfigValue("CVM.PROVISIONINGPROXY");
		String xml = TextReader.getContent("requests\\soap\\cvm\\getMailboxStatus");
		xml = xml.replace("$msisdn", msisdn);

		// post the request and retrieve the response
		Response r = RestAssuredUtil.galaxionSoapPost(soapEndpoint, xml);

		if (r.statusCode() == 200) {
			XmlPath xmlPath = new XmlPath(r.asString());

			String value = xmlPath.getString("Envelope.Body.getMailboxStatusResponse.mailboxStatus");

			String[] responseParts = value.split(";");

			String serviceNumber = responseParts[0];
			String voicemailType = responseParts[1];
			String description = responseParts[2];

			CVMProfile profile = new CVMProfile(msisdn);

			// trim the "SN=" and "VM_TYPE=" off the values
			profile.setDescription(description.substring(description.indexOf("=") + 1));
			profile.setVoicemailType(voicemailType.substring(voicemailType.indexOf("=") + 1));
			profile.setServiceNumber(serviceNumber.substring(serviceNumber.indexOf("=") + 1));
			profile.setRetrievalOk(true);
			return profile;
		} else {
			return null;
		}
	}

	public static boolean terminateCVMProfile(String msisdn) {

		// request to delete from the CVM
		String url = EnvironmentExcelConfigReader.getMMWConfigValue("CVM.PROVISIONINGPROXY");

		String xml = TextReader.getContent("requests\\soap\\cvm\\terminateSubscriber");

		String response = SOAPUtility.getPostResponse(url, xml.replace("$msisdn", msisdn));

		if (response != null) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = builder.parse(is);

				// read in the values from the SOAP response
				String statusCode = doc.getElementsByTagName("com:status").item(0).getFirstChild().getNodeValue();
				return statusCode.equals("1");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static String sendSMSKeyword(String shortcode, String msisdn, String message) {
		System.out.println("Sending keyword " + message + " to " + shortcode + " for " + msisdn);

		// set up the template SOAP message
		String soapRequest = TextReader.getContent("requests\\soap\\sms_app\\sms_app_request");

		// populate the SOAP request with the relevant inputs
		soapRequest = soapRequest.replace("$shortcode", shortcode).replace("$msisdn", msisdn).replace("$message", message);

		System.out.println("SOAP Request:\n" + soapRequest);

		String endpoint = EnvironmentDirectory.getMMWEndpoint() + "/Interceptor/SMSUnitTestProxy?wsdl";

		// send the SOAP request to the endpoint
		String soapResponse = SOAPUtility.getPostResponse(endpoint, soapRequest);
		System.out.println("SOAP Response:\n" + soapResponse);

		// return the value of the "v1:responseSMSMessage" node
		return SOAPUtility.getNodeValue(soapResponse, "v1:responseSMSMessage");
	}

	public static void rateMMS(String aMsisdn, String aImsi) {

		String url = "http://mwatst01.eircom.ie:7022/Interceptor/SAMIRaterURLProxy";
		String contents = TextReader.getContent("requests//soap//mms//mms_rating");
		contents = contents.replace("$msisdn", aMsisdn);
		contents = contents.replace("$imsi", aImsi);
		System.out.println(contents);
		Response r = RestAssuredUtil.galaxionPost(url, contents, null);
		System.out.println(r.getStatusCode() + ", " + r.asString());
	}

	public static HLRFEProfile getHLRFEProfile(String msisdn) {

		// change to 35385 format
		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}

		// get a session ID
		EMASession session = HLRFEUtil.login();
		String sessionID = session.getSessionId();
		System.out.println("SessionID found: " + sessionID);

		// get the endpoint from the config file
		String soapEndpoint = "http://mwatst01.eircom.ie:7022/Interceptor/CommonEDA_BSP_EDAHLR_Business_EDAHLRBusiness_INTCP";

		// create the Get request
		String xmlRequest = TextReader.getContent("requests/soap/hlr/queryHLRFull").replace("$msisdn", msisdn).replace("$sessionID", sessionID);

		// post the request and retrieve the response
		Response r = RestAssuredUtil.HLRFESoapPost(soapEndpoint, xmlRequest, "Get");
		System.err.println("r=" + r.statusCode());

		if (r.statusCode() != 200) {
			return null;
		} else {
			XmlPath xml = new XmlPath(r.asString());

			HLRFEProfile profile = new HLRFEProfile(msisdn);

			profile.setImsi(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.imsi"));
			profile.setMsisdn(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.msisdn"));
			profile.setCsp(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.csp"));
			profile.setOsb4(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.osb4"));
			profile.setObopre(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.obopre"));
			profile.setRsa(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.rsa"));
			profile.setSchar(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.schar"));
			profile.setBs26(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.bs26"));
			profile.setBs3g(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.bs3g"));
			profile.setCat(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.cat"));
			profile.setClip(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.clip"));
			profile.setClir(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.clir"));
			profile.setDbsg(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.dbsg"));
			profile.setHold(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.hold"));
			profile.setObi(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.obi"));
			profile.setObo(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.obo"));
			profile.setObopre(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.obopre"));
			profile.setObopri(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.obopri"));
			profile.setObrf(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.obrf"));
			profile.setOsb2(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.osb2"));
			profile.setOsb3(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.osb3"));
			profile.setIcs(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.ics"));
			profile.setOfa(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.ofa"));
			profile.setPwd(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.pwd"));
			profile.setSocb(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.socb"));
			profile.setSocfb(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.socfb"));
			profile.setSocfu(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.socfu"));
			profile.setSocfrc(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.socfrc"));
			profile.setSoclip(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.soclip"));
			profile.setTcsist(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.tcsist"));
			profile.setTs11(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.ts11"));
			profile.setTs21(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.ts21"));
			profile.setTs22(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.ts22"));
			profile.setRedmch(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.redmch"));
			profile.setOcsist(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.ocsist"));
			profile.setTcsist(xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.tcsist"));

			// APNs
			List<Object> obj = xml.getList("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.gprs");
			for (int i = 0; i < obj.size(); i++) {
				String id = xml.getString("Envelope.Body.GetResponse.MOAttributes.getResponseSubscription.gprs[" + i + "].apnid");
				profile.getApns().add(Integer.parseInt(id));
			}
			profile.setRetrievalOk(true);
			return profile;
		}
	}

	public static String terminateSPRSubscriber(String msisdn) {

		SPRProfile spr = getSprProfile(msisdn);

		// request to interrogate SPR
		String url = EnvironmentExcelConfigReader.getMMWConfigValue("SPR.GETPROFILE");

		String xml = TextReader.getContent("requests/soap/spr/terminate_subscriber");

		xml = xml.replace("$subscriberId", spr.getSubID()).replace("$billingSource", spr.getBillingSource());
		System.out.println("SPR: Sending terminate request:\n" + xml.replace("$subscriberId", msisdn) + "\nTo: " + url);
		String response = SOAPUtility.getPostResponse(url, xml.replace("$msisdn", msisdn));
		System.out.println("SPR  terminate Response: " + response);

		return response;
	}

	public static String recreateAsIon(String networkId, String msisdn, String imsi, String pricePlan, String cfsp) {

		String url = EnvironmentExcelConfigReader.getMMWConfigValue("SPR.GETPROFILE");

		String xml = TextReader.getContent("requests/soap/spr/recreate_as_ion");

		xml = xml.replace("$msisdn", msisdn);
		xml = xml.replace("$imsi", imsi);
		xml = xml.replace("$networkId", networkId);
		xml = xml.replace("$pricePlan", pricePlan);
		xml = xml.replace("$cfsp", cfsp);

		System.out.println(networkId);
		System.out.println(xml);

		String response = SOAPUtility.getPostResponse(url, xml);
		return response;
	}
}

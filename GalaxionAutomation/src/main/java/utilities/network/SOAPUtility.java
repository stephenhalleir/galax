package utilities.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;

public class SOAPUtility {

	public static String getPostResponse(String endpoint, String soapRequest) {

		System.out.println("SOAPUtility: Sending request:\n" + soapRequest + "\nTo: " + endpoint);
		
		try {
			// create the java.net.URL object from the URL string
			URL obj = new URL(endpoint);

			// create and configure the connection
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");

			con.setDoOutput(true);

			// send the request
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(soapRequest);
			wr.flush();
			wr.close();

			// read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			// build the response string
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			// close the connection
			in.close();

			// System.out.println("SOAPUtility.get(): " + response.toString());
			// System.err.println("GetPostRequest: Response code = " +
			// con.getResponseCode());

			System.out.println("SOAPUtility: Response:\n" + response.toString());
			
			// return the output
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static String getEMAPostResponse(String url, String xmlRequest, String method) {

		try {
			// create the java.net.URL object from the URL string
			URL obj = new URL(url);

			// create and configure the connection
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			con.setRequestProperty("accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
			con.setRequestProperty("connection", "keep-alive");

			con.setRequestProperty("SOAPAction", "CAI3G#" + method);

			con.setDoOutput(true);

			// send the request
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(xmlRequest);
			wr.flush();
			wr.close();

			// read the response
			BufferedReader in=null;
			
			try {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			catch(Exception e) {
				System.err.println("Exception happened in getEMAPostResponse()");
				return null;
			}

			// build the response string
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			//System.out.println("SOAPUtility.EMAget(): " + response.toString());

			// close the connection
			in.close();
			if (con.getResponseCode() == 200) {
				// return the output
				return response.toString();
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}

	public static void main(String[] args) {

		String msisdn = "353852323596";

		String url, xml;
		SOAPUtility soapUtility = new SOAPUtility();

		// request to interrogate IN to check all details
		url = "http://mwatst02.eircom.ie:7001/INQueryService/MeteorSOA_Business_INQuery";
		xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:met=\"http://www.meteor.ie/MeteorSOA_Business_INQuery/\" xmlns:v1=\"http://www.meteor.ie/SOA/1.0/Common/IN/v1\" xmlns:v11=\"http://www.meteor.ie/SOA/1.0/IN/v1\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <met:getSubscriberDetails>\r\n" + "         <v1:MSISDN>$msisdn</v1:MSISDN>\r\n"
				+ "         <v11:Correlation>\r\n" + "            <correlationID>4366344696155127430</correlationID>\r\n" + "         </v11:Correlation>\r\n"
				+ "      </met:getSubscriberDetails>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";
		System.out.println("IN tariff plan:\t" + soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));
		parseINGetTariffPlanResponse(soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));

		// request to interrogate SPR
		url = "http://mwatst02.eircom.ie:7022/Interceptor/Common_BSP_SPR_Business_SPRBusiness_INTCP";
		xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:met=\"http://www.meteor.ie/SOA/1.0/MeteorDataModelSPR\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <met:GetSubscriberDetailsRequest>\r\n" + "         <met:serviceUser>\r\n"
				+ "            <met:user>?</met:user>\r\n" + "            <met:applicationDescriptor>?</met:applicationDescriptor>\r\n"
				+ "            <met:applicationIdentifier>?</met:applicationIdentifier>\r\n" + "         </met:serviceUser>\r\n"
				+ "         <met:Msisdn>$msisdn</met:Msisdn>\r\n" + "      </met:GetSubscriberDetailsRequest>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		// System.out.println("SPR:\t" + getPostResponse(url, xml.replace("$msisdn",
		// msisdn)));
		parseSPRResponse(soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));

		// request to interrogate the EHLR
		url = "http://mwatst02.eircom.ie:7022/Interceptor/EMAProvisioningProxy";
		xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:met=\"http://www.meteor.ie/SOA/1.0/MeteorDataModelEMAProvisioningService\" xmlns:com=\"http://www.meteor.ie/SOA/1.0/Common\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <met:queryHLRSettingRequest>\r\n" + "         <com:serviceUser>\r\n"
				+ "            <com:user>?</com:user>\r\n" + "            <com:applicationDescriptor>?</com:applicationDescriptor>\r\n"
				+ "            <com:applicationIdentifier>?</com:applicationIdentifier>\r\n" + "         </com:serviceUser>\r\n"
				+ "         <met:msisdn>$msisdn</met:msisdn>\r\n" + "      </met:queryHLRSettingRequest>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		//System.out.println("EMA:\t" + soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));
		parseHLRResponse(soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));

		// request to EMA for CSP
		url = "http://mwatst02.eircom.ie:7022/Interceptor/Common_BSP_EHLR_Business_EHLRBusiness_INTCP";
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soapenv:Header>\r\n"
				+ "    <cai3:SequenceId xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">1890629843454963441</cai3:SequenceId>\r\n"
				+ "    <cai3:TransactionId xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">3333</cai3:TransactionId>\r\n"
				+ "    <cai3:SessionId xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">100a1206100a1206000000001583773904977</cai3:SessionId>\r\n"
				+ "  </soapenv:Header>\r\n" + "  <soapenv:Body>\r\n" + "    <cai3:Get xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">\r\n"
				+ "      <cai3:MOType>Subscription@http://schemas.ericsson.com/ema/UserProvisioning/GsmHlr/</cai3:MOType>\r\n" + "      <cai3:MOId>\r\n"
				+ "        <cai3:msisdn>353852323596</cai3:msisdn>\r\n" + "      </cai3:MOId>\r\n" + "    </cai3:Get>\r\n" + "  </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		// request for Common_BSP_EHLR_Business_EHLRBusiness_INTCP_Get
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soapenv:Header>\r\n"
				+ "    <cai3:SequenceId xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">1890629843454963441</cai3:SequenceId>\r\n"
				+ "    <cai3:TransactionId xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">3333</cai3:TransactionId>\r\n"
				+ "    <cai3:SessionId xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">100a1206100a1206000000001583773904977</cai3:SessionId>\r\n"
				+ "  </soapenv:Header>\r\n" + "  <soapenv:Body>\r\n" + "    <cai3:Get xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">\r\n"
				+ "      <cai3:MOType>Subscription@http://schemas.ericsson.com/ema/UserProvisioning/GsmHlr/</cai3:MOType>\r\n" + "      <cai3:MOId>\r\n"
				+ "        <cai3:msisdn>353852323596</cai3:msisdn>\r\n" + "      </cai3:MOId>\r\n" + "    </cai3:Get>\r\n" + "  </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		// request for Common_BSP_EHLR_Business_EHLRBusiness_INTCP_Login
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soapenv:Body>\r\n" + "    <cai3:Login xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">\r\n"
				+ "      <cai3:userId>sogadm</cai3:userId>\r\n" + "      <cai3:pwd>sogadm</cai3:pwd>\r\n" + "    </cai3:Login>\r\n" + "  </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		//System.out.println("EMA Login Request :\t" + soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));

		// ECS check subscriber details
		url = "http://mwatst02.eircom.ie:7011/ECSProvisioning/Proxy/ECSProvisioningProxy";
		xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:met=\"http://www.meteor.ie/SOA/1.0/MeteorProvisioningEAI\" xmlns:com=\"http://www.meteor.ie/SOA/1.0/Common\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <met:QuerySubscriberRequest>\r\n" + "         <com:serviceUser>\r\n"
				+ "            <com:user>?</com:user>\r\n" + "            <com:applicationDescriptor>?</com:applicationDescriptor>\r\n"
				+ "            <com:applicationIdentifier>?</com:applicationIdentifier>\r\n" + "            </com:serviceUser>\r\n"
				+ "         <met:SubscriberId>10018561</met:SubscriberId>\r\n" + "      </met:QuerySubscriberRequest>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		//System.out.println("ECS Subscriber:\t" + soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));

		parseECSSubscriberResponse(soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));

		// ECS get bundle details
		url = "http://mwatst02.eircom.ie:7011/ECSProvisioning/Proxy/ECSProvisioningProxy";
		xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:met=\"http://www.meteor.ie/SOA/1.0/MeteorProvisioningEAI\" xmlns:com=\"http://www.meteor.ie/SOA/1.0/Common\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <met:QueryBundleRequest>\r\n" + "         <com:serviceUser>\r\n"
				+ "            <com:user>?</com:user>\r\n" + "            <com:applicationDescriptor>?</com:applicationDescriptor>\r\n"
				+ "            <com:applicationIdentifier>?</com:applicationIdentifier>\r\n" + "            </com:serviceUser>\r\n"
				+ "         <met:SubscriberId>10018561</met:SubscriberId>\r\n" + "      </met:QueryBundleRequest>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		//System.out.println("ECS Bundles:\t" + soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));
		parseECSBundlesResponse(soapUtility.getPostResponse(url, xml.replace("$msisdn", msisdn)));
	}

	// retrieve all details IN for a subscriber
	public static void parseINGetTariffPlanResponse(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			System.out.println("Tariff plan:" + doc.getElementsByTagName("tariffPlan").item(0).getFirstChild().getNodeValue());
			System.out.println("MSISDN:" + doc.getElementsByTagName("msisdn").item(0).getFirstChild().getNodeValue());
			System.out.println("Status:" + doc.getElementsByTagName("lifecycleStatus").item(0).getFirstChild().getNodeValue());

			// balancesList will return the list of <balance> nodes inside <balances>
			NodeList balancesList = doc.getElementsByTagName("balances").item(0).getChildNodes();

			// for each balance in the list
			for (int i = 0; i < balancesList.getLength(); i++) {
				System.out.println("Balance found:");
				Node balanceNode = balancesList.item(i);

				// read the attribute values
				NodeList balanceAttributes = balanceNode.getChildNodes();

				// for each attribute value
				for (int j = 0; j < balanceAttributes.getLength(); j++) {
					Node attributeNode = balanceAttributes.item(j);

					// display the node name and value
					System.out.println("\t" + attributeNode.getNodeName() + " = " + attributeNode.getTextContent());
				}
			}

			NodeList promotionsList = doc.getElementsByTagName("promotions").item(0).getChildNodes();
			// for each balance in the list
			for (int i = 0; i < promotionsList.getLength(); i++) {
				System.out.println("IN Promotion found:");
				Node promotionNode = promotionsList.item(i);

				// read the attribute values
				NodeList promotionAttributes = promotionNode.getChildNodes();

				// for each attribute value
				for (int j = 0; j < promotionAttributes.getLength(); j++) {
					Node attributeNode = promotionAttributes.item(j);

					// display the node name and value
					System.out.println("\t" + attributeNode.getNodeName() + " = " + attributeNode.getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// retrieve all details HLR for a subscriber
	public static void parseHLRResponse(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			System.out.println("HLR MSISDN:" + doc.getElementsByTagName("met:MSISDN").item(0).getFirstChild().getNodeValue());
			System.out.println("HLR IMSI:" + doc.getElementsByTagName("met:IMSI").item(0).getFirstChild().getNodeValue());
			System.out.println("HLR Charging Chars:" + doc.getElementsByTagName("met:chargingCharacteristics").item(0).getFirstChild().getNodeValue());
			// System.out.println("OBO setting:" +
			// doc.getElementsByTagName("met:obo").item(0).getFirstChild().getNodeValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// retrieve all details HLR for a subscriber
	public static void parseHLRLoginResponse(String xml) {
		System.err.println(xml);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			System.out.println("sessionID:" + doc.getElementsByTagName("sessionID").item(0).getFirstChild().getNodeValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// retrieve all details HLR for a subscriber
	public static void parseSPRResponse(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			System.out.println("SPR SUB ID:" + doc.getElementsByTagName("met:SubscriberId").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR Account ID:" + doc.getElementsByTagName("met:CustomerAccountId").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR MSISDN:" + doc.getElementsByTagName("met:Msisdn").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR IMSI:" + doc.getElementsByTagName("met:Imsi").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR Price Plan:" + doc.getElementsByTagName("met:PricePlan").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR Subscriber Type:" + doc.getElementsByTagName("met:SubscriberType").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR Operator:" + doc.getElementsByTagName("met:Operator").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR NotificationFlag:" + doc.getElementsByTagName("met:NotificationFlag").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR AoC:" + doc.getElementsByTagName("met:AOCFlag").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR BillingSource:" + doc.getElementsByTagName("met:BillingSource").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR CSFP:" + doc.getElementsByTagName("met:CFSProfile").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR LTE:" + doc.getElementsByTagName("met:LTE").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR ECS:" + doc.getElementsByTagName("met:ECS").item(0).getFirstChild().getNodeValue());
			System.out.println("SPR WiFi Calling:" + doc.getElementsByTagName("met:WifiCalling").item(0).getFirstChild().getNodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// retrieve all details HLR for a subscriber
	public static void parseECSSubscriberResponse(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			System.out.println("ECS SUB ID:" + doc.getElementsByTagName("met:subscriber_id").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS Account ID:" + doc.getElementsByTagName("met:customer_account_id").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS MSISDN:" + doc.getElementsByTagName("met:msisdn").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS IMSI:" + doc.getElementsByTagName("met:imsi").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS Price Plan:" + doc.getElementsByTagName("met:tariff_plan").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS Subscriber Type:" + doc.getElementsByTagName("met:subscriber_type").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS Operator:" + doc.getElementsByTagName("met:operator").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS Billing Source:" + doc.getElementsByTagName("met:billing_source").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS NotificationFlag:" + doc.getElementsByTagName("met:notification_flag").item(0).getFirstChild().getNodeValue());
			System.out.println("ECS AoC:" + doc.getElementsByTagName("met:aoc_opted_in").item(0).getFirstChild().getNodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// retrieve all details HLR for a subscriber
	public static void parseECSBundlesResponse(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			NodeList balanceList = doc.getElementsByTagName("met:balances").item(0).getChildNodes(); // <met:balances>
			// for each balance in the list
			for (int i = 0; i < balanceList.getLength(); i++) {
				System.out.println("ECS Bundle found:"); // <met:item>
				Node promotionNode = balanceList.item(i);

				// read the attribute values
				NodeList items = promotionNode.getChildNodes();

				// for each attribute value
				for (int j = 0; j < items.getLength(); j++) {
					Node attributeNode = items.item(j);

					// display the node name and value
					System.out.println("ECS Bundle component found:"); // <met:mainBalance> or <met:childBalance>
					NodeList bundleAttributes = attributeNode.getChildNodes();
					for (int k = 0; k < bundleAttributes.getLength(); k++) {
						Node attributes = bundleAttributes.item(k);
						System.out.println("\t" + attributes.getNodeName() + " = " + attributes.getTextContent());
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getElement(NodeList list) {
		if (list.item(0) != null) {
			return list.item(0).getFirstChild().getNodeValue();
		}
		return null;
	}
	
	public static String getNodeValue(String response,String tagName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(response));
			Document doc = builder.parse(is);

			// populate the values
			return getElement(doc.getElementsByTagName(tagName));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
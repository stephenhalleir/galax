package testCases.external.middleware.sms_app.test_cases;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import framework.basetest.BaseTest;
import framework.testNG.Retry;
import testCases.external.middleware.sms_app.test_objects.TestObjectSMSApp;
import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.files.ExcelDataProvider;
import utilities.generic.files.TextReader;
import utilities.network.SOAPUtility;

public class TestSMSApp extends BaseTest {

	@BeforeClass
	public void setUp() {
		
	}
	
	@Test(enabled=false,description = "Test 'SMS Offer' Keyword", retryAnalyzer = Retry.class)
	public void testSMSOffer() {
		sendSMSKeyword("50104", "0852690132", "offer");
	}
	
	@Test(enabled=false,description = "Test SMS 'Balance' Keyword", retryAnalyzer = Retry.class)
	public void testSMSBalance() {
		sendSMSKeyword("50104", "0852690132", "balance");
	}
	
	@Test(enabled=false,description = "Adjust Balance", retryAnalyzer = Retry.class)
	public void testAdjBal() {
		topupSubscriber("0850611000",10);
	}
	
	
	@Test(enabled=true,dataProvider="smsAppsTests",description = "Test SMS 'Balance' Keyword")
	public void testSMSBalanceDataDriven(TestObjectSMSApp test) {
		
		System.out.println("Running test " + test.getTestName());
		
		if(test.isReprovision()) {
			logInfo("Reprovisioning subscriber " + test.getMsisdn());
			MMWUtility.reprovisionEC20Subsriber(test.getMsisdn());
		}
		
		if(test.getTopupAmount()>0) {
			System.out.println("Topping up subscriber " + test.getMsisdn());
			extentLogger.info("Topping up subscriber " + test.getMsisdn());
			topupSubscriber(test.getMsisdn(),test.getTopupAmount());
		}
		
		extentLogger.info("Subscriber " + test.getMsisdn() + " is sending keyword " + test.getKeyword() + " to " + test.getShortcode());
		
		String response = sendSMSKeyword(test.getShortcode(), test.getMsisdn(), test.getKeyword());
		System.err.println(test.getTestName() + ": " + response);

		// strip out the 10e/20e piece
		String smsResponse = test.getResponseSubstring();
		smsResponse = smsResponse.replace(" 10e", "");
		smsResponse = smsResponse.replace(" 20e", "");
		
		// if we've specified a check string, verify it
		if(!smsResponse.equals("")) {
			System.out.println("Checking whether [" + response + "] contains [" + smsResponse + "]");
			assertTrue(response.contains(smsResponse));
			System.out.println("Response contains substring as expected: " + smsResponse);
		}
		
		extentLogger.pass("Response: " + response);
	}

	public void topupSubscriber(String msisdn, int amount) {
		
		SPRProfile sprProfile = MMWUtility.getSprProfile(msisdn);
		
		String request = TextReader.getContent("D:\\Users\\stephenhall\\eclipse-workspace\\GalaxionAutomation\\requests\\soap\\in\\adjustSubscriberBalance");
		request = request.replace("$msisdn", msisdn);
		request = request.replace("$subscriberId", sprProfile.getSubID());
		request = request.replace("$amount", Integer.toString(amount));
		
		String endpoint=EnvironmentExcelConfigReader.getMMWConfigValue("ENDPOINTS.OCS.PROVISIONINGPROXY");
		System.out.println(request);

		String response = SOAPUtility.getPostResponse(endpoint, request);
		System.err.println(response);
	}
	
	// wait for x seconds
	public void waitForSeconds(int sec) {
		try {
			
			Thread.sleep(sec * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	public String sendSMSKeyword(String shortcode, String msisdn, String message) {
		System.out.println("Sending keyword " + message + " to " + shortcode + " for " + msisdn);
		extentLogger.info("Sending keyword " + message + " to " + shortcode + " for " + msisdn);
		
		SOAPUtility s = new SOAPUtility();

		// set up the template SOAP message
		String soapRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.meteor.ie/SOA/1.0/SMSUnitTest/v1\" xmlns:com=\"http://www.meteor.ie/SOA/1.0/Common\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <v1:SMSMessageContextRequest>\r\n" + "         <com:serviceUser>\r\n"
				+ "            <com:user>SOH</com:user>\r\n" + "            <com:applicationDescriptor>SMS</com:applicationDescriptor>\r\n"
				+ "            <com:applicationIdentifier>SMS_Tests</com:applicationIdentifier>\r\n" + "         </com:serviceUser>\r\n"
				+ "         <v1:SMSMessageContext>\r\n" + "            <v1:shortcode>$shortcode</v1:shortcode>\r\n"
				+ "            <v1:msisdn>$msisdn</v1:msisdn>\r\n" + "            <v1:inputMessage>$message</v1:inputMessage>\r\n"
				+ "         </v1:SMSMessageContext>\r\n" + "      </v1:SMSMessageContextRequest>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";
		
		// populate the SOAP request with the relevant inputs
		soapRequest = soapRequest.replace("$shortcode", shortcode).replace("$msisdn", msisdn).replace("$message", message);
		
		System.out.println("SOAP Request:\n" + soapRequest);
		extentLogger.info("SOAP Request:<br>" + soapRequest.replace("<", "&lt;").replace(">","&gt;"));
		
		String endpoint = EnvironmentExcelConfigReader.getMMWConfigValue("ENDPOINTS.SMSAPP");
		
		// send the SOAP request to the endpoint
		String soapResponse = SOAPUtility.getPostResponse(endpoint, soapRequest);
		
		extentLogger.info("SOAP Response:<br>" + soapResponse.replace("<", "&lt;").replace(">","&gt;"));
		// return the value of the "v1:responseSMSMessage" node
		
		String smsResponse=SOAPUtility.getNodeValue(soapResponse, "v1:responseSMSMessage");
		System.err.println(smsResponse);
		
		return smsResponse;
	}
	
	
	public ArrayList<TestObjectSMSApp> getSMSTestsFromSheet() {
		ArrayList<TestObjectSMSApp> tests = new ArrayList<TestObjectSMSApp>();

		String sheetName = "SMSApps";
		ExcelDataProvider excel = new ExcelDataProvider("D:\\Users\\stephenhall\\Desktop\\PrepayOrders.xlsx");

		// for each order on the sheet
		for (int i = 1; i < excel.getRowCount(sheetName); i++) {
			TestObjectSMSApp test = new TestObjectSMSApp();

			String inScope = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "In Scope"));

			if (inScope.equals("Yes")) {

				// read the service from the sheet
				test.setTestName(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Test Name")));
				test.setKeyword(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Keyword")));
				test.setShortcode(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Shortcode")));
				test.setMsisdn(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "MSISDN")));
				test.setResponseSubstring(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Response Substring")));
				String reprovision = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Reprovision"));
				String topupAmount = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Topup Amount"));

				if(reprovision.equals("Yes")) {
					test.setReprovision(true);
				}
				
				if(!topupAmount.equals("")) {
					test.setTopupAmount(Integer.parseInt(topupAmount));
				}
				
				tests.add(test);
				System.out.println("Adding test " + test.getTestName());
			}
		}

		return tests;
	}
	
	@DataProvider(name = "smsAppsTests")
	public Object[][] getSMSAppsArray() {

		// read in the list of orders from the sheet
		ArrayList<TestObjectSMSApp> orders = getSMSTestsFromSheet();

		Object[][] data = new Object[orders.size()][1];

		for (int i = 0; i < orders.size(); i++) {
			data[i] = new Object[] { orders.get(i) };
		}

		return data;
	}

	
	
	@AfterClass
	public void tearDown() {

	}
}

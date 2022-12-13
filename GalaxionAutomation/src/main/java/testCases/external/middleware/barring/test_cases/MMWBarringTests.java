package testCases.external.middleware.barring.test_cases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import framework.basetest.BaseTest;
import testCases.external.middleware.barring.test_objects.TestObjectMMWBarring;
import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.files.ExcelDataProvider;
import utilities.generic.files.TextReader;
import utilities.network.SOAPUtility;

public class MMWBarringTests extends BaseTest {

	@BeforeClass
	public void setUp() {
		
	}

	
	@Test(enabled=false,description = "Add barring via MMW")
	public void testBarring() {
		
		String msisdn="353852410358";
		String barType="FullNetwork";
		
		HLRFEProfile profile = MMWUtility.getHLRFEProfile(msisdn);
		
		// add the bar
		assertTrue(addBarring(msisdn,"ADD", barType));
		waitForSeconds(5);
		profile = MMWUtility.getHLRFEProfile(msisdn);
		assertTrue(profile.checkBarring(barType));
		
		// remove the bar
		assertTrue(addBarring(msisdn,"REMOVE", barType));
		waitForSeconds(5);
		profile = MMWUtility.getHLRFEProfile(msisdn);
		assertFalse(profile.checkBarring(barType));
	}
	
	@Test(enabled=true,dataProvider="mmwBarringTests",description = "Add barring via MMW")
	public void testBarringDataDriven(TestObjectMMWBarring test) {
		
		HLRFEProfile profile = new HLRFEProfile(test.getMsisdn());
		
		// first we need to set up the test
		// i.e. add a bar to remove, or remove a bar to add
		if(test.getAction().equals("ADD")) {
			assertTrue(addBarring(test.getMsisdn(),"REMOVE", test.getBarType()));
			waitForSeconds(5);
			profile = MMWUtility.getHLRFEProfile(test.getMsisdn());
			assertFalse(profile.checkBarring(test.getBarType()));
		} else if(test.getAction().equals("REMOVE")) {
			assertTrue(addBarring(test.getMsisdn(),"ADD", test.getBarType()));
			waitForSeconds(5);
			profile = MMWUtility.getHLRFEProfile(test.getMsisdn());
			assertTrue(profile.checkBarring(test.getBarType()));
		}
		
		System.out.println("Before - bar " + test.getBarType() + " active: " + profile.checkBarring(test.getBarType()));
		
		// trigger the request and confirm that a success response is returned
		assertTrue(addBarring(test.getMsisdn(),test.getAction(), test.getBarType()));
		waitForSeconds(5);
		profile = MMWUtility.getHLRFEProfile(test.getMsisdn());
		
		System.out.println("After - bar " + test.getBarType() + " active: " + profile.checkBarring(test.getBarType()));
		
		// check the result
		if(test.getAction().equals("ADD")) {
			assertTrue(profile.checkBarring(test.getBarType()));
		}
		else if(test.getAction().equals("REMOVE")) {
			assertFalse(profile.checkBarring(test.getBarType()));
		}
		
		System.err.println(test.getTestName() + " ok");
	}
	
	

	public boolean addBarring(String msisdn, String action, String barType) {
		
		SPRProfile sprProfile = MMWUtility.getSprProfile(msisdn);
		
		String request="";
		
		if(action.equalsIgnoreCase("ADD")) {
			request = TextReader.getContent("requests\\soap\\in\\addBarring");
		}
		else if(action.equalsIgnoreCase("REMOVE")) {
			request = TextReader.getContent("requests\\soap\\in\\removeBarring");
		}
		
		request = request.replace("$msisdn", msisdn);
		request = request.replace("$subscriberId", sprProfile.getSubID());
		request = request.replace("$barType", barType);
		
		String endpoint=EnvironmentExcelConfigReader.getMMWConfigValue("ENDPOINTS.OCS.PROVISIONINGPROXY");
		System.out.println(request);
		String response = SOAPUtility.getPostResponse(endpoint, request);
		System.err.println(response);
		
		return response.contains(">1</com:status>");
	}
	
	// wait for x seconds
	public void waitForSeconds(int sec) {
		try {
			
			Thread.sleep(sec * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	
	// read the tests from the sheet
	public ArrayList<TestObjectMMWBarring> getBarringTestsFromSheet() {
		
		ArrayList<TestObjectMMWBarring> tests = new ArrayList<TestObjectMMWBarring>();
		String sheetName = "MMWBarringTests";

		ExcelDataProvider excel = new ExcelDataProvider("data_drivers\\PrepayOrders.xlsx");
		
		// for each order on the sheet
		for (int i = 1; i < excel.getRowCount(sheetName); i++) {
			TestObjectMMWBarring test = new TestObjectMMWBarring();
			String inScope = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "In Scope"));

			if (inScope.equals("Yes")) {

				// read the service from the sheet
				test.setTestName(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Test Name")));
				test.setMsisdn(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "MSISDN")));
				test.setBarType(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Bar")));
				test.setAction(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Action")));
				
				tests.add(test);
				System.out.println("Adding test " + test.getTestName());
			}
		}

		return tests;
	}
	
	// convert the tests from the sheet into a data-provider array
	@DataProvider(name = "mmwBarringTests")
	public Object[][] getMMWBarringTestsArray() {

		// read in the list of orders from the sheet
		ArrayList<TestObjectMMWBarring> orders = getBarringTestsFromSheet();

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

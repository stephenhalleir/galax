package testCases.external.middleware.aoc.test_cases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.ec20.profile.EC20Profile;
import external_systems.mobile_network.utilities.Service;
import framework.basetest.BaseTest;
import framework.testNG.Retry;
import testCases.external.middleware.aoc.test_objects.TestObjectMMWAoC;
import utilities.generic.files.ExcelDataProvider;
import utilities.generic.time.WaitUtil;

public class TestAoCSOAP extends BaseTest {

	private String msisdn;
	private String tariffPlan;
	private Service service;
	private EC20Profile inProfile;

	@BeforeClass
	public void setUp() {

		msisdn = "353852690121";

		service = new Service(msisdn);
		service.loadSPRProfile();
		tariffPlan = service.getSprProfile().getPricePlan();

		MMWUtility.reprovisionEC20Subsriber(msisdn);
		inProfile = MMWUtility.getEC20Profile(msisdn);

	}


	@Test(enabled = true, dataProvider = "aocTests", description = "MMW: Test AoC - DataDriven", retryAnalyzer = Retry.class)
	public void testAoCDD(TestObjectMMWAoC test) {

		inProfile.setOffers(MMWUtility.getEC20Offers(msisdn));
		
		// check whether the subscriber IS NOT PRESENT AT THE START OF THE TEST
		assertFalse(inProfile.hasOffer(test.getOfferID()));
		
		// send the request to MMW to accept terms
		String response = MMWUtility.sendEC20AoCAcceptanceRequest(msisdn, tariffPlan, test.getNotificationType(), test.getBand());

		// confirm that the response contains status 1 (success)
		assertTrue(response.contains("<com:status>1</com:status>"));

		// add assert to check that the offer is added - use getOffers API
		inProfile.setOffers(MMWUtility.getEC20Offers(msisdn));

		// wait
		WaitUtil.waitForSeconds(5);
		
		// check whether the subscriber has the specified offer added to their profile
		assertTrue(inProfile.hasOffer(test.getOfferID()));

	}

	public ArrayList<TestObjectMMWAoC> getAoCTestsFromSheet() {
		ArrayList<TestObjectMMWAoC> tests = new ArrayList<TestObjectMMWAoC>();

		String sheetName = "MMW_AoC";
		ExcelDataProvider excel = new ExcelDataProvider("D:\\Users\\stephenhall\\Desktop\\PrepayOrders.xlsx");

		// for each order on the sheet
		for (int i = 1; i < excel.getRowCount(sheetName); i++) {
			TestObjectMMWAoC test = new TestObjectMMWAoC();

			test.setBand((int) excel.getCellNumericData(sheetName, i, excel.getColumnID(sheetName, "Band")));
			test.setNotificationType(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "NotificationType")));
			test.setOfferID((int) excel.getCellNumericData(sheetName, i, excel.getColumnID(sheetName, "OfferID")));
			tests.add(test);
		}

		return tests;
	}

	@DataProvider(name = "aocTests")
	public Object[][] getAoCTestsArray() {

		// read in the list of orders from the sheet
		ArrayList<TestObjectMMWAoC> orders = getAoCTestsFromSheet();
		Object[][] data = new Object[orders.size()][1];

		for (int i = 0; i < orders.size(); i++) {
			data[i] = new Object[] { orders.get(i) };
		}

		return data;
	}

}

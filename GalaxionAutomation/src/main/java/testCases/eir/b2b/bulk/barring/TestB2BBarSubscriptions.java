package testCases.eir.b2b.bulk.barring;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import io.restassured.response.Response;
import microservices.backend.eir_barring_backend.BarringDAO;
import microservices.backend.eir_barring_backend.enums.BarringAction;
import microservices.backend.eir_barring_backend.enums.BarringType;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.bar_subscriptions_file.BarSubscriptionsFile;
import microservices.backend.eir_bulk_backend.bulk_files.bar_subscriptions_file.BarSubscriptionsRow;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class TestB2BBarSubscriptions extends BaseTest {

	/**
	 * Test barring of a B2B subscription
	 * @param iTestContext
	 */
	@Test(enabled = true, description = "B2B > Bulk File: Bar Subscriptions", invocationCount = 1)
	public void testB2BBulkAddBarring(ITestContext iTestContext) {
		String token="";
		String msisdn = "0858540531";

		// set the test start time for DB lookups later
		String testStartTime = Timestamp.getTimestamp("yyyy-MM-dd hh:mm:ss");

		// set up the test
		ArrayList<BarringType> bars = new ArrayList<BarringType>();
		bars.add(BarringType.BAR_PREMIUM_SMS);

		this.barSubscription(token, msisdn, bars, BarringAction.ADD, true, testStartTime,true);
	}
	
	@Test(enabled = false, description = "B2B > Bulk File: Unbar Subscriptions", invocationCount = 1)
	public void testB2BBulkRemoveBarring(ITestContext iTestContext) {
		String token="";
		String msisdn = "0858540188";

		// set the test start time for DB lookups later
		String testStartTime = Timestamp.getTimestamp("yyyy-MM-dd hh:mm:ss");

		// set up the test
		ArrayList<BarringType> bars = new ArrayList<BarringType>();
		bars.add(BarringType.BAR_PREMIUM_SMS);

		this.barSubscription(token, msisdn, bars, BarringAction.REMOVE, true, testStartTime,true);
	}
	
	/*
	 * Apply a bar to a msisdn via bulk file
	 */
	public void applyBar(String token, String msisdn, BarringType barType) {
		
		// get the test start timestamp
		String testStartTime = Timestamp.getTimestamp("yyyy-MM-dd hh:mm:ss");
		
		// generate the list (with a single item)
		ArrayList<BarringType> bars=new ArrayList<BarringType>();
		bars.add(barType);
		
		// process the barring file
		this.barSubscription(token, msisdn, bars, BarringAction.ADD, true, testStartTime,true);
	}
	
	/*
	 * Remove a bar from a msisdn via bulk file
	 */
	public void removeBar(String token, String msisdn, BarringType barType) {
		
		// get the test start timestamp
		String testStartTime = Timestamp.getTimestamp("yyyy-MM-dd hh:mm:ss");
		
		// generate the list (with a single item)
		ArrayList<BarringType> bars=new ArrayList<BarringType>();
		bars.add(barType);
		
		// process the barring file
		this.barSubscription(token, msisdn, bars, BarringAction.REMOVE, true, testStartTime,true);
	}

	/**
	 * 
	 * @param msisdn
	 * @param bars - list of bar types to be applied
	 * @param action - ADD/REMOVE
	 * @param sendFile - send file to bulk?
	 * @param testStartTime - test start time - used for db lookups
	 * @param separateRows - separate each bar onto separate rows in the file?
	 */
	public void barSubscription(String token, String msisdn, ArrayList<BarringType> bars, BarringAction action, boolean sendFile, String testStartTime,boolean separateRows) {

		// read the service ID for the db lookup later
		int serviceID = SubscriptionManagementDAO.getServiceIDForMSISDN(msisdn);

		// generate the file contents
		//BarSubscriptionsFile file=generateBulkBarringFileSameRow(msisdn, bars, action);
		BarSubscriptionsFile file;
		
		// generate the file as specified
		if(separateRows) {
			file=generateBulkBarringFileMultipleRows(msisdn, bars, action);
		}
		else {
			file=generateBulkBarringFileSameRow(msisdn, bars, action);
		}


		// calculate the filename
		String filename = "bar_subscription_" + action.toString().toLowerCase() + "_" + Timestamp.getUniqueTimestamp() + ".csv";
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\bar_subscription\\" + filename;

		// write the file
		TextReader.writeFile(file.toString(), fullFileName);
		
		logInfo(filename + ":\n" + file.toString());
		System.out.println(filename + ":\n" + file.toString());

		// send the file, if required
		if (sendFile) {

			// upload the file to the bulk service
			Response response = BulkAPI.uploadBulkFile(token,BulkRefFlow.BAR_SUBSCRIPTION, fullFileName);

			logPass("BAR_SUBSCRIPTION file sent to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
			System.err.println(response.statusCode() + ", " + response.asString());

			// check that the microservice has responded with a success
			assertEquals(response.statusCode(), 204);

			// wait for changes to take effect
			WaitUtil.waitForSeconds(10);

			// for each bar
			for (BarringType bar : bars) {

				// check the status of the barring request - lookup bars applied after test
				// start time
				String barStatus = BarringDAO.getBarringStatus(serviceID, bar, testStartTime);
				System.out.println("Bar " + bar.toString() + " on subscriber " + msisdn + ": " + barStatus);
			}
		}
	}

	/*
	 * Generate a bulk file object with each bar on its own line
	 */
	public BarSubscriptionsFile generateBulkBarringFileMultipleRows(String msisdn, ArrayList<BarringType> bars,BarringAction action) {

		// generate the file contents
		BarSubscriptionsFile file = new BarSubscriptionsFile();
		
		// for each bar
		for (BarringType bar : bars) {
			
			// generate a new row in the file
			BarSubscriptionsRow row = new BarSubscriptionsRow(msisdn);
			
			// set the action accordingly
			row.setBarValue(bar, action);
			
			// add the row to the file
			file.addRow(row);
		}
		
		return file;
	}
	
	/*
	 * Generate a bulk file object with multiple bars set on the same row
	 */
	public BarSubscriptionsFile generateBulkBarringFileSameRow(String msisdn, ArrayList<BarringType> bars,BarringAction action) {

		// generate the file contents
		BarSubscriptionsFile file = new BarSubscriptionsFile();
		
		// create 1 row
		BarSubscriptionsRow row = new BarSubscriptionsRow(msisdn);
		
		// for each bar in the list
		for (BarringType bar : bars) {		
			
			// set the action accordingly
			row.setBarValue(bar, action);
		}
		
		// add the row to the file
		file.addRow(row);
		
		return file;
	}

	@BeforeMethod
	public void setUp() {

	}

	@AfterMethod
	public void tearDown() {

	}
}

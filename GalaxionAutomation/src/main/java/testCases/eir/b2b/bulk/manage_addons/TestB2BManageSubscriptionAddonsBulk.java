package testCases.eir.b2b.bulk.manage_addons;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file.AddSubscriptionAddonsFile;
import microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file.AddSubscriptionAddonsRow;
import microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file.RemoveSubscriptionAddonsFile;
import microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file.RemoveSubscriptionAddonsRow;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.AddonBundle;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class TestB2BManageSubscriptionAddonsBulk extends BaseTest {
	
	@Test(enabled = true, description = "B2B > Bulk File: Add Subscription Level Addons", invocationCount = 1)
	public void testB2BBulkAddSubscriptionAddons(ITestContext iTestContext) {
		
		String token="";
		
		//this.removeAddonBulk("0854800604", "ROAEU1", true);
		//this.removeAddonBulk("0854800605", "BOLTGBZ1BZ2", true);
		//this.removeAddonBulk("0854800606", "20GBDATA", true);
		//this.removeAddonBulk("0861300012", SubscriptionAddonBundle.ROAEA1, true);
		this.addAddonBulk(token, "0854800607", "ROAEU1", true);
		this.addAddonBulk(token, "0854800608", "BOLTGBZ1BZ2", true);
		this.addAddonBulk(token, "0854800609", "20GBDATA", true);
		//this.addAddonBulk("0858540721", "ROAFAC", true);
	}

	@Test(enabled = false, description = "B2B > Bulk File: Remove Subscription Level Addons", invocationCount = 1)
	public void testB2BBulkRemoveSubscriptionAddons(ITestContext iTestContext) {

		String s1 = "1,89818434,0854800604,5GENAB,INTER,,,";
		String s2 = "2,89818434,0854800605,5GENAB,10GBDATA,,,";
		String s3 = "3,89810000,0854800606,5GENAB,20GBDATA,,,";
		RemoveSubscriptionAddonsRow r1 = new RemoveSubscriptionAddonsRow(s1);
		RemoveSubscriptionAddonsRow r2 = new RemoveSubscriptionAddonsRow(s2);
		RemoveSubscriptionAddonsRow r3 = new RemoveSubscriptionAddonsRow(s3);

		RemoveSubscriptionAddonsFile file = new RemoveSubscriptionAddonsFile();
		file.addRow(r1);
		file.addRow(r2);
		file.addRow(r3);

		// write the file
		String filename = "remove_subscription_addons_" + System.currentTimeMillis() + ".csv";
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\remove_subscription_addons\\" + filename;
		TextReader.writeFile(file.toString(), fullFileName);
		
		// print the file contents
		System.out.println(file);
	}
	

	public void addAddonBulk(String token, String msisdn, String addon, boolean sendFile) {
		ArrayList<String> addons = new ArrayList<String>();
		addons.add(addon);
		addAddonsBulk(token, msisdn, addons, sendFile);
	}
	
	public void addAddonBulk(String token, String msisdn,SubscriptionAddonBundle bundle, boolean sendFile) {
		String bundleString = bundle.toString();
		if(bundleString.startsWith("_")) {
			bundleString=bundleString.substring(1);
		}
		addAddonBulk(token, msisdn, bundleString, sendFile);
	}
	
	public void removeAddonBulk(String token, String msisdn, String addon, boolean sendFile) {
		ArrayList<String> addons = new ArrayList<String>();
		addons.add(addon);
		removeAddonsBulk(token, msisdn, addons, sendFile);
	}
	
	public void removeAddonBulk(String token, String msisdn,SubscriptionAddonBundle bundle, boolean sendFile) {
		String bundleString = bundle.toString();
		if(bundleString.startsWith("_")) {
			bundleString=bundleString.substring(1);
		}
		removeAddonBulk(token, msisdn, bundleString, sendFile);
	}

	public void addAddonsBulk(String token, String msisdn, ArrayList<String> addons, boolean sendFile) {

		double x=1;
		// retrieve the account ID
		int billingAccountID = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);

		// create the file
		AddSubscriptionAddonsFile addonsFile = new AddSubscriptionAddonsFile();

		// add each addon in the list to the file
		for (String addonCode : addons) {
			AddSubscriptionAddonsRow row = new AddSubscriptionAddonsRow(billingAccountID, msisdn);
			row.addAddon(addonCode);
			addonsFile.addRow(row);
		}

		String filename;
		
		// calculate the filename
		if(addons.size()==1) {
			filename = "add_subscription_addons_" + Timestamp.getUniqueTimestamp() + "_" + msisdn+ "_" + addons.get(0).toLowerCase() + ".csv";
		}
		else {
			filename = "add_subscription_addons_" + Timestamp.getUniqueTimestamp() + "_" + msisdn + ".csv";
		}
		
		// calculate the filename

		// write the file contents to the file
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\add_subscription_addons\\" + filename;
		TextReader.writeFile(addonsFile.toString(), fullFileName);
		
		logInfo("File generated: " + filename + "\n" + addonsFile.toString());

		// send the file, if required
		if (sendFile) {

			// upload the file to the bulk service
			Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.ADD_SUBSCRIPTION_ADDON, fullFileName);

			logPass("ADD_SUBSCRIPTION_ADDON file send to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
			System.err.println(response.statusCode() + ", " + response.asString());

			// check that the microservice has responded with a success
			assertEquals(response.statusCode(), 204);
		}
	}
	
	
	public void removeAddonsBulk(String token, String msisdn, ArrayList<String> addons, boolean sendFile) {

		// retrieve the account ID
		int billingAccountID = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);

		// create the file
		RemoveSubscriptionAddonsFile addonsFile = new RemoveSubscriptionAddonsFile();

		// add each addon in the list to the file
		for (String addonCode : addons) {
			RemoveSubscriptionAddonsRow row = new RemoveSubscriptionAddonsRow(billingAccountID, msisdn);
			row.addAddon(addonCode);
			addonsFile.addRow(row);
		}

		// calculate the filename
		String filename = "remove_subscription_addons_" + Timestamp.getUniqueTimestamp() + ".csv";

		// write the file contents to the file
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\remove_subscription_addons\\" + filename;
		TextReader.writeFile(addonsFile.toString(), fullFileName);
		
		logInfo("File generated: " + filename + "\n" + addonsFile.toString());

		// send the file, if required
		if (sendFile) {

			// upload the file to the bulk service
			Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.REMOVE_SUBSCRIPTION_ADDON, fullFileName);

			logPass("REMOVE_SUBSCRIPTION_ADDON file send to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
			System.err.println(response.statusCode() + ", " + response.asString());

			// check that the microservice has responded with a success
			assertEquals(response.statusCode(), 204);
		}
	}
	
	
	@DataProvider(name = "b2b-addons")
	public Object[][] dataProviderB2BAddons() {
		ArrayList<AddonBundle> bundles = CatalogCoreDAO.getAddonBundlesBetween(21697, 21733);
		return get2DArrayFromArrayList(bundles);
	}
	
	@DataProvider(name = "mischarge-addons")
	public Object[][] dataProviderMischargeAddons() {
		ArrayList<AddonBundle> bundles = CatalogCoreDAO.getAddonBundlesBetween(21700, 21706);
		return get2DArrayFromArrayList(bundles);
	}
	
	public static Object[][] get2DArrayFromArrayList(ArrayList<AddonBundle> objects) {
		Object[][] o = new Object[objects.size()][1];

		for (int i = 0; i < objects.size(); i++) {
			o[i][0] = objects.get(i);
		}

		return o;

	}

	@BeforeMethod
	public void setUp() {

	}

	@AfterMethod
	public void tearDown() {

	}
}

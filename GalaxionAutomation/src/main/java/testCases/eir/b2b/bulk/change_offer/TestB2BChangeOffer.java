package testCases.eir.b2b.bulk.change_offer;

import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferFile;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferRow;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionFile;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.enums.TariffCode;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.galaxion.dao.GalaxionDAO;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class TestB2BChangeOffer extends BaseTest {

	public void processChangeOfferOnBulk(String token, String msisdn, TariffCode tariffCode, boolean sendFile) {

		int baid = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		String tariffCodeString = tariffCode.toString().replace("_", "-");
		String offerCode = CatalogCoreDAO.getTariffPlan(tariffCodeString).getOfferCode();

				
		ChangeOfferRow row = new ChangeOfferRow();
		row.setCustomerAccountNumber(Integer.toString(baid));
		row.setMsisdn(msisdn);
		row.setChangeToOfferCode(offerCode);

		row.setChangeToOfferTariffPlanCode(tariffCodeString);
		//row.setChangeToOfferTariffPlanCharge("23.50"); 
		//row.setReContract("TRUE");
		//row.setDeviceCode("GALC3L21S");
		//row.setDeductDevChargeFromHW("FALSE");
		//row.setDeviceCharge("24");

		ChangeOfferFile file = new ChangeOfferFile();
		file.addRow(row);

		System.out.println(file);

		// calculate the filename
		String filename = "change_offer_" + Timestamp.getUniqueTimestamp() + ".csv";
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\change_offer\\" + filename;

		// write the file
		TextReader.writeFile(file.toString(), fullFileName);

		logInfo(filename + ":\n" + file.toString());
		System.out.println(filename + ":\n" + file.toString());

		// send the file, if required
		if (sendFile) {

			// upload the file to the bulk service
			Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.CHANGE_OFFER, fullFileName);

			logPass("CHANGE_OFFER file sent to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
			System.err.println(response.statusCode() + ", " + response.asString());

			// check that the microservice has responded with a success
			assertEquals(response.statusCode(), 204);
		}
	}

	@Test(enabled = true, description = "B2B > Bulk File: Change Offers", invocationCount = 1)
	public void testB2BBulkAddSubscriptionAddons(ITestContext iTestContext) {
		String token="";
		this.processChangeOfferOnBulk(token, "0858540728", TariffCode.MBB750GB_24, true);
		/*
		 * String s1 =
		 * "1,89818434,0851234567,123456,TRUE,TRUE,PERFORMANCE,PERFORMANCE-HW,23.00,0.00,Billy Jones,,GAPPS264B,250.00,TRUE,TRUE,Primary Contact,joe.duffy@radio.ie"
		 * ; String s2 =
		 * "2,89818434,0851472583,123456,FALSE,TRUE,PERFORMANCE,PERFORMANCE,23.00,,,Upgrade,,,,,,";
		 * String s3 = "3,89810000,0851472555,,FALSE,FALSE,VIP,VIP-SIM,27.00,,,,,,,,,";
		 * ChangeOfferRow r1 = new ChangeOfferRow(s1); ChangeOfferRow r2 = new
		 * ChangeOfferRow(s2); ChangeOfferRow r3 = new ChangeOfferRow(s3);
		 * 
		 * ChangeOfferFile file = new ChangeOfferFile(); file.addRow(r1);
		 * file.addRow(r2); file.addRow(r3); System.out.println("FILE:\n" + file);
		 * 
		 * String filename="change_offer_" + Timestamp.getUniqueTimestamp() + ".csv";
		 * 
		 * BufferedWriter writer; try {
		 * 
		 * // write to the file String fullFileName = System.getProperty("user.dir") +
		 * "\\files\\bulk\\change_offer" + "\\"+ filename; writer = new
		 * BufferedWriter(new FileWriter(fullFileName)); writer.write(file.toString());
		 * writer.close();
		 * 
		 * // upload the file to the server location
		 * //IONFileUploader.uploadFile(fullFileName, "sftp-backend",
		 * "/home/eir-logistics-backend/logistics"); } catch (IOException e) {
		 * e.printStackTrace(); }
		 * 
		 * // print the file contents System.out.println(file);
		 */
	}

	@BeforeMethod
	public void setUp() {

	}

	@AfterMethod
	public void tearDown() {

	}
}

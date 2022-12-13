package microservices.backend.eir_bulk_backend.utilities;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import framework.test_data.generic.RandomStringGenerator;
import io.restassured.response.Response;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.adjustments_file.AdjustmentsFile;
import microservices.backend.eir_bulk_backend.bulk_files.adjustments_file.AdjustmentsRow;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferFile;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionFile;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionRow;
import microservices.backend.eir_bulk_backend.bulk_files.move_subscription_file.MoveSubscriptionsFile;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.TariffPlan;
import microservices.backend.eir_catalog_core_backend.enums.TariffCode;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.ServiceDetail;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.galaxion.dao.GalaxionDAO;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.Subscription;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class BulkProcessor {

	public static ExtentTest extentLogger;
	public static Logger logger4j;

	public BulkProcessor() {

	}

	public BulkProcessor(ExtentTest extentLogger, Logger logger4j) {
		this.extentLogger = extentLogger;
		this.logger4j = logger4j;
	}

	/**
	 * Process a B2B bulk adjustment file
	 * 
	 * @param billingAccountID
	 * @param reason
	 * @param amount
	 * @return
	 */
	public BulkResult generateAndProcessAdjustmentFile(int billingAccountID, AdjustmentReason reason, int amount) {

		String token="";
		
		// generate the file
		AdjustmentsFile file = BulkFileGenerator.generateAdjustmentsFile(billingAccountID, reason, amount);

		// determine the file path
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\adjustment\\" + file.getFilename();

		// write the file to the local directory
		TextReader.writeFile(file.toString(), fullFileName);
		logInfo(file.getFilename() + ":\n" + file.toString());

		// upload the file to the bulk service
		Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.ADJUSTMENT, fullFileName);
		logPass("ADJUSTMENT file sent to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());

		// check that the microservice has responded with a success
		assertEquals(response.statusCode(), 204);

		return new BulkResult(response, file);
	}
	
	public BulkResult generateAndProcessChangeOfferFile(String msisdn, TariffCode tariffCode) {

		int billingAccountID=SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		
		TariffPlan tariff=CatalogCoreDAO.getTariffPlan(tariffCode.toString());
		ChangeOfferFile file = BulkFileGenerator.generateChangeOfferFile(billingAccountID,msisdn,tariff.getOfferCode(),tariff.getCode());

		// determine the file path
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\change_offer\\" + file.getFilename();

		// write the file to the local directory
		TextReader.writeFile(file.toString(), fullFileName);
		logInfo(file.getFilename() + ":\n" + file.toString());

		// upload the file to the bulk service
		Response response = BulkAPI.uploadBulkFile("", BulkRefFlow.CHANGE_OFFER, fullFileName);
		logPass("ADJUSTMENT file sent to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());

		// check that the microservice has responded with a success
		assertEquals(response.statusCode(), 204);

		return new BulkResult(response, file);
	}

	public BulkResult generateAndProcessMoveSubscriptionFile(ArrayList<String> msisdns, int destinationBillingAccountID) {
		MoveSubscriptionsFile moveSubscriptionsFile  = BulkFileGenerator.generateMoveSubscriptionFile(msisdns, destinationBillingAccountID);

		// write to the file
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\move_subscription" + "\\" + moveSubscriptionsFile.getFilename();
		TextReader.writeFile(moveSubscriptionsFile.toString(), fullFileName);

		// print the file contents
		System.out.println(moveSubscriptionsFile);

		// upload the file to the bulk service
		Response response = BulkAPI.uploadBulkFile("",BulkRefFlow.MOVE_SUBSCRIPTION, fullFileName);
		logPass("Move Subscription file sent to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());

		// check that the microservice has responded with a success
		assertEquals(response.statusCode(), 204);

		return new BulkResult(response, moveSubscriptionsFile);
	}
	
	public void logInfo(String s) {
		if (logger4j != null && extentLogger != null) {
			logger4j.info(s);
			extentLogger.info(s.replace("\n", "<br>"));
		}
		else {
			System.out.println(s);
		}
	}

	public void logPass(String s) {
		if (logger4j != null && extentLogger != null) {
			logger4j.info(s);
			extentLogger.pass(s.replace("\n", "<br>"));
		}
		else {
			System.out.println(s);
		}
	}
}
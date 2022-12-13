package testCases.eir.b2b.bulk.change_offer;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferFile;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferRow;
import microservices.backend.eir_bulk_backend.utilities.BulkProcessor;
import microservices.backend.eir_bulk_backend.utilities.BulkResult;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.TariffPlan;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_catalog_core_backend.enums.TariffCode;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.monitor.OrderMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;

public class TestB2BBulkChangeOffer extends BaseTest {

	private BulkProcessor processor;

	/**
	 * Test case: Use the bulk process to change the offer on a subscription
	 * 
	 */
	@Test(enabled = true, description = "eir B2B > Bulk > Change Offer")
	public void testB2BBulkChangeOffer(ITestContext iTestContext) {

		// get the test data from the environments file
		int billingAccountID = 80000141;//TestDataManager.getB2BCorporateAccount();

		// get a service on the account
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);

		// determine the new offer for the subscriber
		OfferCode currentOfferCode = SubscriptionManagementDAO.getOfferCode(service.getName());
		logInfo("Subscriber " + service.getName() + " is on the offer " + currentOfferCode.toString());
		
		// remove the current tariff from the list (as we want to add a new one)
		ArrayList<TariffPlan> allTariffs = CatalogCoreDAO.getB2BCorporateOffers();
		for(TariffPlan tariff:allTariffs) {
			if(tariff.getOfferCode().equals(currentOfferCode.toString())) {
				allTariffs.remove(tariff);
				break;
			}
		}
		
		// get the first tariff in the list (this will be the new tariff plan)
		TariffPlan newTariffPlan = allTariffs.get(0);
		TariffCode newTariffCode = TariffCode.valueOf(newTariffPlan.getCode().replace("-", "_"));
		assertNotNull(newTariffCode);
		
		logInfo("Subscriber " + service.getName() + " will switch to the offer " + newTariffCode);
		
		// generate and process the file
		BulkResult result=processor.generateAndProcessChangeOfferFile(service.getName(), newTariffCode);
		System.out.println(result);
		
		// get the file from the result object
		ChangeOfferFile file = (ChangeOfferFile)result.getFile();
		
		// for each msisdn in the file
		for(ChangeOfferRow row:file.getRows()) {
			
			// verify that the order has been sent to order management
			String orderReference=OrderMonitor.waitForOrderCreation(row.getExternalOrderRef());
			assertNotNull(orderReference);
			
			// if the request includes a handset
			if(row.getDeviceCode()!= null && !row.getDeviceCode().equals("")) {
				boolean orderInDelivery=OrderMonitor.waitForOrderToReachDeliveryStep(orderReference);
				assertTrue(orderInDelivery);
				Logistics.processLogisticsBackend(orderReference);
			}
			
			// verify that the order has completed successfully
			boolean orderComplete = OrderMonitor.waitForOrderToComplete(orderReference);
			assertTrue(orderComplete);
		}
	}

	@BeforeClass
	public void setUp() {
		processor = new BulkProcessor(extentLogger, logger4j);
	}
}

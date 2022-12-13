package testCases.eir.b2b.bulk.move_subscription;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import microservices.backend.eir_bulk_backend.utilities.BulkProcessor;
import microservices.backend.eir_bulk_backend.utilities.BulkResult;
import microservices.backend.eir_cdr_repository_backend.monitor.CDRRepoMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementMonitor;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccount;
import microservices.backend.eir_subscription_management_backend.data_model.Service;

public class TestB2BBulkMoveSubscription extends BaseTest {

	private BulkProcessor processor;

	/**
	 * Test case: Use the bulk process to move as subscription onto another account
	 * in the same hierarchy
	 * 
	 */
	@Test(enabled = true, description = "eir B2B > Bulk > Move Subscription")
	public void testB2BBulkMoveSubscription(ITestContext iTestContext) {

		// get the test data from the environments file
		int billingAccountID = TestDataManager.getB2BCorporateAccount();
		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);

		// get a service on the account
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);

		// determine a new account on the hierarchy
		ArrayList<B2BAccount> childAccounts = SubscriptionManagementDAO.getChildAccounts(accountID);
		int destinationAccountID = childAccounts.get(0).getId();
		int destinationAccountBillingAccountID = SubscriptionManagementDAO.getBillingAccountIDForAccountID(destinationAccountID);

		// add the msisdn to a new list of msisdns
		ArrayList<String> msisdns = new ArrayList<String>();
		msisdns.add(service.getName());

		// process the move subscription file
		BulkResult result = processor.generateAndProcessMoveSubscriptionFile(msisdns, destinationAccountBillingAccountID);
		logInfo(result.getResponse().asString());

		// check that the msisdns are moved successfully
		for (String msisdn : msisdns) {

			// wait for the changes to take place in subscription management
			SubscriptionManagementMonitor.waitForBillingAccountIDUpdated(msisdn, destinationAccountBillingAccountID);
			assertEquals(SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn), destinationAccountBillingAccountID);
			logPass(msisdn + " successfully moved to account " + destinationAccountBillingAccountID + " in subs management");

			// wait for the changes to take place in cdr repository
			boolean cdrRepoUpdated = CDRRepoMonitor.waitForCdrRepoUpdated(msisdn, destinationAccountBillingAccountID);
			assertTrue(cdrRepoUpdated);
			logPass(msisdn + " successfully moved to account " + destinationAccountBillingAccountID + " in CDR repo");
		}
	}

	@BeforeClass
	public void setUp() {
		processor = new BulkProcessor(extentLogger, logger4j);
	}
}

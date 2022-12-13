package testCases.eir.b2b.bulk.terminations;

import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.terminate_subscriptions_file.TerminateSubscriptionsFile;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_bulk_backend.utilities.BulkFileGenerator;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import testCases.Services;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class TestB2BTerminateSubscriptions extends BaseTest {
	
	private String token;

	@Test(enabled = true, description = "B2B > Bulk File: Terminate Subscriptions", invocationCount = 1)
	public void testB2BBulkTerminateSubscription(ITestContext iTestContext) {
		
		// determine the customer account ID and active MSISDN to terminate
		int billingAccountID = TestDataManager.getB2BCorporateAccount();
		String msisdn=SubscriptionManagementDAO.getActiveService(billingAccountID).getName();
		
		// calculate tomorrow's date for the file
		String tomorrowsDate=Timestamp.getFutureDate(5, "yyyy/MM/dd");
		
		// generate the file object
		TerminateSubscriptionsFile file = BulkFileGenerator.generateTerminationFile(billingAccountID, msisdn, TerminationReason.BILLING_ISSUE,tomorrowsDate);

		// write to the file
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\terminate_subscription\\" + file.getFilename();
		TextReader.writeFile(file.toString(), fullFileName);
		
		// upload the file to the bulk service
		Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.TERMINATE_SUBSCRIPTION, fullFileName);

		logPass("TERMINATE_SUBSCRIPTION file send to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
		System.err.println(response.statusCode() + ", " + response.asString());

		// check that the microservice has responded with a success
		assertEquals(response.statusCode(), 204);
		
		// print the file contents
		System.out.println(file);
	}

	@BeforeClass
	public void setUp() {
		LoginCredentials login=EnvironmentDirectory.getB2BAgentLogin();
		Client c = KeycloakDAO.getClient(Services.BULK, Realm.eir);
		token = KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}

	@AfterMethod
	public void tearDown() {

	}
}

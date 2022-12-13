package testCases.backend.eir_billing_abstractor_backend;

import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import io.restassured.response.Response;
import microservices.backend.eir_billing_abstractor_backend.api.BillingAbstractorAPI;
import microservices.backend.eir_cdr_repository_backend.enums.ProcessType;
import microservices.backend.eir_cdr_repository_backend.utilities.CDRGenerator;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.generic.time.Timestamp;

public class TestAPIBillingAbstractor extends BaseTest {

	private Service service;
	private Account account;
	private String currentPeriod;
	
	@Test(enabled = true, description = "Load Usage")
	public void testGenerateUsage() {
		CDRGenerator.generateUsage(service.getName(), Timestamp.getCurrentTimestamp("MM-yyyy"), ProcessType.RANDOM, true, 50,null);
	}
	
	@Test(enabled = true, description = "Billing Abstractor > Accounts")
	public void testGetOneOffCharges(ITestContext iTestContext) {
		String url=EnvironmentDirectory.getAPIBillingAbstractor() + "/billing_account/" + account.getBillingAccountID();
		Response r = RestAssuredUtil.galaxionGet(url,null);
		logPass("Account ID: " + account.getBillingAccountID() + "\nRequest: " + url + "\nStatus Code: " + r.statusCode() + "\nResponse: " + r.asString() + "\n\n");
		 
		assertEquals(r.statusCode(),200);
	}
	
	@Test(enabled = true, description = "Billing Abstractor > Get Usage") 
	public void testGetUsage(ITestContext iTestContext) {
		APITransaction transaction=BillingAbstractorAPI.getUsage(service.getId(), currentPeriod);
		Response r = transaction.getResponse();
		assertEquals(r.statusCode(),200);
		logPass(r.asString());
	}
	
	@Test(enabled = true, description = "Billing Abstractor > Get Usage Summary")
	public void testGetUsageSummary(ITestContext iTestContext) {
		APITransaction transaction=BillingAbstractorAPI.getUsageSummary(service.getId(), currentPeriod);
		Response r = transaction.getResponse();
		logPass(r.asString());
		assertEquals(r.statusCode(),200);
	}

	
	@BeforeClass
	public void setUp() {
		int billingAccountID=TestDataManager.getGoMoMultilineSubscriber();
		account=SubscriptionManagementDAO.getAccountByBillingAccountID(billingAccountID);
		service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		currentPeriod=Timestamp.getCurrentTimestamp("yyyyMM");
	}

	@AfterMethod
	public void tearDown() {

	}	
}

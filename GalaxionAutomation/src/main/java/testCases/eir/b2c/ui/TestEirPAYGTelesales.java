package testCases.eir.b2c.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_catalog_core_backend.enums.SalesChannel;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.enums.RegistrationStatus;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.eir_topup_backend.enums.TopupAmount;
import selenium.pages.eir_prepay.acquisitions.flows.EirPrepayAcquisitionsFlow;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import testCases.testObjects.orders.PrepayOrder;
import testCases.eir.b2c.testObjects.PrepaySingleLineOrder;
import testCases.testObjects.orders.OrderSubscription;
import testCases.testObjects.orders.TestObjectCRMOrder;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.time.WaitUtil;

public class TestEirPAYGTelesales extends BaseTest {

	int billingAccountID;

	ArrayList<String> orderNumbers = new ArrayList<String>();
	ArrayList<TestObjectCRMOrder> placedOrders = new ArrayList<TestObjectCRMOrder>();

	@Test(enabled = true, description = "eir Prepay > CRM UI > Log in to CRM UI", invocationCount = 1) // (dataProvider = // "order-quantities")
	public void testLoginToCRMUI(ITestContext iTestContext) {
				
		// read the URL and login credentials and confirm that they are found
		// successfully
		String url = EnvironmentDirectory.getPaygCrmUiURL();
		LoginCredentials loginDetails = EnvironmentDirectory.getEirPAYGTelesalesLogin();

		// verify that the information has been retrieved successfully
		assertNotNull(url);
		assertNotNull(loginDetails);

		// open a new browser
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		// browse to the URL
		driver.get(url);

		// log into the CRM UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(loginDetails.getUsername(), loginDetails.getPassword());

		// wait for the page to load and grab a screenshot of the homepage
		WaitUtil.waitForSeconds(7);
		logPass("CRM UI is loaded successfully");
	}
	
	@Test(enabled = true, dependsOnMethods = { "testLoginToCRMUI" }, description = "eir Prepay > CRM UI > Order Simplicity Offer", invocationCount = 1) // (dataProvider = // "order-quantities")
	public void testTelesalesSimplicityOrder(ITestContext iTestContext) {
		
		// browse to the URL
		driver.get(EnvironmentDirectory.getPaygCrmUiURL());
		
		// set up the test
		SalesChannel channel=SalesChannel.EIR_TELESALES;
		RegistrationStatus registration=RegistrationStatus.REGISTERED;
		OfferCode offerCode=OfferCode.PAYG_SIMP;
		String deviceCode="GAPP12M1G";
		TopupAmount topup=TopupAmount.EUR40;
		SubscriptionAddonBundle bundle = SubscriptionAddonBundle.AO_SIMPLY_ULTD_DATA_CALLS_TEXTS;
		
		PrepaySingleLineOrder singleOrder=new PrepaySingleLineOrder(channel, registration, deviceCode, offerCode, topup, bundle);
		this.placeSingleLineOrder(singleOrder);
	}
	
	@Test(enabled = true, dependsOnMethods = { "testLoginToCRMUI" }, description = "eir Prepay > CRM UI > Order PAYG MBB Offer", invocationCount = 1) // (dataProvider = // "order-quantities")
	public void testTelesalesMBBOrder(ITestContext iTestContext) {
		
		// browse to the URL
		driver.get(EnvironmentDirectory.getPaygCrmUiURL());
		
		// set up the test
		SalesChannel channel=SalesChannel.EIR_TELESALES;
		RegistrationStatus registration=RegistrationStatus.REGISTERED;
		OfferCode offerCode=OfferCode.PAYG_MBB;
		String deviceCode=null;
		TopupAmount topup=TopupAmount.EUR40;
		SubscriptionAddonBundle bundle = null;
		
		PrepaySingleLineOrder singleOrder=new PrepaySingleLineOrder(channel, registration, deviceCode, offerCode, topup, bundle);
		this.placeSingleLineOrder(singleOrder);
	
	}

	/**
	 * Refactoring test - use Flow object
	 * 
	 * @param iTestContext
	 */
	@Test(enabled = false, description = "eir Prepay: CRM UI > Telesales > New Acquisition [Simplicity]", invocationCount = 1) // (dataProvider =
																																// "order-quantities")
	public void testNewAcquisitionOrderTelesalesSimplicity(ITestContext iTestContext) {

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		EirPrepayAcquisitionsFlow flow = new EirPrepayAcquisitionsFlow(driver, extentLogger, logger4j);

		// create the order
		PrepayOrder order = new PrepayOrder(SalesChannel.EIR_TELESALES, RegistrationStatus.REGISTERED);

		ArrayList<SubscriptionAddonBundle> addons1 = new ArrayList<SubscriptionAddonBundle>();
		addons1.add(SubscriptionAddonBundle.AO_SIMPLY_ULTD_DATA_CALLS_TEXTS);
		addons1.add(SubscriptionAddonBundle.AO_SIMPLY_10GB_BUNDLE);

		ArrayList<SubscriptionAddonBundle> addons2 = new ArrayList<SubscriptionAddonBundle>();
		addons2.add(SubscriptionAddonBundle.AO_SIMPLY_ULTD_CALLS_TEXT_1GB);

		// create the subscription
		OrderSubscription subscription1 = new OrderSubscription(OfferCode.PAYG_SIMP, addons1, TopupAmount.EUR40, "GAPP12M1G");
		OrderSubscription subscription2 = new OrderSubscription(OfferCode.PAYG_SIMP, null, TopupAmount.EUR20, "GAPP12M1G");

		// add the subscriptions to the order
		ArrayList<OrderSubscription> subscriptions = new ArrayList<OrderSubscription>();
		subscriptions.add(subscription1);
		subscriptions.add(subscription2);

		order.setSubscriptions(subscriptions);
		String orderReference = flow.placeEirPAYGOrder(order);
		System.out.println("Complete - order reference = " + orderReference);

		// if the channel is telesales, complete the logistics steos
		if (order.getChannel() == SalesChannel.EIR_TELESALES) {
			boolean orderInDelivery = OrderManagementMonitor.waitForOrderToReachStep(orderReference,"DELIVERY");
			assertTrue(orderInDelivery);
			Logistics.processLogisticsBackend(orderReference);
		}

		// verify that the order has gone to status COMPLETED
		boolean orderComplete = OrderManagementMonitor.waitForOrderToComplete(orderReference);
		assertTrue(orderComplete);
	}
	
	public void placeSingleLineOrder(PrepaySingleLineOrder order) {
		// place the order
		EirPrepayAcquisitionsFlow flow = new EirPrepayAcquisitionsFlow(driver, extentLogger, logger4j);
		
		String orderReference = flow.placeEirPAYGOrder(order.toPrepayOrder());
		assertNotNull(orderReference);
		ArrayList<LogisticsDTO> packs = Logistics.processLogisticsBackend(orderReference);
		
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(orderReference);
		assertTrue(orderCompleted);
		
		Service serviceProvisioned = SubscriptionManagementDAO.getServiceByMSISDN(packs.get(0).getMsisdn());
		Subscription subscription=SubscriptionManagementDAO.getSubscriptionByID(serviceProvisioned.getSubscriptionID());
		assertEquals(subscription.getStatus(), "ACTIVE");
		
		logPass("Service " + serviceProvisioned.getName() + " is now ACTIVE");
	}

	@BeforeMethod
	public void setUp() {
		// driver = new ChromeDriver();
		// driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		// Close chromedriver
		// driver.close();
	}

}

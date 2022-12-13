package testCases.gomo.ui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.generic.Person;
import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.NetworkElement;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.UsageQuota;
import selenium.pages.gomo.eshop.EShopCartPage;
import selenium.pages.gomo.eshop.EShopConfirmationPage;
import selenium.pages.gomo.eshop.EShopCustomerDetailsPage;
import selenium.pages.gomo.eshop.EShopOfferSelectionPage;
import selenium.pages.gomo.eshop.EShopSummaryPage;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import selenium.pages.gomo.my_gomo.flows.GoMoRegistrationFlow;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.time.WaitUtil;

public class GoMoAcquisitionsTests extends BaseTest {

	/**
	 * Test case: Place a single line order via eShop UI (with order completion)
	 * 
	 * @param context
	 */
	@Test(description = "GoMo: eShop > New Acquisition - Single-line", invocationCount = 1)
	public void testGoMoOrderSingle(ITestContext context) {
		String orderNumber = this.placeGoMoNewAcquisitionOrdereShopUI(1);
		WaitUtil.waitForSeconds(5);
		assertTrue(this.completeAndVerifyOrder(orderNumber));
		context.setAttribute("email", OrderManagementDAO.getEmailAddresForOrder(orderNumber));
	}

	/**
	 * Test case: Place a multi line order via eShop UI (with order completion)
	 * 
	 * @param context
	 */
	@Test(description = "GoMo: eShop > New Acquisition - Multi-line")
	public void testGoMoOrderMulti(ITestContext context) {
		String orderNumber = this.placeGoMoNewAcquisitionOrdereShopUI(4);
		assertTrue(this.completeAndVerifyOrder(orderNumber));
		context.setAttribute("email", OrderManagementDAO.getEmailAddresForOrder(orderNumber));
	}

	/**
	 * Test case: Place a cross-sell order via eShop UI (with order completion)
	 * 
	 * @param context
	 */
	@Test(description = "GoMo: eShop > Cross-Sell")
	public void testGoMoOrderCrossSell(ITestContext context) {
		String email = (String) context.getAttribute("email");

		if (email == null) {
			email = "lucas.black_20210924020104@gomo.ie";
		}
		String orderNumber = this.placeGoMoCrossSellOrdereShopUI(email, "Password123", 1);

		assertTrue(this.completeAndVerifyOrder(orderNumber));
	}

	/**
	 * Test case: Complete the registration journey for GoMo
	 * 
	 * @param context
	 */
	@Test(description = "GoMo: Keycloak > Registration")
	public void verifyRegistration(ITestContext context) {

		String email = (String) context.getAttribute("email");
		assertNotNull(email);

		GoMoRegistrationFlow flow = new GoMoRegistrationFlow(driver, extentLogger, logger4j);
		flow.testVerifyUser(email);
	}

	/**
	 * Fulfill the order and check that the order moves to COMPLETED and the
	 * services move to ACTIVE
	 * 
	 * @param orderReference
	 */
	public boolean completeAndVerifyOrder(String orderReference) {

		String[] expectedUsageQuotas = { "EU_ROAMING_(ALL_CALLS,_TEXTS_&_10GB_DATA)", "ALL_DATA", "ALL_CALLS_ALL_TEXTS" };
		String[] expectedNetworkElements = { "ALLOW_LTE", "ALLOW_ROAMING", "ALLOW_INTERNATIONAL_CALLS","ALLOW_PREMIUM_CALLS","ALLOW_VOICE_MAILS","WIFI_CALLING"};

		// wait for the order to reach the delivery step
		boolean orderAwaitingDelivery = OrderManagementMonitor.waitForOrderToReachStep(orderReference,"DELIVERY");
		assertTrue(orderAwaitingDelivery);

		// process logistics
		ArrayList<LogisticsDTO> dtos = Logistics.processLogisticsBackend(orderReference);

		// ArrayList<EquipmentPack> msisdns =
		// LogisticsOld.processLogisticsBackend(orderReference);
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(orderReference);
		assertTrue(orderCompleted);

		// verify that the services are active
		for (LogisticsDTO dto : dtos) {
			assertTrue(SubscriptionManagementDAO.getServiceStatus(dto.getMsisdn()).equals("ACTIVE"));
			logPass("Service " + dto.getMsisdn() + " is now ACTIVE");
		}

		// verify usage quotas
		for (LogisticsDTO dto : dtos) {
			Service service = SubscriptionManagementDAO.getServiceByMSISDN(dto.getMsisdn());
			ArrayList<UsageQuota> usages = SubscriptionManagementDAO.getUsageQuotas(service.getId());
			assertEquals(usages.size(), expectedUsageQuotas.length);

			// for each usage qupta expected
			for (int i = 0; i < expectedUsageQuotas.length; i++) {
				boolean found = false;

				// cycle through the list of usage quotas on the subscription
				for (UsageQuota quota : usages) {

					// check for a match
					if (quota.getCatalogCode().equals(expectedUsageQuotas[i])) {
						found = true;
						assertNotNull(quota.getActivatedAt());
						assertNull(quota.getTerminatedAt());
						break;
					}
				}
				// check that the usage quota has been found
				assertTrue(found);
				logPass("Usage quota " + expectedUsageQuotas[i] + " found");
			}
		}

		// verify network elements
		for (LogisticsDTO dto : dtos) {
			Service service = SubscriptionManagementDAO.getServiceByMSISDN(dto.getMsisdn());
			ArrayList<NetworkElement> networkElements = SubscriptionManagementDAO.getNetworkElements(service.getId());
			assertEquals(networkElements.size(), expectedNetworkElements.length);

			// for each network element expected
			for (int i = 0; i < expectedUsageQuotas.length; i++) {
				boolean found = false;

				// cycle through the list of usage quotas on the subscription
				for (NetworkElement element : networkElements) {

					// check for a match
					if (element.getCatalogCode().equals(expectedNetworkElements[i])) {
						found = true;
						assertNotNull(element.getActivatedAt());
						assertNull(element.getTerminatedAt());
						break;
					}
				}
				// check that the network element has been found
				assertTrue(found);
				logPass("Network Element " + expectedNetworkElements[i] + " found");
			}
		}

		return orderCompleted;
	}

	/**
	 * Place a new acquisition order via eShop
	 * 
	 * @param numOffers - the number of offers required on an order
	 * @return the order reference (string)
	 */
	public String placeGoMoNewAcquisitionOrdereShopUI(int numOffers) {

		Person owner = RandomStringGenerator.getRandomPerson();

		logInfo("Creating a new order with products x " + numOffers);
		logInfo("Customer opens a browser and goes to the GoMo eShop");

		// navigate to eShop
		driver.get(EnvironmentDirectory.getGoMoEShopURL());

		// accept cookies and select the offer
		EShopOfferSelectionPage selectionPage = new EShopOfferSelectionPage(driver);
		selectionPage.clickAcceptCookies();
		selectionPage.selectOffer();

		// populate the cart with the required number of offers and click Continue
		EShopCartPage cartPage = new EShopCartPage(driver);
		cartPage.selectOffers(numOffers - 1);
		cartPage.selectContinue();

		// populate the customer details with the owner details
		EShopCustomerDetailsPage customerDetailsPage = new EShopCustomerDetailsPage(driver);
		customerDetailsPage.populateNewOwner(owner);
		logPass("Customer details entered - email address: " + owner.getEmailAddress());

		// complete the payment steps
		EShopSummaryPage summaryPage = new EShopSummaryPage(driver);
		summaryPage.populate(owner);
		logPass("Payment information entered: " + owner.getCreditCardNumber());

		// wait for PSD2 authorization to complete
		WaitUtil.waitForSeconds(30);
		logPass("Waiting 30 seconds for PSD2 auth to complete");

		// read the order number from the page
		EShopConfirmationPage conf = new EShopConfirmationPage(driver);
		String orderNumber = conf.getOrderNumber();

		logPass("Order placed successfully. Order number is " + orderNumber);

		return orderNumber;
	}

	/**
	 * Place a cross-sell order on the GoMo eShop
	 * 
	 * @param email     - email address of the user
	 * @param password  - password of the user
	 * @param numOffers - number of offers to be added to the order
	 * @return order reference
	 */
	public String placeGoMoCrossSellOrdereShopUI(String email, String password, int numOffers) {

		logInfo("Creating a new order with products x " + numOffers);
		logInfo("Customer opens a browser and goes to the GoMo eShop");

		// navigate to eShop
		driver.get(EnvironmentDirectory.getGoMoEShopURL());

		// accept cookies and select the offer
		EShopOfferSelectionPage selectionPage = new EShopOfferSelectionPage(driver);
		selectionPage.clickAcceptCookies();
		selectionPage.selectOffer();

		// populate the cart with the required number of offers and click Continue
		EShopCartPage cartPage = new EShopCartPage(driver);
		cartPage.selectOffers(numOffers - 1);
		cartPage.selectContinue();

		// log in with the owner details
		EShopCustomerDetailsPage customerDetailsPage = new EShopCustomerDetailsPage(driver);
		customerDetailsPage.selectLogin();
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(email, password);

		// after login, we will be on the offer selection screen
		// so we need to get back to the customer details screen

		// click the cart and proceed to customer details
		EShopOfferSelectionPage offerSelectionPage = new EShopOfferSelectionPage(driver);
		offerSelectionPage.clickAcceptCookies();
		offerSelectionPage.clickCart();

		cartPage = new EShopCartPage(driver);
		// cartPage.selectOffers(order.getNumOffers() - 1);
		cartPage.selectContinue();

		// select T&Cs and proceed to payment
		customerDetailsPage = new EShopCustomerDetailsPage(driver);
		customerDetailsPage.selectTandCs();
		customerDetailsPage.selectNext();

		// make the payment using the saved card
		EShopSummaryPage paymentScreen = new EShopSummaryPage(driver);

		paymentScreen.payWithSavedCard(email, "222");

		// read out the order number
		EShopConfirmationPage confirmationPage = new EShopConfirmationPage(driver);
		assertTrue(confirmationPage.getOrderNumber().length() == 8);

		String orderNumber = confirmationPage.getOrderNumber();
		confirmationPage.logout();
		return orderNumber;
	}

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}

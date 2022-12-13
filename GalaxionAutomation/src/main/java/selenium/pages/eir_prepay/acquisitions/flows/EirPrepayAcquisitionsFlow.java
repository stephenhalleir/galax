package selenium.pages.eir_prepay.acquisitions.flows;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.AddonBundle;
import microservices.backend.eir_catalog_core_backend.enums.SalesChannel;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_order_management_backend.enums.RegistrationStatus;
import selenium.flows.base_flow.BaseUIFlow;
import selenium.pages.eir_prepay.acquisitions.CRMAcquisitionConfigurationPage;
import selenium.pages.eir_prepay.acquisitions.CRMAcquisitionCustomerDetails;
import selenium.pages.eir_prepay.acquisitions.CRMAcquisitionLandingPage;
import selenium.pages.eir_prepay.acquisitions.CRMAcquisitionPaymentPage;
import selenium.pages.eir_prepay.acquisitions.CRMAcquisitionSummaryPage;
import testCases.testObjects.orders.OrderSubscription;
import testCases.testObjects.orders.PrepayOrder;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class EirPrepayAcquisitionsFlow extends BaseUIFlow {

	/*
	 * Constructors
	 */
	public EirPrepayAcquisitionsFlow(WebDriver driver, ExtentTest logger, Logger logger4j) {
		super(logger, logger4j, driver);
	}

	public EirPrepayAcquisitionsFlow(WebDriver driver) {
		super(driver);
	}
	
	public String placeEirPAYGOrder(PrepayOrder order) {
		/*
		// read the URL and login credentials and confirm that they are found
		// successfully
		String url = EnvironmentManager.getConfigValue("UI.CRMUI");
		String username=null;
		String password=null;
		
		// retrieve the login details based on the channel selected
		if(order.getChannel()==SalesChannel.EIR_TELESALES) {
			username = EnvironmentManager.getConfigValue("AGENTS.EIR.TELESALES_AGENT.USERNAME");
			password = EnvironmentManager.getConfigValue("AGENTS.EIR.TELESALES_AGENT.PASSWORD");
		}
		else if(order.getChannel()==SalesChannel.EIR_RETAIL) {
			username = EnvironmentManager.getConfigValue("AGENTS.EIR.RETAIL_AGENT.USERNAME");
			password = EnvironmentManager.getConfigValue("AGENTS.EIR.RETAIL_AGENT.PASSWORD");
		}
		else {
			System.err.println("Error: No sales channel provided for the PAYG order. Unable to determine login");
		}
		
		// verify that the information has been retrieved successfully
		assertNotNull(url);
		assertNotNull(username);
		assertNotNull(password);

		// browse to the URL
		driver.get(url);

		// log into the CRM UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(username, password);

		// wait for the page to load and grab a screenshot of the homepage
		WaitUtil.waitForSeconds(7);
		logPass("CRM UI is loaded successfully");
		*/
		
		// keep a counter of the offers currently in the cart
		int offerCount = 0;
		
		// for each offer on the order
		for(OrderSubscription subscription:order.getSubscriptions()) {
						
			CRMAcquisitionLandingPage acquisitionPage = new CRMAcquisitionLandingPage(driver);
			
			// determine the offer description
			String offerDescription = CatalogCoreDAO.getOfferDescriptionByCode(subscription.getOfferCode().toString());
			acquisitionPage.selectOffer(offerDescription);
			
			offerCount++;

			WaitUtil.waitForSeconds(5);

			CRMAcquisitionConfigurationPage configurationPage = new CRMAcquisitionConfigurationPage(driver);
			
			if(subscription.getAddons() != null) {
				// for each addon on the list
				for(SubscriptionAddonBundle bundleCode:subscription.getAddons()) {
					
					// determine whether the addon is of type "SUBSCRIPTION", "ADDON", or "TOP_UP"
					AddonBundle addon = CatalogCoreDAO.getAddonBundleForCode(bundleCode);
					String bundleType = addon.getItemGroupCode();
					
					if(bundleType.equals("SUBSCRIPTION")) {
						configurationPage.selectSubscriptionOffer(addon.getDescription());
					}
					else if(bundleType.equals("TOP_UP")) {
						configurationPage.selectTopupOffer(addon.getDescription());
					}
					else if(bundleType.equals("ADDON")) {
						configurationPage.selectAddon(addon.getDescription());
					}	
				}
			}
			
			// only select a handset if a handset exists on the order and channel is telesales
			if(subscription.getHandsetCode() != null && order.getChannel()==SalesChannel.EIR_TELESALES) {
				configurationPage.selectHandsetByCode(subscription.getHandsetCode());
			} else {
				logInfo("Error: Skipping handset step due to handset " + subscription.getHandsetCode() + " and channel " + order.getChannel().toString());
			}
			
			// select the topup amount
			configurationPage.selectTopupAmount(subscription.getTopupAmount());
			
			if(offerCount < order.getSubscriptions().size()) {
				System.out.println("Clicking new offer");
				configurationPage.clickAddNewOffer();
				WaitUtil.waitForSeconds(3);
			}
			else {
				configurationPage.clickCheckout();
				WaitUtil.waitForSeconds(3);
			}
		}

		CRMAcquisitionCustomerDetails detailsPage = new CRMAcquisitionCustomerDetails(driver);
		
		if(order.getRegistrationStatus()==RegistrationStatus.REGISTERED) {
			detailsPage.selectRegistered(true);
			detailsPage.enterDOB("10101981");
			detailsPage.selectSecurityQuestion("Memorable date");
			detailsPage.enterResponse("this is my response");
			detailsPage.selectBillingEircode("D24YX53");
		}
		else if(order.getRegistrationStatus()==RegistrationStatus.ANONYMOUS) {
			detailsPage.selectRegistered(false);
			detailsPage.selectDeliveryEircode("D24YX53");
		}

		// enter the name
		detailsPage.enterFirstName("Steve");
		detailsPage.enterLastName("Test");

		// enter the email
		String email = "Steve.Auto-" + Timestamp.getCurrentTimestamp("yyyyMMddHHmmss") + "@eirpayg.ie";
		detailsPage.enterEmail(email);
		detailsPage.enterConfirmEmail(email);
		logPass("Customer details entered: " + email);
		
		// enter the contact phone number
		detailsPage.enterContactNumber("0861231231");
		
		// proceed to the Summary screen
		detailsPage.clickNext();

		WaitUtil.waitForSeconds(3);

		CRMAcquisitionSummaryPage summaryPage = new CRMAcquisitionSummaryPage(driver);
		System.out.println(summaryPage.getOrderNumber());
		summaryPage.acceptTerms();
		logPass("Accepted T&Cs");

		String orderReference=null;
		
		// if the channel is TELESALES, complete the payments step
		if (order.getChannel()==SalesChannel.EIR_TELESALES) {
			summaryPage.clickNext();

			WaitUtil.waitForSeconds(5);

			CRMAcquisitionPaymentPage paymentPage = new CRMAcquisitionPaymentPage(driver);
			paymentPage.enterDetailsAndPay("4263970000005262", "1022", "222", "Steve Test");
			logPass("Completed payment");

			WaitUtil.waitForSeconds(10);

			orderReference = paymentPage.getOrderNumber();

			orderReference = orderReference.substring(orderReference.indexOf("#")).trim();
			orderReference = orderReference.replace("#", "").trim();
			System.err.println("Order placed. Order number:" + orderReference);
			logPass("Order completed: order reference " + orderReference);
		}
		// else if the channel is RETAIL, skip the payments step
		else if (order.getChannel()==SalesChannel.EIR_RETAIL) {
			summaryPage.clickCompleteOrder();
			orderReference = summaryPage.getPopupOrderNumber();
			logPass("Order placed: " + orderReference);
		}
		
		return orderReference;
	}
}

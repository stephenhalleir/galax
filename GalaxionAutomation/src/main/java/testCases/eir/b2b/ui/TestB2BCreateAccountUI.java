package testCases.eir.b2b.ui;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.data_providers.addons.B2BBillableSetting;
import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;
import microservices.backend.eir_order_management_backend.data_model.OrderManagementAccount;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import selenium.pages.eir_b2b.acquisitions.create_account.flows.B2BCreateAccountUIFlow;

public class TestB2BCreateAccountUI extends BaseTest {

	/**
	 * Test the creation of a root account of type CORPORATE via the CRM UI
	 */
	@Test(enabled = false, description = "B2B > CRM UI > Create Root Account [CORPORATE/EBU]", invocationCount = 1)
	public void testCreateNewB2BRootAccountCorporate(ITestContext iTestContext) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		B2BCreateAccountUIFlow flow = new B2BCreateAccountUIFlow(driver, extentLogger, logger4j);
		flow.createB2BAccount(0,B2BAccountType.CORPORATE_OR_EBU);
	}
	
	/**
	 * Test the creation of a root account of type GOVERNMENT via the CRM UI
	 */
	@Test(enabled = false, description = "B2B > CRM UI > Create Root Account (UI) [GOVERNMENT]", invocationCount = 1)
	public void testCreateNewB2BRootAccountGovernment(ITestContext iTestContext) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		B2BCreateAccountUIFlow flow = new B2BCreateAccountUIFlow(driver, extentLogger, logger4j);
		flow.createB2BAccount(0,B2BAccountType.GOVERNMENT);
	}	
	
	
	@Test(enabled = true, description = "B2B > CRM UI > Create Multi-level account via UI", invocationCount = 1)
	public void testCreateNewB2BMultiLevelAccountUI(ITestContext iTestContext) {
		B2BAccountType accountType=B2BAccountType.CORPORATE_OR_EBU;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		B2BCreateAccountUIFlow flow = new B2BCreateAccountUIFlow(driver, extentLogger, logger4j);
		int multilineAccountID = flow.createMultilevelAccount(accountType, 0, 1, 3);
		logInfo("Test completed - account ID = " + multilineAccountID);
	}
	
	
	@Test(enabled = false, description = "B2B > Create Multi-level account via BE", invocationCount = 1)
	public void testCreateNewB2BMultiLevelAccountBE(ITestContext iTestContext) {
		
		int max=5;
		B2BAccountType accountType=B2BAccountType.CORPORATE_OR_EBU;
		B2BBillableSetting subAccountBillingSetting = B2BBillableSetting.BILLABLE;
		
		String rootOrderRef=OrderManagementAPI.createB2BAccount(null,B2BBillableSetting.BILLABLE,accountType);
		
		// create the root account
		OrderManagementAccount rootAccount=OrderManagementMonitor.waitForB2BAccountCreatedForOrder(rootOrderRef);
		
		System.out.println("rootAccountID=" + rootAccount.getId());
		assertNotNull(rootAccount);
		
		int rand=RandomStringGenerator.getRandomInteger(1,max);
		
		// create a random number of sub accounts on the root
		for(int i=0;i<rand;i++) {
			
			// create a L2 sub account
			String l2OrderRef=OrderManagementAPI.createB2BAccount(Integer.toString(rootAccount.getId()),subAccountBillingSetting,accountType);
			
			OrderManagementAccount l2Account = OrderManagementMonitor.waitForB2BAccountCreatedForOrder(l2OrderRef);
			assertNotNull(l2Account);
			System.out.println(i + "/" + rand + ": l2AccountID=" + l2Account.getId());
			
			int rand2=RandomStringGenerator.getRandomInteger(1,max);
			
			// create a random number of L3 sub accounts on the sub account
			for(int j=0;j<rand2;j++) {
				String l3OrderRef=OrderManagementAPI.createB2BAccount(Integer.toString(l2Account.getId()),subAccountBillingSetting,accountType);
				OrderManagementAccount l3Account = OrderManagementMonitor.waitForB2BAccountCreatedForOrder(l3OrderRef);
				System.out.println(j + "/" + rand2 + ": l3AccountID=" + l3Account.getId());
				
				// create a random number of L4 sub accounts on the sub account
				int rand3=RandomStringGenerator.getRandomInteger(1,max);
				for(int k=0;k<rand3;k++) {
					String l4OrderRef=OrderManagementAPI.createB2BAccount(Integer.toString(l3Account.getId()),subAccountBillingSetting,accountType);
					OrderManagementAccount l4Account = OrderManagementMonitor.waitForB2BAccountCreatedForOrder(l4OrderRef);
					System.out.println(k + "/" + rand3 + ": l4AccountID=" + l4Account.getId());
				}
			}
		}
		System.out.println("Completed!!");
		driver.close();
	}
	
	
	/*
	/**
	 * 
	 * @param accountNumber
	 * @param accountType
	 * @return
	 */
	/*
	public int createAccountViaUI(String accountNumber,B2BOfferType accountType) {
		
		log("Creating account on acc " + accountNumber);

		String testStartTime=Timestamp.getTimestamp("yyyy-MM-dd hh:mm:ss");
		System.out.println("Test start: " + testStartTime);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		extentLogger.info("CSR opens the B2B UI");
		
		String url=EnvironmentManager.getConfigValue("UI.B2BUI");
		driver.get(url);

		// generate a random order object
		B2BOrder order = new B2BOrder(accountNumber);
		order.randomize();
		
		int numSiblings;
		String parentName;
		
		if(accountNumber != null) {
			parentName = SubscriptionManagementDAO.getB2BAccountByID(Integer.parseInt(accountNumber)).getName();
			numSiblings=SubscriptionManagementDAO.getB2BAccountsForParent(Integer.parseInt(accountNumber)).size();
			order.setCompanyName(SubscriptionManagementDAO.getCompanyByID(Integer.parseInt(accountNumber)).getName());
			order.setAccountName(parentName + "." + (numSiblings+1));
		}
		
		
		String username = EnvironmentManager.getConfigValue("AGENTS.EIR.B2B_AGENT.USERNAME");
		String password = EnvironmentManager.getConfigValue("AGENTS.EIR.B2B_AGENT.PASSWORD");
		
		// log into the CRM UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(username, password);

		// wait for the page to load and grab a screenshot of the homepage
		extentLogger.pass("B2B UI is loaded successfully");
		
		if(accountNumber!=null) {
			url=url+"/account/" + accountNumber + "/general";
			driver.get(url);
			B2BCareGeneralScreen generalScreen = new B2BCareGeneralScreen(driver);
			generalScreen.clickAddAccount();
		}
		else {
			// select "Create an account"
			B2BSearchScreen searchScreen = new B2BSearchScreen(driver);
			searchScreen.clickCreateAccount();
		}
		
		// enter the account General settings
		B2BGeneralSettingsScreen settingsScreen = new B2BGeneralSettingsScreen(driver);
		settingsScreen.enterCustomerAccountName(order.getAccountName());
		
		if(accountType==B2BOfferType.GOVERNMENT) {
			settingsScreen.selectAccountType("Government");
			settingsScreen.selectMarketSegment("Government/SME");
		}
		else if(accountType==B2BOfferType.CORPORATE_OR_EBU) {
			settingsScreen.selectAccountType("Corporate/EBU");
			settingsScreen.selectMarketSegment("Enterprise/SME");
		}
		
		
		settingsScreen.selectCreditClass("Low");
		settingsScreen.enterEirAccountManager("Steve Test");
		settingsScreen.enterCustomerCostCenter(order.getCustomerCostCenter());
		settingsScreen.enterGroupICID(order.getGroupICID());
		settingsScreen.enterVPNAccountNumber(order.getVpnAccountNumber());
		settingsScreen.enterSalesforceCustomerID(order.getSalesforceCustomerId());
		settingsScreen.enterSalesforceCaseID(order.getSalesforceCaseNumber());
		settingsScreen.enterIndoorCoverageSolutionDate("03/03/2021");
		settingsScreen.enterAgreementDuration("24 months");
		settingsScreen.enterAppleDeviceID(order.getAppleEnrollmentID());
		settingsScreen.enterSamsungDeviceID(order.getSamsungEnrollmentID());
		settingsScreen.enterAndroidDeviceID(order.getGoogleEnrollmentID());
		settingsScreen.clickNext();
		
		// enter the billing account details
		B2BBillingAccountScreen accountScreen = new B2BBillingAccountScreen(driver);
		
		// only complete these fields if they are editable (i.e. if the account is root)
		if(accountNumber==null) {
			accountScreen.selectBillCycle("B2B_BILL_CYCLE_09");
		}
		
		accountScreen.selectBillDeliveryType("Email");
		accountScreen.selectPaymentTerm("30 Days");
		accountScreen.clickNext();
		
		// enter the tax details
		B2BTaxScreen taxScreen = new B2BTaxScreen(driver);
		taxScreen.enterCompanyName(order.getCompanyName());
		taxScreen.enterTaxNumber(order.getTaxNumber());
		taxScreen.clickNext();
		
		// complete the contact screen
		B2BContactScreen contactScreen = new B2BContactScreen(driver);
		contactScreen.enterOwnerFirstName(order.getOwner().getFirstName());
		contactScreen.enterOwnerLastName(order.getOwner().getLastName());
		contactScreen.enterOwnerEmail(order.getOwner().getEmailAddress());
		contactScreen.enterOwnerPhoneNumber(order.getOwner().getContactPhoneNumber());
		contactScreen.enterOwnerMobileNumber(order.getOwner().getMobilePhoneNumber());
		contactScreen.enterOwnerPosition("Owner");
		contactScreen.enterOwnerEircode(order.getOwner().getBillingAddress().getEircode());
		contactScreen.clickConfirmEircode();
		contactScreen.selectPayerIdentical();
		contactScreen.clickSaveAccount();
		
		// enter the contact details
		WaitUtil.waitForSeconds(10);
		
		int accountID=SubscriptionManagementDAO.getB2BAccountID(order.getAccountName(), testStartTime);
		
		assertNotEquals(accountID,-1);
		driver.close();
		
		return accountID;
	}
	*/

	
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

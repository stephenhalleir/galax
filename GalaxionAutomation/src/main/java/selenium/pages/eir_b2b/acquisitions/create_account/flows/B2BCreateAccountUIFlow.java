package selenium.pages.eir_b2b.acquisitions.create_account.flows;

import static org.testng.Assert.assertNotEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import selenium.flows.base_flow.BaseUIFlow;
import selenium.pages.eir_b2b.acquisitions.create_account.B2BBillingAccountScreen;
import selenium.pages.eir_b2b.acquisitions.create_account.B2BContactScreen;
import selenium.pages.eir_b2b.acquisitions.create_account.B2BGeneralSettingsScreen;
import selenium.pages.eir_b2b.acquisitions.create_account.B2BSearchScreen;
import selenium.pages.eir_b2b.acquisitions.create_account.B2BTaxScreen;
import selenium.pages.eir_b2b.acquisitions.customer_care.B2BCareGeneralScreen;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import testCases.testObjects.orders.B2BOrder;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class B2BCreateAccountUIFlow extends BaseUIFlow {

	/*
	 * Constructors
	 */
	public B2BCreateAccountUIFlow(WebDriver driver, ExtentTest logger, Logger logger4j) {
		super(logger, logger4j, driver);
	}

	public B2BCreateAccountUIFlow(WebDriver driver) {
		super(driver);
	}

	/**
	 * Create a new account on the B2B CRM UI
	 * 
	 * @param parentBillingAccountNumber - the parent billing account ID (null if we are
	 *                            creating a root account)
	 * @param accountType         - GOVERNMENT or CORPORATE_OR_EBU
	 * @return the billing acount ID
	 */
	public int createB2BAccount(int parentBillingAccountNumber, B2BAccountType accountType) {

		int accountID=SubscriptionManagementDAO.getAccountIDForBillingAccountID(parentBillingAccountNumber);
		
		// log the information
		if (parentBillingAccountNumber == 0) {
			logInfo("Creating a " + accountType.toString() + " root account");
		} else {
			logInfo("Creating account on parent account " + parentBillingAccountNumber);
		}

		// store the test start time - needed to find the order ID later
		String testStartTime = Timestamp.getTimestamp("yyyy-MM-dd hh:mm:ss");

		String url = EnvironmentDirectory.getB2bCrmUiURL();
		driver.get(url);
		logInfo("CSR opens the B2B UI at " + url);

		// generate a random order object
		B2BOrder order = new B2BOrder(parentBillingAccountNumber);
		order.randomize();

		int numSiblings;
		String parentName;

		if (parentBillingAccountNumber != 0) {
			parentName = SubscriptionManagementDAO.getB2BAccountByID(accountID).getName();
			numSiblings = SubscriptionManagementDAO.getB2BAccountsForParent(accountID).size();
			order.setCompanyName(SubscriptionManagementDAO.getCompanyByID(accountID).getName());
			order.setAccountName(parentName + "." + (numSiblings + 1));
		}

		// if the user needs to log in, log in
		if (driver.getCurrentUrl().contains("keycloak")) {

			LoginCredentials login=EnvironmentDirectory.getB2BAgentLogin();
			logInfo("Logging in using credentials " + login.getUsername() + ", " + login.getPassword());
			KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
			loginPage.login(login.getUsername(), login.getPassword());
		}

		// wait for the page to load and grab a screenshot of the homepage
		logPass("B2B UI is loaded successfully");

		if (parentBillingAccountNumber != 0) {
			url = url + "/account/" + accountID + "/general";
			driver.get(url);
			B2BCareGeneralScreen generalScreen = new B2BCareGeneralScreen(driver);
			generalScreen.clickAddAccount();
		} else {
			// select "Create an account"
			B2BSearchScreen searchScreen = new B2BSearchScreen(driver);
			searchScreen.clickCreateAccount();
		}

		// enter the account General settings
		B2BGeneralSettingsScreen settingsScreen = new B2BGeneralSettingsScreen(driver);
		settingsScreen.enterCustomerAccountName(order.getAccountName());

		if (accountType == B2BAccountType.GOVERNMENT) {
			settingsScreen.selectAccountType("Government");
			settingsScreen.selectMarketSegment("Government/SME");
		} else if (accountType == B2BAccountType.CORPORATE_OR_EBU) {
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
		if (parentBillingAccountNumber == 0) {
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

		// wait for the account to be created
		long waitEndTime=System.currentTimeMillis()+30000;

		// read the billing account ID
		int newAccountID = -1;
		
		while(newAccountID == -1 && System.currentTimeMillis() < waitEndTime) {
			WaitUtil.waitForSeconds(1);
			newAccountID = SubscriptionManagementDAO.getB2BAccountID(order.getAccountName(), testStartTime);
		}

		assertNotEquals(newAccountID, -1);

		return SubscriptionManagementDAO.getBillingAccountIDForAccountID(newAccountID);
	}

	/**
	 * Recursive method to create a b2b account hierarchy 
	 * @param accountType CORPORATE_OR_EBU or GOVERNMENT
	 * @param parentBillingAccountID - enter 0 to create a new root account
	 * @param currentLevel - the current level of the hierarchy (root=0, child 1 is 1, child 1.1 is 2, etc)
	 * @param maxLevels - max number of levels in the hierarchy (exluding root)
	 * @param numSubAccounts - number of child accounts per level
	 * @return
	 */
	private int createMultilevelAccount(B2BAccountType accountType, int parentBillingAccountID, int currentLevel, int maxLevels, int numSubAccounts) {
		
		if (parentBillingAccountID==0) {
			
			// create the root account
			int rootBillingAccountID = this.createB2BAccount(0, accountType);
			createMultilevelAccount(accountType, rootBillingAccountID,currentLevel, maxLevels,numSubAccounts);
		} else {

			if(currentLevel < maxLevels) {
				int[] childAccountIDs=new int[numSubAccounts];
				
				// create the child accounts
				for(int i=0;i<numSubAccounts;i++) {
					childAccountIDs[i]=this.createB2BAccount(parentBillingAccountID, accountType);
				}
				
				for(int i=0;i<childAccountIDs.length;i++) {
					createMultilevelAccount(accountType, childAccountIDs[i],currentLevel+1, maxLevels,numSubAccounts);
				}
			}
			else {
				return parentBillingAccountID;
			}
		}
		return 0;
	}
	
	public int createMultilevelAccount(B2BAccountType accountType, int parentBillingAccountID, int maxLevels, int numSubAccounts) {
		return createMultilevelAccount(accountType, parentBillingAccountID,0,maxLevels,numSubAccounts);
	}
}

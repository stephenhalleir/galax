package microservices.backend.eir_topup_backend.flows;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import selenium.flows.base_flow.BaseUIFlow;
import selenium.pages.eir_prepay.customer_care.CRMLandingPage;
import selenium.pages.eir_prepay.customer_care.CRMServicePage;
import selenium.pages.eir_prepay.customer_care.CRMTopupPage;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.test_data.vouchers.Voucher;
import utilities.galaxion.test_data.vouchers.VoucherManager;
import utilities.generic.time.WaitUtil;

public class CRMUITopupsFlow extends BaseUIFlow {

	/*
	 * Constructors
	 */
	public CRMUITopupsFlow(WebDriver driver, ExtentTest logger, Logger logger4j) {
		super(logger, logger4j, driver);
	}

	public CRMUITopupsFlow(WebDriver driver) {
		super(driver);
	}

	/**
	 * Process a voucher topup via the CRM UI
	 * 
	 * @param msisdn
	 * @param voucherAmount
	 */
	public void processTopup(String msisdn, int voucherAmount) {
		
		int billingAccountID=SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		
		driver.get(EnvironmentDirectory.getPaygCrmUiURL() + "/customer/" + accountID + "/profile");
		
		CRMLandingPage landingPage = new CRMLandingPage(driver);
		landingPage.selectMSISDN(msisdn);

		WaitUtil.waitForSeconds(3);

		CRMServicePage servicePage = new CRMServicePage(driver);
		servicePage.clickTab("Top Up");
		
		CRMTopupPage topupPage = new CRMTopupPage(driver);
		
		// get a voucher from the vouchers file
		Voucher voucher = VoucherManager.getVoucher(voucherAmount);
		
		// process the voucher
		topupPage.processVoucher(voucher);
	}
}

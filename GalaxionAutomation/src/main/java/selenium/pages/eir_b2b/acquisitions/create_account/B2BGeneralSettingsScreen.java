package selenium.pages.eir_b2b.acquisitions.create_account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class B2BGeneralSettingsScreen {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Create an account')]")
	private WebElement btnCreateAccount;
	
	@FindBy(id="customer-account-name")
	private WebElement txtCustomerAccountName;
	
	@FindBy(id="account-type")
	private WebElement ddAccountType;
	
	@FindBy(id="market-segment")
	private WebElement ddMarketSegment;
	
	@FindBy(id="credit-class")
	private WebElement ddCreditClass;
	
	@FindBy(id="eir-account-manager")
	private WebElement txtEirAccountManager;
	
	@FindBy(id="customer-cost-center")
	private WebElement txtCustomerCostCenter;
	
	@FindBy(id="group-icid")
	private WebElement txtGroupICID;
	
	@FindBy(id="vpn-account-number")
	private WebElement txtVPNNumber;
	
	@FindBy(id="salesforce-customer-id")
	private WebElement txtSalesforceCustomerID;
	
	@FindBy(id="salesforce-case-number")
	private WebElement txtSalesforceCaseNumber;
	
	@FindBy(id="indoor-coverage-solution-date")
	private WebElement txtIndoorCoverageSolutionDate;
	
	@FindBy(id="agreement-duration")
	private WebElement txtAgreementDuration;
	
	@FindBy(id="apple-dep-id")
	private WebElement txtAppleDEPID;
	
	@FindBy(id="samsung-kme-id")
	private WebElement txtSamsungKMEID;
	
	@FindBy(id="google-android-zte-id")
	private WebElement txtAndroidZTEID;
	
	@FindBy(xpath="//p[contains(text(),'Next')]")
	private WebElement btnNext;
	
	// Constructor
	public B2BGeneralSettingsScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(txtAndroidZTEID));	
	}
	
	public void clickCreateAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateAccount));	
		btnCreateAccount.click();
	}
	
	public void enterCustomerAccountName(String input) {
		PAYGCRMUIActions.enterValue(driver, txtCustomerAccountName, input);
	}
	
	public void selectAccountType(String input) {
		PAYGCRMUIActions.selectDDValue(driver, ddAccountType,input);
	}
	
	public void selectMarketSegment(String input) {
		PAYGCRMUIActions.selectDDValue(driver, ddMarketSegment, input);
	}
	
	public void selectCreditClass(String input) {
		PAYGCRMUIActions.selectDDValue(driver, ddCreditClass, input);
	}
	
	public void enterEirAccountManager(String input) {
		PAYGCRMUIActions.enterValue(driver, txtEirAccountManager, input);
	}
	
	public void enterCustomerCostCenter(String input) {
		PAYGCRMUIActions.enterValue(driver, txtCustomerCostCenter, input);
	}
	
	public void enterGroupICID(String input) {
		PAYGCRMUIActions.enterValue(driver, txtVPNNumber, input);
	}
	
	public void enterVPNAccountNumber(String input) {
		PAYGCRMUIActions.enterValue(driver, txtVPNNumber, input);
	}
	
	public void enterSalesforceCustomerID(String input) {
		PAYGCRMUIActions.enterValue(driver, txtSalesforceCustomerID, input);
	}
	
	public void enterSalesforceCaseID(String input) {
		PAYGCRMUIActions.enterValue(driver, txtSalesforceCaseNumber, input);
	}
	
	public void enterIndoorCoverageSolutionDate(String input) {
		PAYGCRMUIActions.enterValue(driver, txtIndoorCoverageSolutionDate, input);
	}
	
	public void enterAgreementDuration(String input) {
		PAYGCRMUIActions.enterValue(driver, txtAgreementDuration, input);
	}
	
	public void enterAppleDeviceID(String input) {
		PAYGCRMUIActions.enterValue(driver, txtAppleDEPID, input);
	}
	
	public void enterSamsungDeviceID(String input) {
		PAYGCRMUIActions.enterValue(driver, txtSamsungKMEID, input);
	}
	
	public void enterAndroidDeviceID(String input) {
		PAYGCRMUIActions.enterValue(driver, txtAndroidZTEID, input);
	}
	
	public void clickNext() {
		PAYGCRMUIActions.actionsClick(driver, btnNext);
	}
}
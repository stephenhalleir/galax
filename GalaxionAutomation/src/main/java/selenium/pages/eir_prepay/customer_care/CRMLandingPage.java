package selenium.pages.eir_prepay.customer_care;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import microservices.frontend.common_ui.enums.QuickAction;
import selenium.actions.PAYGCRMUIActions;
import utilities.generic.time.WaitUtil;

public class CRMLandingPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// controls for the left banner on the page
	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div[2]/div[1]/div[1]/div/p[1]")
	public WebElement lblName;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div[2]/div[1]/div[2]/p[1]")
	public WebElement lblEmail;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div[2]/div[1]/div[3]/div/div[1]/p[2]")
	public WebElement lblAccountStatus;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div[2]/div[1]/div[3]/div/div[2]/p[2]")
	public WebElement lblAccountType;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div[2]/div[1]/div[1]/div/p[2]")
	public WebElement lblAccountNumber;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[1]/div/div[2]/div[1]/div[2]/p[2]")
	public WebElement lblPhoneNumber;

	@FindBy(id = "quick-actions")
	public WebElement dropdownQuickActions;
	
	@FindBy(xpath = "//span[contains(text(),'Order')]")
	public WebElement tabOrders;

	// controls for the customer details section

	// String buttonXpath =
	// "//p[contains(text(),'$offerName')]//following::p[contains(text(),'Add')]".replace("$offerName",
	// offerName);
	@FindBy(xpath = "//p[contains(text(),'First name')]//following::p")
	public WebElement lblCustDetailsFirstName;

	@FindBy(xpath = "//p[contains(text(),'Last name')]//following::p")
	public WebElement lblCustDetailsLastName;

	@FindBy(xpath = "//p[contains(text(),'Phone number')]//following::p")
	public WebElement lblCustDetailsPhoneNumber;

	@FindBy(xpath = "//p[contains(text(),'Date of birth')]//following::p")
	public WebElement lblCustDetailsDOB;

	// controls for contact details

	@FindBy(xpath = "//button[@aria-label='Edit information']")
	public WebElement btnEditContactDetails;

	@FindBy(xpath = "//button[@aria-label='Edit Security question']")
	public WebElement btnEditSecurityQuestion;
	
	@FindBy(xpath = "//input[@name='person.firstName']")
	public WebElement txtFirstName;

	@FindBy(xpath = "//input[@name='person.lastName']")
	public WebElement txtLastName;

	@FindBy(xpath = "//input[@name='mobileNumber']")
	public WebElement txtContactNumber;

	@FindBy(xpath = "//input[@name='person.birthDate']")
	public WebElement txtDOB;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	public WebElement btnSaveContactChanges;

	@FindBy(xpath="//p[contains(text(),'Mobile')]")
	public WebElement msisdnDropdown;
	
	
	// controls for the billing address

	@FindBy(xpath = "//p[contains(text(),'Eircode')]//following::p")
	public WebElement lblEircode;

	@FindBy(xpath = "//p[contains(text(),'Address line 1')]//following::p")
	public WebElement lblAddressLine1;

	@FindBy(xpath = "//p[contains(text(),'Address line 2')]//following::p")
	public WebElement lblAddressLine2;

	@FindBy(xpath = "//p[contains(text(),'Address line 3')]//following::p")
	public WebElement lblAddressLine3;

	@FindBy(xpath = "//p[contains(text(),'Town/City')]//following::p")
	public WebElement lblAddressTown;

	@FindBy(xpath = "//p[contains(text(),'County')]//following::p")
	public WebElement lblAddressCounty;

	//@FindBy(xpath = "//button[@aria-label='Edit Address']")
	@FindBy(xpath="//*[@id=\"root\"]/div/main/div[2]/div[2]/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[1]/button")
	public WebElement btnEditAddressDetails;

	@FindBy(xpath = "//input[@id='eircode']")
	public WebElement txtEircode;

	@FindBy(xpath = "//button[@aria-label='Confirm Eircode']")
	public WebElement btnConfirmEircode;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	public WebElement btnSaveAddressChanges;

	// controls for marketing preferences
	@FindBy(xpath = "//input[@aria-label='notContact-ACTIVE_CUSTOMER']")
	public WebElement chkMPActiveNoContact;

	@FindBy(xpath = "//input[@aria-label='ACTIVE_CUSTOMER-ALLOW_EMAIL_CONTACT']")
	public WebElement chkMPActiveEmail;

	@FindBy(xpath = "//input[@aria-label='ACTIVE_CUSTOMER-ALLOW_SMS_CONTACT']")
	public WebElement chkMPActiveSMS;

	@FindBy(xpath = "//input[@aria-label='ACTIVE_CUSTOMER-ALLOW_PHONE_CONTACT']")
	public WebElement chkMPActivePhone;

	@FindBy(xpath = "//input[@aria-label='ACTIVE_CUSTOMER-ALLOW_FOTS_CONTACT']")
	public WebElement chkMPActiveFOTS;

	@FindBy(xpath = "//input[@aria-label='ACTIVE_CUSTOMER-ALLOW_DIRECT_MAIL_CONTACT']")
	public WebElement chkMPActiveDirectMail;

	@FindBy(xpath = "//input[@aria-label='notContact-NO_LONGER_CUSTOMER']")
	public WebElement chkMPInactiveNoContact;

	@FindBy(xpath = "//input[@aria-label='NO_LONGER_CUSTOMER-ALLOW_EMAIL_CONTACT']")
	public WebElement chkMPInactiveEmail;

	@FindBy(xpath = "//input[@aria-label='NO_LONGER_CUSTOMER-ALLOW_SMS_CONTACT']")
	public WebElement chkMPInactiveSMS;

	@FindBy(xpath = "//input[@aria-label='NO_LONGER_CUSTOMER-ALLOW_PHONE_CONTACT']")
	public WebElement chkMPInactivePhone;

	@FindBy(xpath = "//input[@aria-label='NO_LONGER_CUSTOMER-ALLOW_FOTS_CONTACT']")
	public WebElement chkMPInactiveFOTS;

	@FindBy(xpath = "//input[@aria-label='NO_LONGER_CUSTOMER-ALLOW_DIRECT_MAIL_CONTACT']")
	public WebElement chkMPInactiveDirectMail;

	@FindBy(name = "thirdParty")
	public WebElement chkMP3rdParty;
	
	@FindBy(id="security-questions")
	public WebElement securityQuestionDropdown;
	
	@FindBy(id="response")
	public WebElement txtSecurityAnswer;

	// Constructor
	public CRMLandingPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(msisdnDropdown));
	}

	public void clickEditContactDetails() {
		wait.until(ExpectedConditions.visibilityOf(btnEditContactDetails));
		btnEditContactDetails.click();
	}
	
	public void clickEditSecurityQuestion() {
		wait.until(ExpectedConditions.visibilityOf(btnEditSecurityQuestion));
		btnEditSecurityQuestion.click();
	}

	// methods to get details from the left banner
	public String getBannerName() {
		return getBannerValue(lblName);
	}

	public String getBannerEmail() {
		return getBannerValue(lblEmail);
	}

	public String getBannerAccountStatus() {
		return getBannerValue(lblAccountStatus);
	}

	public String getBannerAccountNumber() {
		return getBannerValue(lblAccountNumber);
	}

	public String getBannerAccountType() {
		return getBannerValue(lblAccountType);
	}

	public String getBannerPhoneNumber() {
		return getBannerValue(lblPhoneNumber);
	}

	public void selectQuickAction(QuickAction action) {
		this.clickQuickActions();
		String xpath = "//li[@data-value='$action']".replace("$action", action.toString());
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();

	}

	public void clickQuickActions() {
		wait.until(ExpectedConditions.visibilityOf(dropdownQuickActions));
		dropdownQuickActions.click();
	}

	// methods to get details from the customer details section
	public String getCustDetailsFirstName() {
		wait.until(ExpectedConditions.visibilityOf(lblCustDetailsFirstName));
		return lblCustDetailsFirstName.getText();
	}

	public String getCustDetailsLastName() {
		wait.until(ExpectedConditions.visibilityOf(lblCustDetailsLastName));
		return lblCustDetailsLastName.getText();
	}

	public String getCustDetailsPhoneNumbers() {
		wait.until(ExpectedConditions.visibilityOf(lblCustDetailsPhoneNumber));
		return lblCustDetailsPhoneNumber.getText();
	}

	public String getCustDetailsDOB() {
		wait.until(ExpectedConditions.visibilityOf(lblCustDetailsDOB));
		return lblCustDetailsDOB.getText();
	}

	// methods to get details from the address section
	public String getAddressEircode() {
		wait.until(ExpectedConditions.visibilityOf(lblEircode));
		return lblEircode.getText();
	}

	public String getAddressLine1() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressLine1));
		return lblAddressLine1.getText();
	}

	public String getAddressLine2() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressLine2));
		return lblAddressLine2.getText();
	}

	public String getAddressLine3() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressLine3));
		return lblAddressLine3.getText();
	}

	public String getTown() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressTown));
		return lblAddressTown.getText();
	}

	public String getCounty() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressCounty));
		return lblAddressCounty.getText();
	}
	
	public String getSettingValue(String key) {
		String xpath = "//p[contains(text(),'$key')]//following::p".replace("$key", key);
		WebElement div = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(div));
		return div.getText();
	}

	// methods to read the marketing preferences

	// enter values into the customer details fields
	public void enterFirstName(String input) {
		PAYGCRMUIActions.replaceValue(txtFirstName, input);
	}

	public void enterLastName(String input) {
		PAYGCRMUIActions.replaceValue(txtLastName, input);
	}

	public void enterContactNumber(String input) {
		PAYGCRMUIActions.replaceValue(txtContactNumber, input);
	}

	// TODO - figure out how to interact with this DOB field
	public void enterDOB(String input) {
		PAYGCRMUIActions.replaceValue(txtDOB,input);
	}

	public void saveContactChanges() {
		wait.until(ExpectedConditions.visibilityOf(btnSaveContactChanges));
		btnSaveContactChanges.click();
	}

	// methods to edit the billing address
	public void clickEditAddress() {
		wait.until(ExpectedConditions.visibilityOf(btnEditAddressDetails));
		btnEditAddressDetails.click();
	}

	public void enterEircode(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtEircode));
		txtEircode.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		txtEircode.sendKeys(input);
	}

	public void clickConfirmEircode() {
		btnConfirmEircode.click();
	}

	public void clickSaveAddressChanges() {
		btnSaveAddressChanges.click();
	}

	// methods to get the marketing preferences

	public boolean isMPActiveNoContactSelected() {
		return chkMPActiveNoContact.isSelected();
	}

	public boolean isMPActiveEmailSelected() {
		return chkMPActiveEmail.isSelected();
	}

	public boolean isMPActiveSMSSelected() {
		return chkMPActiveSMS.isSelected();
	}

	public boolean isMPActivePhoneSelected() {
		return chkMPActivePhone.isSelected();
	}

	public boolean isMPActiveFOTSSelected() {
		return chkMPActiveFOTS.isSelected();
	}

	public boolean isMPActiveDirectMailSelected() {
		return chkMPActiveDirectMail.isSelected();
	}

	public boolean isMPInactiveNoContactSelected() {
		return chkMPInactiveNoContact.isSelected();
	}

	public boolean isMPInactiveEmailSelected() {
		return chkMPInactiveEmail.isSelected();
	}

	public boolean isMPInactiveSMSSelected() {
		return chkMPInactiveSMS.isSelected();
	}

	public boolean isMPInactivePhoneSelected() {
		return chkMPInactivePhone.isSelected();
	}

	public boolean isMPInactiveFOTSSelected() {
		return chkMPInactiveFOTS.isSelected();
	}

	public boolean isMPInactiveDirectMailSelected() {
		return chkMPInactiveDirectMail.isSelected();
	}

	public boolean is3rdPartyOptOutSelected() {
		return chkMP3rdParty.isSelected();
	}

	public void toggleAllMarketingPermissions() {

		WebElement[] elementsToClick = { chkMPActiveEmail, chkMPActiveSMS, chkMPActivePhone, chkMPActiveFOTS, chkMPActiveDirectMail, chkMPInactiveEmail,
				chkMPInactiveSMS, chkMPInactivePhone, chkMPInactiveFOTS, chkMPInactiveDirectMail, chkMP3rdParty };

		for (int i = 0; i < elementsToClick.length; i++) {
			elementsToClick[i].click();
			WaitUtil.waitForSeconds(2);
		}
	}

	// methods to select the MSISDN from the left banner
	public void selectMSISDN(String msisdn) {
		this.clickBannerMobileDropdown();
		this.selectMsisdn(msisdn);
	}

	public void clickBannerMobileDropdown() {
		//WebElement element = driver.findElement(By.xpath("//p[contains(text(),'Mobile')]"));
		wait.until(ExpectedConditions.visibilityOf(msisdnDropdown));
		msisdnDropdown.click();
	}

	public void selectMsisdn(String msisdn) {
		String xpath = "//*[contains(text(),'$msisdn')]".replace("$msisdn", msisdn);
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	// method to return an element text from the banner
	public String getBannerValue(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public void scrollToMarketingPreferences() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", chkMPActiveDirectMail);
	}
	
	public void selectSecurityQuestion(String securityQuestion) {
		wait.until(ExpectedConditions.visibilityOf(securityQuestionDropdown));
		securityQuestionDropdown.click();
		WaitUtil.waitForSeconds(1);
		String itemXpath="//li[@data-value='$item']".replace("$item", securityQuestion);
		WebElement selectValue = driver.findElement(By.xpath(itemXpath));
		wait.until(ExpectedConditions.visibilityOf(selectValue));
		selectValue.click();		
	}
	
	public void enterSecurityAnswer(String answer) {
		wait.until(ExpectedConditions.visibilityOf(txtSecurityAnswer));
		PAYGCRMUIActions.replaceValue(txtSecurityAnswer, answer);
	}
	
	public void clickOrdersTab() {
		wait.until(ExpectedConditions.visibilityOf(tabOrders));
		tabOrders.click();
	}
}
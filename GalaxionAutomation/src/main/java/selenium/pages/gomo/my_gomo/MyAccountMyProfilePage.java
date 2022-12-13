package selenium.pages.gomo.my_gomo;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountMyProfilePage {

	private WebDriver driver;
	private WebDriverWait wait;

	// =================================================================================================================
	// Locators for page navigation controls
	// =================================================================================================================

	@FindBy(xpath = "//button[@aria-label='Next']")
	public WebElement arrowRight;

	@FindBy(xpath = "//button[@aria-label='Back']")
	public WebElement arrowLeft;

	// =================================================================================================================
	// Locator for name field
	// =================================================================================================================

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[2]")
	public WebElement txtName;

	// =================================================================================================================
	// Locators for email controls
	// =================================================================================================================

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/p[1]")
	public WebElement txtEmail;

	@FindBy(xpath = "//*[contains(text(), 'Update')]")
	public WebElement btnUpdateEmail;

	@FindBy(xpath = "//*[contains(text(), 'Reset')]")
	public WebElement btnResetPassword;

	@FindBy(name = "email")
	private WebElement newEmailAddressInput;

	@FindBy(name = "emailConfirm")
	private WebElement confirmNewEmailAddressInput;

	@FindBy(xpath = "//*[contains(text(), 'Save')]")
	private WebElement confirmEmailAddressUpdateButton;

	@FindBy(xpath="//span[contains(text(),'Confirm')]")
	private WebElement btnConfirm;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/div/button")
	public WebElement btnEircodeConfirm;

	// =================================================================================================================
	// Locators for billing address
	// =================================================================================================================

	@FindBy(xpath = "//*[@id=\"addressLine1\"]")
	public WebElement address1Field;

	@FindBy(xpath = "//*[@id=\"addressLine2\"]")
	public WebElement address2Field;

	@FindBy(xpath = "//*[@id=\"addressLine3\"]")
	public WebElement address3Field;

	@FindBy(xpath = "//*[@id=\"town\"]")
	public WebElement townField;

	@FindBy(xpath = "//*[@id=\"counties\"]")
	public WebElement countyField;

	@FindBy(xpath = "//*[@id=\"eircodeText\"]")
	public WebElement eircodeField;

	@FindBy(xpath = "//*[contains(text(), 'Edit')]")
	public WebElement btnEditBillingAddress;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div[2]/button")
	public WebElement btnSaveAddressChanges;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/div[2]/form/label/span[1]/span/input")
	public WebElement chkUpdateDelivery;

	// =================================================================================================================
	// Locators for contact permissions
	// =================================================================================================================

	@FindBy(xpath = "//*[contains(text(), 'An existing customer')]")
	public WebElement btnExistingCustomer;

	@FindBy(xpath = "//*[contains(text(), 'No longer a customer')]")
	public WebElement btnNoLongerCustomer;

	@FindBy(name = "Email")
	public WebElement chkEmail;

	@FindBy(name = "SMS")
	public WebElement chkSms;

	@FindBy(name = "Phone")
	public WebElement chkPhone;

	@FindBy(name = "Direct mail")
	public WebElement chkDirectMail;
	
	@FindBy(name = "FOTS")
	public WebElement chkFOTS;

	@FindBy(xpath = "//*[contains(text(), 'SAVE')]")
	public WebElement btnSave;

	@FindBy(xpath = "//*[contains(text(), 'REFRESH')]")
	public WebElement btnRefresh;

	// =================================================================================================================
	// Locators for NDD settings
	// =================================================================================================================

	@FindBy(xpath = "//*[@id=\\\"filled-number\\\"]")
	public WebElement msisdnField;

	//@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/div[2]/div[2]/label[1]/span[1]/span/input")
	@FindBy(xpath="//input[@value='LISTED']")
	public WebElement nddListed;

	//@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/div[2]/div[2]/label[2]/span[1]/span/input")
	@FindBy(xpath="//input[@value='UNLISTED']")
	public WebElement nddUnlisted;

	//@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/div[2]/div[2]/label[3]/span[1]/span/input")
	@FindBy(xpath="//input[@value='EXDIRECTORY']")
	public WebElement nddExDirectory;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[1]")
	public WebElement msisdn_1;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[2]")
	public WebElement msisdn_2;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[3]")
	public WebElement msisdn_3;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[4]")
	public WebElement msisdn_4;

	// =================================================================================================================
	// Constructor
	// =================================================================================================================

	public MyAccountMyProfilePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// =================================================================================================================
	// Functions for NDD settings
	// =================================================================================================================

	// return the NDD setting selected on the page
	public String getSelectedNDD() {
		
		if (nddListed.isSelected()) {
			return "LISTED";
		} else if (nddUnlisted.isSelected()) {
			return "UNLISTED";
		} else if (nddExDirectory.isSelected()) {
			return "EXDIRECTORY";
		}

		return null;
	}

	// select a named NDD setting
	public void selectNDD(String ndd) {
		
		if (ndd.equals("LISTED")) {
			nddListed.click();
		} else if (ndd.equals("UNLISTED")) {
			nddUnlisted.click();
		} else if (ndd.equals("EXDIRECTORY")) {
			nddExDirectory.click();
		}
	}

	// select a random NDD preference from the screen
	public void selectRandomNDD() {

		// build the list of possible NDD settings
		ArrayList<String> nddSettings = new ArrayList<String>();
		nddSettings.add("LISTED");
		nddSettings.add("UNLISTED");
		nddSettings.add("EXDIRECTORY");

		// remove the currently selected NDD setting from the list of possible options
		nddSettings.remove(this.getSelectedNDD());

		// generate a random nmber - 0 or 1
		int random = (int) Math.round((Math.random()));

		System.out.println("Changing NDD preference from " + this.getSelectedNDD() + " to " + nddSettings.get(random));

		// select a random checkbox
		this.selectNDD(nddSettings.get(random));

		// wait 5 seconds for the change to take effect
		waitSeconds(5);
	}

	// =================================================================================================================
	// Functions to check the name displayed on the page
	// =================================================================================================================

	// return the name displayed on the page
	public String getNameDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(txtName));
		return txtName.getText();
	}

	// =================================================================================================================
	// Functions for reset email and password
	// =================================================================================================================

	// return the email displayed on the page
	public String getEmailDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(txtEmail));
		return txtEmail.getText();
	}

	// click the UPDATE EMAIL button on the screen
	public void selectUpdateEmail() {
		wait.until(ExpectedConditions.visibilityOf(btnUpdateEmail));
		btnUpdateEmail.click();
	}

	// click the UPDATE EMAIL button on the screen
	public void enterNewEmailAddress(String newEmail) {
		wait.until(ExpectedConditions.visibilityOf(newEmailAddressInput));
		newEmailAddressInput.sendKeys(newEmail);
		confirmNewEmailAddressInput.sendKeys(newEmail);
	}

	// click SAVE email
	public void selectSaveNewEmail() {
		confirmEmailAddressUpdateButton.click();
	}

	// follow an email deep link
	// public void followEmailDeeplink(String link) {
	// System.out.println("Calling driver.get " + link);
	// driver.get(link);
	// }

	public void clickPopupConfirmEmailChange() {
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
	}

	public void clickResetPassword() {
		wait.until(ExpectedConditions.visibilityOf(btnResetPassword));
		btnResetPassword.click();
	}

	// =================================================================================================================
	// Functions for billing address
	// =================================================================================================================

	public String getAddressLine1() {
		wait.until(ExpectedConditions.visibilityOf(address1Field));
		return address1Field.getAttribute("value");
	}

	public String getAddressLine2() {
		wait.until(ExpectedConditions.visibilityOf(address2Field));
		return address2Field.getAttribute("value").replace("Address Line 2", "");
	}

	public String getAddressLine3() {
		wait.until(ExpectedConditions.visibilityOf(address3Field));
		return address3Field.getAttribute("value").replace("Address Line 3", "");
	}

	public String getTown() {
		wait.until(ExpectedConditions.visibilityOf(townField));
		return townField.getAttribute("value");
	}

	public String getCounty() {
		wait.until(ExpectedConditions.visibilityOf(countyField));
		return countyField.getText();
	}

	public String getEircode() {
		wait.until(ExpectedConditions.visibilityOf(eircodeField));
		return eircodeField.getAttribute("value").replace("Eircode", "").trim();
	}

	public void setAddressLine1(String addr) {
		wait.until(ExpectedConditions.visibilityOf(address1Field));
		address1Field.sendKeys(Keys.CONTROL, "a");
		address1Field.sendKeys(Keys.DELETE);
		address1Field.sendKeys(addr);
	}

	public void setAddressLine2(String addr) {
		wait.until(ExpectedConditions.visibilityOf(address2Field));
		address2Field.sendKeys(Keys.CONTROL, "a");
		address2Field.sendKeys(Keys.DELETE);
		address2Field.sendKeys(addr);
	}

	public void setAddressLine3(String addr) {
		wait.until(ExpectedConditions.visibilityOf(address3Field));
		address3Field.sendKeys(Keys.CONTROL, "a");
		address3Field.sendKeys(Keys.DELETE);
		address3Field.sendKeys(addr);
	}

	public void setTown(String addr) {
		wait.until(ExpectedConditions.visibilityOf(townField));
		townField.sendKeys(Keys.CONTROL, "a");
		townField.sendKeys(Keys.DELETE);
		townField.sendKeys(addr);
	}

	public void setCounty() {
		wait.until(ExpectedConditions.visibilityOf(countyField));

	}

	public void setEircode(String addr) {
		wait.until(ExpectedConditions.visibilityOf(eircodeField));
		eircodeField.sendKeys(Keys.CONTROL, "a");
		eircodeField.sendKeys(Keys.DELETE);
		eircodeField.sendKeys(addr);
	}

	public void selectEditBillingAddress() {
		wait.until(ExpectedConditions.visibilityOf(btnEditBillingAddress));
		btnEditBillingAddress.click();
	}

	public void selectUpdateDelivery() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkUpdateDelivery);
	}

	public void selectSaveAddressChanges() {
		wait.until(ExpectedConditions.visibilityOf(btnSaveAddressChanges));
		btnSaveAddressChanges.click();
	}

	public void selectConfirmEircode() {
		wait.until(ExpectedConditions.visibilityOf(btnEircodeConfirm));
		btnEircodeConfirm.click();
	}

	// =================================================================================================================
	// Functions for navigation testing
	// =================================================================================================================

	// click the right arrow on the customer details screen
	public void moveRight() {
		wait.until(ExpectedConditions.visibilityOf(arrowRight));
		arrowRight.click();
		wait.until(ExpectedConditions.visibilityOf(arrowLeft));
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
		// refresh the page elements
		PageFactory.initElements(driver, this);
		
	}

	// click the left arrow on the customer details screen
	public void moveLeft() {
		wait.until(ExpectedConditions.visibilityOf(arrowLeft));
		arrowLeft.click();
		wait.until(ExpectedConditions.visibilityOf(arrowRight));
	}

	// =================================================================================================================
	// Functions for Customer Permissions and Contact Preferences
	// =================================================================================================================

	// select the Existing Customer button on P&Cs
	public void clickExistingCustomer() {
		wait.until(ExpectedConditions.elementToBeClickable(btnExistingCustomer));
		btnExistingCustomer.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnNoLongerCustomer));
	}

	// select the No Longer a Customer button on P&Cs
	public void clickNoLongerCustomer() {
		wait.until(ExpectedConditions.elementToBeClickable(btnNoLongerCustomer));
		btnNoLongerCustomer.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnExistingCustomer));
		waitSeconds(1);
	}

	public boolean isEmailEnabled() {
		return chkEmail.isSelected();
	}

	public boolean isSMSEnabled() {
		return chkSms.isSelected();
	}
	
	public boolean isFOTSEnabled() {
		return chkFOTS.isSelected();
	}

	public boolean isPhoneEnabled() {
		return chkPhone.isSelected();
	}

	public boolean isDirectMailEnabled() {
		return chkDirectMail.isSelected();
	}

	// toggle all P&Cs settings
	public void toggleAllPermissions() {
		chkEmail.click();
		chkSms.click();
		chkPhone.click();
		chkFOTS.click();
		chkDirectMail.click();
	}

	public void savePermissionsChanges() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();

		// wait 10 seconds for the update to take effect
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void refreshPermissionsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(btnRefresh));
		btnRefresh.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnRefresh));
	}

	// =================================================================================================================
	// Functions for NDD settings
	// =================================================================================================================

	public void selectMsisdn(String msisdn) {

		// locate the MSISDN checkbox on the NDD screen
		WebElement msisdnField = driver.findElement(By.xpath("//*[@id=\"filled-number\"]"));
		wait.until(ExpectedConditions.visibilityOf(msisdnField));
 
		// select the MSISDN field
		msisdnField.click();
		wait.until(ExpectedConditions.visibilityOf(msisdn_1));

		wait.until(ExpectedConditions.elementToBeClickable(msisdn_1));

		// click the appropriate msisdn in the list
		if (msisdn_1.getText().equals(msisdn)) {
			msisdn_1.click();
		} else if (msisdn_2.getText().equals(msisdn)) {
			msisdn_2.click();
		} else if (msisdn_3.getText().equals(msisdn)) {
			msisdn_3.click();
		} else if (msisdn_4.getText().equals(msisdn)) {
			msisdn_4.click();
		}

		waitSeconds(1);
	}

	public void waitSeconds(int sec) {

		// wait 10 seconds for the update to take effect
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

}
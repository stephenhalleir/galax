package selenium.pages.gomo.csr_ui;

//---------------------------------------------------------------------------------------
// Issues
//		- Click Billing Address > County field is not working - therefore unable to select County
// ---------------------------------------------------------------------------------------

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSRDetailsPage extends CSRBasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	// ---------------------------------------------------------------------------------------
	// Controls for DETAILS section
	// ---------------------------------------------------------------------------------------

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/p")
	private WebElement lblFirstName;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/p")
	private WebElement lblLastName;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[3]/div[2]/p")
	private WebElement lblDob;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[4]/div[2]/p")
	private WebElement lblPhoneNumber;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[5]/div[2]/p")
	private WebElement lblEmail;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div/input")
	private WebElement txtFirstName;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div/div/input")
	private WebElement txtLastName;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[3]/div[2]/div/div/input")
	private WebElement txtDob;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[4]/div[2]/div/div/input")
	private WebElement txtPhoneNumber;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[5]/div[2]/div/div/input")
	private WebElement txtEmail;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[6]/div[2]/div/div/input")
	private WebElement txtConfirmEmail;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[6]/button")
	private WebElement btnDetailsEdit;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[7]/div[1]/button")
	private WebElement btnDetailsCancel;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[7]/div[2]/button")
	private WebElement btnDetailsSave;

	// ---------------------------------------------------------------------------------------
	// Controls for BILLING ADDRESS section
	// ---------------------------------------------------------------------------------------

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]")
	private WebElement lblAddressEircode;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/p[1]")
	private WebElement lblAddressLine1;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/p[1]")
	private WebElement lblAddressLine2;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[4]/div[2]/p[1]")
	private WebElement lblAddressLine3;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[5]/div[2]/p")
	private WebElement lblAddressTown;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[6]/div[2]/p")
	private WebElement lblAddressCounty;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[1]/div[2]/div/div/input")
	private WebElement txtAddressEircode;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[3]/div[2]/div/div/input")
	private WebElement txtAddressLine1;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[4]/div[2]/div/div/input")
	private WebElement txtAddressLine2;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[5]/div[2]/div/div/input")
	private WebElement txtAddressLine3;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[6]/div[2]/div/div/input")
	private WebElement txtAddressTown;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul")
	private WebElement txtAddressCounty;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[7]/button")
	private WebElement btnBillingAddressEdit;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/button")
	private WebElement btnConfirmEircode;

	@FindBy(xpath = "//*[contains(text(), 'SAVE')]")
	private WebElement btnSaveChangeAddress;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div[8]/div[1]/button")
	private WebElement btnCancelChangeAddress;

	@FindBy(xpath = "(//input[@value=''])[2]")
	private WebElement chkUpdateDeliveryAddress;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[7]/div[2]/div[1]/div[1]")
	private WebElement countyDropdown;

	// ---------------------------------------------------------------------------------------
	// Controls for CONTACT PREFERENCES section
	// ---------------------------------------------------------------------------------------
	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/div[1]/button")
	private WebElement btnCustomer;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/div[2]/button")
	private WebElement btnNotCustomer;
	
	@FindBy(xpath="//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[1]/label/span[1]/span[1]/input")
	private WebElement chkEmail;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[2]/label/span[1]/span[1]/input")
	private WebElement chkSMS;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[3]/label/span[1]/span[1]/input")
	private WebElement chkPhone;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[4]/label/span[1]/span[1]/input")
	private WebElement chkFOTS;
	
	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[5]/label/span[1]/span[1]/input")
	private WebElement chkDirectMail;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[3]/div[3]/button")
	private WebElement btnSaveContactPreferences;

	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public CSRDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// ---------------------------------------------------------------------------------------
	// Methods for DETAILS section
	// ---------------------------------------------------------------------------------------

	// return the first name displayed on the page
	public String getFirstNameDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblFirstName));
		return lblFirstName.getText();
	}

	// return the last name displayed on the page
	public String getLastNameDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblLastName));
		return lblLastName.getText();
	}

	// return the date of birth displayed on the page
	public String getDOBDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblDob));
		return lblDob.getText();
	}

	// return the phone number displayed on the page
	public String getPhoneNumberDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblPhoneNumber));
		return lblPhoneNumber.getText();
	}

	// return the email displayed on the page
	public String getEmailDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblEmail));
		return lblEmail.getText();
	}

	// click the EDIT button under the section DETAILS
	public void selectEditDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDetailsEdit));
		btnDetailsEdit.click();
	}

	// enter an updated first name for the customer
	public void enterFirstName(String firstName) {
		if (firstName != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtFirstName));
			txtFirstName.sendKeys(Keys.CONTROL, "a");
			txtFirstName.sendKeys(Keys.DELETE);
			txtFirstName.sendKeys(firstName);
		}
	}

	// enter an updated last name for the customer
	public void enterLastName(String lastName) {
		if (lastName != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtLastName));
			txtLastName.sendKeys(Keys.CONTROL, "a");
			txtLastName.sendKeys(Keys.DELETE);
			txtLastName.sendKeys(lastName);
		}
	}

	// enter an updated date of birth for the customer
	public void enterDOB(String dob) {
		if (dob != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtDob));
			txtDob.click();
			txtDob.sendKeys(dob);
		}
	}

	// enter an updated phone number for the customer
	public void enterPhoneNumber(String phoneNumber) {
		if (phoneNumber != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtPhoneNumber));
			txtPhoneNumber.sendKeys(Keys.CONTROL, "a");
			txtPhoneNumber.sendKeys(Keys.DELETE);
			txtPhoneNumber.sendKeys(phoneNumber);
		}
	}

	// enter an updated email address for the customer
	public void enterEmailAddress(String email) {
		if (email != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtEmail));
			txtEmail.sendKeys(Keys.CONTROL, "a");
			txtEmail.sendKeys(Keys.DELETE);
			txtEmail.sendKeys(email);
		}
	}

	// enter an updated email address for the customer
	public void enterConfirmEmailAddress(String email) {
		if (email != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtConfirmEmail));
			txtConfirmEmail.sendKeys(Keys.CONTROL, "a");
			txtConfirmEmail.sendKeys(Keys.DELETE);
			txtConfirmEmail.sendKeys(email);
		}
	}

	// click the SAVE button on the customer details update screen
	public void saveCustomerDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDetailsSave));
		btnDetailsSave.click();
	}

	// ---------------------------------------------------------------------------------------
	// Methods for BILLING ADDRESS section
	// ---------------------------------------------------------------------------------------

	// return the address line 1 displayed on the page
	public String getAddressLine1Displayed() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressLine1));
		return lblAddressLine1.getText();
	}

	// return the address line 2 displayed on the page
	public String getAddressLine2Displayed() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressLine2));
		return lblAddressLine2.getText();
	}

	// return the address line 3 displayed on the page
	public String getAddressLine3Displayed() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressLine3));
		return lblAddressLine3.getText();
	}

	// return the town displayed on the page
	public String getTownDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressTown));
		return lblAddressTown.getText();
	}

	// return the county displayed on the page
	public String getCountyDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressCounty));
		return lblAddressCounty.getText(); 
	}

	// return the eircode displayed on the page
	public String getEircodeDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblAddressEircode));
		return lblAddressEircode.getText();
	}

	// enter an updated address line 1 for the customer
	public void enterAddressLine1(String updatedString) {
		if (updatedString != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtAddressLine1));
			txtAddressLine1.sendKeys(Keys.CONTROL, "a");
			txtAddressLine1.sendKeys(Keys.DELETE);
			txtAddressLine1.sendKeys(updatedString);
		}
	}

	// enter an updated address line 2 for the customer
	public void enterAddressLine2(String updatedString) {
		if (updatedString != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtAddressLine2));
			txtAddressLine2.sendKeys(Keys.CONTROL, "a");
			txtAddressLine2.sendKeys(Keys.DELETE);
			txtAddressLine2.sendKeys(updatedString);
		}
	}

	// enter an updated address line 3 for the customer
	public void enterAddressLine3(String updatedString) {
		if (updatedString != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtAddressLine3));
			txtAddressLine3.sendKeys(Keys.CONTROL, "a");
			txtAddressLine3.sendKeys(Keys.DELETE);
			txtAddressLine3.sendKeys(updatedString);
		}
	}

	// enter an updated address town for the customer
	public void enterAddressTown(String updatedString) {
		if (updatedString != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtAddressTown));
			txtAddressTown.sendKeys(Keys.CONTROL, "a");
			txtAddressTown.sendKeys(Keys.DELETE);
			txtAddressTown.sendKeys(updatedString);
		}
	}

	// enter an updated address line 3 for the customer
	public void enterAddressCounty(String updatedString) {
		if (updatedString != null) {
			// TODO Fix this code - the initial dropdown click action is not working
			driver.findElement(By.xpath("//body")).click();
			System.out.println("Finding county");

			// click the dropdown list
			wait.until(ExpectedConditions.visibilityOf(this.countyDropdown));
			countyDropdown.click();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			// determine the xpath for the specified list item
			String xpath = "//li[contains(text(),'$county')]".replace("$county", updatedString.toUpperCase());

			// find the requested list item based on county specified
			WebElement countyElement = driver.findElement(By.xpath(xpath));
			wait.until(ExpectedConditions.elementToBeClickable(countyElement));

			// click the element
			countyElement.click();
		}
	}

	// enter an updated address line 3 for the customer
	public void enterAddressEircode(String updatedString) {
		if (updatedString != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtAddressEircode));
			txtAddressEircode.sendKeys(Keys.CONTROL, "a");
			txtAddressEircode.sendKeys(Keys.DELETE);
			txtAddressEircode.sendKeys(updatedString);
		}
	}

	public void selectEditAddress() {
		wait.until(ExpectedConditions.elementToBeClickable(btnBillingAddressEdit));
		btnBillingAddressEdit.click();
	}

	public void selectCopyToDelivery() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkUpdateDeliveryAddress);
	}

	public void saveAddressChanges() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveChangeAddress));
		btnSaveChangeAddress.click();
	}

	public void selectEircodeLookup() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmEircode));
		btnConfirmEircode.click();
	}

	// ---------------------------------------------------------------------------------------
	// Methods for CONTACT PREFERENCES section
	// ---------------------------------------------------------------------------------------

	public void clickPermissionEmail() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkEmail);
	}

	public void clickPermissionSMS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkSMS);
	}

	public void clickPermissionPhone() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkPhone);
	}

	public void clickPermissionDirectMail() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkDirectMail);
	}
	
	public void clickPermissionFOTS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkFOTS);
	}

	public void clickCustomer() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCustomer));
		btnCustomer.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnNotCustomer));
	}

	public void clickNotCustomer() {
		wait.until(ExpectedConditions.elementToBeClickable(btnNotCustomer));
		btnNotCustomer.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnNotCustomer));
	}

	// click all radio buttons
	public void toggleAllPermissions() {
		this.clickPermissionEmail();
		this.clickPermissionSMS();
		this.clickPermissionPhone();
		this.clickPermissionDirectMail();
		this.clickPermissionFOTS();
		this.saveAddressChanges();
	}
	
	public void saveContactPermissions() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveContactPreferences));
		btnSaveContactPreferences.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnCustomer));
	}
	
	public boolean isPreferenceSelectedEmail() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveContactPreferences));
		return chkEmail.isSelected();
	}
	
	public boolean isPreferenceSelectedSMS() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveContactPreferences));
		return chkSMS.isSelected();
	}
	
	public boolean isPreferenceSelectedPhone() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveContactPreferences));
		return chkPhone.isSelected();
	}
	
	public boolean isPreferenceSelectedDirectMail() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveContactPreferences));
		return chkDirectMail.isSelected();
	}
	
	public boolean isPreferenceSelectedFOTS() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveContactPreferences));
		return chkFOTS.isSelected();
	}
	
	public void enterPersonalDetails(String firstName, String lastName, String dob, String phoneNumber) {
		
		this.selectDetailsTab();

		// select EDIT to edit the customer's details
		this.selectEditDetails();

		// enter the updated values
		this.enterFirstName(firstName);
		this.enterLastName(lastName);
		this.enterDOB(dob);
		this.enterPhoneNumber(phoneNumber);

		// save the changes
		this.saveCustomerDetails();
	}
	
	public void editBillingAddressByEircode(String eircode, boolean copyToDeliveryAddress) {
		this.selectEditAddress();
		this.enterAddressEircode(eircode);
		this.selectEircodeLookup();

		if (copyToDeliveryAddress) {
			this.selectCopyToDelivery();
		}

		this.saveAddressChanges();
	}
	

	// ---------------------------------------------------------------------------------------
	// Test methods
	// ---------------------------------------------------------------------------------------
	public void test() {

		System.out.println(this.getFirstNameDisplayed());
		System.out.println(this.getLastNameDisplayed());
		System.out.println(this.getDOBDisplayed());
		System.out.println(this.getPhoneNumberDisplayed());
		System.out.println(this.getEmailDisplayed());
		System.out.println(this.getAddressLine1Displayed());
		System.out.println(this.getAddressLine2Displayed());
		System.out.println(this.getAddressLine3Displayed());
		System.out.println(this.getTownDisplayed());
		System.out.println(this.getCountyDisplayed());
		System.out.println(this.getEircodeDisplayed());

		this.selectEditAddress();
		this.enterAddressLine1("new address 1");
		this.enterAddressLine2("new address 2");
		this.enterAddressLine3("new address 3");
		this.enterAddressTown("new town");
		this.enterAddressCounty("Kerry");
		this.enterAddressEircode("C15EF11");

		this.selectPasswordReset();
		this.selectTempUnlock();
		this.selectVerifyEmail();
	}

	public static void main(String[] args) {

	}
}

package selenium.pages.gomo.eshop;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.test_data.generic.Person;
import framework.test_data.generic.RandomStringGenerator;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import utilities.generic.time.Timestamp;

public class EShopCustomerDetailsPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(name = "email")
	public WebElement textEmail;

	@FindBy(name = "emailConfirm")
	public WebElement textEmailConfirmation;

	@FindBy(linkText = "Click here")
	public WebElement loginLink;

	@FindBy(name = "firstName")
	public WebElement textFirstname;

	@FindBy(name = "lastName")
	public WebElement textLastname;

	@FindBy(name = "mobileNumber")
	public WebElement textContactNumber;

	@FindBy(xpath = "//div[contains(label, 'Date of Birth *')]/div[1]/input[1]")
	public WebElement textDateOfBirth;

	@FindBy(xpath = "//*[contains(text(), 'GoMo to contact')]//ancestor::label//input[@type='checkbox']")
	public WebElement checkboxAllowION;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[3]/div[2]/div[1]/div/div[2]/div/button")
	public WebElement buttonConfirmBillingEircode;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[4]/div[2]/div/div/div[2]/div/button")
	public WebElement buttonConfirmDeliveryEircode;

	@FindBy(xpath = "//*[@id=\"sameBillingAddress\"]")
	public WebElement checkboxSameDeliveryAddress;

	@FindBy(xpath = "//*[@id=\"billingAddress-eircodeText\"][1]")
	public WebElement textBillingAddressEircode;

	@FindBy(xpath = "//*[@id=\"addressLine1\"][1]")
	public WebElement textBillingAddressLine1;

	@FindBy(xpath = "//*[@id=\"addressLine2\"][1]")
	public WebElement textBillingAddressLine2;

	@FindBy(xpath = "//*[@id=\"addressLine3\"][1]")
	public WebElement textBillingAddressLine3;

	@FindBy(xpath = "//*[@id=\"town\"][1]")
	public WebElement textBillingTown;

	@FindBy(xpath = "//*[@id=\"eircodeText\"][2]")
	public WebElement textDeliveryAddressEircode;

	@FindBy(xpath = "(//input[@id='addressLine1'])[2]")
	public WebElement textDeliveryAddressLine1;

	@FindBy(xpath = "//*[@id=\"addressLine2\"][2]")
	public WebElement textDeliveryAddressLine2;

	@FindBy(xpath = "//*[@id=\"addressLine3\"][2]")
	public WebElement textDeliveryAddressLine3;

	@FindBy(xpath = "//*[@id=\"town\"][2]")
	public WebElement textDeliveryTown;

	@FindBy(xpath = "//h2[contains(text(),'Billing Address')]//ancestor::div[3]//descendant::div[@role='button']")
	public WebElement dropdownBillingCounty;

	@FindBy(xpath = "//h2[contains(text(),'Delivery Address')]//ancestor::div[3]//descendant::div[@role='button']")
	public WebElement dropdownDeliveryCounty;

	@FindBy(xpath = "//li[contains(text(),'CARLOW')]")
	public WebElement selectCarlow;
	@FindBy(xpath = "//li[contains(text(),'CAVAN')]")
	public WebElement selectCavan;
	@FindBy(xpath = "//li[contains(text(),'CLARE')]")
	public WebElement selectClare;
	@FindBy(xpath = "//li[contains(text(),'CORK')]")
	public WebElement selectCork;
	@FindBy(xpath = "//li[contains(text(),'DONEGAL')]")
	public WebElement selectDonegal;
	@FindBy(xpath = "//li[contains(text(),'DUBLIN')]")
	public WebElement selectDublin;
	@FindBy(xpath = "//li[contains(text(),'GALWAY')]")
	public WebElement selectGalway;
	@FindBy(xpath = "//li[contains(text(),'KERRY')]")
	public WebElement selectKerry;
	@FindBy(xpath = "//li[contains(text(),'KILDARE')]")
	public WebElement selectKildare;
	@FindBy(xpath = "//li[contains(text(),'KILKENNY')]")
	public WebElement selectKilkenny;
	@FindBy(xpath = "//li[contains(text(),'LAOIS')]")
	public WebElement selectLaois;
	@FindBy(xpath = "//li[contains(text(),'LEITRIM')]")
	public WebElement selectLeitrim;
	@FindBy(xpath = "//li[contains(text(),'LIMERICK')]")
	public WebElement selectLimerick;
	@FindBy(xpath = "//li[contains(text(),'LONGFORD')]")
	public WebElement selectLongford;
	@FindBy(xpath = "//li[contains(text(),'LOUTH')]")
	public WebElement selectLouth;
	@FindBy(xpath = "//li[contains(text(),'MAYO')]")
	public WebElement selectMayo;
	@FindBy(xpath = "//li[contains(text(),'MEATH')]")
	public WebElement selectMeath;
	@FindBy(xpath = "//li[contains(text(),'MONAGHAN')]")
	public WebElement selectMonaghan;
	@FindBy(xpath = "//li[contains(text(),'OFFALY')]")
	public WebElement selectOffaly;
	@FindBy(xpath = "//li[contains(text(),'ROSCOMMON')]")
	public WebElement selectRoscommon;
	@FindBy(xpath = "//li[contains(text(),'SLIGO')]")
	public WebElement selectSligo;
	@FindBy(xpath = "//li[contains(text(),'TIPPERARY')]")
	public WebElement selectTipperary;
	@FindBy(xpath = "//li[contains(text(),'WATERFORD')]")
	public WebElement selectWaterford;
	@FindBy(xpath = "//li[contains(text(),'WESTMEATH')]")
	public WebElement selectWestmeath;
	@FindBy(xpath = "//li[contains(text(),'WEXFORD')]")
	public WebElement selectWexford;
	@FindBy(xpath = "//li[contains(text(),'WICKLOW')]")
	public WebElement selectWicklow;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[2]")
	public WebElement selectBoxCounty;
	@FindBy(xpath = "//*[@id=\"sameBillingAddress\"]")
	public WebElement checkboxSameBillingAddress;
	@FindBy(xpath = "//*[contains(b, 'read and agree')]//ancestor::label//input[@type='checkbox']")
	public WebElement checkboxIonTandCs;
	@FindBy(xpath = "//button[contains(span, 'Continue')]")
	public WebElement buttonConfirm;

	@FindBy(xpath = "//p[@id='error-text']")
	public WebElement errorText;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[1]/section/div[3]/div/div[1]/div/p")
	public WebElement errorEmail;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[1]/section/div[3]/div/div[3]/div/p")
	public WebElement errorConfirmEmail;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[2]/section/div[2]/div/div[1]/div/p")
	public WebElement errorFirstName;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[2]/section/div[2]/div/div[2]/div/p")
	public WebElement errorLastName;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div[1]/form/div/div[2]/section/div[2]/div/div[4]/div/p")
	public WebElement errorContactNo;

	public WebElement errorDob;

	// Constructor
	public EShopCustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(textBillingAddressEircode));
	}

	public void enterPersonalDetails(String email, String firstName, String lastName, String contactNumber, String dateOfBirth) {
		enterEmail(email);
		enterConfirmationEmail(email);
		enterFirstName(firstName);
		enterSecondName(lastName);
		enterContactNumber(contactNumber);
		enterDateOfBirth(dateOfBirth);
	}

	public void setBillingEircodeAddress(String billingEircode) {
		enterBillingAddressEircode(billingEircode);
		wait.until(ExpectedConditions.elementToBeClickable(buttonConfirmBillingEircode));
		buttonConfirmBillingEircode.click();
	}

	public void enterBillingAddress(String billingAddressLine1, String billingAddressLine2, String billingAddressLine3, String billingAddressTown,
			String billingAddressCounty) {
		enterBillingAddressLine1(billingAddressLine1);
		enterBillingAddressLine2(billingAddressLine2);
		enterBillingAddressLine3(billingAddressLine3);
		enterBillingAddressTown(billingAddressTown);
		selectBillingAddressCounty(billingAddressCounty);
	}

	public void enterDeliveryAddress(String deliveryAddressLine1, String deliveryAddressLine2, String deliveryAddressLine3, String deliveryAddressTown,
			String deliveryAddressCounty) {
		enterDeliveryAddressLine1(deliveryAddressLine1);
		enterDeliveryAddressLine2(deliveryAddressLine2);
		enterDeliveryAddressLine3(deliveryAddressLine3);
		enterDeliveryAddressTown(deliveryAddressTown);
		selectDeliveryAddressCounty(deliveryAddressCounty);
	}

	public void enterEmail(String email) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		try {
			wait.until(ExpectedConditions.visibilityOf(textEmail));
		} catch (Exception e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		textEmail.sendKeys(email);
	}

	public void enterConfirmationEmail(String confirmationEmail) {
		textEmailConfirmation.sendKeys(confirmationEmail);
	}

	public void enterFirstName(String firstName) {
		textFirstname.sendKeys(firstName);
	}

	public void enterSecondName(String secondName) {
		textLastname.sendKeys(secondName);
	}

	public void enterContactNumber(String contactNumber) {
		textContactNumber.sendKeys(contactNumber);
	}

	/*
	 * public void enterIDNumber(String idNumber) { textIDNumber.sendKeys(idNumber);
	 * }
	 */

	public void enterDateOfBirth(String dateOfBith) {
		textDateOfBirth.sendKeys(dateOfBith);
	}

	public void enterBillingAddressEircode(String billingAddressEircode) {
		textBillingAddressEircode.sendKeys(billingAddressEircode);
	}

	public void enterBillingAddressLine1(String billingAddressLine1) {
		textBillingAddressLine1.sendKeys(billingAddressLine1);
	}

	public void enterBillingAddressLine2(String billingAddressLine2) {
		textBillingAddressLine2.sendKeys(billingAddressLine2);
	}

	public void enterBillingAddressLine3(String billingAddressLine3) {
		textBillingAddressLine3.sendKeys(billingAddressLine3);
	}

	public void enterBillingAddressTown(String billingAddressTown) {
		textBillingTown.sendKeys(billingAddressTown);
	}

	public void selectBillingAddressCounty(String billingAddressCounty) {

		wait.until(ExpectedConditions.elementToBeClickable(dropdownBillingCounty));
		dropdownBillingCounty.click();

		String xpath = "//li[contains(text(),'$county')]".replace("$county", billingAddressCounty.toUpperCase());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement countyItem = driver.findElement(By.xpath(xpath));
		countyItem.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// if the county gets incorrectly selected, try again
		if (!getBillingAddressCounty().toUpperCase().contains(billingAddressCounty.toUpperCase())) {
			System.out.println("Expected " + billingAddressCounty + " but got " + getBillingAddressCounty());
			selectBillingAddressCounty(billingAddressCounty);
		}

	}

	public void enterDeliveryAddressEircode(String deliveryAddressEircode) {
		textDeliveryAddressEircode.sendKeys(deliveryAddressEircode);
	}

	public void enterDeliveryAddressLine1(String deliveryAddressLine1) {
		textDeliveryAddressLine1.sendKeys(deliveryAddressLine1);
	}

	public void enterDeliveryAddressLine2(String deliveryAddressLine2) {
		textDeliveryAddressLine2.sendKeys(deliveryAddressLine2);
	}

	public void enterDeliveryAddressLine3(String deliveryAddressLine3) {
		textDeliveryAddressLine3.sendKeys(deliveryAddressLine3);
	}

	public void enterDeliveryAddressTown(String deliveryAddressTown) {
		textDeliveryTown.sendKeys(deliveryAddressTown);
	}

	public String getBillingAddressCounty() {
		wait.until(ExpectedConditions.elementToBeClickable(dropdownBillingCounty));
		return dropdownBillingCounty.getText();
	}

	public void selectBillingEircode(String eircode) {
		textBillingAddressEircode.sendKeys(eircode);
		buttonConfirmBillingEircode.click();
	}

	public void selectDeliveryEircode(String eircode) {
		textDeliveryAddressEircode.sendKeys(eircode);
		buttonConfirmDeliveryEircode.click();
	}

	public String getDeliveryAddressCounty() {
		wait.until(ExpectedConditions.elementToBeClickable(dropdownDeliveryCounty));
		return dropdownDeliveryCounty.getText();
	}

	public void selectDeliveryAddressCounty(String deliveryAddressCounty) {
		checkboxSameDeliveryAddress.click();

		wait.until(ExpectedConditions.elementToBeClickable(dropdownDeliveryCounty));
		dropdownDeliveryCounty.click();

		String xpath = "//li[contains(text(),'$county')]".replace("$county", deliveryAddressCounty.toUpperCase());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement countyItem = driver.findElement(By.xpath(xpath));
		countyItem.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// if the county gets incorrectly selected, try again
		if (!getDeliveryAddressCounty().toUpperCase().contains(deliveryAddressCounty.toUpperCase())) {
			System.out.println("Expected " + deliveryAddressCounty + " but got " + getDeliveryAddressCounty());
			selectDeliveryAddressCounty(deliveryAddressCounty);
		}
	}

	public void selectNext() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonConfirm));
		buttonConfirm.click();
	}

	public void selectLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		loginLink.click();
	}

	public void selectAllowIon() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxAllowION);
	}

	public void selectTandCs() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonConfirm));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkboxIonTandCs);
	}

	/**
	 * Populate the customer and address details page with random data
	 * 
	 * @return the Person object
	 */
	public void populateNewOwner(Person person) {
		this.enterPersonalDetails(person.getEmailAddress(), person.getFirstName(), person.getLastName(), person.getContactPhoneNumber(), person.getDateOfBirth());
		String eircode = person.getBillingAddress().getEircode();
		this.selectBillingEircode(person.getBillingAddress().getEircode());
		this.selectTandCs();
		this.selectNext();
	}

	void setElementText(WebDriver driver, WebElement textInputWebElement, String textValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// textInputWebElement.sendKeys("");
		js.executeScript("arguments[0].focus()", textInputWebElement);
		js.executeScript("arguments[0].setAttribute('value', '" + textValue + "')", textInputWebElement);
		// js.executeScript("arguments[0].setAttribute('value', '" + textValue +
		// "')",textLastname);
	}

}
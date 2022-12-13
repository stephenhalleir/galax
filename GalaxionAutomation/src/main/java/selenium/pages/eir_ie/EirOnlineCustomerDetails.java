package selenium.pages.eir_ie;

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

public class EirOnlineCustomerDetails {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	
	// customer type
	@FindBy(xpath="//*[@name='customerType'][@value='guest']")
	public WebElement radioAnonymous;

	@FindBy(xpath="//*[@name='customerType'][@value='registered']")
	public WebElement radioRegistered;
	
	// customer details
	@FindBy(id="firstName")
	public WebElement txtFirstName;
	
	@FindBy(id="lastName")
	public WebElement txtLastName;
	
	@FindBy(id="emailAddress")
	public WebElement txtEmail;
	
	@FindBy(id="mobileNumber")
	public WebElement txtContactPhone;
	
	@FindBy(id="birthDate")
	public WebElement txtDOB;

	// address
	@FindBy(id="billingAddressEirCode")
	public WebElement txtBillingEircode;
	
	@FindBy(id="deliveryAddressEirCode")
	public WebElement txtDeliveryEircode;

	@FindBy(xpath="//*[contains(text(), 'Confirm eircode')]")
	public WebElement btnConfirmBillingEircode;
	
	
	@FindBy(id="sameDeliveryAddress")
	public WebElement chkDeliverySame;
	
	@FindBy(id="securityAnswer")
	public WebElement txtSecurityAnswer;
	
	@FindBy(id="securityQuestion")
	public WebElement dropdownSecurityQuestion;
	
	// marketing preferences
	
	@FindBy(name="notContact-Active customer")
	public WebElement chkActiveNoContact;
	
	@FindBy(name="Active customer-Email")
	public WebElement chkActiveEmail;
	
	@FindBy(name="Active customer-SMS")
	public WebElement chkActiveSMS;
	
	@FindBy(name="Active customer-Phone")
	public WebElement chkActivePhone;
	
	@FindBy(name="Active customer-FOTS")
	public WebElement chkActiveFOTS;
	
	@FindBy(name="Active customer-Direct mail")
	public WebElement chkActiveDirectMail;
	
	@FindBy(name="notContact-No longer a customer")
	public WebElement chkInactiveNoContact;
	
	@FindBy(name="No longer a customer-Email")
	public WebElement chkInactiveEmail;
	
	@FindBy(name="No longer a customer-SMS")
	public WebElement chkInactiveSMS;
	
	@FindBy(name="No longer a customer-Phone")
	public WebElement chkInactivePhone;
	
	@FindBy(name="No longer a customer-FOTS")
	public WebElement chkInactiveFOTS;
	
	@FindBy(name="No longer a customer-Direct mail")
	public WebElement chkInactiveDirectMail;
	
	@FindBy(name="thirdParty")
	public WebElement chk3rdParty;
	
	//@FindBy(id="termsAndConditions")
	@FindBy(id="termsAndConditions")
	public WebElement chkTermsAndConditions;
	
	@FindBy(xpath="//span[contains(text(),'Go to Payment')]")
	public WebElement btnNext;
	
	// Constructor
	public EirOnlineCustomerDetails(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	// select whether or not a customer will register
	public void selectRegistered(boolean registered) {
		
		if(registered) {
			radioRegistered.click();
		}
		else {
			radioAnonymous.click();
		}
	}
	
	public void selectOffer(String offerName) {
		String xpathExpression = "//h2[contains(text(),'$offerName')]".replace("$offerName", offerName);
		WebElement btnSelectProduct = driver.findElement(By.xpath(xpathExpression));
		wait.until(ExpectedConditions.elementToBeClickable(btnSelectProduct));
		btnSelectProduct.click();
	}

	public void enterFirstName(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtFirstName));
		txtFirstName.sendKeys(input);
	}
	
	public void enterLastName(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtLastName));
		txtLastName.sendKeys(input);
	}
	
	public void enterEmail(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtEmail));
		txtEmail.sendKeys(input);
	}

	
	public void enterContactNumber(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtContactPhone));
		txtContactPhone.sendKeys(input);
	}
	
	public void enterDOB(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtDOB));
		txtDOB.sendKeys(input);
	}
	
	public void selectConfirmBillingEircode(String input) {
		
		txtBillingEircode.sendKeys("C15EF6E");
		
		WebElement btnEircode=driver.findElement(By.xpath("//*[contains(text(), 'Confirm eircode')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", btnEircode);
	}
	
	public void selectConfirmDeliveryEircode(String input) {
		
		txtDeliveryEircode.sendKeys("C15EF6E");
		
		WebElement btnEircode=driver.findElement(By.xpath("//*[contains(text(), 'Confirm eircode')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", btnEircode);
	}
	
	public void clickSameDeliveryAddress() {
		//wait.until(ExpectedConditions.elementToBeClickable(chkDeliverySame));
		//chkDeliverySame.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkDeliverySame);
	}
	
	public void enterResponse(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtSecurityAnswer));
		txtSecurityAnswer.sendKeys(input);
	}
	
	public void selectSecurityQuestion(String question) {
		
		// tab backwards from the text box - dirty workaround
		txtSecurityAnswer.sendKeys(Keys.chord(Keys.SHIFT, Keys.TAB));
		dropdownSecurityQuestion.sendKeys(Keys.ARROW_DOWN);
		
		// click the value itself
		String xpath = "//*[contains(text(),'$question')]".replace("$question", question);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement q = driver.findElement(By.xpath(xpath));
		q.click();
	}
	
	// marketing preference
	public void clickActiveEmail() {
		chkActiveEmail.click();
	}
	
	public void clickActiveFOTS() {
		chkActiveFOTS.click();
	}
	
	public void clickActiveSMS() {
		chkActiveSMS.click();
	}
	
	public void clickActiveDirectMail() {
		chkActiveDirectMail.click();
	}
	
	public void clickActivePhone() {
		chkActivePhone.click();
	}
	
	public void clickInactiveEmail() {
		chkInactiveEmail.click();
	}
	
	public void clickInactiveFOTS() {
		chkInactiveFOTS.click();
	}
	
	public void clickInactiveSMS() {
		chkInactiveSMS.click();
	}
	
	public void clickInactiveDirectMail() {
		chkInactiveDirectMail.click();
	}
	
	public void clickInactivePhone() {
		chkInactivePhone.click();
	}
	
	public void click3rdParty() {
		chk3rdParty.click();
	}
	
	public void acceptTerms() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", chkTermsAndConditions);
	}
	
	public void clickNext() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", btnNext);
		//btnNext.click();
	}
}
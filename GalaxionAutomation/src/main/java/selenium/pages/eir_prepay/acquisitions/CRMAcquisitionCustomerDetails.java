package selenium.pages.eir_prepay.acquisitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CRMAcquisitionCustomerDetails {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	
	// customer type
	
	@FindBy(xpath="//h1[contains(text(),'Customer Information')]")
	public WebElement header;
	
	@FindBy(xpath="//*[@aria-label='Anonymous customer']")
	public WebElement radioAnonymous;
	
	@FindBy(xpath="//*[@name='Customer Information'][@value='1']")
	public WebElement radioRegistered;
	
	// customer details
	@FindBy(name="customerDetails.person.firstName")
	public WebElement txtFirstName;
	
	@FindBy(name="customerDetails.person.lastName")
	public WebElement txtLastName;
	
	@FindBy(name="customerDetails.email")
	public WebElement txtEmail;
	
	@FindBy(name="confirmEmail")
	public WebElement txtConfirmEmail;
	
	@FindBy(name="customerDetails.mobileNumber")
	public WebElement txtContactPhone;
	
	@FindBy(name="customerDetails.person.birthDate")
	public WebElement txtDOB;

	// address
	@FindBy(name="billingAddress.eircode")
	public WebElement txtBillingEircode;
	
	// address
		@FindBy(name="deliveryAddress.eircode")
		public WebElement txtDeliveryEircode;

	@FindBy(xpath="//*[@aria-label='Confirm Eircode']")
	public WebElement btnConfirmEircode;
	
	@FindBy(id="sameDeliveryAddress")
	public WebElement chkDeliverySame;
	
	@FindBy(id="response")
	public WebElement txtSecurityAnswer;
	
	@FindBy(id="security-questions")
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
	
	@FindBy(xpath="//p[contains(text(),'Next')]")
	public WebElement btnNext;
	
	// Constructor
	public CRMAcquisitionCustomerDetails(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		
		//  scroll to the top of the page (scroll to header)
		wait.until(ExpectedConditions.elementToBeClickable(header));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", header); 
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
		wait.until(ExpectedConditions.visibilityOf(txtFirstName));
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
	
	public void enterConfirmEmail(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtConfirmEmail));
		txtConfirmEmail.sendKeys(input);
	}
	
	public void enterContactNumber(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtContactPhone));
		txtContactPhone.sendKeys(input);
	}
	
	public void enterDOB(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtDOB));
		txtDOB.sendKeys(input);
	}
	
	public void selectDeliveryEircode(String input) {
		txtDeliveryEircode.sendKeys("C15EF6E");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmEircode));
		btnConfirmEircode.click();
	}
	
	public void selectBillingEircode(String input) {
		txtBillingEircode.sendKeys("C15EF6E");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmEircode));
		btnConfirmEircode.click();
	}
	
	public void clickSameDeliveryAddress() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", chkDeliverySame);
	}
	
	public void enterResponse(String input) {
		wait.until(ExpectedConditions.elementToBeClickable(txtSecurityAnswer));
		txtSecurityAnswer.sendKeys(input);
	}
	
	// select a named security question
	public void selectSecurityQuestion(String question) {
		
		// click the dropdown
		wait.until(ExpectedConditions.elementToBeClickable(dropdownSecurityQuestion));
		dropdownSecurityQuestion.click();

		// click the value itself
		String xpath = "//li[contains(text(),'$question')]".replace("$question", question);
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
	
	public void clickNext() {
		btnNext.click();
	}
}
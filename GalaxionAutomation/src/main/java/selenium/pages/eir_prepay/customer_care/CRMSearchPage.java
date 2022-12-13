package selenium.pages.eir_prepay.customer_care;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.generic.time.WaitUtil;

public class CRMSearchPage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(id = "first-name")
	public WebElement txtFirstName;

	@FindBy(id = "last-name")
	public WebElement txtLastName;

	@FindBy(id = "email")
	public WebElement txtEmail;

	@FindBy(id = "contact-phone-number")
	public WebElement txtContactPhoneNumber;

	@FindBy(id = "msisdn")
	public WebElement txtMSISDN;

	@FindBy(id = "customer-reference")
	public WebElement txtAccountNumber;

	@FindBy(id = "iccid")
	public WebElement txtICCID;

	@FindBy(xpath = "//*[contains(text(), 'Customer search')]")
	public WebElement btnCustomerSearch;

	@FindBy(xpath = "//*[contains(text(), 'Order search')]")
	public WebElement btnOrderSearch;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div[2]/form/div/div[2]/div/div[2]/button")
	public WebElement btnSearch;

	@FindBy(id = "order-reference")
	public WebElement txtOrderReference;

	// Constructor
	public CRMSearchPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void selectCustomerSearch() {
		wait.until(ExpectedConditions.visibilityOf(btnCustomerSearch));
		btnCustomerSearch.click();
	}

	public void selectOrderSearch() {
		wait.until(ExpectedConditions.visibilityOf(btnOrderSearch));
		btnOrderSearch.click();
	}

	public void enterOrderReference(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtOrderReference));
		txtOrderReference.sendKeys(input);
	}

	public void enterFirstName(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtFirstName));
		txtFirstName.sendKeys(input);
	}

	public void enterLastName(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtLastName));
		txtLastName.sendKeys(input);
	}

	public void enterEmail(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtEmail));
		txtEmail.sendKeys(input);
	}

	public void enterContactNumber(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtContactPhoneNumber));
		txtContactPhoneNumber.sendKeys(input);
	}

	public void enterMSISDN(String input) {
		txtMSISDN.sendKeys(input);
	}

	public void enterAccountNumber(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtAccountNumber));
		txtAccountNumber.sendKeys(input);
	}

	public void enterICCID(String input) {
		wait.until(ExpectedConditions.visibilityOf(txtICCID));
		txtICCID.sendKeys(input);
	}

	// click the Search button
	public void clickSearch() {

		wait.until(ExpectedConditions.visibilityOf(btnSearch));
		System.out.println(btnSearch.getText());
		btnSearch.click();
	}

	// ---------------------------------------------------------------
	// HELPER METHODS
	// ---------------------------------------------------------------
	public void searchForOrder(String orderNumber) {
		this.selectOrderSearch();
		this.enterOrderReference(orderNumber);
		this.clickSearch();
	}
	
	public void searchForCustomer(String firstName, String lastName, String email, String contactNumber, String msisdn, String accountNumber, String iccid) {
		
		if(firstName!=null) {
			enterFirstName(firstName);
		}
		
		if(lastName!=null) {
			enterLastName(lastName);
		}
		
		if(email!=null) {
			enterEmail(email);
		}
		
		if(contactNumber!=null) {
			enterContactNumber(contactNumber);
		}
		
		if(msisdn!=null) {
			enterMSISDN(msisdn);
		}
		
		if(accountNumber!=null) {
			enterAccountNumber(accountNumber);
		}
		
		if(iccid!=null) {
			enterICCID(iccid);
		}
	}
	
	public void searchByMSISDN(String msisdn) {
		enterMSISDN(msisdn);
		clickSearch();
	}
	
	public void clickText(String text) {
		String xpath = "//p[contains(text(),'$text')]";
		xpath = xpath.replace("$text", text);
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	public void passVerification() {
		String xpath = "//p[contains(text(),'Confirm')]";
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	/**
	 * Open a particular customer in the search results
	 * @param msisdn
	 */
	public void goToCustomer(String msisdn) {
		searchByMSISDN(msisdn);
		int billing_account_id = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		
		WaitUtil.waitForSeconds(2);
		clickText(Integer.toString(billing_account_id));
		
		WaitUtil.waitForSeconds(1);
		passVerification();
	}
}
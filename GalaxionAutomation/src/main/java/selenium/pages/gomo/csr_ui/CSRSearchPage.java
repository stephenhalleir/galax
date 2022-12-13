package selenium.pages.gomo.csr_ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import utilities.galaxion.environments.EnvironmentDirectory;

public class CSRSearchPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div/div[1]/div[1]")
	public WebElement tabCustomerSearch;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div/div[1]/div[2]/h3")
	public WebElement tabOrderSearch;

	@FindBy(xpath = "//*[@name=\"firstName\"]")
	public WebElement txtFirstName;

	@FindBy(xpath = "//*[@name=\"lastName\"]")
	public WebElement txtLastName;

	@FindBy(xpath = "//*[@name=\"emailAddress\"]")
	public WebElement txtEmailAddress;

	@FindBy(xpath = "//*[@name=\"msisdn\"]")
	public WebElement txtMsisdn;

	@FindBy(xpath = "//*[@name=\"accountNumber\"]")
	public WebElement txtAccountNumber;

	@FindBy(xpath = "//*[@name=\"iccid\"]")
	public WebElement txtICCID;

	@FindBy(xpath = "//*[@name=\"orderId\"]")
	public WebElement txtOrderNumber;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div/div[2]/form/div/div[2]/div/button")
	public WebElement btnOrderSearch;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div/div[2]/form/div[3]/div/button")
	public WebElement btnCustomerSearch;

	@FindBy(xpath = "//p[contains(text(),'Steve AAC')]")
	public WebElement firstResult;

	// Constructor
	public CSRSearchPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);

		// Initialize Elements
		PageFactory.initElements(driver, this);
	}

	// click the order search tab
	public void selectOrderSearchTab() {
		wait.until(ExpectedConditions.elementToBeClickable(tabOrderSearch));
		tabOrderSearch.click();
	}

	// click the customer search tab
	public void selectCustomerSearchTab() {
		wait.until(ExpectedConditions.elementToBeClickable(tabCustomerSearch));
		tabCustomerSearch.click();
	}

	// perform an order search on CSR UI
	public void searchForOrder(String orderNumber) {
		txtOrderNumber.sendKeys(orderNumber);
		btnOrderSearch.click();
	}

	// select a specific customer search result from the results list
	public void selectResult(String indicator) {

		// build the xpath based on the specified string
		String xpath = "//p[contains(text(),'" + indicator + "')]";

		// wait until the element exists
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		// find and click the result
		WebElement searchResult = driver.findElement(By.xpath(xpath));
		searchResult.click();
	}
	
	public void selectResult(int indicator) {
		selectResult(Integer.toString(indicator));
	}

	// enter a first name into the First Name field on customer search
	public void enterFirstName(String firstName) {
		if (firstName != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtFirstName));
			txtFirstName.sendKeys(firstName);
		}
	}

	// enter a last name into the Last Name field on customer search
	public void enterLastName(String lastName) {
		if (lastName != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtLastName));
			txtLastName.sendKeys(lastName);
		}
	}

	// enter an email address into the Email field on customer search
	public void enterEmail(String email) {
		if (email != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtEmailAddress));
			txtEmailAddress.sendKeys(email);
		}
	}

	// enter a MSISDN into the MSISDN field on customer search
	public void enterMsisdn(String msisdn) {
		if (msisdn != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtMsisdn));
			txtMsisdn.sendKeys(msisdn);
		}
	}

	// enter an account number into the Account Number field on customer search
	public void enterAccountNumber(String accountNumber) {
		if (accountNumber != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtAccountNumber));
			txtAccountNumber.sendKeys(accountNumber);
		}
	}

	// enter an ICCID into the ICCID field on customer search
	public void enterICCID(String iccid) {
		if (iccid != null) {
			wait.until(ExpectedConditions.elementToBeClickable(txtICCID));
			txtICCID.sendKeys(iccid);
		}
	}

	// method to perform a customer search based on selected fields
	// enter a null where the field does not need to be populated
	public void searchCustomerByDetails(String firstName, String lastName, String email, String msisdn,
			String accountNumber, String iccid) {

		// populate whatever fields are not null
		this.enterFirstName(firstName);
		this.enterLastName(lastName);
		this.enterEmail(email);
		this.enterMsisdn(msisdn);
		this.enterAccountNumber(accountNumber);
		this.enterICCID(iccid);

		// click the customer search button
		btnCustomerSearch.click();
	}

	public void waitUntilPageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(txtFirstName));
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get(EnvironmentDirectory.getGoMoCSRUIURL());

		// log in to CSR UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login("stephen.hall+csr@eir.ie", "testpass");

		CSRSearchPage page = new CSRSearchPage(driver);

		// perform an order search - candidate for helper class
		page.selectOrderSearchTab();
		page.searchForOrder("EGSFNFQN");

	}
}

package selenium.flows.gomo.customer_care;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.pages.gomo.csr_ui.CSRBasePage;
import selenium.pages.gomo.csr_ui.CSRDetailsPage;
import selenium.pages.gomo.csr_ui.CSRSearchPage;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import utilities.galaxion.environments.EnvironmentDirectory;

public class CsrUiHelper {

	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public CsrUiHelper(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");

		// Initialize Elements
		PageFactory.initElements(driver, this);
	}

	// ---------------------------------------------------------------------------------------
	// Browse and login
	// ---------------------------------------------------------------------------------------

	// go to the CSR UI
	public void goToCSRUI() {
		driver.get(EnvironmentDirectory.getGoMoCSRUIURL());
	}

	// log into CSR UI
	public boolean loginToCSRUI(String username, String password) {

		// log in to CSR UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(username,password);
		
		CSRSearchPage searchPage = new CSRSearchPage(driver);
		 
		// if the page loads successfully, return true
		try {
			// wait for the existence of the Customer Search tab
			searchPage.waitUntilPageLoads();
			return true;
		}
		// if the customer search tab does not exist, assume failed login
		catch(NoSuchElementException e) {
			return false;
		}	
	}
	
	// ---------------------------------------------------------------------------------------
	// Search functionality
	// ---------------------------------------------------------------------------------------

	public void performOrderSearch(String orderNumber) {
		CSRSearchPage page = new CSRSearchPage(driver);

		// perform an order search - candidate for helper class
		page.selectOrderSearchTab();
		page.searchForOrder("EGSFNFQN");
	}

	// search for a customer by account number and select the result
	public void performCustomerSearchByAccountNumberAndSelect(int accountNumber) {
		CSRSearchPage page = new CSRSearchPage(driver);
		page.selectCustomerSearchTab();
		page.searchCustomerByDetails(null, null, null, null, Integer.toString(accountNumber), null);
		page.selectResult(Integer.toString(accountNumber));
	}

	// search for a customer by account number and select the result
	public void performCustomerSearchByEmailAndSelect(String email) {
		CSRSearchPage page = new CSRSearchPage(driver);
		page.selectCustomerSearchTab();
		page.searchCustomerByDetails(null, null, email, null, null, null);
		page.selectResult(email);
	}

	// change a customer's email address
	public void changeEmail(String email) {

		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);

		// select the DETAILS tab
		detailsPage.selectDetailsTab();

		// select EDIT to edit the customer's details
		detailsPage.selectEditDetails();

		// enter the updated values
		detailsPage.enterEmailAddress(email);
		detailsPage.enterConfirmEmailAddress(email);

		// save the changes
		detailsPage.saveCustomerDetails();
	}

	public void selectActionVerifyEmail() {
		CSRBasePage page = new CSRBasePage(driver);
		page.selectVerifyEmail();
	}
	
	public void logout() {
		CSRBasePage page = new CSRBasePage(driver);
		page.logout();
	}

	public void testMethod() {
		CSRDetailsPage detailsPage = new CSRDetailsPage(driver);
		detailsPage.test();
	}
}

package selenium.pages.gomo.my_gomo.flows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import selenium.pages.gomo.my_gomo.MyAccountHomePage;
import selenium.pages.gomo.my_gomo.MyAccountMyProfilePage;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.time.WaitUtil;

public class MyGoMoHelper {

	private WebDriver driver;
	private WebDriverWait wait;
 
	// Constructor
	public MyGoMoHelper(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");

		// Initialize Elements
		PageFactory.initElements(driver, this);
	}

	// go to the CSR UI
	public void goToMyAccountUI() {
		driver.get(EnvironmentDirectory.getMyGoMoURL());
	}

	// log into MyAccount UI
	public boolean login(String username, String password) {

		// log in to CSR UI
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(username, password);

		// read the current URL in the browser
		String currentUrl = driver.getCurrentUrl();

		// confirm whether the myaccount homepage is loaded
		return currentUrl.equals(EnvironmentDirectory.getMyGoMoURL() + "/home");
	}

	// accept cookes if asked
	public void acceptCookies() {
		MyAccountHomePage homepage = new MyAccountHomePage(driver);
		try {
			homepage.acceptCookies();
		} catch (WebDriverException e) {
			System.out.println("While trying to accept cookes, got message: " + e.getMessage());
		}

	}

	public void selectLinkFromHomepage(String linkText) {
		MyAccountHomePage basePage = new MyAccountHomePage(driver);
		basePage.clickLink(linkText);
	}

	public void selectLinkFromNavBar(String linkText) {
		MyAccountHomePage basePage = new MyAccountHomePage(driver);
		basePage.clickTopLink(linkText);
	}

	public void selectLinkFromFooter(String linkText) {
		MyAccountHomePage basePage = new MyAccountHomePage(driver);
		basePage.clickFooterLink(linkText);

		WaitUtil.waitForSeconds(1);
	}

	public void returnToHomepage() {
		if (!driver.getCurrentUrl().endsWith("/home")) {
			driver.get(EnvironmentDirectory.getMyGoMoURL() + "/home");
		}
		MyAccountHomePage basePage = new MyAccountHomePage(driver);
		basePage.acceptCookies();
	}

	public void clickRightArrow() {
		MyAccountMyProfilePage myProfilePage = new MyAccountMyProfilePage(driver);
		myProfilePage.moveRight();
	}

	public void clickLeftArrow() {
		MyAccountMyProfilePage myProfilePage = new MyAccountMyProfilePage(driver);
		myProfilePage.moveLeft();
	}

	public void logout() {
		MyAccountHomePage homepage = new MyAccountHomePage(driver);
		homepage.logout();
	}
}

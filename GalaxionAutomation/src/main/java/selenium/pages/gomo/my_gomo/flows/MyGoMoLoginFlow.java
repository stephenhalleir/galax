package selenium.pages.gomo.my_gomo.flows;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import selenium.flows.base_flow.BaseUIFlow;
import selenium.pages.gomo.keycloak.KeycloakInitialVerificationPage;
import selenium.pages.gomo.keycloak.KeycloakLoginPage;
import selenium.pages.gomo.keycloak.KeycloakSetPasswordPage;
import selenium.pages.gomo.keycloak.KeycloakVerifyDOBPage;
import selenium.pages.gomo.my_gomo.MyAccountHomePage;
import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.mailhog.MailHog;

public class MyGoMoLoginFlow extends BaseUIFlow {

	/*
	 * Constructors
	 */
	public MyGoMoLoginFlow(WebDriver driver, ExtentTest logger, Logger logger4j) {
		super(logger, logger4j, driver);
	}

	public MyGoMoLoginFlow(WebDriver driver) {
		super(driver);
	}

	/**
	 * Log into the MyGoMo portal
	 * @param email
	 * @param password
	 * @return true for success, false for failure
	 */
	public boolean loginToMyGoMo(String email, String password) {

		logInfo("Logging into MyAccount with credentials " + email + ", " + password);
		
		String myGomoUrl=EnvironmentExcelConfigReader.getGalaxionConfigValue("UI.GOMO.MYGOMO");
		
		// browse to the MyGoMo homepage
		driver.get(myGomoUrl);
		logInfo("Customer browses to the MyGoMo homepage");

		// log in using the credentials provided
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		loginPage.login(email, password);

		// read the current URL in the browser
		String currentUrl = driver.getCurrentUrl();

		boolean success=currentUrl.equals(myGomoUrl + "/home");
		
		if(success) {
			logPass("User is logged in successfully and directed to " + currentUrl);
		}
		
		// confirm whether the homepage is loaded
		return success;
	}
	
	public void acceptCookies() {
		// accept cookies
		MyAccountHomePage basePage = new MyAccountHomePage(driver);
		basePage.acceptCookies();
		logPass("Customer accepts cookies");
	}
}

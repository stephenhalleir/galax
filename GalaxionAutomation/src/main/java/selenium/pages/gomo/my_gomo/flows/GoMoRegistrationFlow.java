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
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.mailhog.MailHog;

public class GoMoRegistrationFlow extends BaseUIFlow {

	/*
	 * Constructors
	 */
	public GoMoRegistrationFlow(WebDriver driver, ExtentTest logger, Logger logger4j) {
		super(logger, logger4j, driver);
	}

	public GoMoRegistrationFlow(WebDriver driver) {
		super(driver);
	}


	public void testVerifyUser(String email) {

		logPass("Customer " + email + " completing registration");
		String password="Password123";

		// follow the verify email deep link
		String registrationDeeplink = MailHog.getDeeplinkFromMailhog(email, "verifyEmail");
		assertNotNull(registrationDeeplink);
		driver.get(registrationDeeplink);

		logPass("Registration deep link found:\n" + registrationDeeplink);


		// click the link on the page to proceed to the password entry screen
		KeycloakInitialVerificationPage initialPage = new KeycloakInitialVerificationPage(driver);
		initialPage.proceedToPasswordScreen();

		logPass("Proceeding to password screen");

		// set the password
		KeycloakSetPasswordPage passwordPage = new KeycloakSetPasswordPage(driver);
		passwordPage.setPassword(password);
		logPass("Password [" + password + "] entered");

		// retrieve the contact's date of birth
		String uuid=ContactManagementDAO.getUuidForEmailAddress(email);
		Date dateOfBirth = ContactManagementDAO.getDOBForUuid(uuid);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = simpleDateFormat.format(dateOfBirth);
		
		KeycloakVerifyDOBPage dobPage = new KeycloakVerifyDOBPage(driver);
		dobPage.verifyDOB(dateString);

		logPass("Date of birth [" + dateString + "] entered");

		// verify that the customer can now log in to MyAccount
		driver.get(EnvironmentDirectory.getMyGoMoURL());

		// check that the customer can login to MyAccount
		KeycloakLoginPage loginPage = new KeycloakLoginPage(driver);
		assertTrue(loginPage.login(email, password));

		// now log back out of MyAccount
		MyAccountHomePage homepage = new MyAccountHomePage(driver);
		homepage.acceptCookies();
		homepage.logout();
	}
}
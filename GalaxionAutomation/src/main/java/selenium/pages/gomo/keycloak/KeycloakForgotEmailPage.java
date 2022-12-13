package selenium.pages.gomo.keycloak;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.time.WaitUtil;

public class KeycloakForgotEmailPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(id = "mobileNumber")
	public WebElement msisdnField;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
	public WebElement btnContinueGetOTP;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div/div/div/div/div[2]/form/div/div[2]/div/div[2]/button")
	public WebElement btnConfirmOTP;
	
	@FindBy(id = "verificationCode")
	public WebElement otpField;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/span[1]")
	public WebElement emailField;
	
	@FindBy(xpath = "//button[contains(text(),'Accept All')]")
	public WebElement buttonAcceptCookies;

	// Constructor
	public KeycloakForgotEmailPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialize Elements
		PageFactory.initElements(driver, this);
	}

	public void enterMsisdn(String msisdn) {
		wait.until(ExpectedConditions.elementToBeClickable(msisdnField));
		msisdnField.sendKeys(msisdn);
	}

	public void clickContinueRequestOTP() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContinueGetOTP));
		btnContinueGetOTP.click();
	}
	
	public void clickContinueConfirmOTP() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmOTP));
		btnConfirmOTP.click();
	}

	public void enterOTPCode(String otp) {
		wait.until(ExpectedConditions.elementToBeClickable(otpField));
		otpField.sendKeys(otp);
	}

	public String getEmailDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(emailField));
		return emailField.getText();
	}
	
	public void acceptCookies() {

		WebDriverWait wait = new WebDriverWait(this.driver, 10);

		try {
			wait.until(ExpectedConditions.visibilityOf(buttonAcceptCookies));
			WaitUtil.waitForSeconds(1);
			buttonAcceptCookies.click();
		} catch (Exception e) {

		}
	}
}

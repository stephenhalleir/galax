package selenium.pages.gomo.keycloak;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakLoginPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	//@FindBy(xpath = "//*[@id=\"kc-info-message\"]/p[2]/a")
	//public WebElement verifyEmailDeeplink;
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement inputPassword;
	
	@FindBy(xpath = "//input[@id='username']")
	public WebElement inputEmail;
	
	@FindBy(xpath = "//input[@name='login']")
	public WebElement buttonLogin;
	
	@FindBy(xpath = "//h1[contains(text(), 'Your account has been updated.')]")
	public WebElement titleUpdatedAccount;
	
	@FindBy(linkText="Forgot your email?")
	public WebElement forgotEmailLink;
	
	@FindBy(linkText="Forgot your password?")
	public WebElement forgotPasswordLink;
	
	@FindBy(linkText="Dont have an account? Register")
	public WebElement registerLink;
	
	// Constructor
	public KeycloakLoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialize Elements
		PageFactory.initElements(driver, this);
	}
	
	public void clickForgotEmailLink() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotEmailLink));
		forgotEmailLink.click();
	}
	
	public void clickForgotPasswordLink() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
		forgotPasswordLink.click();
	}
	
	public void clickRegisterLink() {
		wait.until(ExpectedConditions.elementToBeClickable(registerLink));
		registerLink.click();
	}
	
	public boolean login(String email, String password) {
		System.out.println("Logging into keycloak with login " + email + ", " + password);
		this.inputEmail(email);
		this.inputPassword(password);
		this.clickLogin();
		return driver.getCurrentUrl().endsWith("/home");
	}
	
	public void inputEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(inputEmail));
		inputEmail.sendKeys(Keys.CONTROL, "a");
		inputEmail.sendKeys(Keys.DELETE);
		inputEmail.sendKeys(email);
	}
	
	public void inputPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(inputPassword));
		inputPassword.sendKeys(Keys.CONTROL, "a");
		inputPassword.sendKeys(Keys.DELETE);
		inputPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
		buttonLogin.click();
	}

}
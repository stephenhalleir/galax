package selenium.pages.gomo.keycloak;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakSetPasswordPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	
	@FindBy(xpath = "//input[@id='password-new']")
	public WebElement inputPassword;
	
	@FindBy(xpath = "//input[@id='password-confirm']")
	public WebElement inputPasswordConfirm;
	
	@FindBy(xpath = "//input[@value='Confirm']")
	public WebElement buttonPasswordConfirm;
	
	// Constructor
	public KeycloakSetPasswordPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// enter and confirm the password and proceed to the next screen
	public void setPassword(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
		wait.until(ExpectedConditions.elementToBeClickable(inputPasswordConfirm));
		inputPassword.sendKeys(password);
		inputPasswordConfirm.sendKeys(password);
		buttonPasswordConfirm.click();
	}
}
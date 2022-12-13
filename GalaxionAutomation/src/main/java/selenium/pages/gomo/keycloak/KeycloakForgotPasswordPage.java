package selenium.pages.gomo.keycloak;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakForgotPasswordPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(id = "username")
	public WebElement emailField;

	@FindBy(xpath = "//input[@value='Reset']")
	public WebElement btnReset;

	// Constructor
	public KeycloakForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialize Elements
		PageFactory.initElements(driver, this);
	}

	public void inputEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(emailField));
		emailField.sendKeys(email);
	}

	public void clickReset() {
		wait.until(ExpectedConditions.visibilityOf(btnReset));
		btnReset.click();
	}
}

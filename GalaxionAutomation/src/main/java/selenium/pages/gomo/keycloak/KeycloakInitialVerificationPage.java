package selenium.pages.gomo.keycloak;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakInitialVerificationPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//*[@id=\"kc-info-message\"]/p[2]/a")
	public WebElement proceedLink;
	
	// Constructor
	public KeycloakInitialVerificationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	// click the link "Click here to proceed"
	public void proceedToPasswordScreen() {
		wait.until(ExpectedConditions.elementToBeClickable(proceedLink));
		proceedLink.click();
	}

}
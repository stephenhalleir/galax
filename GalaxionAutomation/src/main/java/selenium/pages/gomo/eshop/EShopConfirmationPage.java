package selenium.pages.gomo.eshop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EShopConfirmationPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//*[contains(h6,'Order Number:')]")
	public WebElement orderNumber;
	
	@FindBy(xpath = "//*[contains(p,'Logout')]")
	public WebElement btnLogout;
	
	// Constructor
	public EShopConfirmationPage(WebDriver driver) {	
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);		
		wait.until(ExpectedConditions.visibilityOf(orderNumber));
	}

	public String getOrderNumber() {
		wait.until(ExpectedConditions.visibilityOf(orderNumber));
		return orderNumber.getText().split(":")[1].trim();
	}
	
	public void logout() {
		wait.until(ExpectedConditions.visibilityOf(btnLogout));
		btnLogout.click();
	}
}
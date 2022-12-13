package selenium.pages.eir_b2b.acquisitions.customer_care;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class B2BCareGeneralScreen {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Add Account')]")
	private WebElement btnAddAccount;
	
	
	// Constructor
	public B2BCareGeneralScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddAccount));	
	}
	
	public void clickAddAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddAccount));	
		btnAddAccount.click();
	}
}
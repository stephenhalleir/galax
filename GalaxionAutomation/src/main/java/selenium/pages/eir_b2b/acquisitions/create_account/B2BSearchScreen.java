package selenium.pages.eir_b2b.acquisitions.create_account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class B2BSearchScreen {

	private WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Create an Account')]")
	public WebElement btnCreateAccount;
	
	
	// Constructor
	public B2BSearchScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);	
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateAccount));
	}
	
	public void clickCreateAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateAccount));	
		btnCreateAccount.click();
	}
}
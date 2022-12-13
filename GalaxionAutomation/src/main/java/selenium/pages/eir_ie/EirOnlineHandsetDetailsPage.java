package selenium.pages.eir_ie;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EirOnlineHandsetDetailsPage {

	private WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//button[@value='NEW']")
	public WebElement btnNew;
	
	@FindBy(xpath="//button[@value='PORT']")
	public WebElement btnPort;

	// Constructor
	public EirOnlineHandsetDetailsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	public void selectNew() {
		wait.until(ExpectedConditions.visibilityOf(btnNew));
		System.out.println(btnNew.getText());
		btnNew.click();
	}
}
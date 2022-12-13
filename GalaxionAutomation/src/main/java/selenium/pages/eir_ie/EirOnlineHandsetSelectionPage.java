package selenium.pages.eir_ie;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EirOnlineHandsetSelectionPage {

	private WebDriver driver;
	public WebDriverWait wait;
	

	// Constructor
	public EirOnlineHandsetSelectionPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	public void selectHandset(String s) {
		//String buttonXpath = "//h4[contains(text(),'$handset')]//following::button//following::button".replace("$handset", s);
		String buttonXpath = "//h4[contains(text(),'$handset')]//following::span[contains(text(),'Select Phone')]".replace("$handset", s);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println(element.getText());
		element.click();
	}
}
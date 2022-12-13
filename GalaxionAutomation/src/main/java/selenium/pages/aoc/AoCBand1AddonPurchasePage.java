package selenium.pages.aoc;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AoCBand1AddonPurchasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	
	// Constructor
	public AoCBand1AddonPurchasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// confirm bundle purchase
	public void clickBuyAddOn() {
		WebElement element = driver.findElement(By.partialLinkText("Buy Add-On"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
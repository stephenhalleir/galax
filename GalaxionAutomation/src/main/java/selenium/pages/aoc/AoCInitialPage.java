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

public class AoCInitialPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators

	@FindBy(linkText = "Find out more")
	public WebElement btnFindOutMore;
	
	// Constructor
	public AoCInitialPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// add an additional product to the shopping cart
	public void clickFindOutMore() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFindOutMore));
		btnFindOutMore.click();
	}
}
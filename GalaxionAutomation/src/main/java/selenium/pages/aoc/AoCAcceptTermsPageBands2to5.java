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

public class AoCAcceptTermsPageBands2to5 {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators

	@FindBy(linkText = "Find out more")
	public WebElement btnAcceptTerms;
	
	// Constructor
	public AoCAcceptTermsPageBands2to5(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// accept terms on the band 2, 3, 4, 5 AoC screens
	public void clickAcceptTerms() {
		String xpath="//input[@onclick='aocFrm3.submit();']";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
	}
	
	// bundle purchase on the band 2, 3, 4, 5 AoC screens
	public void selectBundlePurchase() {
		String xpath="//input[@onclick='aocFrm.submit();']";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
	}
}
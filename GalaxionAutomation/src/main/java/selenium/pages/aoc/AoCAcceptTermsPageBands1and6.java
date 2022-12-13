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

public class AoCAcceptTermsPageBands1and6 {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators

	@FindBy(linkText = "Find out more")
	public WebElement btnAcceptTerms;

	// Constructor
	public AoCAcceptTermsPageBands1and6(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// bundle purchase on the band 1 AoC screen
	public void selectBundlePurchase() {
		WebElement element = driver.findElements(By.partialLinkText("More info")).get(0);
		element.click();
	}

	// accept terms on the band 1 AoC screen
	public void clickAcceptTerms() {
		WebElement element = driver.findElements(By.partialLinkText("More info")).get(1);
		element.click();
	}
	
	public void clickBundle(String bundleName) {
		System.out.println("Selecting bundle " + bundleName);
		String xpath="//label[contains(text(),'$bundleName')]//preceding-sibling::input".replace("$bundleName", bundleName);
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
	}
	
	public void clickBuyAddOn() {
		String xpath="//input[@value='Buy Add-On']";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
	}
}
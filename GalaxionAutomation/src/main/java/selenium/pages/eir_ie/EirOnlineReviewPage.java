package selenium.pages.eir_ie;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EirOnlineReviewPage {

	private WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//*[contains(text(), 'Continue')]")
	public WebElement btnContinue;

	// Constructor
	public EirOnlineReviewPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	// click a named button
	public void click(WebElement button) {
		wait.until(ExpectedConditions.visibilityOf(button));
		button.click();
	}
	
	public void selectTopup(String amount) {
		
		amount = amount.replace("€", "").replace(".00", "");
		
		String xpath="//button[@value='$amount']".replace("$amount", amount);
		WebElement element = driver.findElement(By.xpath(xpath));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
		//wait.until(ExpectedConditions.visibilityOf(element));
		//element.click();

		/*
		String xpath="//span[contains(text(), '$amount')]";
		WebElement btnTopup = driver.findElement(By.xpath(xpath.replace("$amount", amount)));
		System.out.println(btnTopup.getText());
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", btnTopup);
		*/
	}
	
	public void clickContinue() {
		wait.until(ExpectedConditions.visibilityOf(btnContinue));
		btnContinue.click();
	}
}
package selenium.pages.eir_ie;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EirOnlineOfferSelectionPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(name="1PM_PREPAY_01")
	public WebElement btnPrepay01;

	@FindBy(name="1PM_PREPAY_02")
	public WebElement btnPrepay02;
	
	@FindBy(name="1PM_PREPAY_03")
	public WebElement btnPrepay03;
	
	@FindBy(name="1PM_PREPAY_04")
	public WebElement btnPrepay04;
	
	@FindBy(xpath="//*[contains(text(), 'Prepay Plan Only')]")
	public WebElement radioPlanOnly;
	
	@FindBy(xpath="//*[contains(text(), 'Include a Phone')]")
	public WebElement radioIncludePhone;

	// Constructor
	public EirOnlineOfferSelectionPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	// click a named button
	public void click(WebElement button) {
		wait.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
	}

	public void selectOffer(String offerName) {
		String buttonXpath = "//h5[contains(text(),'$offerName')]//following::span[contains(text(),'Select Plan')]".replace("$offerName", offerName);	
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	// select the various offers
	public void selectOffer1() {
		click(btnPrepay01);
	}
	
	public void selectOffer2() {
		click(btnPrepay02);
	}
	
	public void selectOffer3() {
		click(btnPrepay03);
	}
	
	public void selectOffer4() {
		click(btnPrepay04);
	}
	
	public void clickPlanOnly() {
		click(radioPlanOnly);
	}
	
	public void clickIncludePhone() {
		click(radioIncludePhone);
	}
}
package selenium.pages.eir_prepay.acquisitions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CRMAcquisitionLandingPage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath="//h1[contains(text(),'Select Plan')]")
	private WebElement lblTopOfScreen;
	
	// Locators
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	public WebElement btnCheckout;

	// Constructor
	public CRMAcquisitionLandingPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(lblTopOfScreen));
	}
	
	public void selectOffer(String offerName) {
		
		// move to the top of the page
		String xpathTopOfScreen="//h1[contains(text(),'Select Plan')]";
		WebElement lblTopOfScreen = driver.findElement(By.xpath(xpathTopOfScreen));
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(lblTopOfScreen).perform();
		
		String xpathExpression = "//h3[contains(text(),'$offerName')]".replace("$offerName", offerName);
		WebElement btnSelectProduct = driver.findElement(By.xpath(xpathExpression));
		wait.until(ExpectedConditions.elementToBeClickable(btnSelectProduct));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(btnSelectProduct).click().perform();
		
		//btnSelectProduct.click();
	}


	// select the GoMo offer
	public void clickCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCheckout));
		btnCheckout.click();
	}
}
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

public class CRMAcquisitionSummaryPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(id = "has-agreed-terms-and-conditions")
	public WebElement chkTermsAndConditions;

	@FindBy(xpath = "//p[contains(text(),'Next')]")
	public WebElement btnNext;
	
	@FindBy(xpath = "//p[contains(text(),'Complete order')]")
	public WebElement btnComplete;
	
	@FindBy(xpath = "//div[contains(text(),'#')]")
	public WebElement lblOrderNumber;
	
	@FindBy(xpath = "//p[contains(text(),'Order: #')]")
	public WebElement popupOrderNumber;
	
	// Constructor
	public CRMAcquisitionSummaryPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void acceptTerms() {
		Actions builder = new Actions(driver);
		builder.moveToElement(chkTermsAndConditions).click().perform();
	}
	
	public String getOrderNumber() {
		wait.until(ExpectedConditions.visibilityOf(lblOrderNumber));
		return lblOrderNumber.getText();
	}
	
	public String getPopupOrderNumber() {
		wait.until(ExpectedConditions.visibilityOf(popupOrderNumber));
		return popupOrderNumber.getText();
	}

	public void clickNext() {
		btnNext.click();
	}
	
	public void clickCompleteOrder() {
		btnComplete.click();
	}
}
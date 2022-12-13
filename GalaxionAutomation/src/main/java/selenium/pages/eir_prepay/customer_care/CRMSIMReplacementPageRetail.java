package selenium.pages.eir_prepay.customer_care;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class CRMSIMReplacementPageRetail {

	private WebDriver driver;
	public WebDriverWait wait;

	// controls for the left banner on the page
	@FindBy(id = "iccid")
	public WebElement txtIccid;

	@FindBy(xpath = "//p[contains(text(),'Activate SIM')]")
	public WebElement btnActivate;

	// Constructor
	public CRMSIMReplacementPageRetail(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void enterIccid(String iccid) {
		wait.until(ExpectedConditions.visibilityOf(txtIccid));
		txtIccid.sendKeys(iccid);
	}
	
	public void clickActivate() {
		wait.until(ExpectedConditions.visibilityOf(btnActivate));
		btnActivate.click();
	}
	
	public void performSIMSwap(String iccid) {
		enterIccid(iccid);
		clickActivate();
	}

}
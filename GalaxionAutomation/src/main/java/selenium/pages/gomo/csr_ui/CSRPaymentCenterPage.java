package selenium.pages.gomo.csr_ui;

import java.text.DecimalFormat;

//---------------------------------------------------------------------------------------
// Issues
//		- Click Billing Address > County field is not working - therefore unable to select County
// ---------------------------------------------------------------------------------------

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.test_data.generic.StringUtil;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;

public class CSRPaymentCenterPage extends CSRBasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	// ---------------------------------------------------------------------------------------
	// Controls for DETAILS section
	// ---------------------------------------------------------------------------------------

	@FindBy(id="mui-component-select-reason")
	private WebElement dropAdjustmentReason;
	
	@FindBy(name="amount")
	private WebElement txtAmount;
	
	@FindBy(name="comment")
	private WebElement txtComment;
	
	@FindBy(xpath="//span[contains(text(),'CONFIRM')]")
	private WebElement btnConfirm;
	
	@FindBy(xpath="/html/body/div[2]/div[3]/div/div[2]/div[1]/div/div/h3")
	private WebElement popupAdjustmentsResult;
	
	@FindBy(xpath="//*[@aria-label='close']")
	public WebElement btnClosePopup;

	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public CSRPaymentCenterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// click the reason dropdown list
	public void clickAdjReasonDropdown() {
		wait.until(ExpectedConditions.visibilityOf(dropAdjustmentReason));
		dropAdjustmentReason.click();
	}
	
	public void selectAdjustmentReasonListItem(String reason) {
		//String xpath="//li[contains(text(),'$reason')]";
		String xpath="//li[@data-value='$reason']";
		xpath=xpath.replace("$reason", reason);
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		System.err.println(element.getAttribute("data-value"));
	}
	
	public void enterAmount(String amount) {
		wait.until(ExpectedConditions.visibilityOf(txtAmount));
		txtAmount.sendKeys(amount);
	}
	
	public void enterComment(String comment) {
		wait.until(ExpectedConditions.visibilityOf(txtComment));
		txtComment.sendKeys(comment);
	}
	
	public void clickConfirm() {
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
	}
	
	public String getAdjustmentResult() {
		wait.until(ExpectedConditions.visibilityOf(popupAdjustmentsResult));
		return popupAdjustmentsResult.getText();
	}
	
	public void closeAdjustmentPopup() {
		wait.until(ExpectedConditions.visibilityOf(btnClosePopup));
		btnClosePopup.click();
	}

	
	// COMPLEX METHODS
	public String makeAdjustment(AdjustmentReason reason, int amountInCents) {

		// convert the cents amount to a euro amount
		double amountInEuros=(double)amountInCents/100;
		DecimalFormat df = new DecimalFormat("#.00");
	    String adjustmentAmountFormated = df.format(amountInEuros);
		String comment="Auto adjustment " + System.currentTimeMillis();
		
		// process the adjustment
		this.clickAdjReasonDropdown();
		this.selectAdjustmentReasonListItem(reason.toString());
		this.enterAmount(adjustmentAmountFormated);
		this.enterComment(comment);
		this.clickConfirm();
		return comment;
	}
	
	public static void main(String[] args) {

	}
}

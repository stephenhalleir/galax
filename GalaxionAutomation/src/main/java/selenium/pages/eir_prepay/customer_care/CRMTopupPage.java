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

import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.galaxion.test_data.vouchers.Voucher;
import utilities.generic.time.WaitUtil;

public class CRMTopupPage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(id = "voucher-number")
	public WebElement txtVoucherNumber;

	@FindBy(xpath = "//*[contains(text(), 'Get voucher status')]")
	public WebElement btnGetVoucherStatus;
	
	@FindBy(id = "activation-code")
	public WebElement txtActivationCode;

	@FindBy(xpath = "//*[contains(text(), 'Apply voucher')]")
	public WebElement btnApplyVoucher;

	// Constructor
	public CRMTopupPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(btnGetVoucherStatus));
	}

	public void enterVoucherNumber(String voucherNumber) {
		wait.until(ExpectedConditions.visibilityOf(txtVoucherNumber));
		txtVoucherNumber.sendKeys(voucherNumber);
	}
	
	public void clickGetVoucherStatus() {
		wait.until(ExpectedConditions.visibilityOf(btnGetVoucherStatus));
		btnGetVoucherStatus.click();
	}
	
	public void enterActivationCode(String activationCode) {
		wait.until(ExpectedConditions.visibilityOf(txtActivationCode));
		txtActivationCode.sendKeys(activationCode);
	}
	
	public void clickApplyVoucher() {
		wait.until(ExpectedConditions.visibilityOf(btnApplyVoucher));
		btnApplyVoucher.click();
	}
	
	public void processVoucher(Voucher voucher) {
		enterVoucherNumber(voucher.getVoucherNumber());
		clickGetVoucherStatus();
		enterActivationCode(voucher.getActivationCode());
		clickApplyVoucher();
	}
}
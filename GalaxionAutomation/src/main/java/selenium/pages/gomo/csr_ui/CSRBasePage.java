package selenium.pages.gomo.csr_ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSRBasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[1]/div/div[5]/div[1]/p")
	public WebElement bannerName;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[1]/div/div[5]/div[2]/p")
	public WebElement bannerAccountNumber;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[1]/div/div[5]/div[3]/p")
	public WebElement bannerEmail;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[2]/div[1]/div/div[5]/div[4]/p")
	public WebElement bannerAddress;

	@FindBy(xpath = "//*[@id=\"mui-component-select-actions\"]")
	public WebElement actionsDropdown;

	@FindBy(xpath = "//*[@id=\"menu-actions\"]/div[3]/ul/li[2]")
	public WebElement btnTempUnlock;

	@FindBy(xpath = "//*[@id=\"menu-actions\"]/div[3]/ul/li[3]")
	public WebElement btnVerifyEmail;

	@FindBy(xpath = "//*[@id=\"menu-actions\"]/div[3]/ul/li[4]")
	public WebElement btnPasswordReset;

	@FindBy(xpath = "/html/body/div[2]/div[3]/div/div[1]/div/button")
	public WebElement btnCloseActionsPopup;

	@FindBy(xpath = "//h3[contains(text(),'DETAILS')]")
	public WebElement tabDetails;

	@FindBy(xpath = "//h3[contains(text(),'TRANSACTION HISTORY')]")
	public WebElement tabTransactionHistory;

	@FindBy(xpath = "//h3[contains(text(),'PAYMENT CENTER & ADJUSTMENTS')]")
	public WebElement tabPaymentCenterAdjustments;

	@FindBy(xpath = "//h3[contains(text(),'ORDERS')]")
	public WebElement tabOrders;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/svg[1]/path[1]")
	public WebElement logoutButton;

	// Constructor
	public CSRBasePage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);

		// Initialize Elements
		PageFactory.initElements(driver, this);
	}

	// select the DETAILS tab in CSR UI
	public void selectDetailsTab() {
		wait.until(ExpectedConditions.visibilityOf(tabDetails));
		tabDetails.click();
	}

	// select the TRANSACTION HISTORY tab in CSR UI
	public void selectTransactionHistoryTab() {
		wait.until(ExpectedConditions.visibilityOf(tabTransactionHistory));
		tabTransactionHistory.click();
	}

	// select the PAYMENT CENTER & ADJUSTMENTS tab in CSR UI
	public void selectPaymentCenterAdjustmentsTab() {
		wait.until(ExpectedConditions.visibilityOf(tabPaymentCenterAdjustments));
		tabPaymentCenterAdjustments.click();
	}

	// select the ORDERS tab in CSR UI
	public void selectOrdersTab() {
		wait.until(ExpectedConditions.visibilityOf(tabOrders));
		tabOrders.click();
	}

	// =================================================================================================================
	// Methods to get the details from the top banner in CSR UI
	// =================================================================================================================

	// return the customer's name from the banner
	public String getNameFromBanner() {
		wait.until(ExpectedConditions.visibilityOf(bannerName));
		return bannerName.getText();
	}

	// return the customer's account number from the banner
	public String getAccountNumberFromBanner() {
		wait.until(ExpectedConditions.visibilityOf(bannerAccountNumber));
		return bannerAccountNumber.getText();
	}

	// return the customer's email from the banner
	public String getEmailFromBanner() {
		wait.until(ExpectedConditions.visibilityOf(bannerEmail));
		return bannerEmail.getText();
	}

	// return the customer's address from the banner
	public String getAddressFromBanner() {
		wait.until(ExpectedConditions.visibilityOf(bannerAddress));
		return bannerAddress.getText();
	}

	// =================================================================================================================
	// Methods to select various actions from the top banner in CSR UI
	// =================================================================================================================

	// select TEMP UNLOCK in the banner
	public void selectActionsDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(this.actionsDropdown));
		actionsDropdown.click();
		// this.closeActionsPopup();
	}

	// select TEMP UNLOCK in the banner
	public void selectTempUnlock() {
		System.out.println("selectTempUnlock start");
		this.selectActionsDropdown();
		wait.until(ExpectedConditions.elementToBeClickable(this.btnTempUnlock));
		btnTempUnlock.click();
		this.closeActionsPopup();
		System.out.println("selectTempUnlock end");
	}

	// select Password Reset in the banner
	public void selectPasswordReset() {
		this.selectActionsDropdown();
		wait.until(ExpectedConditions.elementToBeClickable(this.btnPasswordReset));
		btnPasswordReset.click();
		this.closeActionsPopup();
	}

	// select Verify Email in the banner
	public void selectVerifyEmail() {
		this.selectActionsDropdown();
		wait.until(ExpectedConditions.elementToBeClickable(this.btnVerifyEmail));
		btnVerifyEmail.click();
		this.closeActionsPopup();
	}

	// click the logout button
	public void logout() {
		this.selectActionsDropdown();
		wait.until(ExpectedConditions.elementToBeClickable(this.logoutButton));
		logoutButton.click();
	}

	// select a particular MSISDN from the top banner
	public void selectService(String service) {
		String xpath = "//span[contains(text(),'$msisdn')]".replace("$msisdn", service);
		WebElement msisdnButton = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.elementToBeClickable(msisdnButton));
		msisdnButton.click();
	}

	// select close the Actions popup
	public void closeActionsPopup() {

		// TODO improve the handling of this popup box - currently it just refreshes the
		// page
		driver.navigate().refresh();
	}
}
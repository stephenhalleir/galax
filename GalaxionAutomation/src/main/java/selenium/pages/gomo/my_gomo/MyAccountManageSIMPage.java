package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountManageSIMPage extends MyAccountHomePage {

	// Locators
	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[3]/div[1]/div/div[2]/div[3]/button")
	public WebElement btnReplaceSIM;

	@FindBy(id = "eircodeText")
	public WebElement eircodeField;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[3]/div[1]/div/div[2]/form/div[1]/div[2]/div/button")
	public WebElement btnConfirmEircode;

	@FindBy(id = "submit")
	public WebElement btnSendNewSIM;

	@FindBy(xpath="//span[contains(text(),'Close')]")
	public WebElement btnCloseDialog;

	//@FindBy(xpath = "/html/body/div[29]/div[3]/div/div[3]/p")
	@FindBy(xpath="//p[contains(text(),'Do you wish to continue?')]")
	public WebElement dialogMessage;
	
	@FindBy(xpath="//p[contains(text(),'Do you wish to continue?')]//following::span[contains(text(),'Confirm')]")
	public WebElement btnConfirmDialog;

	@FindBy(id = "activationCode")
	public WebElement pukField;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[3]/div[2]/div/div[2]/form/div/div[2]/div/button")
	public WebElement btnActivateSIM;

	@FindBy(xpath = "//*[@id=\"filled-number\"]")
	public WebElement msisdnDropdown;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[1]")
	public WebElement liFirstMsisdn;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[1]")
	public WebElement msisdn_1;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[2]")
	public WebElement msisdn_2;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[3]")
	public WebElement msisdn_3;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[4]")
	public WebElement msisdn_4;

	public MyAccountManageSIMPage(WebDriver driver) {
		super(driver);
	}

	public void clickReplaceSIM() {
		wait.until(ExpectedConditions.elementToBeClickable(btnReplaceSIM));
		btnReplaceSIM.click();
	}

	public void enterEircode(String eircode) {
		wait.until(ExpectedConditions.visibilityOf(eircodeField));
		eircodeField.sendKeys(Keys.CONTROL, "a");
		eircodeField.sendKeys(Keys.DELETE);
		eircodeField.sendKeys(eircode);
	}

	public void selectConfirmEircode() {
		wait.until(ExpectedConditions.visibilityOf(btnConfirmEircode));
		btnConfirmEircode.click();
	}

	public void clickSendSIM() {
		wait.until(ExpectedConditions.visibilityOf(btnSendNewSIM));
		btnSendNewSIM.click();
	}

	public void clickMsisdnDropdown() {
		wait.until(ExpectedConditions.visibilityOf(msisdnDropdown));
		msisdnDropdown.click();
	}

	public void clickFirstMsisdn() {
		wait.until(ExpectedConditions.visibilityOf(liFirstMsisdn));
		liFirstMsisdn.click();

		// wait 1 second
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	// method to select a particular MSISDN from the dropdown list
	public void selectMsisdn(String msisdn) {

		// click the MSISDN dropdown list to display the options
		wait.until(ExpectedConditions.elementToBeClickable(msisdnDropdown));
		msisdnDropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(msisdn_1));

		// click the appropriate msisdn in the list
		if (msisdn_1.getText().equals(msisdn)) {
			msisdn_1.click();
		} else if (msisdn_2.getText().equals(msisdn)) {
			msisdn_2.click();
		} else if (msisdn_3.getText().equals(msisdn)) {
			msisdn_3.click();
		} else if (msisdn_4.getText().equals(msisdn)) {
			msisdn_4.click();
		}
	}

	public void closeDialog() {

		try {
			wait.until(ExpectedConditions.visibilityOf(btnCloseDialog));
			btnCloseDialog.click();
		} catch (Exception e) {
			// do nothing
		}
	}

	public void confirmDialog() {

		wait.until(ExpectedConditions.visibilityOf(btnConfirmDialog));
		System.err.println("***"+btnConfirmDialog.getText());
		btnConfirmDialog.click();
	}

	public String getDialogMessage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(dialogMessage));
			return dialogMessage.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void enterPuk(String puk) {
		wait.until(ExpectedConditions.visibilityOf(pukField));
		pukField.sendKeys(Keys.CONTROL, "a");
		pukField.sendKeys(Keys.DELETE);
		pukField.sendKeys(puk);
	}

	public void clickActivateSIM() {
		wait.until(ExpectedConditions.visibilityOf(btnActivateSIM));
		btnActivateSIM.click();
	}

	// --------- HELPER METHODS
	public void enterAndConfirmEircode(String eircode) {
		enterEircode(eircode);
		selectConfirmEircode();
	}
}
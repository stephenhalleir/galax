package selenium.pages.eir_b2b.acquisitions.create_account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class B2BContactScreen {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Create an account')]")
	private WebElement btnCreateAccount;
	
	@FindBy(id="ownerContactDetails-first-name")
	private WebElement txtOwnerFirstName;
	
	@FindBy(id="ownerContactDetails-last-name")
	private WebElement txtOwnerLastName;
	
	@FindBy(id="ownerContactDetails-email")
	private WebElement txtOwnerEmail;
	
	@FindBy(id="ownerContactDetails-phone-number")
	private WebElement txtOwnerPhoneNumber;
	
	@FindBy(id="ownerContactDetails-mobile-number")
	private WebElement txtOwnerMobileNumber;
	
	@FindBy(id="ownerContactDetails-position")
	private WebElement txtOwnerPosition;
	
	@FindBy(id="ownerAddress-code")
	private WebElement txtOwnerEircode;
	
	@FindBy(xpath="//p[contains(text(),'Confirm Eircode')]")
	private WebElement btnConfirmEircode;
	
	@FindBy(name="isPayerIdentical")
	private WebElement chkPayerSame;
	
	@FindBy(xpath="//p[contains(text(),'Save Account')]")
	private WebElement btnSaveAccount;
	
	// Constructor
	public B2BContactScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);	
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(txtOwnerLastName));
	}
	
	public void enterOwnerFirstName(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerFirstName, input);
	}
	
	public void enterOwnerLastName(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerLastName, input);
	}
	
	public void enterOwnerEmail(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerEmail, input);
	}
	
	public void enterOwnerPhoneNumber(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerPhoneNumber, input);
	}
	
	public void enterOwnerMobileNumber(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerMobileNumber, input);
	}
	
	public void enterOwnerPosition(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerPosition, input);
	}
	
	public void enterOwnerEircode(String input) {
		PAYGCRMUIActions.enterValue(driver, txtOwnerEircode, input);
	}
	
	public void clickConfirmEircode() {
		btnConfirmEircode.click();
	}
	
	public void selectPayerIdentical() {
		chkPayerSame.click();
	}
	
	public void clickSaveAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveAccount));
		btnSaveAccount.click();
	}
}
package selenium.pages.eir_b2b.acquisitions.create_account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class B2BBillingAccountScreen {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Create an account')]")
	private WebElement btnCreateAccount;

	@FindBy(id="bill-cycle")
	private WebElement ddBillCycle;
	
	@FindBy(id="bill-delivery-type")
	private WebElement ddBillDeliveryType;
	
	@FindBy(id="tax-category")
	private WebElement ddPaymentTerm;
	
	@FindBy(xpath="//p[contains(text(),'Next')]")
	private WebElement btnNext;
	
	// Constructor
	public B2BBillingAccountScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));	
	}
	
	public void selectBillCycle(String input) {
		PAYGCRMUIActions.selectDDValue(driver, ddBillCycle,input);
	}
	
	public void selectBillDeliveryType(String input) {
		PAYGCRMUIActions.selectDDValue(driver, ddBillDeliveryType,input);
	}
	
	public void selectPaymentTerm(String input) {
		PAYGCRMUIActions.selectDDValue(driver, ddPaymentTerm,input);
	}
	
	public void clickNext() {
		PAYGCRMUIActions.actionsClick(driver, btnNext);
	}
}
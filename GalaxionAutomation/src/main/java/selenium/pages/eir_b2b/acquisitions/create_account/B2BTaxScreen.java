package selenium.pages.eir_b2b.acquisitions.create_account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class B2BTaxScreen {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Create an account')]")
	private WebElement btnCreateAccount;
	
	@FindBy(id="company-name")
	private WebElement txtCompanyName;
	
	@FindBy(id="tax-number")
	private WebElement txtTaxNumber;
	
	@FindBy(xpath="//p[contains(text(),'Next')]")
	private WebElement btnNext;
	
	// Constructor
	public B2BTaxScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);	
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));
	}
	
	public void enterCompanyName(String input) {
		PAYGCRMUIActions.enterValue(driver, txtCompanyName,input);
	}
	
	public void enterTaxNumber(String input) {
		PAYGCRMUIActions.enterValue(driver, txtTaxNumber,input);
	}
	
	public void clickNext() {
		PAYGCRMUIActions.actionsClick(driver, btnNext);
	}
}
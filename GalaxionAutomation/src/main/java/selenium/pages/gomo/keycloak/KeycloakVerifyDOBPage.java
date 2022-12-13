package selenium.pages.gomo.keycloak;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeycloakVerifyDOBPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	
	// Locators
	@FindBy(id="day")
	public WebElement inputDayDOB;
	
	@FindBy(id="month")
	public WebElement inputMonthDOB;
	
	@FindBy(id="year")
	public WebElement inputYearDOB;
	
	@FindBy(xpath = "//input[@value='Confirm']")
	public WebElement buttonDOBConfirm;
	
	@FindBy(xpath = "//h1[contains(text(), '        Your account has been updated.')]")
	public WebElement titleUpdatedAccount;
	
	// Constructor
	public KeycloakVerifyDOBPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialise Elements
		PageFactory.initElements(driver, this);
	}

	public void verifyDOB(String dateOfBirth) {
		
		System.out.println("entering date " + dateOfBirth);
		
		// wait for the controls to be loaded
		wait.until(ExpectedConditions.elementToBeClickable(inputDayDOB));
		wait.until(ExpectedConditions.elementToBeClickable(inputMonthDOB));
		wait.until(ExpectedConditions.elementToBeClickable(inputYearDOB));
		
		String divider="";
		
		// determine the dd.mm.yyyy separator
		if(dateOfBirth.contains("-")) {
			divider="-";
		}
		else if(dateOfBirth.contains("/")) {
			divider="/";
		}
		
		// split the DOB string into day, month and year values
		String[] dobParts = dateOfBirth.split(divider);
		
		// if the date of birth is in the format dd-mm-yyyy
		if(dobParts[0].length()==2 && dobParts[1].length()==2 && dobParts[2].length()==4) {
			inputDayDOB.sendKeys(dobParts[0]);
			inputMonthDOB.sendKeys(dobParts[1]);
			inputYearDOB.sendKeys(dobParts[2]);
		}
		// else if the date of birth is in the format yyyy-mm-dd
		else if(dobParts[0].length()==4 && dobParts[1].length()==2 && dobParts[2].length()==2) {
			inputDayDOB.sendKeys(dobParts[2]);
			inputMonthDOB.sendKeys(dobParts[1]);
			inputYearDOB.sendKeys(dobParts[0]);
		}
		
		// click the confirm button
		buttonDOBConfirm.click();
	}

}
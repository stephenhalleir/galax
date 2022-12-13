package selenium.pages.gomo.csr_ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSRServicePage extends CSRBasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath = "//h3[contains(text(),'USAGE')]")
	public WebElement tabUsage;

	@FindBy(xpath = "//h3[contains(text(),'ADD-ON HISTORY')]")
	public WebElement tabAddOnHistory;

	@FindBy(xpath = "//h3[contains(text(),'SIM/MOBILE BARRING')]")
	public WebElement tabSimMobileBarring;

	@FindBy(xpath = "//h3[contains(text(),'NDD')]")
	public WebElement tabNDD;

	@FindBy(xpath = "//h3[contains(text(),'PIN')]//following::h2")
	public WebElement lblPin;

	@FindBy(xpath = "//h3[contains(text(),'PUK')]//following::h2")
	public WebElement lblPuk;

	@FindBy(xpath = "//h3[contains(text(),'imsi')]//following::h2")
	public WebElement lblIMSI;

	@FindBy(xpath = "//span[contains(text(),'Cancel my number')]")
	public WebElement btnCancelNumber;

	@FindBy(id = "otherReasonField")
	public WebElement txtCancelReason;
	
	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public CSRServicePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	// read the PIN from the service landing page
	public String getPIN() {
		return lblPin.getText();
	}

	// read the PUK from the service landing page
	public String getPUK() {
		return lblPuk.getText();
	}

	// read the IMSI from the service landing page
	public String getIMSI() {
		return lblIMSI.getText();
	}

	// click the NDD tab on the service landing page
	public void clickTabNDD() {
		wait.until(ExpectedConditions.elementToBeClickable(tabNDD));
		tabNDD.click();
	}

	// select the Cancel My Number on the service landing page
	public void clickCancelNumber() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCancelNumber));
		btnCancelNumber.click();
	}

	// select the cancellation reason on the cancellation popup
	public void selectReason(String reason) {
		
		// click the Select Reason dropdown
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Select Reason')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();

		// click the reason specified in the argument
		String xpath = "//li[contains(text(),'$reason')]".replace("$reason", reason);
		element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();

		// enter a reason in the text field
		txtCancelReason.sendKeys("test this here");
	}

	// click the CANCEL NOW button on the cancellation popup
	public void clickCancelNow() {
		WebElement element = driver.findElement(By.xpath("//div[3]/div/button/span"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public static void main(String[] args) {

	}
}

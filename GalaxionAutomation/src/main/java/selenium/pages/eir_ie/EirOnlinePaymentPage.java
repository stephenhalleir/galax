package selenium.pages.eir_ie;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.database.MariaDBConnection;

public class EirOnlinePaymentPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(id = "targetIframe")
	private WebElement paymentIFrame;

	@FindBy(id = "rxp-primary-btn")
	public WebElement payButton;

	@FindBy(id = "pas_ccnum")
	public WebElement checkCardNumber;

	@FindBy(id = "pas_expiry")
	public WebElement inputExpiry;

	@FindBy(id = "pas_cccvc")
	public WebElement inputSecurityCode;

	@FindBy(id = "pas_ccname")
	public WebElement inputCardholderName;

	// end test

	@FindBy(xpath = "//div[@class='panel-body panel-head-accordion']")
	public WebElement buttonSavedCard;

	public WebElement inputSavedCardCcv;

	// Constructor
	public EirOnlinePaymentPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		PageFactory.initElements(driver, this);
	}

	public void enterDetailsAndPay(String cardNumber, String expiry, String ccv, String cardHolder) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='targetIframe']")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("targetIframe"));
        driver.findElement(By.id("pas_ccnum")).sendKeys("4263970000005262");

        
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(inputExpiry));
		inputExpiry.sendKeys(expiry);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(inputSecurityCode));
		inputSecurityCode.sendKeys(ccv);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(inputCardholderName));
		inputCardholderName.sendKeys(cardHolder);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(payButton));
		payButton.click();
	}
}
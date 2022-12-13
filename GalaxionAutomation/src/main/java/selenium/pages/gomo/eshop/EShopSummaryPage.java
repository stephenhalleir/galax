package selenium.pages.gomo.eshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.test_data.generic.Person;
import microservices.backend.eir_payment_center_backend.dao.PaymentCenterDAO;
import selenium.actions.PAYGCRMUIActions;
import utilities.generic.time.WaitUtil;

public class EShopSummaryPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(id = "targetIframe")
	private WebElement paymentIFrame;

	@FindBy(id = "rxp-primary-btn")
	public WebElement payButton;

	@FindBy(id = "pas_ccnum")
	public WebElement txtCardNumber;

	@FindBy(id = "pas_expiry")
	public WebElement txtExpiryDate;

	@FindBy(id = "pas_cccvc")
	public WebElement txtSecurityCode;

	@FindBy(id = "pas_ccname")
	public WebElement txtCardholderName;

	@FindBy(xpath = "//div[@class='panel-body panel-head-accordion']")
	public WebElement buttonSavedCard;

	public WebElement inputSavedCardCcv;

	// Constructor
	public EShopSummaryPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 30);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='targetIframe']")));
	}

	public void enterDetailsAndPay(String cardNumber, String expiry, String ccv, String cardHolder) {
		this.completeHPP(cardNumber,expiry, ccv, cardHolder,true);
	}
	
	private void completeHPP(String cardNumber, String expiry, String ccv, String cardHolder,boolean submit) {
		expiry = expiry.replace("/", "");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("targetIframe"));
        
        // give some time for the control to render properly
        WaitUtil.waitForMilliSeconds(500);
		
        PAYGCRMUIActions.enterValue(driver, txtCardNumber, cardNumber);
        WaitUtil.waitForMilliSeconds(1000);
        
        // enter the expiry date slowly as entering it quickly can make the numbers change order
        char[] expiryDigits = expiry.toCharArray();  
        for(int i=0;i<expiryDigits.length;i++) {
        	txtExpiryDate.sendKeys(Character.toString(expiryDigits[i]));
        	WaitUtil.waitForMilliSeconds(200);
        }

		PAYGCRMUIActions.enterValue(driver, txtSecurityCode, ccv);
		WaitUtil.waitForMilliSeconds(500);

		PAYGCRMUIActions.enterValue(driver, txtCardholderName, cardHolder);
		WaitUtil.waitForMilliSeconds(500);
		
		WaitUtil.waitForMilliSeconds(2000);

		if(submit) {
			wait.until(ExpectedConditions.elementToBeClickable(payButton));
			payButton.click();
		}
	}

	// method to make the payment for a cross-sell order
	public void payWithSavedCard(String email, String ccv) {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='targetIframe']")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("targetIframe"));
		wait.until(ExpectedConditions.visibilityOf(buttonSavedCard));

		// retrieve the provider-ref for the customer based on email address
		String providerRef = PaymentCenterDAO.getProviderRefForEmail(email);

		// use the provider ref to locate the ccv field - and enter the ccv
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("pas_cccvc_$uuid".replace("$uuid", providerRef))).sendKeys(ccv);

		// click the Pay Now button
		wait.until(ExpectedConditions.visibilityOf(payButton));
		payButton.click();
	}	
	
	public void populate(Person owner) {
		enterDetailsAndPay(owner.getCreditCardNumber(), "12/24", "222", owner.getFirstName() + " " + owner.getLastName());
	}
}
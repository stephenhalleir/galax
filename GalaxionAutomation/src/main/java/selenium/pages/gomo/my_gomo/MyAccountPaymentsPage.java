package selenium.pages.gomo.my_gomo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import microservices.backend.eir_payment_center_backend.data_model.CardPaymentMethod;
import testCases.testObjects.payments.CreditCard;
import utilities.generic.database.MariaDBConnection;
import utilities.generic.time.WaitUtil;

public class MyAccountPaymentsPage {

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//p[contains(text(), 'My Payments')]")
	public WebElement lblPageHeader;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/h2[1]")
	private WebElement lblBalance;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/p[1]")
	private WebElement lblDateDue;

	@FindBy(xpath = "//span[contains(text(), 'Pay Now')]")
	private WebElement btnPayNow;

	@FindBy(xpath = "//a[@id='linkPayWithNewCard']/span[2]")
	private WebElement btnPayWithNewCard;

	@FindBy(id = "balanceField")
	private WebElement txtBalance;

	@FindBy(id = "paymentIframe")
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

	// Controls to manage card details
	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[3]/div/div/button")
	public WebElement btnAddCard;

	@FindBy(id = "pas_ccnum-error")
	public WebElement ccNumErrorMessage;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/nav/div[1]/div[2]/span")
	public WebElement btnEditExpiry2;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/nav/div[2]/div[2]/span")
	public WebElement btnRemove2;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/nav/div[3]/div[2]/span")
	public WebElement btnSetDefault2;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/div/div/div")
	public WebElement displayedCard2;

	@FindBy(xpath = "//div[@aria-haspopup='listbox']")
	public WebElement dropdownMonth;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/form/div[1]/div[2]/div")
	public WebElement dropdownYear;

	@FindBy(xpath = "//*[contains(text(), 'Confirm')]")
	public WebElement btnConfirmExpiryChange;

	@FindBy(xpath = "//button[@aria-label='Next']")
	public WebElement btnMoveRight;

	@FindBy(xpath = "//button[@aria-label='Back']")
	public WebElement btnMoveLeft;

	// return a reference to the 2nd card in the list - this is the card that we
	// will be updating, deleting
	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div/div/div/div")
	public WebElement divCard2;

	@FindBy(xpath = "//span[contains(text(),'Confirm')]")
	public WebElement btnConfirmCardDelete;

	// =================================================================================================================
	// Constructor
	// =================================================================================================================

	public MyAccountPaymentsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void display() {

	}

	public boolean scrollToCard(String cardholderName) {
		String cardNameXpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[$index]/div/div/div/div/div[3]/p[1]";
		boolean cardFound = false;
		int index = 1;
		while (!cardFound) {

			String xpath = cardNameXpath.replace("$index", Integer.toString(index));
			WebElement cardName = driver.findElement(By.xpath(xpath));
			if (cardholderName.equals(cardName.getText())) {
				cardFound = true;
				return true;
			} else
				System.out.println("Not " + cardName.getText());
			btnMoveRight.click();
			WaitUtil.waitForSeconds(1);
			index++;
		}

		return false;
	}

	// click a specified button for a card
	public void clickButton(String cardholderName, String buttonText) {

		// scroll to the card in the widget
		boolean cardFound = scrollToCard(cardholderName);

		// construct the xpath to the "Set as Default" button
		String xpath = "//p[contains(text(),'$name')]//following::span[contains(text(),'$buttonText')]";
		xpath = xpath.replace("$name", cardholderName);
		xpath = xpath.replace("$buttonText", buttonText);

		WebElement button = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
	}

	// return the email displayed on the page
	public String getBalanceDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblBalance));
		return lblBalance.getText();
	}

	// return the email displayed on the page
	public String getDueDateDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(lblDateDue));
		return lblDateDue.getText();
	}

	// click Pay Now
	public void clickPayNow() {
		wait.until(ExpectedConditions.visibilityOf(btnPayNow));
		WaitUtil.waitForSeconds(1);
		btnPayNow.click();
	} 

	// click Pay Now
	public String getBalanceFieldText() {
		wait.until(ExpectedConditions.elementToBeClickable(txtBalance));
		return txtBalance.getAttribute("value");
	}

	// enter an amount to pay
	public void enterPaymentAmount(String amount) {
		wait.until(ExpectedConditions.visibilityOf(txtBalance));
		txtBalance.sendKeys(Keys.CONTROL, "a");
		txtBalance.sendKeys(Keys.DELETE);
		txtBalance.sendKeys(amount);
	}

	public void scrollToTopOfPage() {
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(lblPageHeader).perform();
	}

	// method to make the payment for a cross-sell order
	public void payWithSavedCard(CreditCard card) {

		PageFactory.initElements(driver, this);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='paymentIframe']")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("paymentIframe"));
		wait.until(ExpectedConditions.visibilityOf(payButton));

		// use the provider ref to locate the cvv field - and enter the cvv
		WaitUtil.waitForSeconds(3);

		WebElement cardToClick = driver.findElement(By.xpath("//*[text()='" + card.getCardholderName() + "']"));
		cardToClick.click();
		WaitUtil.waitForSeconds(5);

		// enter the cvv code into the field
		WebElement txtSecurityCode = driver.findElement(By.id("pas_cccvc_" + card.getProviderRef()));
		wait.until(ExpectedConditions.visibilityOf(txtSecurityCode));
		txtSecurityCode.sendKeys("222");

		// click the Pay Now button
		wait.until(ExpectedConditions.visibilityOf(payButton));
		payButton.click();
	}

	// method to make the payment for a cross-sell order
	public void payWithSavedCard(CardPaymentMethod card) {

		PageFactory.initElements(driver, this);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='paymentIframe']")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("paymentIframe"));
		wait.until(ExpectedConditions.visibilityOf(payButton));

		WaitUtil.waitForSeconds(5);

		WebElement txtSecurityCode = driver.findElement(By.id("pas_cccvc_" + card.getProviderRef()));

		// if the security code field is not already displayed, click the card
		if (!txtSecurityCode.isDisplayed()) {
			WebElement cardToClick = driver.findElement(By.xpath("//*[text()='" + card.getCardholderName() + "']"));
			cardToClick.click();

			WaitUtil.waitForSeconds(1);
		}

		// enter the 3 digit security code
		txtSecurityCode.sendKeys("222");

		// use the provider ref to locate the cvv field - and enter the cvv
		WaitUtil.waitForSeconds(2);

		// click the Pay Now button
		wait.until(ExpectedConditions.elementToBeClickable(payButton));
		payButton.click();
	}

	public void selectPayWithNewCard() {
		wait.until(ExpectedConditions.elementToBeClickable(btnPayWithNewCard));
		btnPayWithNewCard.click();
	}

	public void selectAddCard() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddCard));
		btnAddCard.click();
	}

	// return the error message displayed on the credit card number field, if any
	public String getCCNumErrorMessage() {
		try {
			// wait.until(ExpectedConditions.visibilityOf(ccNumErrorMessage));
			return ccNumErrorMessage.getText();
		} catch (NoSuchElementException e) {
			return "";
		} catch (TimeoutException e) {
			return "";
		}
	}

	public void selectEditExpiry() {
		wait.until(ExpectedConditions.visibilityOf(btnEditExpiry2));
		btnEditExpiry2.click();
	}

	public void selectRemoveCard() {
		wait.until(ExpectedConditions.visibilityOf(btnRemove2));
		btnRemove2.click();
	}

	public void selectMakeDefault() {
		wait.until(ExpectedConditions.visibilityOf(btnSetDefault2));
		btnSetDefault2.click();
	}

	public void moveRight() {
		wait.until(ExpectedConditions.visibilityOf(btnMoveRight));
		btnMoveRight.click();
	}

	// return the cardholder name from the 2nd card on the list
	public String get2ndCardName() {

		wait.until(ExpectedConditions.visibilityOf(divCard2));
		String[] cardDetails = divCard2.getText().split("\n");

		// for each piece of information on the card
		for (int i = 0; i < cardDetails.length; i++) {

			// if the string contains the name, then we have identified the card to change
			if (cardDetails[i].length() > 8) {
				return cardDetails[i];
			}
		}
		return null;
	}

	public void selectMonth(String month) {

		wait.until(ExpectedConditions.visibilityOf(dropdownMonth));
		dropdownMonth.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		WebElement listItem = driver.findElement(By.xpath("//li[contains(text(),'" + month + "')]"));
		wait.until(ExpectedConditions.visibilityOf(listItem));
		listItem.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void selectYear(String year) {
		// String xpath="//div[@aria-haspopup='listbox']";

		// WebElement mthList = driver.findElement(By.xpath(xpath));
		dropdownMonth.sendKeys(Keys.TAB, Keys.ARROW_DOWN);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		WebElement listItem = driver.findElement(By.xpath("//li[contains(text(),'" + year + "')]"));
		wait.until(ExpectedConditions.visibilityOf(listItem));
		listItem.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void saveExpiryChange() {
		wait.until(ExpectedConditions.visibilityOf(btnConfirmExpiryChange));
		btnConfirmExpiryChange.click();
	}

	public void clickConfirmDeleteCard() {
		wait.until(ExpectedConditions.visibilityOf(btnConfirmCardDelete));
		btnConfirmCardDelete.click();
	}

	public void enterDetailsAndPay(String cardNumber, String expiry, String ccv, String cardHolder) {

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("card-payment-form")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("paymentIframe"));

		// explicit wait to allow the HPP to load
		waitForSeconds(3);

		wait.until(ExpectedConditions.elementToBeClickable(checkCardNumber));
		checkCardNumber.sendKeys(cardNumber);

		wait.until(ExpectedConditions.elementToBeClickable(inputExpiry));
		inputExpiry.sendKeys(expiry);

		wait.until(ExpectedConditions.elementToBeClickable(inputSecurityCode));
		inputSecurityCode.sendKeys(ccv);

		wait.until(ExpectedConditions.elementToBeClickable(inputCardholderName));
		inputCardholderName.sendKeys(cardHolder);

		waitForSeconds(1);

		wait.until(ExpectedConditions.elementToBeClickable(payButton));
		payButton.click();
	}

	public void enterNewCardDetails(String cardNumber, String expiry, String ccv, String cardHolder) {

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("addPaymentCardIframe"));

		// explicit wait to allow the HPP to load

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(checkCardNumber));
		checkCardNumber.sendKeys(cardNumber);

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

		String errorMessage = getCCNumErrorMessage();

		// if the error message indicates that the ccnum is missing, repopulate it
		if (errorMessage.contains("required")) {
			checkCardNumber.sendKeys(cardNumber);
		}
		System.out.println("ERROR MESSAGE: " + errorMessage);

		// click the button to add the card
		wait.until(ExpectedConditions.elementToBeClickable(payButton));
		payButton.click();
	}

	public void waitForSeconds(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
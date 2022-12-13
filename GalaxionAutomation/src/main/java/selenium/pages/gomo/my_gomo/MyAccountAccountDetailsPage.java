package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountAccountDetailsPage extends MyAccountHomePage {

	public MyAccountAccountDetailsPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//span[contains(text(),'Buy A New SIM')]//ancestor::div[@role='button']")
	public WebElement buttonBuyNewSim;

	@FindBy(xpath = "//span[contains(text(),'Move My Number')]//ancestor::div[@role='button']")
	public WebElement buttonMoveMyNumber;

	@FindBy(xpath = "//span[contains(text(),'Cancel My Number')]//ancestor::div[@role='button']")
	public WebElement buttonCancelMyNumber;

	@FindBy(xpath = "//span[contains(text(),'Replace / Activate My SIM')]")
	public WebElement buttonReplaceActivateSim;

	@FindBy(xpath = "//span[contains(text(),'My Orders')]//ancestor::div[@role='button']")
	public WebElement buttonMyOrders;
	
	@FindBy(xpath = "//span[contains(text(),'Bar Mobile Services')]//ancestor::div[@role='button']")
	public WebElement buttonViewBarring;


	@FindBy(xpath = "//div[@aria-label='Number select']//div[1]")
	public WebElement buttonNumberSelect;

	@FindBy(xpath = "//ul[@role='listbox']//li[1]")
	public WebElement buttonSubscription1;

	@FindBy(xpath = "//ul[@role='listbox']//li[2]")
	public WebElement buttonSubscription2;

	@FindBy(xpath = "//h5[contains(text(),'No phone number available')]")
	public WebElement textNoPhoneNumber;

	@FindBy(xpath = "//h4[contains(text(),'No active offer')]")
	public WebElement textNoActiveOffer;

	//@FindBy(xpath = "//p[contains(text(),'Account Details')]//following::p[contains(text(),'Your account number is')]")
	@FindBy(xpath = "//p[contains(text(),'Your account number is')]")
	public WebElement textAccountNumber;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[2]/div/div/div/div[1]/div/h4")
	public WebElement textPin;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[2]/div/div/div/div[2]/div/h4")
	public WebElement textPuk;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div/div[1]/div/span")
	public WebElement textOfferName;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div/div[2]/h4")
	public WebElement textOfferAmount;

	@FindBy(xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div/div[2]/p")
	public WebElement textOfferDescription;
	
	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[1]")
	public WebElement msisdn_1;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[2]")
	public WebElement msisdn_2;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[3]")
	public WebElement msisdn_3;

	@FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[4]")
	public WebElement msisdn_4;
	
	@FindBy(id="filled-number")
	public WebElement dropdownMsisdn;

	public void clickBuyNewSim() {
		wait.until(ExpectedConditions.visibilityOf(buttonBuyNewSim));
		buttonBuyNewSim.click();
	}

	public void clickMoveMyNumber() {
		wait.until(ExpectedConditions.visibilityOf(buttonMoveMyNumber));
		buttonMoveMyNumber.click();
	}
	
	public void clickBarring() {
		wait.until(ExpectedConditions.visibilityOf(buttonViewBarring));
		buttonViewBarring.click();
	}

	public void clickCancelMyNumber() {
		wait.until(ExpectedConditions.visibilityOf(buttonCancelMyNumber));
		buttonCancelMyNumber.click();
	}

	public void clickReplaceActivateSim() {
		wait.until(ExpectedConditions.visibilityOf(buttonReplaceActivateSim));
		buttonReplaceActivateSim.click();
	}

	public void clickMyOrders() {
		wait.until(ExpectedConditions.visibilityOf(buttonMyOrders));
		buttonMyOrders.click();
	}

	// read the account number displayed on the page
	public String getAccountNumber() {
		wait.until(ExpectedConditions.visibilityOf(textAccountNumber));
		return textAccountNumber.getText().replaceAll("\\D+","");
	}

	// read the PIN displayed on the page
	public String getPin() {
		wait.until(ExpectedConditions.visibilityOf(textPin));
		return textPin.getText();
	}

	// read the PUK displayed on the page
	public String getPuk() {
		wait.until(ExpectedConditions.visibilityOf(textPuk));
		return textPuk.getText();
	}

	// read the offer name displayed on the page - e.g. "Go More 3"
	public String getOfferName() {
		wait.until(ExpectedConditions.visibilityOf(textOfferName));
		return textOfferName.getText();
	}

	// read the offer description displayed on the page - e.g. "Unlimited Data, Calls, Texts and 10GB...."
	public String getOfferDescription() {
		wait.until(ExpectedConditions.visibilityOf(textOfferDescription));
		return textOfferDescription.getText();
	}

	// read the offer amount displayed on the page - e.g. "€9.99 per month"
	public String getOfferAmount() {
		wait.until(ExpectedConditions.visibilityOf(textOfferAmount));
		return textOfferAmount.getText();
	}
	
	// method to select a particular MSISDN from the dropdown list
	public void selectMsisdn(String msisdn) {
		
		// click the MSISDN dropdown list to display the options
		wait.until(ExpectedConditions.elementToBeClickable(dropdownMsisdn));
		dropdownMsisdn.click();
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
}
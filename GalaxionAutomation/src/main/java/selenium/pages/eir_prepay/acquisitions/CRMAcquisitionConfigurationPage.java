package selenium.pages.eir_prepay.acquisitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_topup_backend.enums.TopupAmount;
import selenium.actions.PAYGCRMUIActions;
import utilities.generic.time.WaitUtil;

public class CRMAcquisitionConfigurationPage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(id = "msisdn")
	public WebElement txtMSISDN;

	@FindBy(xpath = "//h1[contains(text(),'Select offers/add-ons')]")
	public WebElement header;

	@FindBy(xpath = "//p[contains(text(),'Submit')]")
	public WebElement btnSubmitMsisdn;

	@FindBy(xpath = "//p[contains(text(),'Add')]")
	public WebElement btnAddMsisdn;

	@FindBy(xpath = "//p[contains(text(),'Add New Plan')]")
	public WebElement btnAddNewPlan;

	// Constructor
	public CRMAcquisitionConfigurationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", header);
	}

	// select the offer type - Subscriptions, Top-ups or Addons
	public void selectExtrasType(String extrasType) {
		System.out.println("Clicking tab " + extrasType);

		// scroll to the section "Select add-ons"
		String sectionXpath = "//h1[contains(text(),'Select offers/add-ons')]";
		WebElement element2 = driver.findElement(By.xpath(sectionXpath));
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(element2).perform();

		// locate and click the button
		String buttonXpath = "//span[contains(text(),'$extrasType')]".replace("$extrasType", extrasType);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.elementToBeClickable(element));

		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();

		// element.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		PageFactory.initElements(driver, this);
	}

	// select an offer based on the display name
	public void selectExtra(String offerName) {
		String buttonXpath = "//p[contains(text(),'$offerName')]//following::p[contains(text(),'Add')]".replace("$offerName", offerName);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		PAYGCRMUIActions.jsClick(driver, element);
	}

	// determine whether the extra is shown on the screen
	public boolean extraExists(String offerName) {
		String buttonXpath = "//p[contains(text(),'$offerName')]".replace("$offerName", offerName);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.isDisplayed();
	}

	// select subscription offer based on the display name
	public void selectSubscriptionOffer(String offerName) {
		selectExtrasType("Offers");
		selectExtra(offerName);
	}

	// select topup offer based on the display name
	public void selectTopupOffer(String offerName) {
		selectExtrasType("Simply Top Up");
		selectExtra(offerName);
	}

	// select addon based on the display name
	public void selectAddon(String offerName) {
		System.out.println("Selecting add-on: " + offerName);
		selectExtrasType("Addons");
		selectExtra(offerName);
	}

	// select subscription offer based on the display name
	public String getDisplayName(String offerName) {

		String buttonXpath = "//p[contains(text(),'$offerName')]//preceding-sibling::p".replace("$offerName", offerName);
		WebElement element = driver.findElement(By.xpath(buttonXpath));

		return element.getText();
	}

	// HANDSETS

	public void clickWantsHandset(boolean selection) {
		String buttonXpath;

		if (selection) {
			buttonXpath = "//span[contains(text(),'Wants a handset')]";
		} else {
			buttonXpath = "//span[contains(text(),'Doesn't want a handset']";
		}

		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("Clicking " + element.getText());
		element.click();
	}

	// expand a section on the screen
	public void expandSection(String section) {
		System.out.println("Expanding section: " + section);
		String buttonXpath = "//h1[contains(text(),'$section')]//following::button".replace("$section", section);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
	}

	public void selectHandsetByCode(String inventoryCode) {
		String handsetModel = CatalogCoreDAO.getEquipmentModelByInventoryCode(inventoryCode);
		this.selectHandset(handsetModel);
	}

	// select a handset for the order
	public void selectHandset(String handsetType) {
		System.out.println("Selecting handset: " + handsetType);
		expandSection("Handset");
		clickWantsHandset(true);
		WaitUtil.waitForSeconds(5);
		String buttonXpath = "//p[contains(text(),'$handset')]//following::button".replace("$handset", handsetType);

		int page = 1;
		boolean handsetFound = false;

		// search for the handset
		while (!handsetFound) {
			try {
				WebElement element = driver.findElement(By.xpath(buttonXpath));
				System.out.println("Looking for handset " + handsetType + " on page " + page);
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				handsetFound = true;
			} catch (NoSuchElementException e) {
				System.out.println("Handset not found on page " + page);
				page++;
				System.out.println("Going to page " + page);
				String xpath = "//*[@aria-label='Go to page $page']";
				xpath = xpath.replace("$page", Integer.toString(page));
				WebElement nextPageNumber = driver.findElement(By.xpath(xpath));
				System.out.println("Going to page " + page);
				nextPageNumber.click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

			}
		}
	}

	// select a discount for the order
	public void selectDiscount(String discount) {
		System.out.println("Selecting discount: " + discount);
		// String buttonXpath="//p[contains(text(),'$discount')]".replace("$discount",
		// discount);
		String buttonXpath = "//*[@aria-label='$discount']".replace("$discount", discount);
		;
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		element.click();
	}

	public void selectDirectoryPreference(String preference) {
		System.out.println("Selecting directory preference: " + preference);
		expandSection("Directory Preference");
		String buttonXpath = "//span[contains(text(),'$preference')]".replace("$preference", preference);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
	}

	// click the 'Checkout' button
	public void clickCheckout() {
		System.out.println("Clicking 'Checkout' button");
		WebElement el = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/div[2]/button[1]"));
		wait.until(ExpectedConditions.visibilityOf(el));
		el.click();
	}

	// click the 'Add New Offer' button
	public void clickAddNewOffer() {
		// WebElement el =
		// driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[2]/div[2]/button[2]"));
		wait.until(ExpectedConditions.visibilityOf(btnAddNewPlan));
		btnAddNewPlan.click();
	}

	public void enterMSISDN(String msisdn) {
		txtMSISDN.sendKeys(msisdn);
	}

	public void clickSubmitMsisdn() {
		btnSubmitMsisdn.click();
	}

	public void clickAddMsisdn() {
		String xpath = "//p[contains(text(),'Add')]";
		xpath = "//*[@id=\"root\"]/div/main/div[2]/div[2]/div[1]/div/div[3]/div/div/div[3]/div/div/div/form/div/div[3]/button";
		WebElement addButton = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(addButton));
		addButton.click();
	}

	public void submitMSISDN(String msisdn) {
		expandSection("SIM");
		WaitUtil.waitForSeconds(1);
		enterMSISDN(msisdn);
		clickSubmitMsisdn();
		WaitUtil.waitForSeconds(2);
		clickAddMsisdn();
	}

	// select a top up from the Top Up section
	public void selectTopupAmount(String topupAmount) {
		expandSection("Top Up");
		WaitUtil.waitForSeconds(1);
		String buttonXpath = "//h1[contains(text(),'Top Up')]//following::p[contains(text(),'$topupAmount')]".replace("$topupAmount", topupAmount);
		WebElement element = driver.findElement(By.xpath(buttonXpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("Selecting topup amount " + element.getText());
		element.click();
		WaitUtil.waitForSeconds(3);
	}

	// select a top up from the Top Up section
	public void selectTopupAmount(TopupAmount amount) {
		String amountString = amount.toString().replace("EUR", "€") + ".00";
		this.selectTopupAmount(amountString);
	}

}
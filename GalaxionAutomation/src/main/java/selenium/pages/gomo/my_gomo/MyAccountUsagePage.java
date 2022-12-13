package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.time.WaitUtil;

public class MyAccountUsagePage extends MyAccountHomePage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath = "//*[@aria-label='next-button']")
	public WebElement btnSubtotalNext;

	@FindBy(xpath = "//*[@aria-label='prev-button']")
	public WebElement btnSubtotalPrev;

	@FindBy(id = "filled-number")
	public WebElement dropdownMsisdn;

	@FindBy(id = "selected-page-size")
	public WebElement dropdownResultsPerPage;

	private String xpathUsageDate = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[4]/div[1]/table/tbody/tr[$index]/td[1]/p";
	private String xpathUsageType = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[4]/div[1]/table/tbody/tr[$index]/td[2]/p";
	private String xpathUsageValue = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[4]/div[1]/table/tbody/tr[$index]/td[3]/p";
	private String xpathUsageCharge = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[4]/div[1]/table/tbody/tr[$index]/td[4]/p";
	private String xpathUsageNumber = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[4]/div[1]/table/tbody/tr[$index]/td[5]/p";
	private String xpathUsageLocation = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[4]/div[1]/table/tbody/tr[$index]/td[6]/p";

	private String xpathSubtotalName = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[3]/div[1]/div[1]/div/div/div[$index]/div/div/p[1]";
	private String xpathSubtotalDuration = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[3]/div[1]/div[1]/div/div/div[$index]/div/div/h2";
	private String xpathSubtotalCharge = "//*[@id=\"app-container\"]/div[1]/div[2]/div[2]/div[1]/div/div/div[3]/div[1]/div[1]/div/div/div[$index]/div/div/p[2]";

	public MyAccountUsagePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public String getUsageDate(int index) {
		return getTextValue(xpathUsageDate.replace("$index", Integer.toString(index)));
	}

	public String getUsageType(int index) {
		return getTextValue(xpathUsageType.replace("$index", Integer.toString(index))).replace("Ordered on: ", "");
	}

	public String getUsageValue(int index) {
		return getTextValue(xpathUsageValue.replace("$index", Integer.toString(index))).replace("Order ID: #:", "");
	}

	public String getUsageCharge(int index) {
		return getTextValue(xpathUsageCharge.replace("$index", Integer.toString(index)));
	}

	public String getUsageNumber(int index) {
		return getTextValue(xpathUsageNumber.replace("$index", Integer.toString(index)));
	}

	public String getUsageLocation(int index) {
		return getTextValue(xpathUsageLocation.replace("$index", Integer.toString(index)));
	}

	public String getSubtotalName(int index) {
		return getTextValue(xpathSubtotalName.replace("$index", Integer.toString(index)));
	}

	public String getSubtotalCharge(int index) {
		return getTextValue(xpathSubtotalCharge.replace("$index", Integer.toString(index)));
	}

	public String getSubtotalDuration(int index) {
		return getTextValue(xpathSubtotalDuration.replace("$index", Integer.toString(index)));
	}

	private String getTextValue(String xpath) {
		WebElement messageText = driver.findElement(By.xpath(xpath));
		try {
			WebDriverWait wait2 = new WebDriverWait(this.driver, 1);
			wait2.until(ExpectedConditions.visibilityOf(messageText));
			return messageText.getText();
		} catch (TimeoutException e) {
			return "";
		}
	}

	public void clickNext() {
		wait.until(ExpectedConditions.visibilityOf(btnSubtotalNext));
		btnSubtotalNext.click();
	}

	public void clickPrev() {
		wait.until(ExpectedConditions.visibilityOf(btnSubtotalPrev));
		btnSubtotalPrev.click();
	}

	public void clickMsisdnDropdown() {
		wait.until(ExpectedConditions.visibilityOf(dropdownMsisdn));
		dropdownMsisdn.click();
	}

	public void selectMsisdn(String msisdn) {
		clickMsisdnDropdown();
		String xpath = "//li[contains(text(),'$msisdn')]";
		xpath = xpath.replace("$msisdn", msisdn);
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public void selectResultsPerPage(int resultsPerPage) {
		wait.until(ExpectedConditions.visibilityOf(dropdownResultsPerPage));
		dropdownResultsPerPage.click();
		String xpath = "//li[contains(text(),'$resultsPerPage')]";
		xpath = xpath.replace("$resultsPerPage", Integer.toString(resultsPerPage));
		WaitUtil.waitForSeconds(1);
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
package selenium.pages.eir_prepay.customer_care;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;

public class CRMServicePage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath = "//h3[contains(text(),'pin')]//following::h3")
	public WebElement lblPin;

	@FindBy(xpath = "//h3[contains(text(),'puk')]//following::h3")
	public WebElement lblPuk;

	// Constructor
	public CRMServicePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void clickTab(String tabName) {

		String xpath = "//*[contains(text(),'$tabName')]".replace("$tabName", tabName);
		WebElement tab = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(tab));
		tab.click();
	}

	public void clickManageSubscriptionOption(String option) {
		clickTab("Manage Subscription");
		clickTab(option);
	}

	public String getPin() {
		return lblPin.getText();
	}

	public String getPuk() {
		return lblPuk.getText();
	}

	public String getOfferValue(String key) {
		String xpath = "//div[contains(text(),'$key')]//following::div".replace("$key", key);
		WebElement div = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(div));
		return div.getText();
	}

	public void scrollToNDD() {
		String xpath = "//input[@value='EXDIRECTORY']";
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public boolean getNDDValue(String value) {
		String xpath = "//input[@value='$value']".replace("$value", value);
		WebElement div = driver.findElement(By.xpath(xpath));
		return div.isSelected();
	}

	public String getSelectedNDD() {
		if (getNDDValue("EXDIRECTORY")) {
			return "EXDIRECTORY";
		} else if (getNDDValue("LISTED")) {
			return "LISTED";
		} else if (getNDDValue("UNLISTED")) {
			return "UNLISTED";
		} else {
			return null;
		}
	}

	public void selectNDD(String nddSetting) {
		String xpath = "//input[@value='$value']".replace("$value", nddSetting);
		WebElement ndd = driver.findElement(By.xpath(xpath));
		ndd.click();
	}
}
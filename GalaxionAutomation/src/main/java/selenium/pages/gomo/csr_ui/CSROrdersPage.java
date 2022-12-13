package selenium.pages.gomo.csr_ui;

//---------------------------------------------------------------------------------------
// Issues
//		- Click Billing Address > County field is not working - therefore unable to select County
// ---------------------------------------------------------------------------------------

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSROrdersPage extends CSRBasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public CSROrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	public void scrollToBottom() {
		
	}

	// ---------------------------------------------------------------------------------------
	// Methods for DETAILS section
	// ---------------------------------------------------------------------------------------

	public String getCreatedDate(int index) {
		String xpath="//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/table/tbody/tr[$row]/th/p";
		xpath=xpath.replace("$row", Integer.toString(index));
		return getValue(xpath);
	}
	
	public String getOrderReference(int index) {
		String xpath="//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/table/tbody/tr[$row]/td[1]/p";
		xpath=xpath.replace("$row", Integer.toString(index));
		return getValue(xpath);
	}
	
	public String getOrderCost(int index) {
		String xpath="//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/table/tbody/tr[$row]/td[2]/p";
		xpath=xpath.replace("$row", Integer.toString(index));
		return getValue(xpath);
	}
	
	public String getOrderType(int index) {
		String xpath="//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/table/tbody/tr[$row]/td[3]/p";
		xpath=xpath.replace("$row", Integer.toString(index));
		return getValue(xpath);
	}
	
	public String getOrderStatus(int index) {
		String xpath="//*[@id=\"app-container\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/table/tbody/tr[$row]/td[4]/p";
		xpath=xpath.replace("$row", Integer.toString(index));
		return getValue(xpath);
	}
	
	public String getValue(String xpath) {
		WebElement messageText = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(messageText));
		return messageText.getText();
		
	}

	public static void main(String[] args) {

	}
}

package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountViewOrdersPage extends MyAccountHomePage {
	
	private WebDriver driver;
	public WebDriverWait wait;
	
	private String xpathOrderType="//*[@id=\"app-container\"]/div[1]/div[2]/div[4]/div[$index]/div/div/div/div/div[1]/h4";
	private String xpathOrderDate="//*[@id=\"app-container\"]/div[1]/div[2]/div[4]/div[$index]/div/div/div/div/div[1]/p[1]";
	private String xpathOrderReference="//*[@id=\"app-container\"]/div[1]/div[2]/div[4]/div[$index]/div/div/div/div/div[1]/p[2]";
	private String xpathOrderStatus="//*[@id=\"app-container\"]/div[1]/div[2]/div[4]/div[$index]/div/div/div/div/div[1]/div/span";
	private String xpathOrderCharge="//*[@id=\"app-container\"]/div[1]/div[2]/div[4]/div[$index]/div/div/div/div/div[2]/div/h2";
	
	public MyAccountViewOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public String getOrderType(int index) {
		return getTextValue(xpathOrderType.replace("$index", Integer.toString(index)));
	}
	
	public String getOrderDate(int index) {
		return getTextValue(xpathOrderDate.replace("$index", Integer.toString(index))).replace("Ordered on: ", "");
	}
	
	public String getOrderReference(int index) {
		return getTextValue(xpathOrderReference.replace("$index", Integer.toString(index))).replace("Order ID: #:", "");
	}
	
	public String getOrderStatus(int index) {
		return getTextValue(xpathOrderStatus.replace("$index", Integer.toString(index)));
	}
	
	public String getOrderCharge(int index) {
		return getTextValue(xpathOrderCharge.replace("$index", Integer.toString(index)));
	}
	
	private String getTextValue(String xpath) {
		WebElement messageText = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(messageText));
		return messageText.getText();
	}
	
	
}
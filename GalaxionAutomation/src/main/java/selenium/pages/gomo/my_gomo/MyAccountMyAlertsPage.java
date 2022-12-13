package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountMyAlertsPage extends MyAccountHomePage {
	
	private WebDriver driver;
	public WebDriverWait wait;
	
	public MyAccountMyAlertsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}

	public String getAlertMessage(int index) {
		String xpath = "//*[@id=\"app-container\"]/div[1]/div[2]/div/div[$id]/div/div/div[2]/p[1]";
		xpath=xpath.replace("$id", Integer.toString(index+1));
		WebElement messageText = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(messageText));
		return messageText.getText();
	}
}
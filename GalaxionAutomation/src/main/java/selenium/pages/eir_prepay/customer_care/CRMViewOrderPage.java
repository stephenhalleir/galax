package selenium.pages.eir_prepay.customer_care;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CRMViewOrderPage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath = "//h3[contains(text(), 'ORDER HISTORY')]")
	public WebElement lblOrderDetails;

	// Constructor
	public CRMViewOrderPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(lblOrderDetails));
	}

	public boolean isLoaded() {
		return lblOrderDetails.isDisplayed();
	}
	
	public String getTableValue(int row, int column) {
		String xpath="//*[@id=\"root\"]/div/main/div[2]/div[2]/div/div[2]/div[2]/div/div/table/tbody/tr[$row]/td[$col]";
		xpath=xpath.replace("$row", Integer.toString(row));
		xpath=xpath.replace("$col", Integer.toString(column));
		WebElement value = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(value));
		return value.getText();
	}
}
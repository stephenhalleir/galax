package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountBarringPage extends MyAccountHomePage {

	// Locators
	@FindBy(xpath="//*[@id=\"app-container\"]/div[1]/div[2]/div[3]/div[2]/div/div/div/div[5]/label/span[1]/span/input")
	public WebElement chkFullNetworkBar;
	
	public MyAccountBarringPage(WebDriver driver) {
		super(driver);
	}
	
	// not working
	public boolean isFullNetworkBarChecked() {
		wait.until(ExpectedConditions.visibilityOf(chkFullNetworkBar));
		return chkFullNetworkBar.isSelected();
	}
}
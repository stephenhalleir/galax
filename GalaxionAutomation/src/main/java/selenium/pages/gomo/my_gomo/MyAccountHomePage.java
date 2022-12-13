package selenium.pages.gomo.my_gomo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.time.WaitUtil;

/*
 * 
 * This class includes the header links and 
 * will be extended by all MyAccount sub pages
 * 
 * */
public class MyAccountHomePage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	
	@FindBy(xpath = "//button[contains(text(),'Accept All')]")
	public WebElement buttonAcceptCookies;

	@FindBy(xpath = "//h6[contains(text(),'My GoMo Home')]")
	public WebElement buttonNavBarMyAccountHome;
	
	@FindBy(xpath = "//h6[contains(text(),'My Alerts')]")
	public WebElement buttonNavBarMyAlerts;
	

	@FindBy(xpath = "//h6[contains(text(),'My Payments')]")
	public WebElement buttonNavBarMyPayments;
	
	@FindBy(xpath = "//h6[contains(text(),'My Profile')]")
	public WebElement buttonNavBarMyProfile;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'My Bills')]")
	public WebElement buttonNavBarMyBills;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'Account Details')]")
	public WebElement buttonNavBarAccountDetails;
	
	// Locators
	@FindBy(xpath = "//h6[contains(text(),'Contact Us')]")
	public WebElement buttonNavBarContactUs;
	
	// Locators
	@FindBy(xpath = "//p[contains(text(),'Logout')]")
	public WebElement logoutButton;
	
	@FindBy(xpath = "//h6[contains(text(),'My Alerts')]//following::span")
	public WebElement lblAlertsCount;
	
	// Constructor
	public MyAccountHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		// Initialize Elements
		PageFactory.initElements(driver, this);

	}
	
	// log out of MyAccount
	public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
	}

	public void clickNavBarMyAlerts() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyAlerts));
		buttonNavBarMyAlerts.click();
	}
	
	public void acceptCookies() {
		
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		
		try {
			wait.until(ExpectedConditions.visibilityOf(buttonAcceptCookies));
			WaitUtil.waitForSeconds(1);
			buttonAcceptCookies.click();
		}
		catch(Exception e) {
			
		}
	}
	
	public void clickNavBarMyPayments() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyPayments));
		buttonNavBarMyPayments.click();
	}
	
	public void clickNavBarMyProfile() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyProfile));
		buttonNavBarMyProfile.click();
	}
	
	public void clickNavBarMyBills() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarMyBills));
		buttonNavBarMyBills.click();
	}
	
	public void clickNavBarAccountDetails() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarAccountDetails));
		buttonNavBarAccountDetails.click();
	}
	
	
	public void clickNavBarContactUs() {
		wait.until(ExpectedConditions.visibilityOf(buttonNavBarContactUs));
		buttonNavBarContactUs.click();
	}
	
	// this link will work for all navigation while the xpaths remain unchanged
	public void clickLink(String linkText) {
		WebElement link = driver.findElement(By.xpath("//h6[contains(text(),'" + linkText + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(link));

		link.click();
		
		System.out.println("Clicked link " + linkText);
	}
	
	// this link will work for all navigation while the xpaths remain unchanged
	public void clickTopLink(String linkText) {
		WebElement link = driver.findElement(By.xpath("//span[.='" + linkText + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(link));
		link.click();
	}
	
	// this link will work for all navigation while the xpaths remain unchanged
		public void clickFooterLink(String linkText) {
			WebElement link = driver.findElement(By.linkText(linkText));
			wait.until(ExpectedConditions.elementToBeClickable(link));
			System.err.println("URL = " + link.getAttribute("href"));
			link.click();
			try{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
		
		public String getURLFromFooterLink(String linkText) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
			WebElement link = driver.findElement(By.linkText(linkText));
			wait.until(ExpectedConditions.elementToBeClickable(link));
			String url = link.getAttribute("href");
			if(url.endsWith("/")) {
				url = url.substring(0,url.length()-1);
			}
			
			return url;
		}
		
		public int getAlertsCount() {
			wait.until(ExpectedConditions.visibilityOf(lblAlertsCount));
			return Integer.parseInt(lblAlertsCount.getText());
		}
}
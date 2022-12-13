package selenium.pages.gomo.eshop;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.time.WaitUtil;

public class EShopOfferSelectionPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath = "//button[contains(text(),'Accept All')]")
	public WebElement btnAcceptCookies;

	@FindBy(xpath = "//button[contains(span, 'Buy now')]")
	public WebElement btnSelectGoMoreProduct;

	@FindBy(xpath = "//button[@aria-label=\"cart\"]/*/*/span")
	public WebElement btnCart;

	@FindBy(xpath = "//div[contains(p, 'Login')]")
	public WebElement buttonLogin;

	// Constructor
	public EShopOfferSelectionPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 30);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(btnSelectGoMoreProduct));
	}

	// accept cookies (if prompted)
	public void clickAcceptCookies() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnAcceptCookies));
			WaitUtil.waitForSeconds(1);
			btnAcceptCookies.click();
		} catch (TimeoutException e) {
			System.out.println("No cookies to accept");
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	// select the GoMo offer
	public void selectOffer() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSelectGoMoreProduct));
		btnSelectGoMoreProduct.click();
	}

	// click the Login button
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
		buttonLogin.click();
	}

	// click the Cart icon
	public void clickCart() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCart));
		btnCart.click();
	}
}
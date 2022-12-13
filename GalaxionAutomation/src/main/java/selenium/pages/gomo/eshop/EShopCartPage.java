package selenium.pages.gomo.eshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EShopCartPage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Locators

	@FindBy(xpath = "//button[contains(text(),'Accept Cookies')]")
	public WebElement buttonAcceptCookies;

	@FindBy(xpath = "//button[contains(span, 'Buy now')]")
	public WebElement buttonSelectGoMoreProduct;

	@FindBy(xpath = "//span[text() = 'Add another SIM']/..")
	public WebElement buttonSelectAnotherProduct;

	@FindBy(xpath = "//button[@class=\"jss27 jss1 jss3 jss4 jss6 jss7 jss24\"]")
	public WebElement buttonNextOffer;

	@FindBy(xpath = "//button[@type='button' and contains(., 'Continue')]")
	public WebElement buttonContinue;

	@FindBy(xpath = "//button[@aria-label=\"cart\"]/*/*/span")
	public WebElement pageItemsInCart;

	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting1;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting2;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting3;
	@FindBy(xpath = "//*[@id=\"container\"]/div[2]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/span[1]")
	private WebElement buttonMobilePorting4;

	@FindBy(xpath = "//div[contains(p, 'Login')]")
	public WebElement buttonLogin;
	
	// Constructor
	public EShopCartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(buttonContinue));
	}

	// add an additional product to the shopping cart
	public void addOfferToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonSelectAnotherProduct));
		buttonSelectAnotherProduct.click();		
		wait.until(ExpectedConditions.elementToBeClickable(buttonSelectGoMoreProduct));
		buttonSelectGoMoreProduct.click();
	}
	
	// select x additional products from the cart screen
	public void selectOffers(int count) {
		for(int i=0;i<count;i++) {
			addOfferToCart();
		}
	}

	// select Continue to proceed to the customer details screen
	public void selectContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonContinue));
		buttonContinue.click();
	}

	// remove an item from the shopping cart
	public void deleteSelectedOfferLine(int row) {
		String xpath="//*[@id=\"container\"]/div[2]/div[1]/div[1]/div[$row]/div/div/div/div[1]/div[2]/div/button".replace("$row", Integer.toString(row));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		WebElement deleteButton = driver.findElement(By.xpath(xpath));
		deleteButton.click();
	}


	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
		buttonLogin.click();
	}
}
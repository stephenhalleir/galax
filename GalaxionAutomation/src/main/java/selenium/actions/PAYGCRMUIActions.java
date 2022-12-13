package selenium.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.time.WaitUtil;

public class PAYGCRMUIActions {

	/**
	 * Wait for the URL in the driver to end with a particular string
	 * @param driver
	 * @param urlEnding
	 * @return true/false
	 */
	public static boolean waitForURL(WebDriver driver, String urlEnding) {
		
		long endTime = System.currentTimeMillis() + 000;
		
		while(System.currentTimeMillis() < endTime && driver.getCurrentUrl().endsWith(urlEnding)) {
			WaitUtil.waitForSeconds(1);
		}
		
		return driver.getCurrentUrl().endsWith(urlEnding);
	}
	

	/**
	 * Read the text from an element in the CRM UI (using the 'value' attribute)
	 * @param driver
	 * @param element
	 * @return the string value
	 */
	public static String getText(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getAttribute("value");
	}
	
	/**
	 * Replace the value in a text field by doing CTRL+A and then typing the new string
	 * @param element
	 * @param input
	 */
	public static void replaceValue(WebElement element,String input) {
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(input);
	}
	
	public static void enterValue(WebDriver driver, WebElement element, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));	
		element.sendKeys(value);
	}
	
	public static void jsClick(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void actionsClick(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		WaitUtil.waitForSeconds(1);
	}
	
	public static void selectDDValue(WebDriver driver,WebElement element, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));	
		element.click();
		
		String xpath="//li[contains(text(),'$value')]";
		xpath = xpath.replace("$value", value);
		
		WebElement valueItem = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.elementToBeClickable(valueItem));
		valueItem.click();
		
		WaitUtil.waitForSeconds(1);
	}
	
	public static void click(WebDriver driver,String text) {
		String xpath="//*[contains(text(),'$text')]";
		xpath = xpath.replace("$text", text);
		WebElement element = driver.findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));	
		element.click();
	}
}

package selenium.pages.eir_ie;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.generic.database.MariaDBConnection;

public class EirOnlineConfirmationPage {
	
	private WebDriver driver;
	public WebDriverWait wait;

	// Locators
	@FindBy(xpath="//h1[contains(text(), 'Thank you for your order')]")
	private WebElement confirmationMessage;
	
	@FindBy(xpath="//h1[contains(text(), 'Thank you for your order')]//following::span")
	private WebElement lblOrderNumber;

	// Constructor
	public EirOnlineConfirmationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		PageFactory.initElements(driver, this);
	}
	
	public String getConfirmationMessage() {
		wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
		return confirmationMessage.getText();
	}
	
	public String getOrderNumber() {
		wait.until(ExpectedConditions.visibilityOf(lblOrderNumber));
		return lblOrderNumber.getText();
	}
}
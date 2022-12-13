package selenium.pages.eir_b2b.acquisitions.customer_care;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.actions.PAYGCRMUIActions;
import utilities.generic.time.WaitUtil;

public class B2BCareDocumentsScreen {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//span[contains(text(),'Add Document')]")
	private WebElement btnAddDocument;
	
	@FindBy(id="type-label")
	private WebElement ddDocType;
	
	@FindBy(name="documentFile")
	private WebElement btnUpload;
	
	// Constructor
	public B2BCareDocumentsScreen(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddDocument));	
	}
	
	public void clickAddDocument() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddDocument));	
		btnAddDocument.click();
	}
	
	public void clickDocTypeDropdown() {

		PAYGCRMUIActions.actionsClick(driver, ddDocType);
		
		WebElement h1=driver.findElement(By.xpath("//h1[contains(text(),'Add Document')]"));
		System.out.println("*"+h1.getText());

		WebElement element = driver.findElement(By.xpath("//h1[contains(text(),'Add Document')]//following::*[@id=\"type-label\"]"));
		PAYGCRMUIActions.actionsClick(driver, element);
		
		WebElement choice = driver.findElement(By.xpath("//*[@id=\"menu-type\"]/div[3]/ul/li[2]"));
		choice.click();
		
		WaitUtil.waitForSeconds(10);
		/*
		WebElement docTypeDD = driver.findElement(By.xpath("//*[@id=\"type\"]"));
		wait.until(ExpectedConditions.visibilityOf(docTypeDD));	
		docTypeDD.click();
		
		driver.findElement(By.id("upload-button")).clear();
	    driver.findElement(By.id("upload-button")).sendKeys("D:\\Users\\stephenhall\\Desktop\\retest_2152_bill.pdf");
	    driver.findElement(By.xpath("//button[2]/span/p")).click();
	    */
	}
	
	public void clickUploadButton() {
		wait.until(ExpectedConditions.visibilityOf(btnUpload));	
		btnUpload.click();
	}
}
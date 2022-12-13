package selenium.pages.gomo.csr_ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSRNDDPage extends CSRBasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath="//h3[contains(text(),'USAGE')]")
	public WebElement tabUsage;
	
	@FindBy(xpath="//h3[contains(text(),'ADD-ON HISTORY')]")
	public WebElement tabAddOnHistory;
	
	@FindBy(xpath="//h3[contains(text(),'SIM/MOBILE BARRING')]")
	public WebElement tabSimMobileBarring;
	
	@FindBy(xpath="//h3[contains(text(),'NDD')]")
	public WebElement tabNDD;
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	public WebElement btnSaveNDD;
	
	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public CSRNDDPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	// select an NDD preference from the radio button set
	public void selectNDD(String nddPreference) {
		String xpath="//input[@value='$ndd']".replace("$ndd", nddPreference);;
		WebElement radioBtn=driver.findElement(By.xpath(xpath));
		radioBtn.click();
	}
	
	public String getSelectedNDDValue() {
		
		String[] nddSettings= {"LISTED","UNLISTED","EXDIRECTORY"};
		
		for(int i=0;i<nddSettings.length;i++) {
			String xpath="//input[@value='$ndd']".replace("$ndd", nddSettings[i]);
			WebElement radioBtn=driver.findElement(By.xpath(xpath));
			if(radioBtn.isSelected()) {
				return nddSettings[i];
			}
		}
		return null;
	}
	
	// click the Save button to save NDD changes
	public void clickSaveNDD() {
		btnSaveNDD.click();
	}

	public static void main(String[] args) {

	}
}

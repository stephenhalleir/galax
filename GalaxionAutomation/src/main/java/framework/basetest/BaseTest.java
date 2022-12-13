package framework.basetest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import framework.helpers.SeleniumHelper;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utilities.generic.database.MariaDBConnection;

@Listeners({ framework.basetest.TestListener.class })
public class BaseTest {

	public WebDriver driver;

	// Initialize loggers
	public static ExtentTest extentLogger;
	public static Logger logger4j;
	public static MariaDBConnection db;

	public void log(String s) {
		logger4j.info(s);
	}

	public void logInfo(String s) {
		if (logger4j != null && extentLogger != null) {
			logger4j.info(s);
			extentLogger.info(s.replace("\n", "<br>"));
		}
	}

	public void logPass(String s) {
		if (logger4j != null && extentLogger != null) {
			logger4j.info(s);
			extentLogger.pass(s.replace("\n", "<br>"));
		}
	}

	@BeforeMethod
	public void setUpMethod() {
		Thread.currentThread();
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				SeleniumHelper.captureScreenshot(driver);
			} catch (Exception e) {
			}
		}
	}

	@BeforeSuite
	public void setUpSuite() {
		// set Chromedriver property
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");
		logger4j = Logger.getLogger("");
		PropertyConfigurator.configure("res/config/Log4j.properties");
	}

	@AfterSuite
	public void afterSuite() {

		// kill chromedriver on program shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "Shutdown-thread"));
	}

	// save screenshot
	public void saveFullPageScreenshot() {

		log("Saving full page screenshot on " + driver.getCurrentUrl());

		// Take Screenshot of entire page by AShot
		Screenshot entirePageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(driver);

		try {
			File file = new File("results\\screenshots\\fullimage" + System.currentTimeMillis() + ".jpg");
			ImageIO.write(entirePageScreenShot.getImage(), "jpg", file);

			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String encodedString = Base64.getEncoder().encodeToString(fileContent);

			extentLogger.addScreenCaptureFromBase64String(encodedString);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void BeforeMethod(Method method, Object[] testData, ITestContext ctx) {
		
		// if test data is provided through as data provider, set the details in the iTestContext object
		if (testData.length > 0) {
			ctx.setAttribute("testData", testData[0]);
		}
	}
	
	@BeforeMethod
	public void BeforeMethod(Method method, ITestContext ctx, Object[] testData) {
		
		// if test data is provided through as data provider, set the details in the iTestContext object
		if (testData.length > 0) {
			ctx.setAttribute("testData", testData[0]);
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
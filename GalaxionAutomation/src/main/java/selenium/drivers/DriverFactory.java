package selenium.drivers;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utilities.config.ConfigReader;

public class DriverFactory {

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/res/chromedriver.exe");
		
		String driverType=ConfigReader.getConfigValue("DRIVER_TYPE");
		System.out.println("Creating a WebDriver of type " + driverType);
		
		if(driverType.equals("CHROME_DRIVER")) {
			return new ChromeDriver();
		}
		else if(driverType.equals("HEADLESS")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			return new ChromeDriver(options);
		}
		
		return null;
	}
	
	public static void killAllChromedrivers() {
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
}

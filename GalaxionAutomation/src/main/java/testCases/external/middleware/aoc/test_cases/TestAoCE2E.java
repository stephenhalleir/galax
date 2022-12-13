package testCases.external.middleware.aoc.test_cases;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.ec20.components.OCSOffer;
import external_systems.mobile_network.nodes.ec20.profile.EC20Profile;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import framework.basetest.BaseTest;
import framework.helpers.SeleniumHelper;
import framework.testNG.Retry;
import selenium.pages.aoc.AoCAcceptTermsPageBands1and6;
import selenium.pages.aoc.AoCAcceptTermsPageBands2to5;
import selenium.pages.aoc.AoCBand1AddonPurchasePage;
import selenium.pages.aoc.AoCInitialPage;
import testCases.external.middleware.aoc.test_objects.TestObjectAoC;
import utilities.generic.files.ExcelDataProvider;

public class TestAoCE2E extends BaseTest {

	@BeforeClass
	public void setUp() {
		
		// set up the ChromeDriver object
		System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='70%'");

		// use data from the DB to build the contact object
		//contact = new Contact(billingAccountNumber);
		//contact.retrieveDetailsFromDB();
		//System.out.println("Customer info: billing_account_number = " + billingAccountNumber + ", email = " + contact.getEmail());
	}

	
	@Test(enabled=true,description = "Test AoC", retryAnalyzer = Retry.class)
	public void testAoC() {
		
		int band=1;
		
		String msisdn="0853190041";//0852690127
		
		this.goToAocPage(msisdn,"Initial",band);
		waitForSeconds(2);
		AoCInitialPage aocInitial=new AoCInitialPage(driver);
		aocInitial.clickFindOutMore();
		waitForSeconds(2);
		
		if(band==2 || band ==3 || band==4 || band==5) {
			AoCAcceptTermsPageBands2to5 acceptPage = new AoCAcceptTermsPageBands2to5(driver);
			acceptPage.clickAcceptTerms();
			//acceptPage.selectBundlePurchase();
		}
		else if(band==1 || band==6) {
			AoCAcceptTermsPageBands1and6 acceptPage = new AoCAcceptTermsPageBands1and6(driver);
			acceptPage.clickAcceptTerms();
			
			/*
			acceptPage.selectBundlePurchase();
			waitForSeconds(2);
			acceptPage.clickBundle("1GB");
			acceptPage.clickBundle("500MB €4.99 Booster");
			acceptPage.clickBundle("7.5GB");
			acceptPage.clickBundle("500MB €4.99 every 4 weeks");
			acceptPage.clickBuyAddOn();
			
			AoCBand1AddonPurchasePage addonPage = new AoCBand1AddonPurchasePage(driver);
			addonPage.clickBuyAddOn();
			*/
		}
		
		waitForSeconds(5);
		
		EC20Profile ocsProfile = MMWUtility.getEC20Profile(msisdn);
		
		for(OCSOffer offer:ocsProfile.getOffers()) {
			System.out.println(offer.getOfferName());
		}
		
		waitForSeconds(50);
	}
	
	
	@Test(enabled=true,dataProvider="aocTests",description = "Test AoC > Data Driven", retryAnalyzer = Retry.class)
	public void testAoCDataDriven(TestObjectAoC test) {
		
		extentLogger.info("Test: " + test.getTestName());

		this.goToAocPage(test.getMsisdn(),"Initial",test.getBand());
		waitForSeconds(2);
		AoCInitialPage aocInitial=new AoCInitialPage(driver);
		extentLogger.pass("Page is opened at " + driver.getCurrentUrl());
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		aocInitial.clickFindOutMore();
		
		waitForSeconds(2);
		extentLogger.pass("Customer clicked 'Find out more'");
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		if(test.getBand()==2 || test.getBand() ==3 || test.getBand()==4 || test.getBand()==5) {
			AoCAcceptTermsPageBands2to5 acceptPage = new AoCAcceptTermsPageBands2to5(driver);
			
			if(test.getAction().equalsIgnoreCase("ACCEPT_TERMS")) {
				acceptPage.clickAcceptTerms();
				extentLogger.pass("Customer clicked 'Accept Terms'");
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
			}
			else if(test.getAction().equalsIgnoreCase("BUNDLE_PURCHASE")) {
				acceptPage.selectBundlePurchase();
				extentLogger.pass("Customer clicked 'Bundle Purchase'");
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
			}
		}
		else if(test.getBand()==1 || test.getBand()==6) {
			AoCAcceptTermsPageBands1and6 acceptPage = new AoCAcceptTermsPageBands1and6(driver);
			extentLogger.pass("Customer is on the AoC page");
			SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);

			if(test.getAction().equalsIgnoreCase("ACCEPT_TERMS")) {
				acceptPage.clickAcceptTerms();
				extentLogger.pass("Customer clicked 'Accept Terms'");
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
			}
			else if(test.getAction().equalsIgnoreCase("BUNDLE_PURCHASE")) {
				acceptPage.selectBundlePurchase();
				waitForSeconds(1);
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
				
				acceptPage.clickBundle(test.getBundleName());
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
				
				acceptPage.clickBuyAddOn();
				waitForSeconds(2);
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
				
				AoCBand1AddonPurchasePage addonPage = new AoCBand1AddonPurchasePage(driver);
				addonPage.clickBuyAddOn();
				SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
			}
		}
		
		waitForSeconds(5);
		
		EC20Profile ocsProfile = MMWUtility.getEC20Profile(test.getMsisdn());
		
		for(OCSOffer offer:ocsProfile.getOffers()) {
			System.out.println(offer.getOfferName());
		}
		
		waitForSeconds(5);
	}
	
	public void goToAocPage(String msisdn, String notificationType, int band) {
		
		if(msisdn.startsWith("08")) {
			msisdn=msisdn.replaceFirst("08", "3538");
		}
		
		// determine the subscriber type from the SPR (via MMW)
		SPRProfile sprProfile = MMWUtility.getSprProfile(msisdn);
		
		String url="http://aoctest/PacketCoreAOC/AOCControllerServlet?MSISDN=$msisdn&NotificationType=$notificationType&Band=$band&SubscriberType=$subscriberType";
		url=url.replace("$msisdn", msisdn);
		url=url.replace("$notificationType", notificationType);
		url=url.replace("$band", Integer.toString(band));
		url=url.replace("$subscriberType", sprProfile.getSubscriberType());
		System.out.println(url);
		extentLogger.info("Customer goes to URL: " + url);
		driver.get(url);
	}
	
	
	public ArrayList<TestObjectAoC> getAoCTestsFromSheet() {
		ArrayList<TestObjectAoC> tests = new ArrayList<TestObjectAoC>();

		String sheetName = "AoCTests";
		ExcelDataProvider excel = new ExcelDataProvider("D:\\Users\\stephenhall\\Desktop\\PrepayOrders.xlsx");

		// for each order on the sheet
		for (int i = 1; i < excel.getRowCount(sheetName); i++) {
			TestObjectAoC test = new TestObjectAoC();

			String inScope = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "In Scope"));

			if (inScope.equals("Yes")) {

				// read the service from the sheet
				test.setTestName(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Test Name")));
				test.setAction(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Action")));
				test.setBundleName(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Bundle")));
				test.setMsisdn(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "MSISDN")));
				String reprovision = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Reprovision"));
				String topupAmount = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Topup Amount"));
				String band=excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Band"));
				test.setBand(Integer.parseInt(band));
				
				if(reprovision.equals("Yes")) {
					test.setReprovision(true);
				}
				
				if(!topupAmount.equals("")) {
					test.setTopupAmount(Integer.parseInt(topupAmount));
				}
				
				tests.add(test);
				System.out.println("Adding test " + test.getTestName());
			}
		}

		return tests;
	}
	
	@DataProvider(name = "aocTests")
	public Object[][] getAoCTestsArray() {

		// read in the list of orders from the sheet
		ArrayList<TestObjectAoC> orders = getAoCTestsFromSheet();
		System.out.println("Orders = " + orders.size());
		Object[][] data = new Object[orders.size()][1];

		for (int i = 0; i < orders.size(); i++) {
			data[i] = new Object[] { orders.get(i) };
		}

		return data;
	}
	
	
	// wait for x seconds
	public void waitForSeconds(int sec) {
		try {
			
			Thread.sleep(sec * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	@AfterClass
	public void tearDown() {

		// close the driver
		driver.close();

		// kill any running chromedriver.exe processes
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

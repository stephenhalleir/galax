package testCases.eir_ie.ui;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.helpers.SeleniumHelper;
import selenium.pages.eir_ie.EirOnlineConfirmationPage;
import selenium.pages.eir_ie.EirOnlineCustomerDetails;
import selenium.pages.eir_ie.EirOnlineHandsetDetailsPage;
import selenium.pages.eir_ie.EirOnlineHandsetSelectionPage;
import selenium.pages.eir_ie.EirOnlineOfferSelectionPage;
import selenium.pages.eir_ie.EirOnlinePaymentPage;
import selenium.pages.eir_ie.EirOnlineReviewPage;
import testCases.testObjects.orders.TestObjectCRMOrder;
import testCases.testObjects.orders.TestObjectCRMOrderService;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.files.ExcelDataProvider;

public class TestCases_eir_ie extends BaseTest {

	ArrayList<String> orderNumbers = new ArrayList<String>();
	ArrayList<TestObjectCRMOrder> placedOrders = new ArrayList<TestObjectCRMOrder>();


	public TestCases_eir_ie() {

	}

	@Test (enabled=false,description="eir.ie > New Acquisition order",invocationCount=1)// (dataProvider = "order-quantities")
	public void testNewAcquisitionOrder(ITestContext iTestContext) {
		
		//WebDriver driver = DriverFactory.getInstance().getDriver(BrowserType.CHROME);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		extentLogger.info("Customer opens a browser and goes to the eir.ie");
		driver.get(EnvironmentDirectory.getEirIEUrl());
		
		EirOnlineOfferSelectionPage selectionPage = new EirOnlineOfferSelectionPage(driver);
		
		selectionPage.clickIncludePhone();
		selectionPage.clickPlanOnly();
		
		selectionPage.selectOffer("Simply Unlimited Data and Any Network Calls");
		//selectionPage.selectOffer("Simply Unlimited Data and Any Network Texts");
		//selectionPage.selectOffer("Simply Unlimited Data and Any Net Calls+Texts");
		//selectionPage.selectOffer(service.getSubscriptionOffer());
		//selectionPage.selectOffer1();
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		EirOnlineReviewPage reviewPage = new EirOnlineReviewPage(driver);
		reviewPage.selectTopup("€0");
		reviewPage.clickContinue();
		
		EirOnlineCustomerDetails detailsPage = new EirOnlineCustomerDetails(driver);
		detailsPage.enterFirstName("Steve");
		detailsPage.enterLastName("Test");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = simpleDateFormat.format(new Date());
		String email = "Steve.Auto-Online_" + date + "@eir.ie";
		System.out.println("Email: " + email);

		detailsPage.enterEmail(email);
		detailsPage.enterContactNumber("0851234567");
		detailsPage.enterDOB("10101981");
		detailsPage.selectConfirmBillingEircode("D24YX53");
		extentLogger.pass("Customer details entered: " + email);

		detailsPage.selectSecurityQuestion("Memorable date");
		detailsPage.enterResponse("this is my response");

		detailsPage.acceptTerms();
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		detailsPage.clickNext();
		
		EirOnlinePaymentPage paymentPage = new EirOnlinePaymentPage(driver);
		paymentPage.enterDetailsAndPay("4263970000005262", "1222", "222", "Steve Test");
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		EirOnlineConfirmationPage confirmationPage = new EirOnlineConfirmationPage(driver);
		System.err.println(confirmationPage.getConfirmationMessage());
		
		assertEquals(confirmationPage.getConfirmationMessage(),"Thank you for your order");
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		driver.close();
	}
	
	
	@Test (enabled=true,dataProvider = "excelOrders",description="eir.ie > New Acquisition order",invocationCount=1)// (dataProvider = "order-quantities")
	public void testNewAcquisitionOrderDataDriven(ITestContext iTestContext, TestObjectCRMOrder order) {
		
		TestObjectCRMOrderService service = order.getServices().get(0);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		extentLogger.info("Customer opens a browser and goes to the eir.ie");
		driver.get(EnvironmentDirectory.getEirIEUrl());
		
		EirOnlineOfferSelectionPage selectionPage = new EirOnlineOfferSelectionPage(driver);
		
		// select the relevant radio button to indicate whether or not a handset is needed
		if(service.getHandset()==null) {
			selectionPage.clickPlanOnly();
		}
		else {
			selectionPage.clickIncludePhone();
		}
		
		// select the subscription offer
		selectionPage.selectOffer(service.getSubscriptionOffer());
		
		waitForSeconds(10);
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		// if a handset is needed
		if(service.getHandset()!=null) {
			
			// handset selection
			EirOnlineHandsetSelectionPage handsetPage = new EirOnlineHandsetSelectionPage(driver);
			handsetPage.selectHandset(service.getHandset());
			
			EirOnlineHandsetDetailsPage handsetDetailsPage = new EirOnlineHandsetDetailsPage(driver);
			handsetDetailsPage.selectNew();
			
			waitForSeconds(5);
		}
		
		EirOnlineReviewPage reviewPage = new EirOnlineReviewPage(driver);
		
		// if there is a top-up on the service, select it
		if(service.getTopupAmount()!=null && !service.getTopupAmount().equals("")) {
			reviewPage.selectTopup(service.getTopupAmount());
		}
		
		reviewPage.clickContinue();
		
		EirOnlineCustomerDetails detailsPage = new EirOnlineCustomerDetails(driver);

		// if the customer is anonymous, select the Guest radio button
		if(!order.isRegistered()) {
			detailsPage.selectRegistered(false);
		}
		
		// generate a unique email address
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = simpleDateFormat.format(new Date());
		String email = "Steve.Auto-"+order.getChannel() +"_" + date + "@eir.ie";
		email=email.toLowerCase();
		System.out.println("Email: " + email);

		// enter the mandatory fields
		detailsPage.enterEmail(email);
		detailsPage.enterFirstName("Steve");
		detailsPage.enterLastName("Test");
		detailsPage.enterContactNumber("0851234567");
		extentLogger.pass("Customer details entered: " + email);
		
		// if the customer is registering, enter the optional details
		if(order.isRegistered()) {
			detailsPage.enterDOB("10101981");
			detailsPage.selectSecurityQuestion("Memorable date");
			detailsPage.enterResponse("this is my response");
			detailsPage.selectConfirmBillingEircode("D24YX53");
		}
		else{
			detailsPage.selectConfirmDeliveryEircode("D24YX53");
		}
		
		detailsPage.acceptTerms();
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		detailsPage.clickNext();
		
		EirOnlinePaymentPage paymentPage = new EirOnlinePaymentPage(driver);
		paymentPage.enterDetailsAndPay("4263970000005262", "1222", "222", "Steve Test");
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		EirOnlineConfirmationPage confirmationPage = new EirOnlineConfirmationPage(driver);
		System.err.println(confirmationPage.getConfirmationMessage());
		System.err.println(confirmationPage.getOrderNumber());
		
		assertEquals(confirmationPage.getConfirmationMessage(),"Thank you for your order");
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		driver.close();
	}
	
	
	@Test (enabled=false,description="eir.ie > New Acquisition order with handset",invocationCount=1)// (dataProvider = "order-quantities")
	public void testOrderWithHandset(ITestContext iTestContext) {

		//WebDriver driver = DriverFactory.getInstance().getDriver(BrowserType.CHROME);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		extentLogger.info("Customer opens a browser and goes to the eir.ie");
		driver.get(EnvironmentDirectory.getEirIEUrl());
		
		EirOnlineOfferSelectionPage selectionPage = new EirOnlineOfferSelectionPage(driver);
		
		selectionPage.clickIncludePhone();
		
		selectionPage.selectOffer1();
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		// handset selection
		EirOnlineHandsetSelectionPage handsetPage = new EirOnlineHandsetSelectionPage(driver);
		handsetPage.selectHandset("iPhone SE 2020");
		
		EirOnlineHandsetDetailsPage handsetDetailsPage = new EirOnlineHandsetDetailsPage(driver);
		handsetDetailsPage.selectNew();
		
		EirOnlineReviewPage reviewPage = new EirOnlineReviewPage(driver);
		reviewPage.selectTopup("€0");
		reviewPage.clickContinue();
		
		EirOnlineCustomerDetails detailsPage = new EirOnlineCustomerDetails(driver);
		detailsPage.enterFirstName("Steve");
		detailsPage.enterLastName("Test");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = simpleDateFormat.format(new Date());
		String email = "Steve.Auto-Online_" + date + "@eir.ie";
		System.out.println("Email: " + email);

		detailsPage.enterEmail(email);
		detailsPage.enterContactNumber("0851234567");
		detailsPage.enterDOB("10101981");
		detailsPage.selectConfirmBillingEircode("D24YX53");
		extentLogger.pass("Customer details entered: " + email);

		detailsPage.selectSecurityQuestion("Memorable date");
		detailsPage.enterResponse("this is my response");

		detailsPage.acceptTerms();
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		detailsPage.clickNext();
		
		EirOnlinePaymentPage paymentPage = new EirOnlinePaymentPage(driver);
		paymentPage.enterDetailsAndPay("4263970000005262", "1222", "222", "Steve Test");
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		EirOnlineConfirmationPage confirmationPage = new EirOnlineConfirmationPage(driver);
		System.err.println(confirmationPage.getConfirmationMessage());
		
		assertEquals(confirmationPage.getConfirmationMessage(),"Thank you for your order");
		
		SeleniumHelper.saveScreenshotToExtentReport(driver, extentLogger);
		
		
		driver.close();
	}
	
	public ArrayList<TestObjectCRMOrder> getOrdersFromSheet() {
		ArrayList<TestObjectCRMOrder> orders = new ArrayList<TestObjectCRMOrder>();

		String sheetName = "OnlineOrders";
		ExcelDataProvider excel = new ExcelDataProvider("D:\\Users\\stephenhall\\Desktop\\PrepayOrders.xlsx");

		// for each order on the sheet
		for (int i = 1; i < excel.getRowCount(sheetName); i++) {
			TestObjectCRMOrderService service = new TestObjectCRMOrderService();

			String inScope = excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "In Scope"));

			if (inScope.equals("Yes")) {

				// read the service from the sheet
				service.setOrderName(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Order Name")));
				service.setDescription(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Order Description")));
				service.setOfferName(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Offer")));
				service.setNddSetting(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Directory Preference")));
				service.setSubscriptionOffer(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Subscription Offers")));
				service.setTopupOffer(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Topup Offers")));
				service.setAddon(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Addons")));
				service.setHandset(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Handset")));
				service.setTopupAmount(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Topup Amount")));

				boolean orderExists = false;

				// if an order exists with the current order number, add the service to the
				// existing order
				// add the service to an order
				for (TestObjectCRMOrder order : orders) {

					if (order.getOrderName().equals(service.getOrderName())) {
						orderExists = true;
						order.add(service);
						break;
					}
				}

				// if no order exists with the current order number, create a new order and add
				// to the list
				if (!orderExists) {
					TestObjectCRMOrder newOrder = new TestObjectCRMOrder();
					newOrder.setOrderName(service.getOrderName());
					newOrder.setChannel(excel.getCellStringData(sheetName, i, excel.getColumnID(sheetName, "Channel")));
					newOrder.add(service);
					orders.add(newOrder);
				}
			}
		}

		for (TestObjectCRMOrder o : orders) {
			System.out.println("Order " + o.getOrderName() + " has services x " + o.getServices().size());
		}

		return orders;
	}

	
	@DataProvider(name = "excelOrders")
	public Object[][] getOrdersArray() {

		// read in the list of orders from the sheet
		ArrayList<TestObjectCRMOrder> orders = getOrdersFromSheet();

		Object[][] data = new Object[orders.size()][1];

		for (int i = 0; i < orders.size(); i++) {
			data[i] = new Object[] { orders.get(i) };
		}
		System.out.println(data.length + " orders returned.");
		return data;
	}

	
	@DataProvider(name = "placedOrders")
	public Object[][] getPlacedOrders(){
		System.out.println("Returning placed orders x " + placedOrders.size());
		Object[][] data = new Object[placedOrders.size()][1];

		for (int i = 0; i < placedOrders.size(); i++) {
			data[i] = new Object[] { placedOrders.get(i) };
		}

		return data;
	}
	
	
	@BeforeMethod
	public void setUp() {
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		// Close chromedriver
		//driver.close();
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}

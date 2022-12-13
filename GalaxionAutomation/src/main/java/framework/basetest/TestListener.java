package framework.basetest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import framework.extent_reporting.ExtentManager;
import utilities.config.ConfigReader;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.EnvironmentSnapshot;
import utilities.generic.files.TextReader;
import utilities.generic.google.GMailSender;
import utilities.generic.time.Timestamp;

@Listeners({ ExtentITestListenerClassAdapter.class })

public class TestListener extends BaseTest implements ITestListener {
	
	// determine the test name to display in the extent report
	private static String getTestMethodName(ITestResult iTestResult) {
		
		String testName="";
		
		// if the test has a description, use it
		if (!iTestResult.getMethod().getDescription().equals("")) {
			testName = iTestResult.getMethod().getDescription();
		}
		// else if no description is set, use the method name
		else {
			testName = iTestResult.getMethod().getConstructorOrMethod().getName();
		}
		
		// string to catch the test_runner
		boolean isTestRunner=(""+iTestResult.getTestContext().getAttribute("testData")).contains("TestRunner");
		
		// if this is a data provider method, append the data
		if(iTestResult.getTestContext().getAttribute("testData") != null && !isTestRunner) {
			testName = testName + " [" + iTestResult.getTestContext().getAttribute("testData") + "]";
		}
	
		return testName;
	}

	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", driver);
	}

	public void onFinish(ITestContext iTestContext) {

		ExtentManager.setEndTime(new Date());
		// Do tear down operations for extentreports reporting!
//        logger.
//        ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		String suiteName=iTestContext.getSuite().getName();
		System.out.println("Run is finished: " + iTestContext.getSuite().getName());
		sendReportEmail(suiteName);
	}

	public void onTestStart(ITestResult iTestResult) {
		extentLogger = ExtentManager.getInstance().createTest(getTestMethodName(iTestResult));
		ExtentManager.addTest(getTestMethodName(iTestResult));
	}
	

	public void onTestSuccess(ITestResult iTestResult) {
		// ExtentReports log operation for passed tests.
		extentLogger.log(Status.PASS, "Test passed");
		System.out.println((String) iTestResult.getAttribute("description"));
	}

	public void onTestFailure(ITestResult iTestResult) {
		// Get driver from BaseTest and assign to local webDriver variable.
		extentLogger.info(iTestResult.getThrowable());
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getDriver();

		// Take base64Screenshot screenshot.
		String base64Screenshot = null;
		try {
			base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
			extentLogger.addScreenCaptureFromBase64String(base64Screenshot);
		} catch (NullPointerException e) {
			System.out.println("Driver not initialised, Screenshot not captured.");
		}

		// ExtentReports log and screenshot operations for failed tests.
		extentLogger.log(Status.FAIL, "Test Failed");

	}

	public void sendReportEmail(String suiteName) {
		
		// determine the name of the extent report file
		String filename = ExtentManager.getReportPath();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date startDate = ExtentManager.getStartTime();
		Date endDate = ExtentManager.getEndTime();

		// email the file for review
		String from = ConfigReader.getConfigValue("email_username");
		String pass = ConfigReader.getConfigValue("email_password");
		String[] to = ConfigReader.getConfigValue("email_recipients").split(";");
		String subject = ConfigReader.getConfigValue("email_subject").replace("$timestamp", formatter.format(startDate)).replace("$suiteName", suiteName);
		String bodyText = TextReader.getContent("templates/emails/extent_report_email.txt");


		// sub in the execution time and environment
		bodyText = bodyText.replace("$env", ConfigReader.getConfigValue("environment")).replace("$startTime", formatter.format(startDate)).replace("$endTime",
				formatter.format(endDate)).replace("$suiteName", suiteName);

		ArrayList<String> testList = ExtentManager.getTests();

		String testString = "";

		for (String test : testList) {
			testString = testString + test + "<br>";
		}

		bodyText = bodyText.replace("$tests", testString);
		
		// capture the list of release versions on the environment
		String versionsString="";
		
		try {
			String releaseWiki=EnvironmentDirectory.getEnvWikiUrl();
			ArrayList<String> versions = EnvironmentSnapshot.getReleaseVersions(releaseWiki);
			if(versions.size()>0) {
				for(String version:versions) {
					versionsString = versionsString + "<br>" + version;
				}
			}
			else {
				versionsString="[Environment snapshot unavailable]";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bodyText = bodyText.replace("$versions", versionsString.trim());
		TextReader.writeFile(bodyText, "files\\email_"+Timestamp.getCurrentTimestamp("yyyyMMddHHmmss") + ".html");

		GMailSender.sendFromGMail(from, pass, to, subject, bodyText, filename);
	}

	public void onTestSkipped(ITestResult iTestResult) {
		extentLogger.log(Status.SKIP, "Test Skipped");
	}
}
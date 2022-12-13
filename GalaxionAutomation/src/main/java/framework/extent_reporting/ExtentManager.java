package framework.extent_reporting;

import java.util.ArrayList;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import utilities.generic.time.Timestamp;

public class ExtentManager {
	
	private static ExtentReports report;
	private static String reportFileName = "Galaxion_Automation_Report_" + Timestamp.getCurrentTimestamp("yyyy-MM-dd_HH-mm-ms") + ".html";
	private static String reportPath = System.getProperty("user.dir") + "\\results\\extent_reports\\" + reportFileName;
	private static Date startTime;
	private static Date endTime;
	private static ArrayList<String> testList = new ArrayList<String>();

	public static ExtentReports getInstance() {
		if (report == null)
			createInstance();
		return report;
	}
	
	public static String getReportPath() {
		return reportPath;
	}
	
	public static void setEndTime(Date date) {
		endTime=date;
	}
	
	public static Date getEndTime() {
		return endTime;
	}
	
	public static void addTest(String testName) {
		testList.add(testName);
	}
	
	public static ArrayList<String> getTests() {
		return testList;
	}
	
	public static Date getStartTime() {
		return startTime;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		System.out.println("Extent report created at: " + reportPath);
		startTime = new Date();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
		/*
		 * htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		 * htmlReporter.config().setChartVisibilityOnOpen(true);
		 * htmlReporter.config().setTheme(Theme.STANDARD);
		 * htmlReporter.config().setDocumentTitle(fileName);
		 * htmlReporter.config().setEncoding("utf-8");
		 * htmlReporter.config().setReportName(fileName);
		 */
		report = new ExtentReports();
		report.attachReporter(htmlReporter);

		return report;
	}
}

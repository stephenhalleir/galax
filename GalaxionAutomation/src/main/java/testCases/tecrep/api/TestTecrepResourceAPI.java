package testCases.tecrep.api;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.tecrep.resource_management.dao.ResourceManagementDAO;
import microservices.backend.tecrep.resource_management.enums.Action;
import microservices.backend.tecrep.resource_management.utility.TecrepInventoryManagementAPI;
import testCases.testObjects.tecrep.resources.MsisdnLifecycleTransition;
import utilities.generic.api.APITransaction;
import utilities.generic.files.TextReader;

public class TestTecrepResourceAPI extends BaseTest {

	public int accountID;
	
	@Test(enabled = true, description = "Tecrep API > Resource Management > Msisdn Lifecycle", dataProvider = "msisdn-transitions", invocationCount = 1)
	public void testMsisdnLifecycle(String s,ITestContext iTestContext) {
		String number="353850000000";
		
		MsisdnLifecycleTransition transition = new MsisdnLifecycleTransition(s);
		
		ResourceManagementDAO.updateNumberStatus(number, transition.getStatusBefore());
		String status=ResourceManagementDAO.getNumber(number).getStatus();
		logInfo("Before test: Number " + number + " is in status " + status);
		
		APITransaction t = TecrepInventoryManagementAPI.setAction(number, Action.valueOf(transition.getAction()));
		
		logInfo("Making call to " + t.getUrl());
		
		status=ResourceManagementDAO.getNumber(number).getStatus();
		assertEquals(transition.getStatusAfter(),status);
		logPass("After test: Number " + number + " is in status " + status);
	}
	
	
	@DataProvider(name = "msisdn-transitions")
	public Iterator<String> getMsisdnTransitions() {
		ArrayList<MsisdnLifecycleTransition> transitions = new ArrayList<MsisdnLifecycleTransition>();
		
		ArrayList<String> tests = TextReader.getContentAsArrayList("data_drivers//mapping_files//tecrep_msisdn_lifecycle_transitions");
		
		return tests.iterator();
	}
	
	@BeforeMethod
	public void setUp() {
		
		
	}

	@AfterMethod
	public void tearDown() {

	}
}

package testCases.environment_acceptance.test_objects;

import java.util.ArrayList;

import utilities.generic.files.ExcelDataProvider;

public class EATDataProvider {

	private final static String sqlDirectory = System.getProperty("user.dir") + "/data_drivers/EAT_Spec.xlsx";

	public static ArrayList<ApiCheck> getTests() {
		
		ArrayList<ApiCheck> tests = new ArrayList<ApiCheck>();
		
		// link to the spreadsheet
		ExcelDataProvider excel = new ExcelDataProvider(sqlDirectory);

		// determine the number of rows & columns in the sheet
		int numRows = excel.getRowCount(0);
		System.out.println(numRows);

		// find the specified tariff row in the sheet
		for (int i = 1; i < numRows; i++) {
			ApiCheck apiTest = new ApiCheck();
			apiTest.setSystem(excel.getCellStringData(0, i, 0));
			apiTest.setDescription(excel.getCellStringData(0, i, 1));
			apiTest.setMethod(excel.getCellStringData(0, i, 3));
			apiTest.setUrl(excel.getCellStringData(0, i, 4));
			apiTest.setPayload(excel.getCellStringData(0, i, 5));
			apiTest.setExpectedStatusCode(Integer.parseInt(excel.getCellStringData(0, i, 6)));
			apiTest.setExpectedStatusCode(200);
			tests.add(apiTest);
		}
		return tests;
	}

	public static void main(String[] args) {
		EATDataProvider.getTests();
		System.out.println("Done");
	}
	
}

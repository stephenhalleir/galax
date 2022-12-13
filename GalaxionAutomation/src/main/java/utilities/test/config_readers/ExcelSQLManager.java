package utilities.test.config_readers;

import utilities.generic.files.ExcelDataProvider;

public class ExcelSQLManager {
	
	private final static String sqlDirectory = System.getProperty("user.dir") + "/maps/GalaxionSQLNew.xlsx";

	public static String getSQLQuery(String tab, String key) {
		
		// link to the spreadsheet
		ExcelDataProvider excel = new ExcelDataProvider(sqlDirectory);

		// determine the number of rows & columns in the sheet
		int numRows = excel.getRowCount(tab);

		// find the specified tariff row in the sheet
		for (int i = 0; i < numRows; i++) {
			if(excel.getCellStringData(tab, i, 0).equalsIgnoreCase(key)) {
				return excel.getCellStringData(tab, i, 2);
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		System.out.println(ExcelSQLManager.getSQLQuery("BULK", "GET_CHANGE_OFFER"));
	}

}

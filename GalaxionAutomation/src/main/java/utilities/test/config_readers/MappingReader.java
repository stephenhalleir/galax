package utilities.test.config_readers;

import utilities.generic.files.ExcelDataProvider;

public class MappingReader {
	
	private final static String mappingFile = System.getProperty("user.dir") + "/maps/GalaxionMappingDocument.xlsx";

	public static String getAPNName(int apnID) {
		
		// link to the spreadsheet
		ExcelDataProvider excel = new ExcelDataProvider(mappingFile);

		String tabName="APNs";
		
		// determine the number of rows & columns in the sheet
		int numRows = excel.getRowCount(tabName);

		// find the specified tariff row in the sheet
		for (int i = 1; i < numRows; i++) {
			if(excel.getCellNumericData(tabName, i, 0)==apnID) {
				return excel.getCellStringData(tabName, i, 1);
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		System.out.println(MappingReader.getAPNName(34));
	}

}

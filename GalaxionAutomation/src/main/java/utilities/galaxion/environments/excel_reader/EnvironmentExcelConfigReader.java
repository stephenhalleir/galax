package utilities.galaxion.environments.excel_reader;

import utilities.config.ConfigReader;
import utilities.generic.files.ExcelDataProvider;

public class EnvironmentExcelConfigReader {

	private static final String configFileDirectory = ConfigReader.getConfigValue("environment_sheet");
	private static final int galaxionTabIndex = 0;
	private static final int mmwTabIndex = 1;

	/**
	 * Read the name of the current/selected environment from the main config sheet
	 * @return the environment - e.g. "PERF" or "E2E"
	 */
	private static String getCurrentEnvironment() {
		return ConfigReader.getConfigValue("environment");
	}

	/**
	 * Read a config value from the Galaxion tab of the excel for a named environment
	 * @param env - e.g PERF
	 * @param path - the key from the spreadsheet (column A) - e.g. "UI.GOMO.ESHOP"
	 * @return the URL or config value
	 */
	private static String getGalaxionConfigValue(String env, String path) {
		return getConfigValue(env, path, galaxionTabIndex);
	}
	
	/**
	 * Read a config value from the Galaxion tab of the excel for the current environment
	 * @param path - the key from the spreadsheet (column A) - e.g. "UI.GOMO.ESHOP"
	 * @return the URL or config value
	 */
	public static String getGalaxionConfigValue(String path) {
		return getConfigValue(getCurrentEnvironment(), path, galaxionTabIndex);
	}
	
	/**
	 * Read a config value from the excel sheet
	 * @param env - e.g. PERF
	 * @param path - e.g. MARIADB.HOST
	 * @param tabID - e.g. the index of the tab on the sheet - e.g. 0 or 1
	 * @return the config value
	 */
	private static String getConfigValue(String env, String path, int tabID) {

		// link to the spreadsheet
		ExcelDataProvider excel = new ExcelDataProvider(configFileDirectory);

		// determine the number of rows & columns in the sheet
		int numRows = excel.getRowCount(tabID);
		int numCols = excel.getColCount(tabID);

		int selectedCol = -1;

		// search the top row of the sheet for the env name
		for (int i = 0; i < numCols; i++) {
			if (excel.getCellStringData(tabID, 0, i).equalsIgnoreCase(env)) {
				selectedCol=i;
				 break;
			}
		}
		
		// if no column is found with that environment name, print an error
		if(selectedCol==-1) {
			System.err.println("Error: Environment " + env + " not found in " + configFileDirectory);
			return null;
		}
		
		// check the rows for the config item
		for(int i=0;i<numRows;i++) {
			if (excel.getCellStringData(tabID, i, 0).equalsIgnoreCase(path)) {
				return excel.getCellStringData(tabID, i, selectedCol);
			}
		}
		
		// display an error
		System.err.println("Error: Setting " + env +  ", " + path + " not found in " + configFileDirectory);
		return "";
	}

	

	/**
	 * Retrieve an environment setting from the MMW tab of the selected MMW environment
	 * @param path
	 * @return the value
	 */
	public static String getMMWConfigValue(String path) {
		String mmwinstance=getGalaxionConfigValue(getCurrentEnvironment(), "MMW");
		return getConfigValue(mmwinstance, path, mmwTabIndex);
	}

	public static void main(String[] args) {



	}
}
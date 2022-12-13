package utilities.test.config_readers;
import microservices.backend.eir_catalog_core_backend.enums.AddonType;
import utilities.generic.files.ExcelDataProvider;

public class AddonConfigManager {

	private final String configMapDirectory = System.getProperty("user.dir") + "/config/OfferNetworkMapping.xlsx";

	public AddonConfigManager() {

	}

	/**
	 * Check the addons list and return the expected provisioning values
	 * e.g. [APN, mms.eir.ie]
	 */
	public AddonConfigPair getExpectedAddonValues(String addonCode) {

		// link to the spreadsheet
		ExcelDataProvider excel = new ExcelDataProvider(configMapDirectory);

		// determine the number of rows & columns in the sheet
		int numRows = excel.getRowCount(1);

		// for each row on the sheet
		for (int i = 0; i < numRows; i++) {
			
			// read the addon name
			String addon = excel.getCellStringData(1, i, 0);
			
			// if the name is a match...
			if (addon.equalsIgnoreCase(addonCode)) {
				
				// read the addon type and expected values
				String addonType=excel.getCellStringData(1, i, 1);
				String value=excel.getCellStringData(1, i, 2);
				
				// create and return the result object
				AddonConfigPair expectedValues = new AddonConfigPair(AddonType.valueOf(addonType),value);
				return expectedValues;
			}
		}
		
		return null;

	}


	public static void main(String[] args) {
		AddonConfigManager reader = new AddonConfigManager();
		System.out.println(reader.getExpectedAddonValues("APNIRISHRAIL").getAddonType() + reader.getExpectedAddonValues("APNIRISHRAIL").getValue());
	}

}

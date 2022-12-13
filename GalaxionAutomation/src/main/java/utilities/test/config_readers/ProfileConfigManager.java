package utilities.test.config_readers;

import external_systems.mobile_network.enums.NetworkNode;
import external_systems.mobile_network.enums.NetworkSetting;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import utilities.generic.files.ExcelDataProvider;

public class ProfileConfigManager {

	private final String configMapDirectory = System.getProperty("user.dir") + "/maps/OfferNetworkMapping.xlsx";
	private String[] settings;
	private String[] systems;
	private OfferCode offerCode;
	private String[] offerValues;

	public ProfileConfigManager(OfferCode offerCode) {
		this.offerCode=offerCode;
		this.populateArrays();
	}

	/**
	 * Look up the provisioning values spreadsheet and return the value based on an
	 * offer code, system and setting value
	 * 
	 * @param offerCode - Enum value containing the list of offers
	 * @param system    - Enum value containing the list of network nodes
	 * @param setting   - Enum value containing the list of network settings
	 * @return the expected provisioning value from the spreadsheet
	 */
	public String getProvisioningValue(NetworkNode system, NetworkSetting setting) {

		for(int i=0;i<settings.length;i++) {
			if(systems[i].equals(system.toString()) && settings[i].equals(setting.toString())) {
				return offerValues[i];
			}
		}

		return null;
	}

	/**
	 * Load the spreadsheet data into the arrays
	 */
	public void populateArrays() {
		
		// link to the spreadsheet
		ExcelDataProvider excel = new ExcelDataProvider(configMapDirectory);

		// determine the number of rows & columns in the sheet
		int numRows = excel.getRowCount(0);
		int numCols = excel.getColCount("offers");
		
		// initialize the arrays
		settings = new String[numCols];
		systems = new String[numCols];
		offerValues=new String[numCols];
		
		int selectedRow=-1;

		// find the specified tariff row in the sheet
		for (int i = 0; i < numRows; i++) {
			if(offerCode.toString().contains(excel.getCellStringData(0, i, 0))) {
				selectedRow=i;
				break;
			}
		}
		
		// populate the arrays with the data from the sheet
		for(int i=0;i<numCols;i++) {
			offerValues[i]=excel.getCellStringData(0, selectedRow, i);
			settings[i]=excel.getCellStringData(0, 1, i);
			systems[i]=excel.getCellStringData(0, 0, i);
			
			if(systems[i].equals("") && i>0) {
				systems[i]=systems[i-1];
			}
		}
	}

	public static void main(String[] args) {
		ProfileConfigManager reader = new ProfileConfigManager(OfferCode.GOMO2);
		System.out.println(reader.getProvisioningValue(NetworkNode.SPR, NetworkSetting.BILLING_SOURCE));
	}

}

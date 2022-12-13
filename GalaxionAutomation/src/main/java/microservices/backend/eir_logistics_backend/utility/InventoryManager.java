package microservices.backend.eir_logistics_backend.utility;

import java.util.ArrayList;

import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_inventory_management_backend.InventoryCode;
import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import microservices.backend.eir_logistics_backend.file_objects.ORDFile;
import microservices.backend.eir_logistics_backend.file_objects.ORDLineItem;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Item;
import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;
import utilities.generic.files.TextReader;
import utilities.generic.time.WaitUtil;

public class InventoryManager {

	/**
	 * Return a list of EquipmentPacks to fulfill a given ORD file
	 * 
	 * @param ordFile
	 * @return
	 */
	public static ArrayList<EquipmentPack> getPacksForOrd(ORDFile ordFile) {

		// create a new list to hold the equipment packs
		ArrayList<EquipmentPack> packs = new ArrayList<EquipmentPack>();

		// for each item in the ORD file
		for (ORDLineItem item : ordFile.getLineItems()) {

			// retrieve a pack from the google sheet
			EquipmentPack pack = getInventoryForOrderItemID(Integer.parseInt(item.getCaseId()));

			// add the pack to the list of packs
			packs.add(pack);
		}

		// return the list of SIM packs
		return packs;
	}

	/**
	 * Retrieve an EquipmentPack based on an order item ID
	 * 
	 * @param orderItemID
	 * @return
	 */
	public static EquipmentPack getInventoryForOrderItemID(int orderItemID) {

		// read the item object from order management based on the order item ID
		Item item = OrderManagementDAO.getItem(orderItemID);

		String equipmentCode = item.getCatalogItemCode();

		if (equipmentCode == null) {

			// get the item_id (link to the catalog)
			int catalogItemID = item.getItemId();

			// read the equipment_code from the catalog
			equipmentCode = CatalogCoreDAO.getSIMCardInventoryCodeByID(catalogItemID);
		}

		return getInventory(equipmentCode);

	}

	/**
	 * Retrieve an Equipment/SIM Pack from the relevant file on the current
	 * environment
	 * 
	 * @param inventoryCode - e.g. 0SIMUPREE
	 * @return a EquipmentPack object
	 */
	public static EquipmentPack getInventory(String inventoryCode) {

		String filename = null;
		
		if(inventoryCode==null) {
			System.err.println("Error: InventoryCode=null");
			return null;
		}

		// indicate the key value for the sheet
		String keyValue = "";
		
		// determine the file name based on the inventory code
		switch (inventoryCode) {
		case "0PREPROVISIONNED":
			filename = null;
			break;
		case "0SIMUMBBE":
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.PREPROVISIONED.MBB");
			keyValue="EIR.PREPROVISIONED.MBB";
			break;
		case "0SIMUPREE":
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.PREPROVISIONED.SIMPLICITY");
			keyValue="EIR.PREPROVISIONED.SIMPLICITY";
			break;
		case "1SIMBILLE":
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.UNPAIRED");
			keyValue="EIR.UNPAIRED";
			break;
		case "1SIMBIMBE":
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.UNPAIRED");
			keyValue="EIR.UNPAIRED";
			break;
		case "1SIMIONFT":
			keyValue="GOMO.PAIRED";
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("GOMO.PAIRED");
			break;
		case "2SIMIONRP":
			keyValue="GOMO.UNPAIRED";
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("GOMO.UNPAIRED");
			break;
		case "2SIMM2FAA":
			filename = null;
			break;
		case "2SIMREPBE":
			filename = null;
			break;
		case "2SIMREPEE":
			keyValue="EIR.UNPAIRED";
		//	filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.UNPAIRED");
			break;
		case "6SIMBILLE":
			filename = null;
			break;
		case "6SIMBUSLE":
			keyValue="EIR.PAIRED";
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.PAIRED");
			break;
		case "6SIMMBBEE":
			keyValue="EIR.PAIRED";
			//filename = EnvironmentExcelConfigReader.getMMWConfigValue("EIR.PAIRED");
			break;
		}
		
		// read the value of the "INVENTORY" value on the sheet to determine whether the env will use TECREP or MMW
		String inventorySystem = EnvironmentExcelConfigReader.getGalaxionConfigValue("INVENTORY");
		
		// if the environment is using Tecrep, read the file name from the Galaxion sheet
		if(inventorySystem.equals("TECREP")) {
			filename=EnvironmentExcelConfigReader.getGalaxionConfigValue(keyValue);
		}
		// else, read it from the MMW sheet
		else if(inventorySystem.equals("MMW")) {
			filename = EnvironmentExcelConfigReader.getMMWConfigValue(keyValue);
		}
		
		// confirm that a filename was determined for the inventory
		if (filename == null) {
			System.err.println("Error: InventoryManager cannot find a filename for InventoryCode " + inventoryCode);
		}
		
		System.out.println("Retrieving inventory from the file " + filename);
		return getInventoryFromFile(filename);
	}

	/**
	 * Retrieve an Equipment/SIM Pack from the relevant file on the current
	 * environment
	 * 
	 * @param inventoryCode - e.g. 0SIMUPREE
	 * @return a EquipmentPack object
	 */
	public static EquipmentPack getInventory(int itemId) {
		String inventoryCode = CatalogCoreDAO.getSimCardById(itemId).getInventoryCode();
		return getInventory(inventoryCode);
	}

	public static EquipmentPack getInventory(InventoryCode inventoryCode) {

		String code = inventoryCode.toString();

		if (code.startsWith("_")) {
			code = code.substring(1);
		}

		return getInventory(code);
	}

	/**
	 * Retrieve a piece of inventory from a named inventory file
	 * 
	 * @param filename
	 * @return EquipmentPack object
	 */
	public static EquipmentPack getInventoryFromFile(String filename) {
		System.err.println("InventoryManager retrieving inventory from file '" + filename + "'");
		return new EquipmentPack(TextReader.cutLineFromFile("files\\inventory\\" + filename));
	}
}

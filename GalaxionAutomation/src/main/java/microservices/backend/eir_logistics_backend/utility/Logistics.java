package microservices.backend.eir_logistics_backend.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import microservices.backend.eir_logistics_backend.enums.CatalogItemType;
import microservices.backend.eir_logistics_backend.file_objects.CNFFile;
import microservices.backend.eir_logistics_backend.file_objects.CNFLineItem;
import microservices.backend.eir_logistics_backend.file_objects.ORDFile;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Item;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import utilities.config.ConfigReader;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.galaxion.kubernetes.Kubernetes;
import utilities.generic.files.TextReader;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;
import utilities.generic.time.WaitUtil;
import utilities.rabbitmq.RabbitMQUtil;

public class Logistics {

	private Kubernetes kubernetes;
	private String ordPath;
	private static String ordName;
	private String cnfPath;
	private String workDirectory;
	private String outboundDirectory;
	private static String localDirectory = System.getProperty("user.dir") + "\\files\\logistics";

	public String getOrdPath() {
		return ordPath;
	}

	public static String getOrdName() {
		return ordName;
	}

	public String getCnfPath() {
		return cnfPath;
	}

	// constructor
	public Logistics(Kubernetes kubernetes) {
		this.kubernetes = kubernetes;

		File directory = new File(localDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	// constructor
	public Logistics() {
		this.kubernetes = new Kubernetes();

		File directory = new File(localDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	// return the name of the current Logistics pod
	public String getLogisticsPod() {
		return kubernetes.getRunningPod("eir-sftp-backend");
	}

	// Get shipping file for a specific order number work folder
	private ORDFile getORDFileFromServer(String orderReference) {

		workDirectory = ConfigReader.getConfigValue("ion_ord_file_work");
		outboundDirectory = ConfigReader.getConfigValue("ion_ord_file_outbound");

		if (orderReference == null || orderReference.equals("")) {
			System.out.println("Error: Logistics.getOrderShippingFile - orderReference is " + orderReference);
		}

		// grep in each directory for the order number
		String getFilenameCommand = "kubectl exec -it $pod -- /bin/grep -r -ls $orderNumber $directory";
		getFilenameCommand = getFilenameCommand.replace("$orderNumber", orderReference).trim().replace("$pod", getLogisticsPod());

		String fileName = null;

		long endTime = System.currentTimeMillis() + 60000;
		String directory = outboundDirectory;

		// wait for up to 1 minute to retrieve the ORD file name
		while (fileName == null && System.currentTimeMillis() < endTime) {
			fileName = kubernetes.getResponseFromServer(getFilenameCommand.replace("$directory", directory));
			System.out.println("Filename=" + fileName);

			// check the other directory next time
			if (directory.equals(outboundDirectory)) {
				directory = workDirectory;
			} else {
				directory = outboundDirectory;
			}

			WaitUtil.waitForSeconds(1);

		}

		// create the ORD file object
		ORDFile ordFile = new ORDFile();

		// if the file name is not found
		if (fileName == null) {
			System.out.println("ORD file not found");
			return null;
		} else {
			ordFile.setORDName(fileName);
			System.err.println("Order number " + orderReference + " found in file(s): " + fileName);
			String catCommand = "kubectl exec -it " + getLogisticsPod() + " -- /bin/cat $fileName";

			// cat the file on the server to retrieve the contents
			String fileContents = kubernetes.getResponseFromServer(catCommand.replace("$fileName", fileName)).trim();

			if (fileContents.equals("")) {
				fileContents = null;
			}

			ordFile.setContents(fileContents);

			return ordFile;
		}
	}

	/**
	 * Complete the logistics steps by reading the shipping file from the pod
	 * and generating and uploading the delivery file
	 * @param orderReference
	 * @return a LogisticsReport object
	 */
	public static LogisticsReport processLogisticsE2E(String orderReference) {

		LogisticsReport report = new LogisticsReport();
		Kubernetes kubernetes = new Kubernetes();
		Logistics logistics = new Logistics(kubernetes);

		// get the shipping file content for the order number
		ORDFile ordFile = logistics.getORDFileFromServer(orderReference);

		// save the ORD file locally
		TextReader.writeFile(ordFile.toString(), localDirectory + "\\" + ordFile.getORDName());

		// save the ORD file to the report object
		report.setOrdFile(ordFile);

		// get a list of SIM packs to fulfill the order
		ArrayList<EquipmentPack> packs = InventoryManager.getPacksForOrd(ordFile);

		// create the CNF file based on the ORD file and the corresponding inventory set
		CNFFile cnfFile = new CNFFile(ordFile, packs);
		report.setCnfFile(cnfFile);

		// determine the local directory for the CNF file
		String localCNFFilePath = localDirectory + "\\" + cnfFile.getCnfName();

		// save the CNF file
		TextReader.writeFile(cnfFile.toString(), localCNFFilePath);
		System.out.println(cnfFile.getCnfName() + ":\n" + cnfFile.toString());
		
		// upload the CNF file to the server
		IONFileUploader.uploadFile(localCNFFilePath, "eir-sftp-backend", ConfigReader.getConfigValue("ion_cnf_file"));

		System.out.println("CNF file sent to logistics");

		ArrayList<String> msisdns = new ArrayList<String>();

		// add the MSISDNs into the report/result object
		for (CNFLineItem item : cnfFile.getLineItems()) {
			msisdns.add(item.getMsisdn());
		}

		// add the msisdn list to the report
		report.setMsisdns(msisdns);

		return report;
	}

	/*
	 * Process the logistics steps for the order
	 */
	public static ArrayList<LogisticsDTO> processLogisticsBackend(String orderReference) {
		
		// get the list of DTOs belonging to the order
		ArrayList<LogisticsDTO> dtos = getLogisticsDTOs(orderReference);
		
		// send all DTOs to the queue "order.delivery_confirm"
		for(LogisticsDTO dto:dtos) {
			System.err.println(PojoToJsonConverter.getJSON(dto));
			
			if(!dto.getEquipmentType().equals("")) {
				postMessageToDeliveryQueue(dto);
			}
			else {
				System.out.println("Error in Logistics: DTO.equipment type is blank");
				return null;
			}
			
		}
		
		return dtos;
	}
	
	/**
	 * Generate a list of logistics messages for an order
	 * @param orderReference
	 * @return list of logistics messages that should get posted to the order.delivery_confirm queue
	 */
	public static ArrayList<LogisticsDTO> getLogisticsDTOs(String orderReference) {
		
		// get the list of equipment items linked the order
		ArrayList<Item> items = OrderManagementDAO.getEquipmentItemsForOrder(orderReference);
		ProductOrder order = OrderManagementDAO.getOrderByReference(orderReference);
		
		// create a map to hold the logistics messages
		HashMap<Integer, LogisticsDTO> dtoMap = new HashMap<Integer, LogisticsDTO>();
		
		// for each item
		for(Item item:items) {
			LogisticsDTO dto;
			
			System.out.println(item.getOrderOfferId() + ", " + item.getItemType());
			
			// Case: Standalone handset order
			//if(item.getOrderOfferId()==0 && (item.getItemType().equalsIgnoreCase("HANDSET"))) {
			if(item.getItemType().equalsIgnoreCase("HANDSET")) {
				dto = new LogisticsDTO(orderReference,item.getId());
				dto.setEquipmentType("Handset");
				dto.setImei("35" + System.currentTimeMillis());
				dtoMap.put(item.getId(), dto);
				WaitUtil.waitForMilliSeconds(1);
			}
			// Case: Standalone ACCESSORY order
			else if(item.getOrderOfferId()==0 && (item.getItemType().equalsIgnoreCase("ACCESSORY"))) {
				dto = new LogisticsDTO(orderReference,item.getId());
				dto.setEquipmentType("Accessory");
				dtoMap.put(item.getId(), dto);
			}
			// Case: Replacement SIM order
			else if(item.getItemType().equalsIgnoreCase("EQUIPMENT") && order.getOrder_type().equals("REPLACEMENT")){
				EquipmentPack pack = InventoryManager.getInventory(item.getCatalogItemCode());
				dto = new LogisticsDTO(orderReference,item.getId());
				dtoMap.put(item.getOrderOfferId(), dto);
				dto.setIccId(pack.getIccid());
				dto.setImsi(pack.getImsi());
				dto.setMsisdn("0000000000");
			}
			// Case: Acquisition orders (with/without handsets)
			else {
				
				// determine whether a DTO already exists for this offer
				dto=dtoMap.get(item.getOrderOfferId());
				
				// if there is no DTO in the map for this offer, create one
				if(dto==null) {
					dto = new LogisticsDTO(orderReference,item.getId());
					dtoMap.put(item.getOrderOfferId(), dto);
				}
				
				// determine the item type for the current item
				CatalogItemType itemType = CatalogCoreDAO.getCatalogItemType(item.getItemId());

				// if the current item type is a SIM card
				if (itemType == CatalogItemType.SIM_CARD) {
					
					// retrieve a pack from the inventory 
					EquipmentPack pack = InventoryManager.getInventory(item.getItemId());
					
					// enter the pack values into the DTO
					dto.setIccId(pack.getIccid());
					dto.setImsi(pack.getImsi());
					dto.setMsisdn(pack.getMsisdn());
				}
				// else if the current item is a handset
				else if (itemType == CatalogItemType.HANDSET) {
					dto.addHandset();
				}
			}
		}
		
        // return an arraylist of the map values
        return new ArrayList<>(dtoMap.values());
	} 
	
	
	 /**
	  * Post a logistics DTO to the 'order.delivery_confirm' queue
	  * @param dto
	  */
	public static void postMessageToDeliveryQueue(LogisticsDTO dto) {

		// post the message to the queue "order.delivery_confirm"
		RabbitMQUtil.publishMessage(PojoToJsonConverter.getJSON(dto), "order.delivery_confirm");
	}

}

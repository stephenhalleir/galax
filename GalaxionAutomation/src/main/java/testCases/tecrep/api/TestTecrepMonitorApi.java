package testCases.tecrep.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_logistics_backend.file_objects.DELFile;
import microservices.backend.tecrep.equipments_management.DBInventoryPool;
import microservices.backend.tecrep.equipments_management.dao.TecrepEquipmentsDAO;
import microservices.backend.tecrep.equipments_management.files.InpFile;
import microservices.backend.tecrep.equipments_management.utility.TecrepEquipmentsUtility;
import microservices.backend.tecrep.files.import_number_file.ImportNumberFile;
import microservices.backend.tecrep.files.import_number_file.ImportNumberRow;
import microservices.backend.tecrep.resource_management.dao.ResourceManagementDAO;
import microservices.backend.tecrep.stock_management.utility.DELGenerator;
import microservices.frontend.tecrep_monitor.api.TecrepMonitorAPI;
import microservices.frontend.tecrep_monitor.enums.InventoryPool;
import microservices.frontend.tecrep_monitor.enums.SimCardConfiguration;
import microservices.frontend.tecrep_monitor.responses.GetNumberBlockResponse;
import microservices.frontend.tecrep_monitor.utility.TecrepUtility;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.generic.api.APITransaction;
import utilities.generic.files.TextReader;

public class TestTecrepMonitorApi extends BaseTest {

	private String token;

	@Test(description = "Tecrep Monitor API: Authenticate")
	public void testLogin() {
		token = TecrepMonitorAPI.getToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1, 200) + "...");
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get resources dashboard")
	public void testGetResourcesDashboard(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getResourcesDashboard(token);
		assertEquals(201, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get equipments dashboard")
	public void testGetEquipmentsDashboard(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getEquipmentsDashboard(token);
		assertEquals(201, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get inventory pools")
	public void testGetInventoryPools(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getInventoryPools(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get numbers")
	public void testGetNumbers(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getInventoryPools(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get warehouses")
	public void testGetWarehouses(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getWarehouses(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get providers")
	public void testGetProviders(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getProviders(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get PLMNs")
	public void testGetPLMNs(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getPLMNs(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get SIM cards")
	public void testGetSimCards(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.getSimCards(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Generate unpaired SIM card batch")
	public void testGenerateUnpairedSimCardBatch(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.generateUnpairedSimCardBatch(token, InventoryPool.TEST_PAIRED_POOL, 1, SimCardConfiguration.TEST_SIM_PAIRED);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String filename = (String) jsonPathEvaluator.get("filename");
		logPass("File " + filename + " created");
	}

	@Test(dataProvider = "getAllInventoryPools", dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Generate unpaired SIM card batch")
	public void testGenerateUnpairedSimCardBatches(InventoryPool pool, ITestContext iTestContext) {

		// determine the config type to use based on the pool
		SimCardConfiguration config = TecrepUtility.getConfiguration(pool);

		// make the call to the API
		APITransaction t = TecrepMonitorAPI.generateUnpairedSimCardBatch(token, pool, 1, config);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String filename = (String) jsonPathEvaluator.get("filename");
		logPass("File " + filename + " created");
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Generate paired SIM card batch")
	public void testGeneratePairedSimCardBatch(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.generatePairedSimCardBatch(token, InventoryPool.METEOR_PAIRED_CUSTOMER_POOL, 1,
				SimCardConfiguration.METEOR_PAIR_ALL_IN_ONE, "850000102", "850000103");
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String filename = (String) jsonPathEvaluator.get("filename");
		logPass("File " + filename + " created");
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get an inp file")
	public void testGetInpFile(ITestContext iTestContext) {
		String inpFilename = "MMC00003.inp";
		APITransaction t = TecrepMonitorAPI.exportSimCardBatch(token, inpFilename);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Generate number block")
	public void testGenerateNumberBlock(ITestContext iTestContext) {
		APITransaction t = TecrepMonitorAPI.generateNumberBlock(token, "81", "353", "9", "0", "SYSTEM", false);
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());

		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		int blockId = (int) jsonPathEvaluator.get("blockId");

		logPass("Block ID " + blockId + " created");
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Get number block")
	public void testGetNumberBlock(ITestContext iTestContext) {
		int blockNumberId = 3;
		APITransaction t = TecrepMonitorAPI.getNumberBlock(token, blockNumberId);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Delete number block")
	public void testDeleteNumberBlock(ITestContext iTestContext) {
		int blockNumberId = 2;
		APITransaction t = TecrepMonitorAPI.deleteNumberBlock(token, blockNumberId);
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Create interval numbers")
	public void testCreateIntervalNumbers(ITestContext iTestContext) {

		String intervalStart = "353851001";
		String intervalEnd = "353851002";

		// determine the block for addition of the number
		int blockNumberId = 1;

		// get the block number response
		APITransaction t = TecrepMonitorAPI.getNumberBlock(token, blockNumberId);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// convert the block number response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetNumberBlockResponse blockNumberResponse = (GetNumberBlockResponse) jsonPathEvaluator.getObject("", GetNumberBlockResponse.class);
		System.err.println(blockNumberResponse.getBlockPrefix());

		// post the new interval number
		t = TecrepMonitorAPI.generateIntervalNumbers(token, intervalStart, intervalEnd, "MOBILE", blockNumberResponse);
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());

		jsonPathEvaluator = t.getResponse().jsonPath();
		int newIntervalId = (int) jsonPathEvaluator.get("intervalId");
		logPass("Interval " + newIntervalId + " created");
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Import SIM cards")
	public void testImportSimCards(ITestContext iTestContext) {
		
		//TODO make dynamic
		String filepath="files\\tecrep\\sim_manufacturing_files\\MMC00003.out";
		
		APITransaction t = TecrepMonitorAPI.importSimCardFile(token, filepath);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Add number")
	public void testAddNumber(ITestContext iTestContext) {

		String range = "83100";

		// get the last created number in the current specified range
		String currentMaxNumber = ResourceManagementDAO.getHighestMsisdnInRange(range);

		if (currentMaxNumber == null) {
			currentMaxNumber = (range + "0000000").substring(0, 9);
		}

		// determine the next available msisdn in the range
		String nextAvailableMsisdn = (new BigInteger(currentMaxNumber).add(new BigInteger("1")).toString());

		// make the API call
		APITransaction t = TecrepMonitorAPI.addNumber(token, nextAvailableMsisdn, "AVAILABLE", "GOLD");
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Import numbers")
	public void testImportNumbers(ITestContext iTestContext) {

		InventoryPool pool = InventoryPool.METEOR_PAIRED_CUSTOMER_POOL;

		// create the file object
		ImportNumberRow row1 = new ImportNumberRow("850000001", "available", "STANDARD", "", pool, "new row " + System.currentTimeMillis());
		ImportNumberRow row2 = new ImportNumberRow("850000002", "available", "STANDARD", "", pool, "new row " + System.currentTimeMillis());
		ImportNumberRow row3 = new ImportNumberRow("850000003", "available", "STANDARD", "", pool, "new row " + System.currentTimeMillis());
		ImportNumberFile file = new ImportNumberFile();
		file.getRows().add(row1);
		file.getRows().add(row2);
		file.getRows().add(row3);

		// write the xlsx file to the directory
		String filepath = file.writeToFile("files\\tecrep");

		// post it to the API
		APITransaction t = TecrepMonitorAPI.importNumbers(token, filepath);
		logPass(t.toString());

		// verify the response code
		assertEquals(200, t.getResponse().statusCode());
	}

	@Test(dependsOnMethods = "testLogin", description = "Tecrep Monitor API: Test end to end SIM card generation and processing")
	public void testCreateUnpairedSIMs(ITestContext iTestContext) {

		// specify the pool to be used
		InventoryPool pool = InventoryPool.METEOR_UNPAIRED_POOL;

		// determine the config type to use based on the pool
		SimCardConfiguration config = TecrepUtility.getConfiguration(pool);

		// make the call to the API to create the batch
		APITransaction t = TecrepMonitorAPI.generateUnpairedSimCardBatch(token, pool, 5, config);
		logInfo(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// determine the MMC0000XX.inp filename for the batch created
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String inpFilename = (String) jsonPathEvaluator.get("filename");

		// read in the details of the .inp file
		InpFile inpFile = TecrepEquipmentsUtility.getInpFileFromAPI(inpFilename);

		// generate a MMC0000XX.out file for the .inp file
		String s = TecrepEquipmentsUtility.generateOutFile(inpFilename);
		System.out.println(s);

		// upload the .out file to Tecrep
		t = TecrepMonitorAPI.uploadSimCardBatch(token, inpFile.getBatch(), s);
		System.out.println(t);

		// trigger the "PROCEED IMPORT" action to complete the upload
		t = TecrepMonitorAPI.proceedImport(token, inpFile.getBatch());
		System.out.println(t);
	}

	@Test(dependsOnMethods = "testLogin", dataProvider = "getPools", description = "Tecrep Monitor API: Create paired SIM & MSISDN batch")
	public void testCreatePairedInventory(InventoryPool pool, ITestContext iTestContext) {

		// specify the inventory pool, MSISDN range and batch size
		String range = "85001";
		int batchSize = 5;
		// InventoryPool pool = InventoryPool.TEST_PAIRED_POOL;

		logInfo("Creating " + batchSize + " paired packs in the pool " + pool + " in the range " + range + "x");

		// create an arraylist to hold the list of MSISDNs in the specified range
		ArrayList<String> msisdns = new ArrayList<String>();

		// get the last created number in the current specified range
		String currentMaxNumber = ResourceManagementDAO.getHighestMsisdnInRange(range);

		if (currentMaxNumber == null) {
			currentMaxNumber = (range + "0000000").substring(0, 9);
		}

		// determine the start and end msisdns
		String startMsisdn = (new BigInteger(currentMaxNumber).add(new BigInteger("1")).toString());
		String endMsisdn = (new BigInteger(startMsisdn).add(new BigInteger(Integer.toString(batchSize - 1))).toString());

		BigInteger current = new BigInteger(startMsisdn);

		// create the list of msisdns to be created
		while (current.compareTo(new BigInteger(endMsisdn)) <= 0) {
			msisdns.add(current.toString());
			current = current.add(new BigInteger("1"));
		}

		// specify the block number to use
		int blockNumberId = 1;

		// read the block number details
		APITransaction t = TecrepMonitorAPI.getNumberBlock(token, blockNumberId);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// convert the block number response into an object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		GetNumberBlockResponse blockNumberResponse = (GetNumberBlockResponse) jsonPathEvaluator.getObject("", GetNumberBlockResponse.class);
		System.err.println(blockNumberResponse.getBlockPrefix());

		// generate the interval numbers via the API
		t = TecrepMonitorAPI.generateIntervalNumbers(token, startMsisdn, endMsisdn, "MOBILE", blockNumberResponse);
		logPass(t.toString());
		assertEquals(201, t.getResponse().statusCode());

		jsonPathEvaluator = t.getResponse().jsonPath();
		int newIntervalId = (int) jsonPathEvaluator.get("intervalId");
		logPass("Numbers created successfully: " + startMsisdn + " - " + endMsisdn + ". Interval block " + newIntervalId);

		// create an import file to update the msisdn pools
		ImportNumberFile file = new ImportNumberFile();
		for (String msisdn : msisdns) {
			ImportNumberRow row = new ImportNumberRow(msisdn, "", "STANDARD", "", pool, "this is a new row " + System.currentTimeMillis());
			file.getRows().add(row);
		}

		// write the xlsx file to the directory
		String filepath = file.writeToFile("files\\tecrep");

		// post it to the API
		t = TecrepMonitorAPI.importNumbers(token, filepath);
		logPass(t.toString());

		// verify the response code
		assertEquals(200, t.getResponse().statusCode());
		logPass("Numbers moved to the pool " + pool);

		// determine the config type to use based on the pool
		SimCardConfiguration config = TecrepUtility.getConfiguration(pool);

		// make the call to the API to create the SIM batch
		t = TecrepMonitorAPI.generatePairedSimCardBatch(token, pool, batchSize, config, startMsisdn, endMsisdn);
		logInfo(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// determine the MMC0000XX.inp filename for the batch created
		jsonPathEvaluator = t.getResponse().jsonPath();
		String inpFilename = (String) jsonPathEvaluator.get("filename");

		// read in the details of the .inp file
		InpFile inpFile = TecrepEquipmentsUtility.getInpFileFromAPI(inpFilename);
		logPass(".inp file created: " + inpFilename + "\n" + inpFile.toString());

		// generate a MMC0000XX.out file for the .inp file
		String s = TecrepEquipmentsUtility.generateOutFile(inpFilename, msisdns);
		String outFileContents = TextReader.getContent(s);
		logPass(".out file generated:\n" + outFileContents);

		// upload the .out file to Tecrep
		t = TecrepMonitorAPI.uploadSimCardBatch(token, inpFile.getBatch(), s);
		logInfo("SIM card batch uploaded:\n" + t.toString());

		// trigger the "PROCEED IMPORT" action to complete the upload
		t = TecrepMonitorAPI.proceedImport(token, inpFile.getBatch());
		logInfo("SIM card batch imported:\n" + t.toString());

		logPass("Batch completed");
	}

	@Test(description = "Tecrep: Process DEL file")
	public void testProcessHandsetsDELFile(ITestContext iTestContext) {

		// generate a DEL file
		DELFile delFile = DELGenerator.generateHandsets("GSAMS2P5G", 1,false,true);

		logPass("Generated and uploaded DEL file " + delFile.getFilepath() + "\n" + delFile.toString());
	}

	@DataProvider(name = "getAllInventoryPools")
	public Object[][] getAllChannelGroups() {
		ArrayList<DBInventoryPool> pools = TecrepEquipmentsDAO.getInventoryPools();

		// Object[][] data = new Object[pools.size()][1];
		// for (int i = 0; i < pools.size(); i++) {
		Object[][] data = new Object[1][1];
		for (int i = 0; i < 1; i++) {
			data[i] = new Object[] { InventoryPool.valueOf(pools.get(i).getCode()) };
		}

		return data;
	}

	@DataProvider(name = "getPools")
	public Object[][] getPools() {
		ArrayList<InventoryPool> pools = new ArrayList<InventoryPool>();
		pools.add(InventoryPool.METEOR_PAIRED_CUSTOMER_POOL);
		// pools.add(InventoryPool.ION_PAIRED_POOL);
		// pools.add(InventoryPool.EIRCOM_PAIRED_CUSTOMER_POOL);
		Object[][] data = new Object[pools.size()][1];
		for (int i = 0; i < pools.size(); i++) {
			data[i] = new Object[] { pools.get(i) };
		}

		return data;
	}

	@BeforeClass
	public void setUp() {

	}

	@AfterClass
	public void tearDown() {

	}
}

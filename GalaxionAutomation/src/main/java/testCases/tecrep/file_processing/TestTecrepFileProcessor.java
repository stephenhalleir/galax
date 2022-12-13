package testCases.tecrep.file_processing;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Equipment;
import microservices.backend.eir_inventory_management_backend.utility.R6EquipmentUpdateFileGenerator;
import microservices.backend.tecrep.stock_management.utility.DELGenerator;

public class TestTecrepFileProcessor extends BaseTest {

	private String msisdn="0850201011";
	private String iccid="893530305243716026";
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 21 - Activation")
	public void testProcessR6EquipmentUpdateFile21() {
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,21,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 22 - Termination")
	public void testProcessR6EquipmentUpdateFile22() {
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,22,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 23 - MSISDN Swap (old)")
	public void testProcessR6EquipmentUpdateFile23() {
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,23,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 24 - MSISDN Swap (new)")
	public void testProcessR6EquipmentUpdateFile24() {
		String iccid="893530305243716022";
		String msisdn="0850323209";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,24,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 25 - SIM Swap (old)")
	public void testProcessR6EquipmentUpdateFile25() {
		String iccid="893530305243716015";
		String msisdn="0850201000";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,25,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 26 - SIM Swap (new)")
	public void testProcessR6EquipmentUpdateFile26() {
		String iccid="893530305243720784";
		String msisdn="0850201000";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,26,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 27 - Port in from OAO")
	public void testProcessR6EquipmentUpdateFile27() {
		String iccid="893530305243716016";
		String msisdn="0869990991";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,27,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 28 - Port in Internal")
	public void testProcessR6EquipmentUpdateFile28() {
		String iccid="893530305243716026";
		String msisdn="0850201011";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,28,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 29 - Port out to OAO")
	public void testProcessR6EquipmentUpdateFile29() {
		String iccid="893530305243716026";
		String msisdn="0850201011";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,29,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 31 - Port in reversal")
	public void testProcessR6EquipmentUpdateFile31() {
		String iccid="893530305243716026";
		String msisdn="0850201011";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,31,true);
	}
	
	@Test(description = "Tecrep File Processing: Process R6 Equipment Update File - 33 - Port in reversal")
	public void testProcessR6EquipmentUpdateFile33() {
		String iccid="893530305243716026";
		String msisdn="0850201011";
		R6EquipmentUpdateFileGenerator.generateEquipmentUpdateFile(msisdn, iccid,33,true);
	}
	
	@Test(description = "Tecrep File Processing: Process DEL File")
	public void testProcessDELFile() {
		Equipment randomHandset = CatalogCoreDAO.getRandomHandset();
		randomHandset.setCode("GAPPS264B");
		DELGenerator.generateHandsets(randomHandset.getCode(), 5,true, false);
	}

	@BeforeClass
	public void setUp() {

	}

	@AfterClass
	public void tearDown() {

	}
}
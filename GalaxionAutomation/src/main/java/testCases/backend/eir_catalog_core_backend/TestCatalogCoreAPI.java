package testCases.backend.eir_catalog_core_backend;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_catalog_core_backend.api.CatalogCoreAPI;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Channel;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.ChannelGroup;
import microservices.backend.eir_catalog_core_backend.enums.ChargeType;
import microservices.backend.eir_catalog_core_backend.enums.EquipmentType;
import microservices.backend.eir_catalog_core_backend.enums.ItemGroupCode;
import microservices.backend.eir_catalog_core_backend.enums.ManufacturerCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_catalog_core_backend.enums.ServiceGroupCode;
import utilities.generic.api.APITransaction;

public class TestCatalogCoreAPI extends BaseTest {

	/**
	 * Test the catalog core get offers call
	 * 
	 * offer-controller-v-1 GET /api/v1/offers/{offer_code}
	 * 
	 * Run: for each offer
	 * 
	 * @param offerCode
	 * @param iTestContext
	 */
	@Test(dataProvider = "getAllOffers", description = "Catalog Core API: Get offer")
	public void testGetOffer(String offerCode, ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getOffer(offerCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(description = "Catalog Core API: Get accessory")
	public void testGetAccessory(ITestContext iTestContext) {

		String accessoryCode = "ACCGRIC3FB";

		APITransaction t = CatalogCoreAPI.getAccessory(accessoryCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(description = "Catalog Core API: Get all accessories")
	public void testGetAccessories(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getAllAccessories();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(description = "Catalog Core API: Get all brands")
	public void testGetBrands(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getBrands();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(dataProvider = "getAllBrands",description = "Catalog Core API: Get brand")
	public void testGetBrand(Brand brandCode, ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getBrand(brandCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(description = "Catalog Core API: Get channels")
	public void testGetAllChannels(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getAllChannels();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(dataProvider="getAllChannelGroups", description = "Catalog Core API: Get channels in channel group")
	public void testGetChannelsInChannelGroup(ChannelGroup channelGroup,ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getChannelsInChannelGroup(channelGroup);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	@Test(dataProvider= "getAllChannels",description = "Catalog Core API: Get channel by channel code")
	public void testGetChannelByChannelCode(ChannelCode channelCode,ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getChannel(channelCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get all charges")
	public void testGetAllCharges(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getAllCharges();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(dataProvider = "getAllChargeTypes",description = "Catalog Core API: Get charges by charge type")
	public void testGetChargesByType(ChargeType chargeType,ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getChargesByType(chargeType);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get charge")
	public void testGetCharge(ITestContext iTestContext) {
		String chargeCode="SIM_FEE";
		APITransaction t = CatalogCoreAPI.getCharge(chargeCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get all equipments")
	public void testGetAllEquipments(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getEquipments();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get equipment by code")
	public void testGetEquipmentByCode(ITestContext iTestContext) {
		String equipmentCode="GALC1066S";
		APITransaction t = CatalogCoreAPI.getEquipmentByCode(equipmentCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get item groups")
	public void testGetItemGroups(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getItemGroups();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get item group by item group code")
	public void testGetItemGroupByItemGroupCode(ITestContext iTestContext) {
		ItemGroupCode itemGroupCode=ItemGroupCode.BARRING;
		APITransaction t = CatalogCoreAPI.getItemGroupByItemGroupCode(itemGroupCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get all manufacturers")
	public void testGetAllManufacturers(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getAllManufacturers();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get manufacturers by equipment type")
	public void testGetAllManufacturersByEquipmentType(ITestContext iTestContext) {
		EquipmentType type=EquipmentType.HANDSET;
		APITransaction t = CatalogCoreAPI.getManufacturersForEquipmentType(type);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get manufacturer by manufacturer code")
	public void testGetAllManufacturerByManufacturerCode(ITestContext iTestContext) {
		ManufacturerCode code=ManufacturerCode.APPLE;
		APITransaction t = CatalogCoreAPI.getManufacturersByManufacturerCode(code);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get network element by code")
	public void testGetNetworkElement(ITestContext iTestContext) {
		String networkElementCode="ALL_CALLS_ALL_TEXTS";
		APITransaction t = CatalogCoreAPI.getNetworkElement(networkElementCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get all offer types")
	public void testGetAllOfferTypes(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getAllOfferTypes();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(dataProvider="getAllOfferTypes",description = "Catalog Core API: Get offer type by offer type code")
	public void testGetOfferType(OfferType type,ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getOfferType(type);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get service groups")
	public void testGetServiceGroups(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getServiceGroups();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get service group by code")
	public void testGetServiceGroupByCode(ITestContext iTestContext) {
		ServiceGroupCode code=ServiceGroupCode.MOBILE;
		APITransaction t = CatalogCoreAPI.getServiceGroup(code);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get SIM card")
	public void testGetSimCard(ITestContext iTestContext) {
		String simCode="1SIMBILLE";
		APITransaction t = CatalogCoreAPI.getSimCard(simCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get subsidy")
	public void testGetSubsidy(ITestContext iTestContext) {
		String equipmentCode="GSAMA52S5";
		String tariffPlanCode="PERFORMANCE-24";
		APITransaction t = CatalogCoreAPI.getSubsidies(tariffPlanCode, equipmentCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get tariff plan")
	public void testGetTariffPlan(ITestContext iTestContext) {
		String tariffPlanCode="GOVALLIN10GB-A";
		APITransaction t = CatalogCoreAPI.getTariffPlan(tariffPlanCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get usage quota")
	public void testGetUsageQuota(ITestContext iTestContext) {
		String usageCode="6009-5GB-USAGE";
		APITransaction t = CatalogCoreAPI.getUsage(usageCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Get  all VAT codes")
	public void testGetVatCodes(ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getAllVatCodes();
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(dataProvider = "getAllVatCodes", description = "Catalog Core API: Get VAT code")
	public void testGetVatCode(String vatCode, ITestContext iTestContext) {
		APITransaction t = CatalogCoreAPI.getVatCode(vatCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}
	
	@Test(description = "Catalog Core API: Check compatibility")
	public void testAddonCheckCompatibility(ITestContext iTestContext) {
		String offerCode="BASIC";
		String addonCode="ROAFAC";
		APITransaction t = CatalogCoreAPI.checkAddonCompatibility(offerCode, addonCode);
		logInfo(t.toString());
		assertEquals(t.getResponse().statusCode(), 200);
	}

	// DATA PROVIDERS
	
	@DataProvider(name = "getAllOffers")
	public Object[][] getAllOffers() {
		ArrayList<Offer> offers = CatalogCoreDAO.getAllOffers();

		Object[][] data = new Object[offers.size()][1];
		for (int i = 0; i < offers.size(); i++) {
			data[i] = new Object[] { offers.get(i).getCode() };
		}

		return data;
	}
	
	@DataProvider(name = "getAllBrands")
	public Object[][] getAllBrands() {
		Brand[] brands= {Brand.EIR, Brand.GOMO};

		Object[][] data = new Object[brands.length][1];
		for (int i = 0; i < brands.length; i++) {
			data[i] = new Object[] { brands[i] };
		}

		return data;
	}
	
	@DataProvider(name = "getAllOfferTypes")
	public Object[][] getAllOfferTypes() {
		OfferType[] types= {OfferType.POSTPAY,OfferType.PREPAY};

		Object[][] data = new Object[types.length][1];
		for (int i = 0; i < types.length; i++) {
			data[i] = new Object[] { types[i] };
		}

		return data;
	}
	
	@DataProvider(name = "getAllChannels")
	public Object[][] getAllChannels() {
		ArrayList<Channel> channels = CatalogCoreDAO.getAllChannels();

		Object[][] data = new Object[channels.size()][1];
		for (int i = 0; i < channels.size(); i++) {
			data[i] = new Object[] { ChannelCode.valueOf(channels.get(i).getCode()) };
		}

		return data;
	}
	
	@DataProvider(name = "getAllChannelGroups")
	public Object[][] getAllChannelGroups() {
		ArrayList<String> channels = CatalogCoreDAO.getAllChannelGroups();

		Object[][] data = new Object[channels.size()][1];
		for (int i = 0; i < channels.size(); i++) {
			data[i] = new Object[] { ChannelGroup.valueOf(channels.get(i)) };
		}

		return data;
	}
	
	@DataProvider(name = "getAllVatCodes")
	public Object[][] getAllVatCodes() {
		String[] vatCodes= {"VAT_STD","VAT_0","VAT_REDUC"};

		Object[][] data = new Object[vatCodes.length][1];
		for (int i = 0; i < vatCodes.length; i++) {
			data[i] = new Object[] { vatCodes[i] };
		}

		return data;
	}
	
	@DataProvider(name = "getAllChargeTypes")
	public Object[][] getAllChargeTypes() {
		ChargeType[] chargeTypes= {ChargeType.ONE_OFF,ChargeType.RECURRING,ChargeType.UPFRONT};

		Object[][] data = new Object[chargeTypes.length][1];
		for (int i = 0; i < chargeTypes.length; i++) {
			data[i] = new Object[] { chargeTypes[i] };
		}

		return data;
	}

	@AfterClass
	public void tearDown() {

	}
}

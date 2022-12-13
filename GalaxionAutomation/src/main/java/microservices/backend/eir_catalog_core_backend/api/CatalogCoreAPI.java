package microservices.backend.eir_catalog_core_backend.api;

import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.ChannelGroup;
import microservices.backend.eir_catalog_core_backend.enums.ChargeType;
import microservices.backend.eir_catalog_core_backend.enums.EquipmentType;
import microservices.backend.eir_catalog_core_backend.enums.ItemGroupCode;
import microservices.backend.eir_catalog_core_backend.enums.ManufacturerCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_catalog_core_backend.enums.ServiceGroupCode;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;

public class CatalogCoreAPI {

	/**
	 * Make a call to the catalog-core API and retrieve details of an offer
	 * 
	 * @param offerCode - e.g. GOMO3
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getOffer(String offerCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/offers/" + offerCode + "?view=FULL";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	

	/**
	 * Make a call to the catalog-core API and retrieve details of an accessory
	 * 
	 * accessory-controller-v-1
	 * /api/v1/accessories/{accessory_code}
	 * 
	 * @param accessoryCode - e.g. ACCGRICCAR
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getAccessory(String accessoryCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/accessories/" + accessoryCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	/**
	 * Make a call to the catalog-core API and retrieve the list of accessories
	 * 
	 * accessory-controller-v-1
	 * /api/v1/accessories
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getAllAccessories() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/accessories?page=0&size=10&status=ACTIVE";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	/**
	 * Make a call to the catalog-core API and retrieve the list of brands
	 * 
	 * brand-controller-v-1
	 * /api/v1/brands
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getBrands() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/brands";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	/**
	 * Make a call to the catalog-core API and retrieve the list of brands
	 * 
	 * brand-controller-v-1
	 * /api/v1/brands
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getBrand(Brand brandCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/brands/" + brandCode.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	/**
	 * Make a call to the catalog-core API and retrieve the list of channels (unfiltered)
	 * 
	 * channel-controller-v-1
	 * /api/v1/channels
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getAllChannels() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/channels";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}

	/**
	 * Make a call to the catalog-core API and retrieve the list of channels for a specific channel group
	 * 
	 * channel-controller-v-1
	 * /api/v1/channels
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getChannelsInChannelGroup(ChannelGroup channelGroup) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/channels?channelGroup=" + channelGroup.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	/**
	 * Make a call to the catalog-core API and retrieve the list of channels for a specific channel group
	 * 
	 * channel-controller-v-1
	 * /api/v1/channels
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getChannel(ChannelCode channelCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/channels/" + channelCode.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	
	/**
	 * Make a call to the catalog-core API and retrieve the list of charges
	 * 
	 * chrge-controller-v-1
	 * /api/v1/charges
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getAllCharges() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/charges";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getChargesByType(ChargeType type) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/charges?billingTypes=" + type.toString() + "&status=ACTIVE";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	/**
	 * Make a call to the catalog-core API and retrieve a charge
	 * 
	 * charge-controller-v-1
	 * /api/v1/charges/{charge_code}
	 *  
	 * @return the JSON response object
	 * 
	 * updated v1
	 */
	public static APITransaction getCharge(String chargeCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/charges/" + chargeCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction checkAddonCompatibility(String offerCode, String addonCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/offers/" + offerCode + "/addon-bundles/" + addonCode + "/conciliate";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getEquipments() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/equipments?page=0&size=10&status=ACTIVE";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}

	public static APITransaction getEquipmentByCode(String equipmentCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/equipments/" + equipmentCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getItemGroups() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/item-groups";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getItemGroupByItemGroupCode(ItemGroupCode itemGroupCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/item-groups/" + itemGroupCode.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getAllManufacturers() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/manufacturers";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getManufacturersForEquipmentType(EquipmentType type) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/manufacturers?equipmentTypes=" + type.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getManufacturersByManufacturerCode(ManufacturerCode code) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/manufacturers/" + code.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getNetworkElement(String networkElementCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/network-elements/" + networkElementCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getAllOfferTypes() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/offer-types";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getOfferType(OfferType type) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/offer-types/" + type.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getServiceGroups() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/service-groups";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getServiceGroup(ServiceGroupCode code) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/service-groups/" + code.toString();
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getSimCard(String inventoryCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/sim-cards/" + inventoryCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getSubsidies(String tariffPlanCode, String equipmentCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/subsidies/" + tariffPlanCode + "/" + equipmentCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getTariffPlan(String tariffPlanCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/tariff-plans/" + tariffPlanCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getAllVatCodes() {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/vat-codes";
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getVatCode(String vatCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/vat-codes/" + vatCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getUsage(String usageCode) {
		String url = EnvironmentDirectory.getAPICatalog() + "/api/v1/usages/" + usageCode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
	//==============  V0  ==========================
	/**
	 * Make a call to the catalog-core API and retrieve details of an addon
	 * 
	 * @param addonId 
	 * @return the JSON response object
	 */
	public static Response getAddon(int addonId) {
		String url = EnvironmentDirectory.getAPICatalog() + "/addon_bundle/id/" + addonId;
		System.out.println("CatalogCoreAPI calling: GET " + url);
		return RestAssuredUtil.galaxionGet(url, null);
	}

}

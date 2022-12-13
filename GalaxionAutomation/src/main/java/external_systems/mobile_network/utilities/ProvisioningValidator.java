package external_systems.mobile_network.utilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import external_systems.mobile_network.enums.NetworkNode;
import external_systems.mobile_network.enums.NetworkSetting;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import microservices.backend.eir_catalog_core_backend.enums.AddonType;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import utilities.test.config_readers.AddonConfigManager;
import utilities.test.config_readers.AddonConfigPair;
import utilities.test.config_readers.ProfileConfigManager;

public class ProvisioningValidator {

	private String msisdn;
	private OfferCode offerCode;
	private ProfileConfigManager expectedSettings;
	private Service service;

	/*
	 * Constructor
	 */
	public ProvisioningValidator(String msisdn, OfferCode offerCode) { 
		this.msisdn=msisdn;
		this.offerCode=offerCode;
		this.expectedSettings = new ProfileConfigManager(offerCode);
		this.service = new Service(msisdn);
	}

	/*
	 * Getters/setters
	 */
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public OfferCode getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(OfferCode offerCode) {
		this.offerCode = offerCode;
	}
	
	/*
	 * Check all provisioning nodes
	 */
	public boolean validateProvisioning(String msisdn, OfferCode offerCode) {
		
		this.validateHLRProvisioning();
		this.validateHLRFEProvisioning();
		this.validateINProvisioning();
		this.validateSPRProvisioning();
		
		// print an extract of the network profile
		NetworkProfileExtract extract = new NetworkProfileExtract(service.getMsisdn());
		String fileLocation = extract.printNetworkProfile();
		
		return true;
	}
	
	public boolean validateTerminated(String msisdn) {
		service.getHlrfeProfile();
		assertNull(service.getHlrfeProfile());
		
		//service.load
		assertNull(service.getOCSProfile());
		
		service.loadSPRProfile();
		assertNull(service.getSprProfile());
		
		//service.loadCVMProfile();
		assertNull(service.getCvmProfile());
		
		return true;
	}
	
	/*
	 * Validate whether the subscriber has been provisioned to the legacy HLR as expected
	 */
	public boolean validateHLRProvisioning() {
				
		// retrieve the values from the HLR
		service.loadHLRFEProfile();
		
		// verify that the subscriber has been provisioned to all nodes
		assertNotNull(service.getHlrfeProfile());
		
		// validate HLR
		assertEquals(service.getHlrfeProfile().getCsp(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.CSP));
		assertEquals(service.getHlrfeProfile().getRsa(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.RSA));
		assertEquals(service.getHlrfeProfile().getCat(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.CAT));
		assertEquals(service.getHlrfeProfile().getSchar(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.SCHAR));
		assertEquals(service.getHlrfeProfile().getMsisdn(), service.getMsisdn());
		
		return true;
	}
	
	/*
	 * Validate whether the subscriber has been provisioned to the HLR-FE as expected
	 */
	public boolean validateHLRFEProvisioning() {
			
		// retrieve the values from the HLR
		HLRFEProfile profile = service.getHlrfeProfile();
		
		// verify that the subscriber has been provisioned to all nodes
		assertNotNull(service.getHlrfeProfile());
		
		// validate HLR
		assertEquals(profile.getCsp(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.CSP));
		assertEquals(profile.getRsa(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.RSA));
		assertEquals(profile.getCat(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.CAT));
		assertEquals(profile.getSchar(), expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.SCHAR));
		assertEquals(profile.getMsisdn(), service.getMsisdn());
		
		// validate the APNs are present
		String[] expectedAPNs=expectedSettings.getProvisioningValue(NetworkNode.HLRFE, NetworkSetting.APN).split(",",-1);
		for(int i=0;i<expectedAPNs.length;i++) {
			assertTrue(profile.hasAPN(Integer.parseInt(expectedAPNs[i])));
		}
		return true;
		
	}
	
	/*
	 * Validate whether the subscriber has been provisioned to the IN as expected
	 */
	public boolean validateINProvisioning() {
		
		// retrieve the values from the HLR
		service.loadINProfile();
		
		// verify that the subscriber has been provisioned to all nodes
		assertNotNull(service.getOCSProfile());
		
		// validate profile settings
		assertEquals(service.getOCSProfile().getStatus(), expectedSettings.getProvisioningValue(NetworkNode.EC20, NetworkSetting.STATUS));
		assertEquals(service.getOCSProfile().getTariffPlan(), expectedSettings.getProvisioningValue(NetworkNode.EC20, NetworkSetting.TARIFF_PLAN));
		assertEquals(service.getOCSProfile().getMsisdn(), service.getMsisdn());
		
		// validate the bundles
		String bundleString=expectedSettings.getProvisioningValue(NetworkNode.EC20, NetworkSetting.BUNDLES);
		String[] expectedBundles=bundleString.split(",");
		for(int i=0;i<expectedBundles.length;i++) {
			assertTrue(service.getOCSProfile().hasDataBundle(expectedBundles[i].trim()));
		}
		
		return true;
	}
	
	/*
	 * Validate whether the subscriber has been provisioned to the SPR as expected
	 */
	public boolean validateSPRProvisioning() {
		
		// retrieve the values from the HLR
		service.loadSPRProfile();
		
		// verify that the subscriber has been provisioned to all nodes
		assertNotNull(service.getSprProfile());
		
		// validate SPR
		assertEquals(service.getSprProfile().getBillingSource(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.BILLING_SOURCE));
		assertEquals(service.getSprProfile().getPricePlan(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.PRICE_PLAN));
		assertEquals(service.getSprProfile().getSubscriberType(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.SUBSCRIBER_TYPE));
		assertEquals(service.getSprProfile().getOperator(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.OPERATOR));
		assertEquals(service.getSprProfile().getAocOptedIn(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.AOC_OPTED_IN));
		assertEquals(service.getSprProfile().getNotificationFlag(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.NOTIFICATION_FLAG));
		assertEquals(service.getSprProfile().getCfsp(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.CFSP));
		assertEquals(service.getSprProfile().getLteEnabled(), expectedSettings.getProvisioningValue(NetworkNode.SPR, NetworkSetting.LTE_ENABLED));
		assertEquals(service.getSprProfile().getMsisdn(), service.getMsisdn());
		
		return true;
	}
	
	/*
	 * Validate that the subscriber has been provisioned to the CVM voicemail platform as expected
	 */
	public boolean validateCVMProvisioning() {
		
		// retrieve the values from the HLR
		service.loadCVMProfile();
		
		// verify that the subscriber has been provisioned
		assertNotNull(service.getCvmProfile());
		
		// validate CVM
		assertEquals(service.getCvmProfile().getServiceNumber(), service.getMsisdn08x());
		assertEquals(service.getCvmProfile().getVoicemailType(), expectedSettings.getProvisioningValue(NetworkNode.CVM, NetworkSetting.VM_TYPE));
		assertEquals(service.getCvmProfile().getDescription(), expectedSettings.getProvisioningValue(NetworkNode.CVM, NetworkSetting.VM_DESC));
		
		return true;
	}
	
	/*
	 * Validate whether an addon has been successfully provisioned
	 */
	public boolean validateAddonProvisioning(String addonCode) {
		
		AddonConfigManager reader = new AddonConfigManager();
		
		// find out what needs to be checked - e.g. APN, DATA_BUNDLE, etc
		AddonConfigPair expectedValues = reader.getExpectedAddonValues(addonCode);
		
		// if APN, check the HLR-FE profile
		if(expectedValues.getAddonType()==AddonType.APN) {
			return service.getHlrfeProfile().hasAPN(expectedValues.getValue());
		}
		 
		return false;
	}
}

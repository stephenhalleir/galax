package external_systems.mobile_network.utilities;

import java.util.ArrayList;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.ec20.profile.EC20Profile;
import external_systems.mobile_network.nodes.hlr_fe.components.APN;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;

public class AddonsValidator {

	public static boolean isAddonProvisioned(String msisdn, SubscriptionAddonBundle addon) {
		
		switch(addon){
		case _10GBDATA:
			break;
		case _20GBDATA:
			break;
		case _5GENAB:
			break;
		case APNASAVIE:
			return isAPNProvisioned(msisdn, "business.emobile.ie");
		case APNBARROW:
			return isAPNProvisioned(msisdn, "barrow");
		case APNDCN:
			return isAPNProvisioned(msisdn, "dcnapn");
		case APNEIRACF:
			return isAPNProvisioned(msisdn, "acf.myeirmobile.ie");
		case APNEIRBB:
			return isAPNProvisioned(msisdn, "broadband.myeirmobile.ie");
		case APNEIRCOMMMS:
			return isAPNProvisioned(msisdn, "mms.eircom.ie");
		case APNEIRDATA:
			return isAPNProvisioned(msisdn, "data.myeirmobile.ie");
		case APNFWACPEMGMT:
			return isAPNProvisioned(msisdn, "dm.eirfwa.ie");
		case APNFWADATA:
			return isAPNProvisioned(msisdn, "data.eirfwa.ie");
		case APNGOV:
			return isAPNProvisioned(msisdn, "gn.gov.ie");
		case APNIMS:
			return isAPNProvisioned(msisdn, "IMS");
		case APNIRISHRAIL:
			return isAPNProvisioned(msisdn, "irishrailmobile.ie");
		case APNMETEORMMS:
			return isAPNProvisioned(msisdn, "mms.mymeteor.ie");
		case APNPADDYPOWER:
			return isAPNProvisioned(msisdn, "ppbwan");
		case APNSOS:
			return isAPNProvisioned(msisdn, "SOS");
		case BOLTGBZ1BZ2:
			break;
		case BOLTGMT200:
			break;
		case BOLTGUSINT:
			break;
		case ECC:
			break;
		case MBB12MTHFREEPROMOTION:
			break;
		case MDMSSF:
			break;
		case PREM:
			break;
		case PRESMS:
			break;
		case ROABARRED:
			break;
		case ROABZ1BZ2:
			break;
		case ROAEA1:
			break;
		case ROAEU1:
			break;
		case ROAUKNI:
			break;
		case VOICEMAIL:
			break;
		case VPN_CUG:
			break;
		case WIFI:
			break;
		}
		
		return false;
	}
	
	/**
	 * Determine whether an APN is provisioned on a msisdn
	 * @param msisdn
	 * @param apn - e.g. gn.gov.ie
	 * @return true if the APN exists on the account
	 */
	public static boolean isAPNProvisioned(String msisdn, String apn) {
		
		// retrieve the HLR-FE profile and get the list of active APNs
		HLRFEProfile profile = MMWUtility.getHLRFEProfile(msisdn);
		ArrayList<Integer> apns = profile.getApns();
		
		// for each APN ID found on the subscription
		for(int apnId:apns) {
			
			APN thisAPN = new APN(apnId);
			
			// get the APN name
			thisAPN.enhanceWithName();
	
			// if the apn name matches, return true
			if(thisAPN.getApnName().equalsIgnoreCase(apn)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean hasINOffer(String msisdn, int offerID) {
		EC20Profile profile = MMWUtility.getEC20Profile(msisdn);
		return profile.hasOffer(offerID);
	}
	
	/**
	 * Determine whether a subscription has a DA account set on the IN
	 * @param msisdn
	 * @param da - DA account eventType - e.g. MDInEventTyAnyMobNetV
	 * @return true if it exists
	 */
	public static boolean hasINDA(String msisdn, String da) {
		EC20Profile profile = MMWUtility.getEC20Profile(msisdn);
		return profile.hasDA(da);
	}
}

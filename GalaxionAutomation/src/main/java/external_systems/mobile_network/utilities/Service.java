package external_systems.mobile_network.utilities;

import java.text.DecimalFormat;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.cvm.CVMProfile;
import external_systems.mobile_network.nodes.ec20.profile.EC20Profile;
import external_systems.mobile_network.nodes.hlr_fe.profile.HLRFEProfile;
import external_systems.mobile_network.nodes.spr.SPRProfile;

public class Service {

	// subscription/service attributes
	private String msisdn;

	// PCAT details
	private String offerName;
	private String offerDescription;
	private int offerMonthlyCharge;

	// network profiles
	private HLRFEProfile hlrfeProfile;
	private SPRProfile sprProfile;
	private EC20Profile ocsProfile;
	private CVMProfile cvmProfile;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public Service() {
		this.msisdn = "";
	}

	public Service(String msisdn) {
		this.msisdn = msisdn;
	}

	public void loadNetworkProfile() {
		this.loadHLRFEProfile();
		this.loadSPRProfile();
		this.loadINProfile();
		this.loadCVMProfile();
	}

	public void loadSPRProfile() {
		sprProfile = MMWUtility.getSprProfile(msisdn);
	}
	
	public void loadHLRFEProfile() {
		hlrfeProfile = MMWUtility.getHLRFEProfile(msisdn);
	}
	
	public void loadINProfile() {
		ocsProfile = MMWUtility.getEC20Profile(msisdn);
	}
	
	public void loadCVMProfile() {
		cvmProfile = MMWUtility.getVoicemailProfile(msisdn);
	}

	public void printNetworkProfile() {
		hlrfeProfile.print();
		sprProfile.print();
		ocsProfile.print();
		cvmProfile.print();
	}


	public EC20Profile getOCSProfile() {
		return ocsProfile;
	}

	public SPRProfile getSprProfile() {
		return sprProfile;
	}


	public void setOcsProfile(EC20Profile ocsProfile) {
		this.ocsProfile = ocsProfile;
	}

	public CVMProfile getCvmProfile() {
		return cvmProfile;
	}

	public void setCvmProfile(CVMProfile cvmProfile) {
		this.cvmProfile = cvmProfile;
	}

	public void setSprProfile(SPRProfile sprProfile) {
		this.sprProfile = sprProfile;
	}
	
	

	public HLRFEProfile getHlrfeProfile() {
		return hlrfeProfile;
	}

	public void setHlrfeProfile(HLRFEProfile hlrfeProfile) {
		this.hlrfeProfile = hlrfeProfile;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public String getOfferMonthlyCharge() {
		DecimalFormat df2 = new DecimalFormat("0.00");
		double bal = (double) offerMonthlyCharge / 100;
		return "€" + df2.format(bal);
	}

	public void setOfferMonthlyCharge(int offerMonthlyCharge) {
		this.offerMonthlyCharge = offerMonthlyCharge;
	}

	public String getMsisdn3538x() {
		if (msisdn.startsWith("08")) {
			return msisdn.replaceFirst("08", "3538");
		}
		return msisdn;
	}

	public String getMsisdn08x() {
		if (msisdn.startsWith("3538")) {
			return msisdn.replaceFirst("3538", "08");
		}
		return msisdn;
	}

	public static void main(String[] args) {
		Service s = new Service();
		s.setMsisdn("0857094635");
		s.loadNetworkProfile();
		s.printNetworkProfile();
	}
}

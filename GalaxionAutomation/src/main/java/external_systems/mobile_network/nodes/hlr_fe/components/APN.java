package external_systems.mobile_network.nodes.hlr_fe.components;

import utilities.test.config_readers.MappingReader;

public class APN {
	private int apnID;
	private String apnName;
	
	public APN(int apnID) {
		this.apnID=apnID;
	} 
	
	/**
	 * Enhance the APN object with the APN name based on the ID
	 */
	public void enhanceWithName() {
		apnName=MappingReader.getAPNName(apnID);
	}

	public int getApnID() {
		return apnID;
	}

	public void setApnID(int apnID) {
		this.apnID = apnID;
	}

	public String getApnName() {
		return apnName;
	}

	public void setApnName(String apnName) {
		this.apnName = apnName;
	}
}

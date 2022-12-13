package utilities.test.config_readers;

import microservices.backend.eir_catalog_core_backend.enums.AddonType;

public class AddonConfigPair {
	
	private AddonType addonType;
	private String value;
	
	AddonConfigPair(AddonType addonType,String value) {
		this.addonType=addonType;
		this.value=value;
	}
	
	public AddonType getAddonType() {
		return addonType;
	}
	public void setAddonType(AddonType addonType) {
		this.addonType = addonType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}

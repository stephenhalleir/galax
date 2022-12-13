package testCases.external.middleware.barring.test_objects;

import java.util.ArrayList;

import microservices.backend.eir_barring_backend.enums.BarringType;
import utilities.generic.files.TextReader;

public class Bar {

	private BarringType barType;
	private String provisioningValue;
	
	public Bar(BarringType barType) {
		this.barType=barType;
	}
	
	public Bar(String provisioningValue) {
		this.provisioningValue=provisioningValue;
	}
	
	public String getMMWProvisioningCode() {
		ArrayList<String> barList = TextReader.getContentAsArrayList("config\\BarringMap.txt");
		for(String bar:barList) {
			String ionReference=bar.split(",")[0];
			if(ionReference.equals(barType.toString())) {
				return bar.split(",")[1];
			}
		}
		return null;
	}
	
	public BarringType getBarringType() {
		ArrayList<String> barList = TextReader.getContentAsArrayList("config\\BarringMap.txt");
		for(String bar:barList) {
			BarringType type=BarringType.valueOf(bar.split(",")[0]);
			String mmwReference=bar.split(",")[1];
			if(mmwReference.equals(provisioningValue)) {
				return type;
			}
		}
		return null;
	}
	
}

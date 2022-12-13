package microservices.frontend.tecrep_monitor.dto.generate_sim_card_batch;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;

import microservices.frontend.tecrep_monitor.enums.InventoryPool;
import microservices.frontend.tecrep_monitor.enums.SimCardConfiguration;

public class GenerateSimCardBatchDTO {
	
	private String inventoryPoolCode;
	private boolean pairing;
	private int quantity;
	private String configurationName;
	private String brand;
	private String simCardType;
	private String provider;
	private String profile;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Numbers numbers;
	
	public GenerateSimCardBatchDTO(InventoryPool inventoryPoolCode,boolean pairing,int quantity,SimCardConfiguration configurationName) {
		this.inventoryPoolCode=inventoryPoolCode.toString();
		this.pairing=pairing;
		this.quantity=quantity;
		this.configurationName=configurationName.toString();
		this.brand="";
		this.simCardType="";
		this.provider="";
		this.profile="";
		this.numbers=null;
	}
	
	public String getInventoryPoolCode() {
		return inventoryPoolCode;
	}
	public void setInventoryPoolCode(String inventoryPoolCode) {
		this.inventoryPoolCode = inventoryPoolCode;
	}
	public boolean isPairing() {
		return pairing;
	}
	public void setPairing(boolean pairing) {
		this.pairing = pairing;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getConfigurationName() {
		return configurationName;
	}
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSimCardType() {
		return simCardType;
	}
	public void setSimCardType(String simCardType) {
		this.simCardType = simCardType;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Numbers getNumbers() {
		return numbers;
	}

	public void setNumbers(Numbers numbers) {
		this.numbers = numbers;
	}
	
	public void setPairingNumbers(BigInteger firstNumber, BigInteger lastNumber) {
		this.numbers = new Numbers(firstNumber,lastNumber);
	}
}
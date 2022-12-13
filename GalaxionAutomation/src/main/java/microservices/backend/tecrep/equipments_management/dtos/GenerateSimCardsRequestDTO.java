package microservices.backend.tecrep.equipments_management.dtos;

import java.util.List;

public class GenerateSimCardsRequestDTO {
	
	private String brand;
	private List<String> numbers;
	private String profile;
	private String provider;
	private int quantity;
	private String simCardType;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSimCardType() {
		return simCardType;
	}

	public void setSimCardType(String simCardType) {
		this.simCardType = simCardType;
	}
}

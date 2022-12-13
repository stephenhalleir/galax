package microservices.backend.tecrep.equipments_management.dtos;

public class CreateBatchRequestDTO {

	private String configurationName;
	private String inventoryPoolCode;

	public CreateBatchRequestDTO() {
		super();
	}

	public CreateBatchRequestDTO(String configurationName, String inventoryPoolCode) {
		super();
		this.configurationName = configurationName;
		this.inventoryPoolCode = inventoryPoolCode;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public String getInventoryPoolCode() {
		return inventoryPoolCode;
	}

	public void setInventoryPoolCode(String inventoryPoolCode) {
		this.inventoryPoolCode = inventoryPoolCode;
	}

}

package microservices.backend.tecrep.resource_management.dto;

public class AddNumberDTO {
	
	private String number;
	private String status;
	private String vanityCategory;
	
	public AddNumberDTO(String number, String status, String vanityCategory) {
		super();
		this.number = number;
		this.status = status;
		this.vanityCategory = vanityCategory;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVanityCategory() {
		return vanityCategory;
	}
	public void setVanityCategory(String vanityCategory) {
		this.vanityCategory = vanityCategory;
	}
}

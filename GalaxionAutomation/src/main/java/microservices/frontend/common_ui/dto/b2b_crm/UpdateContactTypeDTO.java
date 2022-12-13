package microservices.frontend.common_ui.dto.b2b_crm;

public class UpdateContactTypeDTO {
	private String contactType;

	public UpdateContactTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateContactTypeDTO(String contactType) {
		super();
		this.contactType = contactType;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
}

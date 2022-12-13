package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

public class ServiceGroup {
	private String code;
	private String itemType;
	
	public ServiceGroup() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}

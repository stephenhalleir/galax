package microservices.frontend.eir_b2b_crm_ui_frontend.dto.account_level_barring;

public class AccountBarringDTO {

	private String code;
	private String action;
	
	public AccountBarringDTO(String code, String action) {
		super();
		this.code = code;
		this.action = action;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}

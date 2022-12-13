package microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_account_general_details;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateGeneralDetailsDTO {

	private String ACCOUNT_MANAGER;
	private String AGREEMENT_DURATION;
	private String CUSTOMER_COST_CENTER;
	private String GROUP_ICID;
	private String INDOOR_COVERAGE_SOLUTION_DATE;
	private String SALESFORCE_CASE_NUMBER;
	private String SALESFORCE_CUSTOMER_ID;
	private String VPN_ACCOUNT_NUMBER;

	public UpdateGeneralDetailsDTO(String ACCOUNT_MANAGER, String AGREEMENT_DURATION, String CUSTOMER_COST_CENTER, String GROUP_ICID,
			String INDOOR_COVERAGE_SOLUTION_DATE, String SALESFORCE_CASE_NUMBER, String SALESFORCE_CUSTOMER_ID, String VPN_ACCOUNT_NUMBER) {
		super();
		this.ACCOUNT_MANAGER = ACCOUNT_MANAGER;
		this.AGREEMENT_DURATION = AGREEMENT_DURATION;
		this.CUSTOMER_COST_CENTER = CUSTOMER_COST_CENTER;
		this.GROUP_ICID = GROUP_ICID;
		this.INDOOR_COVERAGE_SOLUTION_DATE = INDOOR_COVERAGE_SOLUTION_DATE;
		this.SALESFORCE_CASE_NUMBER = SALESFORCE_CASE_NUMBER;
		this.SALESFORCE_CUSTOMER_ID = SALESFORCE_CUSTOMER_ID;
		this.VPN_ACCOUNT_NUMBER = VPN_ACCOUNT_NUMBER;
	}

	public UpdateGeneralDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("ACCOUNT_MANAGER")
	public String getACCOUNT_MANAGER() {
		return ACCOUNT_MANAGER;
	}

	public void setACCOUNT_MANAGER(String aCCOUNT_MANAGER) {
		ACCOUNT_MANAGER = aCCOUNT_MANAGER;
	}

	@JsonProperty("AGREEMENT_DURATION")
	public String getAGREEMENT_DURATION() {
		return AGREEMENT_DURATION;
	}

	public void setAGREEMENT_DURATION(String aGREEMENT_DURATION) {
		AGREEMENT_DURATION = aGREEMENT_DURATION;
	}

	@JsonProperty("CUSTOMER_COST_CENTER")
	public String getCUSTOMER_COST_CENTER() {
		return CUSTOMER_COST_CENTER;
	}

	public void setCUSTOMER_COST_CENTER(String cUSTOMER_COST_CENTER) {
		CUSTOMER_COST_CENTER = cUSTOMER_COST_CENTER;
	}

	@JsonProperty("GROUP_ICID")
	public String getGROUP_ICID() {
		return GROUP_ICID;
	}

	public void setGROUP_ICID(String gROUP_ICID) {
		GROUP_ICID = gROUP_ICID;
	}

	@JsonProperty("INDOOR_COVERAGE_SOLUTION_DATE")
	public String getINDOOR_COVERAGE_SOLUTION_DATE() {
		return INDOOR_COVERAGE_SOLUTION_DATE;
	}

	public void setINDOOR_COVERAGE_SOLUTION_DATE(String iNDOOR_COVERAGE_SOLUTION_DATE) {
		INDOOR_COVERAGE_SOLUTION_DATE = iNDOOR_COVERAGE_SOLUTION_DATE;
	}

	@JsonProperty("SALESFORCE_CASE_NUMBER")
	public String getSALESFORCE_CASE_NUMBER() {
		return SALESFORCE_CASE_NUMBER;
	}

	public void setSALESFORCE_CASE_NUMBER(String sALESFORCE_CASE_NUMBER) {
		SALESFORCE_CASE_NUMBER = sALESFORCE_CASE_NUMBER;
	}

	@JsonProperty("SALESFORCE_CUSTOMER_ID")
	public String getSALESFORCE_CUSTOMER_ID() {
		return SALESFORCE_CUSTOMER_ID;
	}

	public void setSALESFORCE_CUSTOMER_ID(String sALESFORCE_CUSTOMER_ID) {
		SALESFORCE_CUSTOMER_ID = sALESFORCE_CUSTOMER_ID;
	}

	@JsonProperty("VPN_ACCOUNT_NUMBER")
	public String getVPN_ACCOUNT_NUMBER() {
		return VPN_ACCOUNT_NUMBER;
	}

	public void setVPN_ACCOUNT_NUMBER(String vPN_ACCOUNT_NUMBER) {
		VPN_ACCOUNT_NUMBER = vPN_ACCOUNT_NUMBER;
	}

}

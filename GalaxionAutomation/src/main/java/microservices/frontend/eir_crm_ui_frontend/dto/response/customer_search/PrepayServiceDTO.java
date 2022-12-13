package microservices.frontend.eir_crm_ui_frontend.dto.response.customer_search;

/**
 * This class represents the "service" component of a search result returned to the Eir CRM UI customer search
 *
 * 
 * @author stephenhall 25/11/21
 *
 */
public class PrepayServiceDTO {

	private int id;
	private String createdAt;
	private String terminatedAt;
	private String msisdn;
	private String iccid;
	private String imsi;
	private String imei;
	private String domain;

	public PrepayServiceDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(String terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}

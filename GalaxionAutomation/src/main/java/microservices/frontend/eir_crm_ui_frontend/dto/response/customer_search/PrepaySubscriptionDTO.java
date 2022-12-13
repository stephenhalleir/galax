package microservices.frontend.eir_crm_ui_frontend.dto.response.customer_search;

import java.util.List;


/**
 * This class represents the "subscription" component of a search result returned to the Eir CRM UI customer search
 *
 * 
 * @author stephenhall 25/11/21
 *
 */
public class PrepaySubscriptionDTO {

	private int id;
	private String type;
	private String status;
	private String createdAt;
	private String terminatedAt;
	private String tariffPlanCode;
	private String offerCode;
	private String name;
	private String userUuid;
	private List<PrepayServiceDTO> services;

	public PrepaySubscriptionDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public List<PrepayServiceDTO> getServices() {
		return services;
	}

	public void setServices(List<PrepayServiceDTO> services) {
		this.services = services;
	}
}

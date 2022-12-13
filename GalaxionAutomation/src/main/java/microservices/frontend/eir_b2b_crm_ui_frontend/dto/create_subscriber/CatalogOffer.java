package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogOffer {
	private int id;
	private String description;
	private String comment;
	private OfferType offerType;
	private String code;
	
	@JsonIgnore
	private ServiceGroup serviceGroup;
	private List<Charge> charges;
	private List<Usage> usages;
	private List<Network> networks;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private EquipmentSet equipments;
	private String tariffPlanCode;

	public CatalogOffer() {
		super();
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonIgnore
	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ServiceGroup getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(ServiceGroup serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public List<Usage> getUsages() {
		return usages;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public List<Network> getNetworks() {
		return networks;
	}
	
	public Charge getCharge() {
		return charges.get(0);
	}

	public EquipmentSet getEquipments() {
		return equipments;
	}

	public void setEquipments(EquipmentSet equipments) {
		this.equipments = equipments;
	}

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}
	
	public List<Integer> getUsageCatalogIdList(){
		List<Integer> usageIDs = new ArrayList<Integer>();
		
		for(Usage u:usages) {
			usageIDs.add(u.getCatalogItemId());
		}
		
		
		return usageIDs;
	}
	
	public List<Integer> getNetworkCatalogIdList(){
		List<Integer> networkIDs = new ArrayList<Integer>();
		for(Network n:networks) {
			networkIDs.add(n.getId());
		}
		return networkIDs;
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getOfferCode() {
		return code;
	}
	
	public int getCatalogOfferId() {
		return id;
	}
}

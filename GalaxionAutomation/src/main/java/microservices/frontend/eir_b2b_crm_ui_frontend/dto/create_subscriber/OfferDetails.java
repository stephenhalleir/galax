package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.ArrayList;
import java.util.List;

public class OfferDetails {
	private List<AddonBundle> addOns;
	private String directoryPreference;
	private List<Equipment> equipments;

	public OfferDetails(CatalogOffer offer) {
		addOns = new ArrayList<AddonBundle>();
		equipments = new ArrayList<Equipment>();
		directoryPreference = "EXDIRECTORY";
		
		for(SimCard simCard:offer.getEquipments().getSimCards()) {
			Equipment e=new Equipment();
			e.setCatalogItemId(simCard.getId());
			e.setDeviceEnrolled(false);
			e.setPricePlans(simCard.getPricePlans());
			equipments.add(e);
		}
	}

	public List<AddonBundle> getAddOns() {
		return addOns;
	}

	public void setAddOns(List<AddonBundle> addOns) {
		this.addOns = addOns;
	}

	public String getDirectoryPreference() {
		return directoryPreference;
	}

	public void setDirectoryPreference(String directoryPreference) {
		this.directoryPreference = directoryPreference;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

}

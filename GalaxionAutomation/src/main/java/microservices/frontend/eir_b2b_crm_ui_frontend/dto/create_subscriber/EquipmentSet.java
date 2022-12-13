package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

import java.util.List;

public class EquipmentSet {
	
	private List<SimCard> simCards;

	public EquipmentSet() {
		super();
	}

	public List<SimCard> getSimCards() {
		return simCards;
	}

	public void setSimCards(List<SimCard> simCards) {
		this.simCards = simCards;
	}	
}

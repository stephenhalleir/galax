package microservices.frontend.eir_crm_ui_frontend.dto;

import microservices.backend.eir_catalog_core_backend.dto.ChannelDTO;

public class TriggerSimSwapPAYGRetailDTO {
	
	private ChannelDTO channel;
	private String iccid;
	
	public TriggerSimSwapPAYGRetailDTO() {
		
	}

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}	
}

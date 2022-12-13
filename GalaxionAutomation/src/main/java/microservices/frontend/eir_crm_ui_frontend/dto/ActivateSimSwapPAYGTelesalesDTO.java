package microservices.frontend.eir_crm_ui_frontend.dto;

import microservices.backend.eir_catalog_core_backend.dto.ChannelDTO;

public class ActivateSimSwapPAYGTelesalesDTO {

	private ChannelDTO channel;
	private String activationCode;

	public ActivateSimSwapPAYGTelesalesDTO(String activationCode) {
		this.channel = new ChannelDTO();
		this.activationCode = activationCode;
	}

	public ActivateSimSwapPAYGTelesalesDTO(ChannelDTO channel, String activationCode) {
		super();
		this.channel = channel;
		this.activationCode = activationCode;
	}

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
}

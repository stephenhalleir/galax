package microservices.frontend.common_ui.dto.service_actions.request.update_ndd;

import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;

public class UpdateNddDTO {

	private NDDSetting nddPreference;
	
	public UpdateNddDTO(NDDSetting nddPreference) {
		super();
		this.nddPreference = nddPreference;
	}

	public NDDSetting getNddPreference() {
		return nddPreference;
	}

	public void setNddPreference(NDDSetting nddPreference) {
		this.nddPreference = nddPreference;
	}	
}

package microservices.frontend.common_ui.dto.b2b_crm;

import microservices.frontend.eir_b2b_crm_ui_frontend.dto.TariffPlan;

public class ChangeOfferB2BCrmUiDTO {

	private String approvedBy;
	private String contact;
	private boolean deductedFromHardwareFund;
	private String device;
	private String offerCode;
	private int earlyCeaseChargeAmount;
	private boolean reContract;
	private TariffPlan tariffPlan;

	public ChangeOfferB2BCrmUiDTO() {
		tariffPlan = new TariffPlan();
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isDeductedFromHardwareFund() {
		return deductedFromHardwareFund;
	}

	public void setDeductedFromHardwareFund(boolean deductedFromHardwareFund) {
		this.deductedFromHardwareFund = deductedFromHardwareFund;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public int getEarlyCeaseChargeAmount() {
		return earlyCeaseChargeAmount;
	}

	public void setEarlyCeaseChargeAmount(int earlyCeaseChargeAmount) {
		this.earlyCeaseChargeAmount = earlyCeaseChargeAmount;
	}

	public boolean isReContract() {
		return reContract;
	}

	public void setReContract(boolean reContract) {
		this.reContract = reContract;
	}
	
	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public TariffPlan getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(TariffPlan tariffPlan) {
		this.tariffPlan = tariffPlan;
	}
}

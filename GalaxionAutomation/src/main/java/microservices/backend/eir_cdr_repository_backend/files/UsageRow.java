package microservices.backend.eir_cdr_repository_backend.files;

public class UsageRow {
	private String aNumber;
	private String aIMSI;
	private String aPartyCarrierCode;
	private String bPartyId;
	private String bPartyName;
	private String bPartyCarrierCode;
	private String fullPath;
	private String cascadeCarrierCode;
	private String chargeStartDateTime;
	private String mmsType;
	private String eventSource;
	private String duration;
	private String volume;
	private String charge;
	private String daBalance;
	private String uniqueExternalRef;
	
	public UsageRow(String usageRowString) {
		String[] bits = usageRowString.split("\\|");
		
		// extract the key information from the CDR record string
		aNumber=bits[16];
		aIMSI=bits[17];
		aPartyCarrierCode=bits[21];
		bPartyId=bits[24];
		bPartyCarrierCode=bits[29];
		fullPath=bits[42];
		chargeStartDateTime=bits[45];
		duration=bits[54];
		charge=bits[57];
		mmsType=bits[68];
		daBalance=bits[79];
		uniqueExternalRef=bits[80];
		/*
		System.out.println(bits.length);
		for(int i=0;i<bits.length;i++) {
			System.err.println(i + "\t" + bits[i]);
		}
		System.err.println("DONE");
		*/
	}

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getaIMSI() {
		return aIMSI;
	}

	public void setaIMSI(String aIMSI) {
		this.aIMSI = aIMSI;
	}

	public String getaPartyCarrierCode() {
		return aPartyCarrierCode;
	}

	public void setaPartyCarrierCode(String aPartyCarrierCode) {
		this.aPartyCarrierCode = aPartyCarrierCode;
	}

	public String getbPartyId() {
		return bPartyId;
	}

	public void setbPartyId(String bPartyId) {
		this.bPartyId = bPartyId;
	}

	public String getbPartyName() {
		return bPartyName;
	}

	public void setbPartyName(String bPartyName) {
		this.bPartyName = bPartyName;
	}

	public String getbPartyCarrierCode() {
		return bPartyCarrierCode;
	}

	public void setbPartyCarrierCode(String bPartyCarrierCode) {
		this.bPartyCarrierCode = bPartyCarrierCode;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getCascadeCarrierCode() {
		return cascadeCarrierCode;
	}

	public void setCascadeCarrierCode(String cascadeCarrierCode) {
		this.cascadeCarrierCode = cascadeCarrierCode;
	}

	public String getChargeStartDateTime() {
		return chargeStartDateTime;
	}

	public void setChargeStartDateTime(String chargeStartDateTime) {
		this.chargeStartDateTime = chargeStartDateTime;
	}

	public String getMmsType() {
		return mmsType;
	}

	public void setMmsType(String mmsType) {
		this.mmsType = mmsType;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getDaBalance() {
		return daBalance;
	}

	public void setDaBalance(String daBalance) {
		this.daBalance = daBalance;
	}

	public String getUniqueExternalRef() {
		return uniqueExternalRef;
	}

	public void setUniqueExternalRef(String uniqueExternalRef) {
		this.uniqueExternalRef = uniqueExternalRef;
	}
}


package microservices.frontend.myeir.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Variables {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String token;
	private String serviceId;
	private String msisdn;
	private String otpUuid;
	private String otpCode;
	private String nddPreference;
	private String emailAddress;
	private String username;
	private String password;
	private String brand;
	private String offerType;
	private String channelType;
	private PlanIDSet planIds;

	public Variables() {
		super();
		planIds=new PlanIDSet();
		// TODO Auto-generated constructor stub
	}

	public String getBrand() {
		return brand;
	}

	public PlanIDSet getPlanIds() {
		return planIds;
	}

	public void setPlanIds(PlanIDSet planIds) {
		this.planIds = planIds;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOtpUuid() {
		return otpUuid;
	}

	public void setOtpUuid(String otpUuid) {
		this.otpUuid = otpUuid;
	}

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	public String getNddPreference() {
		return nddPreference;
	}

	public void setNddPreference(String nddPreference) {
		this.nddPreference = nddPreference;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

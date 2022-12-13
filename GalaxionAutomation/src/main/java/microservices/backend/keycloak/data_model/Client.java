package microservices.backend.keycloak.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {

	private String id;
	private int enabled;
	private int fullScopeAllowed;
	private String clientId;
	private int notBefore;
	private int publicClient;
	private String secret;
	private String baseUrl;
	private int bearerOnly;
	private String managementUrl;
	private int surrogateAuthRequired;
	private String realmId;
	private String protocol;
	private int nodeReregTimeout;
	private int frontchannelLogout;
	private int consentRequired;
	private String name;
	private int serviceAccountsEnabled;
	private String clientAuthenticatorType;
	private String rootUrl;
	private String description;
	private String registrationToken;
	private int standardFlowEnabled;
	private int implicitFlowEnabled;
	private int directAccessGrantsEnabled;

	public Client() {

	}

	public Client(ResultSet rs) {
		try {
			id = rs.getString("ID");
			enabled = rs.getInt("ENABLED");
			fullScopeAllowed = rs.getInt("FULL_SCOPE_ALLOWED");
			clientId = rs.getString("CLIENT_ID");
			notBefore = rs.getInt("NOT_BEFORE");
			publicClient = rs.getInt("PUBLIC_CLIENT");
			secret = rs.getString("SECRET");
			baseUrl = rs.getString("BASE_URL");
			bearerOnly = rs.getInt("BEARER_ONLY");
			managementUrl = rs.getString("MANAGEMENT_URL");
			surrogateAuthRequired = rs.getInt("SURROGATE_AUTH_REQUIRED");
			realmId = rs.getString("REALM_ID");
			protocol = rs.getString("PROTOCOL");
			nodeReregTimeout = rs.getInt("NODE_REREG_TIMEOUT");
			frontchannelLogout = rs.getInt("FRONTCHANNEL_LOGOUT");
			consentRequired = rs.getInt("CONSENT_REQUIRED");
			name = rs.getString("NAME");
			serviceAccountsEnabled = rs.getInt("SERVICE_ACCOUNTS_ENABLED");
			clientAuthenticatorType = rs.getString("CLIENT_AUTHENTICATOR_TYPE");
			rootUrl = rs.getString("ROOT_URL");
			description = rs.getString("DESCRIPTION");
			registrationToken = rs.getString("REGISTRATION_TOKEN");
			standardFlowEnabled = rs.getInt("STANDARD_FLOW_ENABLED");
			implicitFlowEnabled = rs.getInt("IMPLICIT_FLOW_ENABLED");
			directAccessGrantsEnabled = rs.getInt("DIRECT_ACCESS_GRANTS_ENABLED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getFullScopeAllowed() {
		return fullScopeAllowed;
	}

	public void setFullScopeAllowed(int fullScopeAllowed) {
		this.fullScopeAllowed = fullScopeAllowed;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(int notBefore) {
		this.notBefore = notBefore;
	}

	public int getPublicClient() {
		return publicClient;
	}

	public void setPublicClient(int publicClient) {
		this.publicClient = publicClient;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getBearerOnly() {
		return bearerOnly;
	}

	public void setBearerOnly(int bearerOnly) {
		this.bearerOnly = bearerOnly;
	}

	public String getManagementUrl() {
		return managementUrl;
	}

	public void setManagementUrl(String managementUrl) {
		this.managementUrl = managementUrl;
	}

	public int getSurrogateAuthRequired() {
		return surrogateAuthRequired;
	}

	public void setSurrogateAuthRequired(int surrogateAuthRequired) {
		this.surrogateAuthRequired = surrogateAuthRequired;
	}

	public String getRealmId() {
		return realmId;
	}

	public void setRealmId(String realmId) {
		this.realmId = realmId;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getNodeReregTimeout() {
		return nodeReregTimeout;
	}

	public void setNodeReregTimeout(int nodeReregTimeout) {
		this.nodeReregTimeout = nodeReregTimeout;
	}

	public int getFrontchannelLogout() {
		return frontchannelLogout;
	}

	public void setFrontchannelLogout(int frontchannelLogout) {
		this.frontchannelLogout = frontchannelLogout;
	}

	public int getConsentRequired() {
		return consentRequired;
	}

	public void setConsentRequired(int consentRequired) {
		this.consentRequired = consentRequired;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServiceAccountsEnabled() {
		return serviceAccountsEnabled;
	}

	public void setServiceAccountsEnabled(int serviceAccountsEnabled) {
		this.serviceAccountsEnabled = serviceAccountsEnabled;
	}

	public String getClientAuthenticatorType() {
		return clientAuthenticatorType;
	}

	public void setClientAuthenticatorType(String clientAuthenticatorType) {
		this.clientAuthenticatorType = clientAuthenticatorType;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegistrationToken() {
		return registrationToken;
	}

	public void setRegistrationToken(String registrationToken) {
		this.registrationToken = registrationToken;
	}

	public int getStandardFlowEnabled() {
		return standardFlowEnabled;
	}

	public void setStandardFlowEnabled(int standardFlowEnabled) {
		this.standardFlowEnabled = standardFlowEnabled;
	}

	public int getImplicitFlowEnabled() {
		return implicitFlowEnabled;
	}

	public void setImplicitFlowEnabled(int implicitFlowEnabled) {
		this.implicitFlowEnabled = implicitFlowEnabled;
	}

	public int getDirectAccessGrantsEnabled() {
		return directAccessGrantsEnabled;
	}

	public void setDirectAccessGrantsEnabled(int directAccessGrantsEnabled) {
		this.directAccessGrantsEnabled = directAccessGrantsEnabled;
	}

}
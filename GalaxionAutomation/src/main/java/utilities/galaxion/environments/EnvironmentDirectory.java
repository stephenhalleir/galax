package utilities.galaxion.environments;

import utilities.config.ConfigReader;
import utilities.galaxion.environments.excel_reader.EnvironmentExcelConfigReader;

public class EnvironmentDirectory {

	// keys for APIs
	private final static String order_management_key = "API.EIR-ORDER-MANAGEMENT-BACKEND";
	private final static String subscription_management_key = "API.EIR-SUBSCRIPTION-MANAGEMENT-BACKEND";

	// keys for GoMo URLs
	private final static String myGoMoURLKey = "UI.GOMO.MYGOMO";
	private final static String gomoEShopURLKey = "UI.GOMO.ESHOP";
	private final static String gomoWordpressURLKey = "UI.GOMO.WORDPRESS";
	private final static String gomoCsrUiURLKey = "UI.GOMO.CSRUI";
	private final static String b2bCrmUiURLKey = "UI.B2B.CRMUI";

	// keys for CRM UI URLs
	private final static String paygCrmUiURLKey = "UI.PAYG.CRMUI";

	// keys for logins
	private final static String gomoCSRUILoginUsernameKey = "GOMO.CSR.USERNAME";
	private final static String gomoCSRUILoginPasswordKey = "GOMO.CSR.PASSWORD";
	private final static String paygTelesalesUsernameKey = "EIR.TELESALES_AGENT.USERNAME";
	private final static String paygTelesalesPasswordKey = "EIR.TELESALES_AGENT.PASSWORD";
	private final static String paygRetailUsernameKey = "EIR.RETAIL_AGENT.USERNAME";
	private final static String paygRetailPasswordKey = "EIR.RETAIL_AGENT.PASSWORD";
	private final static String b2bAgentUsernameKey = "EIR.B2B_AGENT.USERNAME";
	private final static String b2bAgentPasswordKey = "EIR.B2B_AGENT.PASSWORD";
	private final static String b2bAgentEmailKey="EIR.B2B_AGENT.EMAIL";
	private final static String tecrepUsernameKey="TECREP_ADMIN.USERNAME";
	private final static String tecrepPasswordKey="TECREP_ADMIN.PASSWORD";

	// keys for MariaDB
	private final static String mariaDBHostKey = "MARIADB.HOST";
	private final static String mariaDBPortKey = "MARIADB.PORT";
	private final static String mariaDBUsernameKey = "MARIADB.USERNAME";
	private final static String mariaDBPasswordKEy = "MARIADB.PASSWORD";
	
	// keys for api endpoints
	private final static String apiDocumentsKey="API.EIR-DOCUMENT-MANAGEMENT-BACKEND";
	private final static String apiKeycloakKey="API.KEYCLOAK";
	private final static String apiInventoryManagementKey="API.EIR-INVENTORY-MANAGEMENT-BACKEND";
	private final static String apiBulkKey="API.EIR-BULK_BACKEND";
	private final static String apiProvFacadeKey="API.EIR-PROVISIONING-FACADE";
	private final static String apiAddressFinderKey="API.EIR-ADDRESS-FINDER-BACKEND";
	private final static String apiCustomerOfferKey="API.EIR-CUSTOMER-OFFER-BACKEND";
	private final static String apiBillingAbstractorKey="API.EIR-BILLING-ABSTRACTOR-BACKEND";
	private final static String apiProspectKey="API.EIR-PROSPECT-BACKEND";
	private final static String apiCatalogKey="API.EIR-CATALOG-CORE-BACKEND";
	private final static String apiAdjustmentKey="API.EIR-ADJUSTMENT-BACKEND";
	private final static String apiElavonFacade="API.EIR-ELAVON-FACADE-BACKEND";
	private final static String apiB2BCRMUIKey="API.EIR-B2B-CRM-UI-API";
	private final static String apiPAYGCRMUIKey="API.EIR-PAYG-CRM-UI-API";
	private final static String apiPAYGAcquisitionUIKey="API.EIR-PAYG-ACQUISITION-UI-API";
	private final static String apiMyGoMoKey="API.MYGOMO-API";
	private final static String apiEshopKey="API.ESHOP-API";	
	private final static String apiTecrepMonitorKey="API.TECREP-MONITOR-API";
	private final static String apiCSRUIKey="API.EIR-CSR-API";
	
	// rabbitMQ keys
	private final static String rabbitMQURLKey="RABBITMQ.URL";
	private final static String rabbitMqUsernameKey="RABBITMQ.USERNAME";
	private final static String rabbitMqPasswordKey="RABBITMQ.PASSWORD";
	
	// kubernetes
	private final static String kubernetesContextKey="KUBERNETES.CONTEXT";
	
	// mailhog
	private final static String mailhogUsernameKey="MAILHOG.USERNAME";
	private final static String mailhogPasswordKey="MAILHOG.PASSWORD";
	private final static String mailhogURLKey="MAILHOG.HOST";
	
	private final static String eirieUrlKey="UI.EIR_IE";
	
	private final static String mmwKey="MMW";
	
	private final static String fmnpDbHostKey="FMNP_DB.HOST";
	private final static String fmnpDbPortKey="FMNP_DB.PORT";
	private final static String fmnpDbUsernameKey="FMNP_DB.USERNAME";
	private final static String fmnpDbPasswordKey="FMNP_DB.PASSWORD";
	
	private final static String envWikiKey="ENV.WIKI";
	
	public static String getEnvWikiUrl() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(envWikiKey);
	}
	
	public static String getOrderManagementAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(order_management_key);
	}

	public static String getSubscriptionManagementAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(subscription_management_key);
	}

	public static String getMyGoMoURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(myGoMoURLKey);
	}

	public static String getGoMoEShopURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(gomoEShopURLKey);
	}

	public static String getGoMoWordpressURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(gomoWordpressURLKey);
	}

	public static String getGoMoCSRUIURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(gomoCsrUiURLKey);
	}

	public static String getPaygCrmUiURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(paygCrmUiURLKey);
	}
	
	public static String getB2bCrmUiURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(b2bCrmUiURLKey);
	}

	// Maria DB Configuration
	
	public static String getMariaDBHost() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(mariaDBHostKey);
	}

	public static String getMariaDBPort() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(mariaDBPortKey);
	}

	public static String getMariaDBUsername() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(mariaDBUsernameKey);
	}

	public static String getMariaDBPassword() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(mariaDBPasswordKEy);
	}
	
	// API URLs
	
	public static String getAPIAdjustment() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiAdjustmentKey);
	}
	
	public static String getAPIDocuments() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiDocumentsKey);
	}
	
	public static String getAPIBulkKey() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiBulkKey);
	}
	
	public static String getAPIAddressFinder() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiAddressFinderKey);
	}
	
	public static String getAPIProvFacade() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiProvFacadeKey);
	}
	
	public static String getAPIInventory() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiInventoryManagementKey);
	}
	
	public static String getAPIProspect() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiProspectKey);
	}
	
	public static String getAPICustomerOffer() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiCustomerOfferKey);
	}
	
	public static String getAPIBillingAbstractor() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiBillingAbstractorKey);
	}
	
	public static String getAPIElavonFacade() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiElavonFacade);
	}
	
	public static String getAPICatalog() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiCatalogKey);
	}
	
	public static String getB2BCRMAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiB2BCRMUIKey);
	}
	
	public static String getPAYGCRMAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiPAYGCRMUIKey);
	}
	
	public static String getCSRAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiCSRUIKey);
	}
	
	public static String getTecrepMonotirAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiTecrepMonitorKey);
	}
	
	
	public static String getPAYGAcquisitionAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiPAYGAcquisitionUIKey);
	}
	
	public static String getMyGoMoAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiMyGoMoKey);
	}
	
	public static String getEshopAPI() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiEshopKey);
	}
	// URLs
	public static String getKeycloakURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(apiKeycloakKey);
	}
	
	public static String getRabbitMQURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(rabbitMQURLKey);
	}
	
	public static String getEirIEUrl() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(eirieUrlKey);
	}
	
	// Kubernetes
	public static String getKubernetesContext() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(kubernetesContextKey);
	}
	
	public static String getKubernetesHostname() {
		return ConfigReader.getConfigValue("kubernetesHostname");
	}
	
	public static String getKubernetesUsername() {
		return ConfigReader.getConfigValue("kubernetesUsername");
	}
	
	public static String getKubernetesPrivateKeyPath() {
		return ConfigReader.getConfigValue("kubernetesAuthenticationPrivateKeyPath");
	}

	// MailHog
	public static LoginCredentials getMailhogLoginCredentials() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(mailhogUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(mailhogPasswordKey);
		return new LoginCredentials(username, password);
	}
	
	public static String getMailhogURL() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(mailhogURLKey);
	}
	
	/**
	 * Return credentials for a GoMo CSR
	 * 
	 * @return
	 */
	public static LoginCredentials getGoMoCSRLogin() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(gomoCSRUILoginUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(gomoCSRUILoginPasswordKey);
		return new LoginCredentials(username, password);
	}
	
	public static LoginCredentials getRabbitMQCredentials() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(rabbitMqUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(rabbitMqPasswordKey);
		return new LoginCredentials(username, password);
	}

	public static LoginCredentials getEirPAYGTelesalesLogin() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(paygTelesalesUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(paygTelesalesPasswordKey);
		return new LoginCredentials(username, password);
	}

	public static LoginCredentials getEirPAYGRetailLogin() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(paygRetailUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(paygRetailPasswordKey);
		return new LoginCredentials(username, password);
	}
	
	public static LoginCredentials getB2BAgentLogin() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(b2bAgentUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(b2bAgentPasswordKey);
		return new LoginCredentials(username, password);
	}
	
	
	public static LoginCredentials getTecrepAdminLogin() {
		String username = EnvironmentExcelConfigReader.getGalaxionConfigValue(tecrepUsernameKey);
		String password = EnvironmentExcelConfigReader.getGalaxionConfigValue(tecrepPasswordKey);
		return new LoginCredentials(username, password);
	}
	
	public static String getB2BAgentEmailAddress() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(b2bAgentEmailKey);
	}
	
	public static String getMMWEndpoint() {
		return EnvironmentExcelConfigReader.getMMWConfigValue("BASEURL");
	}
	
	public static String getMMWInstance() {
		return EnvironmentExcelConfigReader.getGalaxionConfigValue(mmwKey);
	}
	
	// FMNP DB Configuration
	
		public static String getFmnpDBHost() {
			return EnvironmentExcelConfigReader.getGalaxionConfigValue(fmnpDbHostKey);
		}

		public static String getFmnpDBPort() {
			return EnvironmentExcelConfigReader.getGalaxionConfigValue(fmnpDbPortKey);
		}

		public static String getFmnpDBUsername() {
			return EnvironmentExcelConfigReader.getGalaxionConfigValue(fmnpDbUsernameKey);
		}

		public static String getFmnpDBPassword() {
			return EnvironmentExcelConfigReader.getGalaxionConfigValue(fmnpDbPasswordKey);
		}
}

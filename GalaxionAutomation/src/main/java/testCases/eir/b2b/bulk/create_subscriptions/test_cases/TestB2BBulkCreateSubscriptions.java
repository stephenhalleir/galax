package testCases.eir.b2b.bulk.create_subscriptions.test_cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.generic.RandomStringGenerator;
import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.activate_subscription_file.ActivateSubscriptionFile;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionFile;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionRow;
import microservices.backend.eir_bulk_backend.dao.BulkDAO;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_bulk_backend.utilities.BulkFileGenerator;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.TariffPlan;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_catalog_core_backend.enums.TariffCode;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_provisioning_facade_backend.monitor.ProvisioningFacadeRequestMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import testCases.Services;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.Handset;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.Subscription;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.SubscriptionsDataProvider;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.galaxion.test_data.accounts.DataSetupUtil;
import utilities.generic.files.TextReader;
import utilities.generic.time.WaitUtil;

public class TestB2BBulkCreateSubscriptions extends BaseTest {

	String token;
	
	@Test(enabled = true, description = "B2B > Bulk File: Create and Activate Subscriptions - Ad Hoc [GOVERNMENT]")
	public void testAdHocGov(ITestContext iTestContext) {

		// create a new account
		int billingAccountNumber = DataSetupUtil.createB2BRootAccount(B2BAccountType.GOVERNMENT);
		logInfo("Created a new account " + billingAccountNumber);

		// create a handset
		Handset handset1 = new Handset("GALC3L21S", "", true, false);

		// add a handset to the first subscription ArrayList<LogisticsDTO>
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PUBLICSECTOR_19X, null, new SubscriptionAddonBundle[] {}));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.GOVALLIN20GB_A, null, new SubscriptionAddonBundle[] {}));
		//subscriptions.add(new Subscription(billingAccountNumber, TariffCode.GOVTALKANDTEXT, handset1, new SubscriptionAddonBundle[] {}));

		ActivateSubscriptionFile f = this.createAndActivateSubscriptions(subscriptions);
		this.validateActivation(f);
	}

	@Test(enabled = true, description = "B2B > Bulk File: Create and Activate Subscriptions - Ad Hoc [CORPORATE_EBU]")
	public void testAdHocCorp(ITestContext iTestContext) {

		// create a new account
		int billingAccountNumber = DataSetupUtil.createB2BRootAccount(B2BAccountType.CORPORATE_OR_EBU);
		logInfo("Created a new account " + billingAccountNumber);

		// create a handset
		Handset handset1 = new Handset("GAPP12P1A", "", true, false);

		// add a handset to the first subscription ArrayList<LogisticsDTO>
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.BASIC_24, null, new SubscriptionAddonBundle[] {}));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PERFORMANCE_24, null, new SubscriptionAddonBundle[] {}));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.VIP_SIM, null, new SubscriptionAddonBundle[] {}));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PERFORMANCE_24, null, new SubscriptionAddonBundle[] {}));
		//subscriptions.add(new Subscription(billingAccountNumber, TariffCode.VIP_24, null, new SubscriptionAddonBundle[] {}));
		//subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PREMIUM_24, null, new SubscriptionAddonBundle[] {}));
		/*
		 * subscriptions.add(new Subscription(billingAccountNumber, TariffCode.BASIC_24,
		 * null, new SubscriptionAddonBundle[] { })); subscriptions.add(new
		 * Subscription(billingAccountNumber, TariffCode.DATASIM1GB_24, null, new
		 * SubscriptionAddonBundle[] { }));
		 */
		ActivateSubscriptionFile f = this.createAndActivateSubscriptions(subscriptions);
		this.validateActivation(f);
	}

	/**
	 * Test: Create and activate a single corporate subscription using bulk files
	 * 
	 */
	@Test(enabled = false, description = "B2B > Bulk File: Create and Activate Subscriptions [CORPORATE_EBU]")
	public void testCreateAndActivateB2BCorpSubscriptionBulk(ITestContext iTestContext) {

		// create a new account
		int billingAccountNumber = DataSetupUtil.createB2BRootAccount(B2BAccountType.CORPORATE_OR_EBU);
		logInfo("Created a new account " + billingAccountNumber);

		// create a handset
		Handset handset1 = new Handset("GSAMA52S5", "", true, false);

		// add a handset to the first subscription ArrayList<LogisticsDTO>
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		subscriptions
				.add(new Subscription(billingAccountNumber, TariffCode.BASIC_24, handset1, new SubscriptionAddonBundle[] { SubscriptionAddonBundle.ROAEU1 }));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PREMIUM_24, handset1, null));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PERFORMANCE_24, null, null));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.VIP_SIM, null, null));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.MBB100GB_24, null, null));

		ActivateSubscriptionFile f = this.createAndActivateSubscriptions(subscriptions);
		this.validateActivation(f);
	}

	/**
	 * Test: Create and activate a single corporate subscription using bulk files
	 * 
	 */
	@Test(enabled = false, description = "B2B > Bulk File: Create and Activate Subscriptions [GOVERNMENT]")
	public void testCreateAndActivateB2BGovSubscriptionBulk(ITestContext iTestContext) {

		// create a new account
		int billingAccountNumber = DataSetupUtil.createB2BRootAccount(B2BAccountType.GOVERNMENT);
		logInfo("Created a new account " + billingAccountNumber);

		// create a handset
		Handset handset1 = new Handset("GAPP12P1A", "", true, false);

		// add a handset to the first subscription ArrayList<LogisticsDTO>
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.VIP_24, null,
				new SubscriptionAddonBundle[] { SubscriptionAddonBundle.ADVCHG, SubscriptionAddonBundle.ROAEU1 }));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PREMIUM_24, handset1, null));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.GOVALLIN5GB_A, null, null));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.PUBLICSECTOR_21, null, null));
		subscriptions.add(new Subscription(billingAccountNumber, TariffCode.DATASIM1GB_24, null, null));

		ActivateSubscriptionFile f = this.createAndActivateSubscriptions(subscriptions);
		this.validateActivation(f);
	}

	/**
	 * Generate a random batch of subscriptions for a particular tariff set and then
	 * activate those subscriptions
	 */
	@Test(enabled = false, description = "B2B > Bulk File: Create and activate ALL [GOVERNMENT] offers")
	public void testCreateAndActivateB2BAllGovSubscriptionBulk(ITestContext iTestContext) {

		// create a new account
		int billingAccountNumber = DataSetupUtil.createB2BRootAccount(B2BAccountType.GOVERNMENT);
		logInfo("Created a new account " + billingAccountNumber);

		// read the list of plans
		ArrayList<TariffPlan> plans = CatalogCoreDAO.getB2BGovernmentOffers();

		// get the list of subscriptions based on the plans list
		ArrayList<Subscription> subscriptions = SubscriptionsDataProvider.getSubscriptions(billingAccountNumber, plans);

		// process the files
		ActivateSubscriptionFile f = this.createAndActivateSubscriptions(subscriptions);
		this.validateActivation(f);
	}

	/**
	 * Generate a random batch of subscriptions for a particular tariff set and then
	 * activate those subscriptions
	 */
	@Test(enabled = false, description = "B2B > Bulk File: Create and activate ALL [CORPORATE_OR_EBU] offers")
	public void testCreateAndActivateB2BAllCorpSubscriptionBulk(ITestContext iTestContext) {

		// create a new account
		int billingAccountNumber = DataSetupUtil.createB2BRootAccount(B2BAccountType.CORPORATE_OR_EBU);
		logInfo("Created a new account " + billingAccountNumber);

		// read the list of plans
		ArrayList<TariffPlan> plans = CatalogCoreDAO.getB2BCorporateOffers();

		// get the list of subscriptions based on the plans list
		ArrayList<Subscription> subscriptions = SubscriptionsDataProvider.getSubscriptions(billingAccountNumber, plans);

		// process the files
		ActivateSubscriptionFile f = this.createAndActivateSubscriptions(subscriptions);
		this.validateActivation(f);
	}

	/**
	 * E2E method which creates and processes creation and activation files for a
	 * list of tariffs
	 * 
	 * @param billingAccountID
	 * @param tariffCodes
	 */
	public ActivateSubscriptionFile createAndActivateSubscriptions(ArrayList<Subscription> subscriptions) {

		// create the subscription file
		CreateSubscriptionFile createSubscriptionFile = BulkFileGenerator.generateCreateSubscriptionFile(subscriptions);

		// process the subscription file
		this.processCreateSubscriptionFile(token, createSubscriptionFile);

		// wait for processing to complete
		WaitUtil.waitForSeconds(10);

		// fulfill the orders
		ArrayList<LogisticsDTO> packs = fulfillCreateSubscriptionFile(createSubscriptionFile);

		// create the subscription activation file
		ActivateSubscriptionFile activationFile = BulkFileGenerator.generateActivateSubscriptionFile(createSubscriptionFile, packs);

		// create and process the activation file
		return this.processActivateSubscriptionFile(activationFile);
	}

	/**
	 * Create and process a subscription creation file
	 * 
	 * @param customerAccountNumber
	 * @param tariffCodes           - list of tariff plans to be included in the
	 *                              file
	 * @param sendFile              - if true, send the file to bulk
	 * @return the completed create subscription file object
	 */
	public CreateSubscriptionFile processCreateSubscriptionFile(String token, CreateSubscriptionFile createSubscriptionFile) {

		// write the file contents to the file
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\create_subscription\\" + createSubscriptionFile.getFilename();
		TextReader.writeFile(createSubscriptionFile.toString(), fullFileName);

		// log results
		logPass("Created subscription creation file " + fullFileName + ":<br>" + createSubscriptionFile.toString());

		// upload the file to the bulk service
		Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.CREATE_SUBSCRIPTION, fullFileName);

		logPass("Create subscription file send to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
		System.err.println(response.statusCode() + ", " + response.asString());

		// check that the microservice has responded with a success
		assertEquals(response.statusCode(), 204);

		return createSubscriptionFile;
	}

	/**
	 * Use this method if we want to fulfill a file from the bulk database based on
	 * the filename
	 */

	public ActivateSubscriptionFile processActivateSubscriptionFileByFilename(String createSubscriptionFilename, boolean sendFile) {

		// create the file object
		CreateSubscriptionFile createSubscriptionFile = BulkDAO.getCreateSubscriptionFile(createSubscriptionFilename);

		// fulfill the orders
		ArrayList<LogisticsDTO> packs = fulfillCreateSubscriptionFile(createSubscriptionFile);

		// create the subscription activation file
		ActivateSubscriptionFile activationFile = BulkFileGenerator.generateActivateSubscriptionFile(createSubscriptionFile, packs);

		// create and process the activation file
		return this.processActivateSubscriptionFile(activationFile);
	}

	/**
	 * Generate and process an activation file based on a fulfilled create
	 * subscriber file
	 * 
	 * @param createSubscriptionFile
	 * @param sendFile               - if true, the file will be sent to bulk
	 * @return the activation file object
	 */
	public ActivateSubscriptionFile processActivateSubscriptionFile(ActivateSubscriptionFile activationFile) {

		// write the activation file
		String activationFileName = System.getProperty("user.dir") + "\\files\\bulk\\activate_subscription\\" + activationFile.getFilename();
		TextReader.writeFile(activationFile.toString(), activationFileName);

		logInfo("Created activation creation file " + activationFileName + ":\n" + activationFile.toString());

		// upload the activations file to the bulk service
		Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.SUBSCRIPTION_ACTIVATION, activationFileName);
		System.err.println(response.asString());
		log("Activate subscription file send to the bulk service: Response:<br>" + response.statusCode() + ", " + response.asString());

		// check that the microservice has responded with a success
		assertEquals(response.statusCode(), 204);

		return activationFile;
	}

	public ArrayList<LogisticsDTO> fulfillCreateSubscriptionFile(CreateSubscriptionFile createSubscriptionFile) {
		ArrayList<LogisticsDTO> packsForFile = new ArrayList<LogisticsDTO>();

		// now get the list of external IDs from the file
		for (CreateSubscriptionRow r : createSubscriptionFile.getRows()) {

			// wait 30 seconds for the order creation and read the reference
			String orderReference = OrderManagementMonitor.waitForOrderByExternalReference(r.getExternalOrderRef(), 30);

			// check that the order number has been found
			assertNotNull(orderReference);

			// wait for the order to reach the delivery step
			boolean orderPendingDelivery = OrderManagementMonitor.waitForOrderToReachStep(orderReference,"DELIVERY");
			assertTrue(orderPendingDelivery);

			// process logistics and return the pack used
			ArrayList<LogisticsDTO> packsForThisOrder = Logistics.processLogisticsBackend(orderReference);

			// confirm that 1 pack is used to fulfill the subscription
			assertEquals(packsForThisOrder.size(), 1);

			for (LogisticsDTO pack : packsForThisOrder) {
				packsForFile.add(pack);
			}
		}

		return packsForFile;
	}

	public void validateActivation(ActivateSubscriptionFile activationFile) {

		// wait for all msisdns to complete
		for (String msisdn : activationFile.getMsisdns()) {
			ProvisioningFacadeRequestMonitor prov = new ProvisioningFacadeRequestMonitor(msisdn);
			boolean provisioningComplete = prov.pollForProvisioningCompletion(300);
		}

		// check that all are completed successfully
		for (String msisdn : activationFile.getMsisdns()) {
			ProvisioningFacadeRequestMonitor prov = new ProvisioningFacadeRequestMonitor(msisdn);
			assertEquals(prov.getProvisioningStatus(), "COMPLETED");
			microservices.backend.eir_subscription_management_backend.data_model.Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
			assertEquals(SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID()).getStatus(), "ACTIVE");
		}
	}

	/**
	 * A data-provider which returns all CORPORATE_OR_EBU tariffs from the catalog
	 * 
	 * @return 2D object array
	 */
	@DataProvider(name = "b2b-corp-tariffs")
	public Object[][] dataProviderB2BCorpTariffs() {
		return get2DArrayFromArrayList(CatalogCoreDAO.getB2BCorporateTariffs());
	}

	/**
	 * A data-provider which returns all B2B GOVERNMENT tariffs from the catalog
	 * 
	 * @return 2D object array
	 */
	@DataProvider(name = "b2b-gov-tariffs")
	public Object[][] dataProviderB2BGovTariffs() {
		return get2DArrayFromArrayList(CatalogCoreDAO.getB2BGovernmentTariffs());
	}

	/**
	 * A data-provider which returns all CORPORATE_OR_EBU offers from the catalog
	 * along with 1 tariff plan per offer
	 * 
	 * @return 2D object array
	 */
	@DataProvider(name = "b2b-corp-offers")
	public static Object[][] dataProviderB2BCorpOffers() {
		return get2DArrayFromArrayList(CatalogCoreDAO.getB2BCorporateOffers());
	}

	/**
	 * A data-provider which returns all GOVERNMENT offers from the catalog along
	 * with 1 tariff plan per offer
	 * 
	 * @return 2D object array
	 */
	@DataProvider(name = "b2b-gov-offers")
	public Object[][] dataProviderB2BGovOffers() {
		return get2DArrayFromArrayList(CatalogCoreDAO.getB2BGovernmentOffers());
	}

	public ArrayList<TariffPlan> getRandomPlans(ArrayList<TariffPlan> plans, int amount) {

		ArrayList<TariffPlan> randomPlans = new ArrayList<TariffPlan>();

		for (int i = 0; i < amount; i++) {
			int rand = RandomStringGenerator.getRandomInteger(0, plans.size() - 1);
			randomPlans.add(plans.get(rand));
		}

		return randomPlans;
	}

	/*
	 * Get a 2D array based on an arraylist of tariff plans
	 */
	public static Object[][] get2DArrayFromArrayList(ArrayList<TariffPlan> objects) {
		Object[][] o = new Object[objects.size()][1];

		for (int i = 0; i < objects.size(); i++) {
			o[i][0] = objects.get(i);
		}

		return o;
	}
	
	@BeforeClass
	public void setup() {
		LoginCredentials login=EnvironmentDirectory.getB2BAgentLogin();
		Client c = KeycloakDAO.getClient(Services.BULK, Realm.eir);
		token = KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}

}
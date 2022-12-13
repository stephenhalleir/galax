package testCases.eir.b2b.bulk;

import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import io.restassured.response.Response;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferFile;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferRow;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.enums.TariffCode;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.galaxion.dao.GalaxionDAO;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import testCases.Services;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class TestB2BBulk2 extends BaseTest {

	private int billingAccountID;
	private String token;

	@Test(description = "eir B2B > Bulk > Change Offer")
	public void testB2BBulkChangeOffer(ITestContext iTestContext) { 
		// get the test data from the environments file
		billingAccountID = TestDataManager.getB2BCorporateAccount();
		billingAccountID=80000019;
		
		// get a service on the account
		Service service = SubscriptionManagementDAO.getActiveService(billingAccountID);
		service=SubscriptionManagementDAO.getServiceByMSISDN("0858540778");
	
		Subscription subscription=SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
		boolean subOutOfContract=SubscriptionManagementDAO.isContractEnded(subscription.getContractID());
		
		TariffCode tariffCode = TariffCode.VIP_SIM;
		boolean recontract=true;
		String overrideTariffCharge="";
		String deviceCode="";
		String deviceCharge="";
		String deductFromHWFund="";
		
		String earlyCeaseCharge="";
		
		if(recontract && !subOutOfContract) {
			earlyCeaseCharge="0";
		}
		
		this.processChangeOfferOnBulk(billingAccountID, service.getName(), tariffCode, recontract,earlyCeaseCharge,overrideTariffCharge, deviceCode, deviceCharge,deductFromHWFund, true);
	}
	
	
	/**
	 * Method for processing a Change Offer file
	 * @param msisdn
	 * @param tariffCode
	 * @param sendFile
	 */
	public void processChangeOfferOnBulk(int billingAccountID, String msisdn, TariffCode tariffCode, boolean recontract, String earlyCeaseCharge, String overrideTariffCharge, String deviceCode, String deviceCharge, String deductFromHWFund, boolean sendFile) {

		int accountID = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);

		// calculate the email address
		String deliveryContactEmailAddress = GalaxionDAO.getOwnerEmailAddressByAccountID(accountID);

		String tariffCodeString = tariffCode.toString().replace("_", "-");
		String offerCode = CatalogCoreDAO.getTariffPlan(tariffCodeString).getOfferCode();

		ChangeOfferRow row = new ChangeOfferRow();
		
		if(!earlyCeaseCharge.equals("0")) {
			row.setApprovedBy("Steve Test");
		}
		row.setCustomerAccountNumber(Integer.toString(billingAccountID));
		row.setMsisdn(msisdn);
		row.setChangeToOfferCode(offerCode);
		row.setChangeToOfferTariffPlanCode(tariffCodeString);
		row.setChangeToOfferTariffPlanCharge(overrideTariffCharge);
		row.setReContract(recontract);
		row.setEarlyCeaseCharge(earlyCeaseCharge);
		row.setDeviceCode(deviceCode);
		row.setDeductDevChargeFromHW(deductFromHWFund);
		row.setDeviceCharge(deviceCharge);
		row.setDeliveryContactEmailAddress(deliveryContactEmailAddress);
		
		ChangeOfferFile file = new ChangeOfferFile();
		
		file.addRow(row);

		System.out.println(file);

		// calculate the filename
		String filename = "change_offer_" + Timestamp.getUniqueTimestamp() + ".csv";
		String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\change_offer\\" + filename;

		// write the file
		TextReader.writeFile(file.toString(), fullFileName);

		logInfo(filename + ":\n" + file.toString());
		System.out.println(filename + ":\n" + file.toString());

		// send the file, if required
		if (sendFile) {

			// upload the file to the bulk service
			Response response = BulkAPI.uploadBulkFile(token, BulkRefFlow.CHANGE_OFFER, fullFileName);

			logPass("CHANGE_OFFER file sent to the bulk service. Response: <br>" + response.statusCode() + ", " + response.asString());
			System.err.println(response.statusCode() + ", " + response.asString());

			// check that the microservice has responded with a success
			assertEquals(response.statusCode(), 204);
		}
	}
	
	@BeforeClass
	public void setUp() {
		LoginCredentials login=EnvironmentDirectory.getB2BAgentLogin();
		Client c = KeycloakDAO.getClient(Services.BULK, Realm.eir);
		token = KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
}

package utilities.galaxion.test_data.accounts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import framework.data_providers.addons.B2BBillableSetting;
import framework.test_data.generic.RandomStringGenerator;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_cdr_repository_backend.utilities.NonUsageGenerator;
import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import microservices.backend.eir_logistics_backend.utility.InventoryManager;
import microservices.backend.eir_notification_center_backend.api.NotificationCenterAPI;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_otp_verification_backend.dao.OtpDAO;
import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.frontend.eir_b2b_crm_ui_frontend.B2BCRMAPI;
import utilities.generic.api.APITransaction;
import utilities.generic.time.WaitUtil;

public class DataSetupUtil {

	/**
	 * Add 3 web notifications to an account
	 * 
	 * @param billingAccountID
	 */
	public static void addWebNotifications(int billingAccountID) {
		NotificationCenterAPI.publishWebMessagePortScheduled(billingAccountID,"20/02/2022","0860850850");
		NotificationCenterAPI.publishWebMessageAdjustmentApplied(billingAccountID);
		NotificationCenterAPI.publishWebMessageCardExpired(billingAccountID);
	}

	public static void clearOTPCodes(String msisdn) {
		OtpDAO.clearOTPCodes(msisdn);
	}

	public static void convertToB2C(String list) {
		String[] msisdns= list.split(",");
		for (int i = 0; i < msisdns.length; i++) {
			String msisdn = msisdns[i].trim();
			ChangeOfferUtil.updateEmailAndCreditScore(msisdn);
			int billingAccountID = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
			ChangeOfferUtil.updatePayerBrand(billingAccountID, "EIR");
			ChangeOfferUtil.createContract(msisdn);
			Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
			System.err.println("SUBMITTING CHANGE OFFER");
			String orderReference = ChangeOfferUtil.submitChangeOffer(msisdn);
			System.err.println("WAITING FOR ORDER TO COMPLETE");
			OrderManagementMonitor.waitForOrderToComplete(orderReference);
			ChangeOfferUtil.reprovisionAsEirNetworkProfile(msisdn);
			
			/*
			SubscriptionManagementDAO.createUsage(service.getId(), 32103, "WIFI_GUEST_U");
			SubscriptionManagementDAO.createUsage(service.getId(), 32104, "WIFI_CALLING");
			SubscriptionManagementDAO.createUsage(service.getId(), 32105, "5G_ENABLED_U");
			SubscriptionManagementDAO.createUsage(service.getId(), 32106, "LTE_U");
			SubscriptionManagementDAO.createUsage(service.getId(), 32107, "VOICEMAIL_U");
			SubscriptionManagementDAO.createUsage(service.getId(), 32108, "DRAOC_U");
			*/
			System.err.println(msisdn + " done");
		}
		
		System.err.println("All done");
	}
	
	public static int createB2BRootAccount(B2BAccountType accountType) {

		String token = B2BCRMAPI.getToken();

		// generate a random company name fro the new account
		String companyName = RandomStringGenerator.getRandomCompanyName();

		// make a call to the API to generate a new order reference
		APITransaction t = B2BCRMAPI.generateNewOrderNumber(token);
		assertEquals(200, t.getResponse().statusCode());

		// read the reference from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String orderReference = (String) jsonPathEvaluator.get("reference");

		// now make a call to the API to generate the order
		t = B2BCRMAPI.createAccount(token, 0, companyName, accountType, orderReference);
		assertEquals(201, t.getResponse().statusCode());

		// wait for the order to complete
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(orderReference);
		System.out.println("Order completed: " + orderCompleted);

		// check that the order is COMPLETED successfully
		assertTrue(orderCompleted);

		// return the account.id
		int accountID = OrderManagementDAO.getSubscriptionAccountIDForOrderReference(orderReference);
		return SubscriptionManagementDAO.getBillingAccountIDForAccountID(accountID);
	}
	

	/**
	 * Generate a number of anonymous prepay subscribers in Galaxion using non-usage event files
	 * @param numRecords
	 * @param tariffCode
	 * @return list of MSISDNs
	 */
	public static ArrayList<String> generatePrepayData(int numRecords, String tariffCode) {

		// create 2 empty lists to hold the MSISDNs
		ArrayList<String> msisdns = new ArrayList<String>();
		ArrayList<String> activeMsisdns = new ArrayList<String>();

		// for each subscriber requested (in numRecords)
		for(int i=0;i<numRecords;i++) {
			
			// get a pack from the inventory file
			EquipmentPack pack = InventoryManager.getInventory("0SIMUPREE");
			
			// add the MSISDN and IMSI to the batch table, if not already there
			ProvisioningFacadeDAO.addToBatchTable(pack.getMsisdn(), pack.getImsi(), tariffCode);
			
			// create and upload a type 2 non-usage event file for the service
			NonUsageGenerator.processNonUsageFile(pack.getMsisdn(), 2);
			
			// add the MSISDN to the list
			msisdns.add(pack.getMsisdn());
		}
		
		// for each MSISDN in the list
		for(String msisdn: msisdns) {
			long waitEndTime = System.currentTimeMillis()+60000;
			Service service;
			
			// wait up to 60 seconds for the service to be created and activated
			while(System.currentTimeMillis()<waitEndTime) {
				service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
				
				// if the service does not exist, wait 5 seconds
				if(service==null) {
					WaitUtil.waitForSeconds(5);
				}
				// else, if the service is created, check whether the associated subscription is active
				else {
					Subscription sub = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
					if(sub.getStatus().equals("ACTIVE")) {
						
						// if active, add to the active MSISDNs list
						activeMsisdns.add(service.getName());
						break;
					}
				}
			}
		}
		
		// display the active MSISDNs
		System.out.println("Displaying msisdns:");
		for(String msisdn:activeMsisdns) {
			System.out.println("MSISDN active: " + msisdn);
		}
		return msisdns;
	}	
}

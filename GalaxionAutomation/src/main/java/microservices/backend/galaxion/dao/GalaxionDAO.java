package microservices.backend.galaxion.dao;

import java.util.ArrayList;

import framework.test_data.galaxion.TestDataManager;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.RatingSubtotal;
import microservices.backend.eir_cdr_repository_backend.data_model.UsagePartition;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Offer;
import microservices.backend.eir_payment_center_backend.dao.PaymentCenterDAO;
import microservices.backend.eir_payment_center_backend.data_model.CardPaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.Payer;
import microservices.backend.eir_payment_center_backend.data_model.PaymentMethod;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.RefBillingCycle;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import utilities.generic.time.Timestamp;

public class GalaxionDAO {

	public static String getOwnerEmailAddressByAccountID(int accountID) {
		String uuid=SubscriptionManagementDAO.getB2BContactUuidForAccount(accountID, "OWNER");
		return ContactManagementDAO.getEmailAddressForUUID(uuid);
	}
	
	public static String getEmailAddressForAccountID(int accountID) {
		String contactUuid=SubscriptionManagementDAO.getContactUuidForAccountID(accountID);
		return ContactManagementDAO.getEmailAddressForUUID(contactUuid);
	}
	
	public static String getOwnerEmailAddress(int billingAccountID) {
		int accountID=SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
		return getOwnerEmailAddressByAccountID(accountID);
	}
	
	public static ArrayList<Service> getServicesProvisionedForOrder(String reference){
		
		// safeguard against a null value here
		if(reference==null) {
			System.out.println("Error: GalaxionDAO.getServicesProvisionedForOrder(): reference is null");
			return null;
		}
		
		ArrayList<Service> services = new ArrayList<Service>();
		
		// get the list of offers on an order
		ArrayList<Offer> offers = OrderManagementDAO.getOffersOnOrder(reference);
		
		//for each offer
		for(Offer offer:offers) {
			
			// get the offer's subscription ID
			int subscriptionID=offer.getSubscriptionID();
			
			// find the corresponding service for the subscription ID
			Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(subscriptionID);
			
			// add the service to the list
			services.add(service);
		}
		
		return services;
	}
	
	
	/*
	public static UsagePartition getUsagePartition(String msisdn, String billingPeriod){
		
		// read the billing account ID and account details
		int billingAccountID=SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		
		// get the subscriber's bill cycle name
		Account acc=SubscriptionManagementDAO.getAccountByBillingAccountID(billingAccountID);
		int billCycleID=acc.getBillingCycleID();
		RefBillingCycle billCycle=SubscriptionManagementDAO.getBillCycle(billCycleID);
		String billCycleName=billCycle.getName();
		
		// read the service details
		Service service=SubscriptionManagementDAO.getActiveService(billingAccountID);
		
		// determine the partition ID based on the bill cycle name and billing period
		return CDRRepoDAO.getUsagePartition(billingPeriod, billCycleName);
	}
	*/
	
	public static void main(String [] args) {
		
	}
}

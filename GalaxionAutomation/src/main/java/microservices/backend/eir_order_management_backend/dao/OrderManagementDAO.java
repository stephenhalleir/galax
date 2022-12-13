package microservices.backend.eir_order_management_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_inventory_management_backend.objects.ExertisRequest;
import microservices.backend.eir_order_management_backend.data_model.Event;
import microservices.backend.eir_order_management_backend.data_model.HardwareFund;
import microservices.backend.eir_order_management_backend.data_model.Item;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.Offer;
import microservices.backend.eir_order_management_backend.data_model.OrderManagementAccount;
import microservices.backend.eir_order_management_backend.data_model.Payment;
import microservices.backend.eir_order_management_backend.data_model.PricePlan;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_order_management_backend.data_model.QuartzTrigger;
import microservices.backend.eir_order_management_backend.data_model.Task;
import microservices.backend.eir_order_management_backend.data_model.TerminationRequest;
import microservices.backend.eir_order_management_backend.enums.EventType;
import microservices.backend.eir_order_management_backend.enums.OrderStatus;
import microservices.backend.eir_order_management_backend.enums.OrderType;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.generic.time.WaitUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class OrderManagementDAO {

	/**
	 * GET an order using the reference
	 * 
	 * @param order reference
	 * @return order object
	 */
	public static ProductOrder getOrderByReference(String reference) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_BY_REFERENCE");
		query = query.replace("$reference", reference);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new ProductOrder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ProductOrder getOrderByID(int id) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_BY_ID");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new ProductOrder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Event getEvent(int eventID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_EVENT");
		query = query.replace("$id", Integer.toString(eventID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new Event(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static OrderManagementAccount getOrderManagementAccount(int orderID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ACCOUNT_FOR_ORDER");
		query = query.replace("$id", Integer.toString(orderID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new OrderManagementAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Event getMostRecentEventOfType(EventType type) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_MOST_RECENT_EVENT_OF_TYPE");
		query = query.replace("$eventType", type.toString());

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new Event(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static OmService getMostRecentOrderService(int serviceID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_MOST_RECENT_ORDER_SERVICE_FOR_SERVICE_ID");
		query = query.replace("$serviceID", Integer.toString(serviceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new OmService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ProductOrder getProductOrderByEventID(int eventID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_BY_EVENT_ID");
		query = query.replace("$eventID", Integer.toString(eventID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new ProductOrder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get an order using the external_id
	 * 
	 * @param externalReference
	 * @return order object
	 */
	public static ProductOrder getOrderByExternalReference(String externalReference) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_BY_EXTERNAL_ID");
		query = query.replace("$external_reference", externalReference);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new ProductOrder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get an order using the external_id
	 * 
	 * @param externalReference
	 * @return order object
	 */
	public static ArrayList<ProductOrder> getOrdersForBillingAccountID(int billingAccountID) {

		ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDERS_FOR_BILLING_ACCOUNT_ID");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				orders.add(new ProductOrder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}
	
	/**
	 * Get an order using the external_id
	 * 
	 * @param externalReference
	 * @return order object
	 */
	public static ArrayList<ProductOrder> getOrdersForSubsAccountID(int accountID) {

		ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDERS_BY_SUBSCRIPTION_ACCOUNT_ID");
		query = query.replace("$id", Integer.toString(accountID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				orders.add(new ProductOrder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	/**
	 * Get the list of tasks linked to an order
	 * 
	 * @param reference
	 * @return ArrayList<Task>
	 */
	public static ArrayList<Task> getTasks(String reference) {

		ArrayList<Task> tasks = new ArrayList<Task>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_TASKS_FOR_ORDER");
		query = query.replace("$reference", reference);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				tasks.add(new Task(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public static String getTaskStatus(String reference, String taskType) {
		ArrayList<Task> tasks = getTasks(reference);
		for (Task task : tasks) {
			if (task.getType().equals(taskType)) {
				return task.getStatus();
			}
		}
		return null;
	}

	/**
	 * Get orders from the product_order table, filtered by type
	 * 
	 * @param billingAccountID
	 * @param orderType
	 * @return list of orders
	 */
	public static ArrayList<ProductOrder> getOrdersByType(int billingAccountID, OrderType orderType) {

		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersForBillingAccountID(billingAccountID);
		ArrayList<ProductOrder> filteredOrders = new ArrayList<ProductOrder>();

		for (ProductOrder order : orders) {
			if (order.getOrder_type().equals(orderType.toString())) {
				filteredOrders.add(order);
			}
		}

		return filteredOrders;
	}

	/**
	 * Get the most recent order of a particular type
	 * 
	 * @param billingAccountID
	 * @param orderType
	 * @return the order ID
	 */
	public static int getMostRecentOrderID(int billingAccountID, OrderType orderType) {
		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersByType(billingAccountID, orderType);

		int index = 0;

		for (ProductOrder order : orders) {
			if (order.getId() > index) {
				index = order.getId();
			}
		}

		return index;
	}

	/**
	 * Get a list of product_orders filtered by billing ID, type and status
	 * 
	 * @param billingAccountID
	 * @param orderType
	 * @param status
	 * @return
	 */
	public static ArrayList<ProductOrder> getOrdersByTypeAndStatus(int billingAccountID, OrderType orderType, OrderStatus status) {

		ArrayList<ProductOrder> orders = OrderManagementDAO.getOrdersByType(billingAccountID, orderType);
		ArrayList<ProductOrder> filteredOrders = new ArrayList<ProductOrder>();

		for (ProductOrder order : orders) {
			if (order.getStatus().equals(status.toString())) {
				filteredOrders.add(order);
			}
		}

		return filteredOrders;
	}

	/**
	 * Return the dynamic attribute value for a particular order item Used for
	 * retrieving iccid, imsi, msisdn values for an order item (use
	 * refDynamicAttributeID 3)
	 * 
	 * @param orderItemID
	 * @param refDynamicAttributeID
	 * @return the string value
	 */
	public static String getDynamicAttributeValueForItemID(int orderItemID, int refDynamicAttributeID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_DYNAMIC_ATTRIBUTE_VALUE_FOR_ITEM");
		query = query.replace("$ref_dynamic_attribute_id", Integer.toString(refDynamicAttributeID));
		query = query.replace("$itemID", Integer.toString(orderItemID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("string_value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	public static OrderManagementAccount getSubsAccountIDForB2BCreateAccountOrder(String reference) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_SUBS_ACCOUNT_ID_FOR_B2B_ORDER");
		query = query.replace("$reference", reference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new OrderManagementAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	


	public static String getOrderStatus(String orderReference) {
		return getOrderByReference(orderReference).getStatus();
	}

	

	public static String getOrderType(String orderReference) {
		return getOrderByReference(orderReference).getOrder_type();
	}

	// check whether a cancellation is scheduled for a particular MSISDN and after a
	// certain time
	public static boolean isCancellationScheduled(String msisdn, String startTime) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_TERMINATION_REQUEST");
		query = query.replace("$msisdn", msisdn);

		if (startTime != null) {
			query = query.replace("$startTime", startTime);
		} else {
			query = query.replace("$startTime", "");
		}

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// update the cancellation trigger to the current timestamp
	public static String rescheduleCancellationToNow(String msisdn) {

		// build the update query to update the QUARTZ_TRIGGERS time
		String epoch = Long.toString(System.currentTimeMillis());
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "UPDATE_QUARTZ_TRIGGERS");
		query = query.replace("$msisdn", msisdn);
		query = query.replace("$epoch", epoch);

		try {
			GalaxionDBUtil.runUpdateQuery(query);
			return epoch;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/*
	public static boolean isTerminationOrderServiceActionComplete(String msisdn) {
		return isOrderServiceActionComplete(msisdn, "TERMINATE_SUBSCRIBER");
	}
	*/



	public static ArrayList<ExertisRequest> getOrderItemIdsForAcquisitionsOrder(String reference) {

		ArrayList<ExertisRequest> sims = new ArrayList<ExertisRequest>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_ITEM_IDS_FOR_ACQUISITION_ORDER");
		query = query.replace("$reference", reference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				ExertisRequest er = new ExertisRequest();
				try {
					er.setOrderItemId(rs.getInt("orderItemId"));
					er.setOfferCode(rs.getString("code"));

					// item type ID (links to catalog
					er.setItemID(rs.getInt("item_id"));
					sims.add(er);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return sims;
	}

	public static int getOrderItemIdsForReplacementOrder(String reference) {

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_ITEM_IDS_FOR_REPLACEMENT_ORDER");
		query = query.replace("$reference", reference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getInt("orderItemId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static String getIccidForReplacementOrder(String reference) {
		ArrayList<Item> r = OrderManagementDAO.getEquipmentItemsForOrder(reference);
		return OrderManagementDAO.getDynamicAttributeValueForItemID(r.get(0).getId(), 3);
	}

	/**
	 * Retrieve the order ID that is linked to a specific order-management item.id
	 * 
	 * @return
	 */
	public static String getOrderReferenceForItemID(int itemID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_REFERENCE_FOR_ITEM_ID");
		query = query.replace("$itemID", Integer.toString(itemID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("reference");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieve the order reference by external ID
	 * 
	 * @return
	 */
	public static String getOrderReferenceForExternalID(String external_id) {
		ProductOrder order = getOrderByExternalReference(external_id);
		return order.getReference();
	}

	// return a list of order numbers for replacement SIM orders awaiting completion
	public static ArrayList<String> getPendingReplacementOrders(String billing_account_id) {

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_REPLACEMENT_ORDERS").replace("$billing_account_id", billing_account_id);
		ArrayList<String> orderNumbers = new ArrayList<String>();

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				orderNumbers.add(rs.getString("reference"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (orderNumbers == null || orderNumbers.size() == 0) {
			return null;
		} else {
			return orderNumbers;
		}
	}

	public static void cleanUpPendingOrders(int billing_account_id) {

		// set pending orders in the product_order table to COMPLETED
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "SET_ORDERS_TO_COMPLETED");
		query = query.replace("$billing_account_id", Integer.toString(billing_account_id));

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// set pending events in the event table to COMPLETED
		query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "SET_EVENTS_TO_COMPLETED");

		query = query.replace("$billing_account_id", Integer.toString(billing_account_id));
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// wait for an order to progress through to the DELIVERY step
	public static boolean isOrderInDeliveryStep(String orderReference) {
		return isOrderInStep(orderReference, "DELIVERY");
	}

	// wait for an order to progress through to a named step
	public static boolean isOrderInStep(String orderReference, String step) {
		ArrayList<Task> tasks = OrderManagementDAO.getTasks(orderReference);

		for (Task task : tasks) {
			if (task.getType().equals(step) && task.getStatus().equals("IN_PROGRESS")) {
				return true;
			}
		}

		return false;
	}

	// count the number of offers on an order
	public static int getOfferCount(String reference) {
		return getOffersOnOrder(reference).size();

	}

	// return order-management.account.subscription_account_id for an order
	public static int getSubscriptionAccountIDForOrderReference(String orderReference) {

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ACCOUNT_DETAILS").replace("$orderReference", orderReference);
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getInt("subscription_account_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static ArrayList<Offer> getOffersOnOrder(String reference) {
		ArrayList<Offer> offers = new ArrayList<Offer>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_OFFERS_ON_ORDER");
		query = query.replace("$reference", reference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				offers.add(new Offer(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return offers;
	}

	/**
	 * Get the list of order-management items linked to an order-management.offer
	 * 
	 * @param offerID
	 * @return
	 */
	public static ArrayList<Item> getItemsForOffer(int offerID) {
		ArrayList<Item> items = new ArrayList<Item>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_EQUIPMENTS_FOR_OFFER");
		query = query.replace("$offerID", Integer.toString(offerID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				Item item = new Item(rs);
				items.add(item);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return items;
	}

	/**
	 * Get a list of items linked to an order
	 * 
	 * @param reference
	 * @return a list of item objects
	 */
	public static ArrayList<Item> getEquipmentItemsForOrder(String reference) {
		ArrayList<Item> items = new ArrayList<Item>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ITEMS_FOR_ORDER");
		query = query.replace("$reference", reference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				Item item = new Item(rs);
				items.add(item);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return items;
	}

	public static String getServiceStatus(int orderServiceId) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_SERVICE");
		query = query.replace("$id", Integer.toString(orderServiceId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("order_service_status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getEmailAddresForOrder(String orderReference) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_EMAIL_ADDRESS_FOR_ORDER");
		query = query.replace("$orderReference", orderReference);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Get an order item by ID
	 */
	public static Item getItem(int id) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_ITEM");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				Item item = new Item(rs);
				return item;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static OmService getOrderService(String msisdn, ProvisioningAction action) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_SERVICE");
		query = query.replace("$msisdn", msisdn);
		query = query.replace("$provisioning_action", action.toString());

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new OmService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static OmService getOrderServiceByRequestID(int requestId) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_OM_SERVICE_BY_REQUEST_ID");
		query = query.replace("$requestId", Integer.toString(requestId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new OmService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static OmService getOrderServiceByRequestIDAndProvisioningAction(int requestId, ProvisioningAction action) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_OM_SERVICE_BY_REQUEST_ID_AND_PROVISIONING_ACTION");
		query = query.replace("$requestId", Integer.toString(requestId));
		query = query.replace("$provisioningAction", action.toString());

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new OmService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static OmService getOrderServiceByID(int omServiceID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_OM_SERVICE_BY_ID");
		query = query.replace("$id", Integer.toString(omServiceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new OmService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieve a termination request from the termination_request table in OM
	 * 
	 * @param orderServiceID
	 * @return
	 */
	public static TerminationRequest getTerminationRequest(int orderServiceID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_TERMINATION_REQUEST");
		query = query.replace("$orderServiceID", Integer.toString(orderServiceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new TerminationRequest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static QuartzTrigger getQuartsTrigger(int orderServiceID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_QUARTZ_TRIGGER");
		query = query.replace("$orderServiceID", Integer.toString(orderServiceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new QuartzTrigger(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<ProductOrder> getOrdersForContact(String contactUuid) {

		ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDERS_FOR_CONTACT");
		query = query.replace("$contactUuid", contactUuid);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				orders.add(new ProductOrder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public static Payment getPaymentForOrder(int orderID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_PAYMENT_FOR_ORDER");
		query = query.replace("$orderID", Integer.toString(orderID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new Payment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	public static int getOrderPrice(String reference) {
		
		int charge=0;
		ProductOrder order = getOrderByReference(reference);
		ArrayList<PricePlan> pricePlans = getChargesForOrder(order.getId());
		
		for(PricePlan pricePlan:pricePlans) {
			charge += pricePlan.getPrice();
		}
		
		return charge;
		
		
	}

	public static String getOrderReference(String id) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_ORDER_REFERENCE");
		query = query.replace("$reference", id);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HardwareFund getHardwareFund(int id) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_HARDWARE_FUND");
		query = query.replace("$hardwareFundId", Integer.toString(id));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new HardwareFund(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HardwareFund getHardwareFundForOrderManagementAccountID(int id) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_HARDWARE_FUND_FOR_OM_ACCOUNT_ID");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new HardwareFund(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Integer> getReplacementOrdersForService(int serviceID) {

		ArrayList<Integer> orderIDs = new ArrayList<Integer>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_REPLACEMENT_SIM_ORDERS_FOR_SERVICE");
		query = query.replace("$serviceID", Integer.toString(serviceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				orderIDs.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderIDs;
	}
	
	public static ArrayList<PricePlan> getChargesForOrder(int orderID) {

		ArrayList<PricePlan> pricePlans = new ArrayList<PricePlan>();

		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "GET_PRICE_PLANS_FOR_ORDER");
		query = query.replace("$orderID", Integer.toString(orderID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				pricePlans.add(new PricePlan(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pricePlans;
	}

	/*
	public static void updateOrderToCompleted(int orderID) {
		String query = ExcelSQLManager.getSQLQuery("ORDER_MANAGEMENT", "COMPLETE_ORDER");
		query = query.replace("$orderID", Integer.toString(orderID));

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clearDownPendingChangeOfferOrders(String msisdn) {
		int pendingOrderID = OrderManagementDAO.getPendingOrderServiceID(msisdn, ProvisioningAction.UPDATE_SUBSCRIBER_PROFILE);
		if (pendingOrderID > 0) {
			updateOrderToCompleted(pendingOrderID);
			System.out.println("Pending change offer order " + pendingOrderID + " set to COMPLETED in Order Management");
		}
	}
	*/
}

package microservices.backend.eir_payment_center_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import microservices.backend.eir_payment_center_backend.data_model.CardPaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.DirectDebitPaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.Payer;
import microservices.backend.eir_payment_center_backend.data_model.Payment;
import microservices.backend.eir_payment_center_backend.data_model.PaymentMethod;
import microservices.backend.eir_payment_center_backend.data_model.RefBank;
import microservices.backend.eir_payment_center_backend.data_model.custom.DetailedPaymentMethod;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class PaymentCenterDAO {

	// specify the name of the tab on the SQL sheet which contains the SQL for this service
	private final static String sqlTabName="PAYMENT_CENTER";
	
	public static String getProviderRefForEmail(String email) {
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_PROVIDER_REF");

		query = query.replace("$email", email);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return rs.getString("provider_ref");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static RefBank getBank(String branchCode) {
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_BANK");

		query = query.replace("$branchCode", branchCode);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new RefBank(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static RefBank getRandomIrishBank() {
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_RANDOM_IRISH_BANK");

		// make the call out to the database
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new RefBank(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Return all credit cards for a billing account ID
	 * @param billingAccountID
	 * @return
	 */
	public static ArrayList<CardPaymentMethod> getPaymentCardsForBillingAccountID(int billingAccountID) {

		// read the payer object
		Payer p = getPayer(billingAccountID);
		return getCardPaymentMethodsForPayer(p.getId());
	}

	/**
	 * Overwrite the correlation_transaction_uuid and provider_transaction_red for a
	 * payment
	 * 
	 * This is required for test automation as these fields need to be unique
	 */
	public static void overwritePaymentCorrelationUuid(String oldRef) {
		// update payment center so that the payment create step doesnt fail
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "UPDATE_TRANSACTION_REF");
		query = query.replace("$uuid", UUID.randomUUID().toString());
		query = query.replace("$newRef", System.currentTimeMillis() + "0000");
		query = query.replace("$oldRef", oldRef);
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the list of PAYMENT_METHOD objects for a payer ID
	 * 
	 * @param payerId
	 * @return a list of payment methods
	 */
	public static ArrayList<PaymentMethod> getPaymentMethodsForPayer(int payerId) {

		ArrayList<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_PAYMENT_METHODS_FOR_PAYER_ID");
		query = query.replace("$payer_id", Integer.toString(payerId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				paymentMethods.add(new PaymentMethod(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paymentMethods;
	}

	public static ArrayList<CardPaymentMethod> getCardPaymentMethodsForPayer(int payerId) {

		ArrayList<CardPaymentMethod> cardPaymentMethods = new ArrayList<CardPaymentMethod>();

		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_CARD_PAYMENT_METHOD_BY_PAYER");
		query = query.replace("$payer_id", Integer.toString(payerId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				CardPaymentMethod card = new CardPaymentMethod(rs);
				card.setCancelled(rs.getString("canceled_at") != null);
				cardPaymentMethods.add(card);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cardPaymentMethods;
	}

	/**
	 * Get a payer from the database for a given billing account ID
	 * 
	 * @param billingAccountID
	 * @return PAYER object
	 */
	public static Payer getPayer(int billingAccountID) {
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_PAYER");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Payer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get a card payment method for a specific ID
	 * 
	 * @param id
	 * @return the card payment method object
	 */
	public static CardPaymentMethod getCardPaymentMethod(int id) {
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_CARD_PAYMENT_METHOD_BY_ID");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				CardPaymentMethod card = new CardPaymentMethod(rs);
				card.setCancelled(rs.getDate("canceled_at") != null);
				return card;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static DirectDebitPaymentMethod getDirectDebitPaymentMethod(int id) {
		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_DD_PAYMENT_METHOD");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new DirectDebitPaymentMethod(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public static ArrayList<Payment> getPaymentsForPayer(int payerID) {
		ArrayList<Payment> payments = new ArrayList<Payment>();

		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_PAYMENTS_FOR_PAYER");
		query = query.replace("$payerID", Integer.toString(payerID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				payments.add(new Payment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payments;
	}

	/**
	 * Get the most recent payment on a payer's account
	 * @param payerID
	 * @return the most recent payment
	 */
	public static Payment getLastPaymentForPayer(int payerID) {
		ArrayList<Payment> payments = getPaymentsForPayer(payerID);

		if (payments.size() == 0) {
			return null;
		}

		Payment paymentToReturn = payments.get(0);

		for (Payment payment : payments) {
			if (payment.getId() > paymentToReturn.getId()) {
				paymentToReturn = payment;
			}
		}

		return paymentToReturn;
	}
	
	public static ArrayList<DetailedPaymentMethod> getDetailedPaymentMethodsForPayer(int payerID) {
		ArrayList<DetailedPaymentMethod> methods = new ArrayList<DetailedPaymentMethod>();

		String query = ExcelSQLManager.getSQLQuery(sqlTabName, "GET_DETAILED_PAYMENT_METHODS_FOR_PAYER");
		query = query.replace("$payerID", Integer.toString(payerID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				methods.add(new DetailedPaymentMethod(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return methods;
	}

}

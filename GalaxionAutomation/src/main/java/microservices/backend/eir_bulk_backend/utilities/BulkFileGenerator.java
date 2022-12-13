package microservices.backend.eir_bulk_backend.utilities;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import framework.test_data.generic.RandomStringGenerator;
import io.restassured.response.Response;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_bulk_backend.api.BulkAPI;
import microservices.backend.eir_bulk_backend.bulk_files.activate_subscription_file.ActivateSubscriptionFile;
import microservices.backend.eir_bulk_backend.bulk_files.activate_subscription_file.ActivateSubscriptionRow;
import microservices.backend.eir_bulk_backend.bulk_files.adjustments_file.AdjustmentsFile;
import microservices.backend.eir_bulk_backend.bulk_files.adjustments_file.AdjustmentsRow;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferFile;
import microservices.backend.eir_bulk_backend.bulk_files.change_offer_file.ChangeOfferRow;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionFile;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionRow;
import microservices.backend.eir_bulk_backend.bulk_files.move_subscription_file.MoveSubscriptionsFile;
import microservices.backend.eir_bulk_backend.bulk_files.move_subscription_file.MoveSubscriptionsRow;
import microservices.backend.eir_bulk_backend.bulk_files.terminate_subscriptions_file.TerminateSubscriptionRow;
import microservices.backend.eir_bulk_backend.bulk_files.terminate_subscriptions_file.TerminateSubscriptionsFile;
import microservices.backend.eir_bulk_backend.enums.BulkRefFlow;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;
import microservices.backend.galaxion.dao.GalaxionDAO;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.Subscription;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class BulkFileGenerator {

	public static CreateSubscriptionFile generateCreateSubscriptionFile(ArrayList<Subscription> subscriptions) {
		CreateSubscriptionFile createSubscriptionFile = new CreateSubscriptionFile();
		createSubscriptionFile.setFilename("create_subscriptions_" + Timestamp.getUniqueTimestamp() + ".csv");

		// for each tariff plan in the list, add a row to the file
		for (Subscription sub : subscriptions) {

			// create the create subscription row
			String deliveryContactEmailAddress = GalaxionDAO.getOwnerEmailAddress(sub.getAccountNumber());
			CreateSubscriptionRow createSubscriptionRow = new CreateSubscriptionRow();
			createSubscriptionRow.setCustomerAccountNumber(Integer.toString(sub.getAccountNumber()));
			createSubscriptionRow.randomize();
			createSubscriptionRow.setDeliveryContactEmailAddress(deliveryContactEmailAddress);
			createSubscriptionRow.setOffer(CatalogCoreDAO.getTariffPlan(sub.getTariffCode()).getOfferCode());
			createSubscriptionRow.setOfferPricePlan(sub.getTariffCode());
			createSubscriptionRow.setSubscriptionName(RandomStringGenerator.getRandomName());
			createSubscriptionRow.setHandset(sub.getHandset());
			createSubscriptionRow.setAddons(sub.getAddons());

			// add the row to the file
			createSubscriptionFile.addRow(createSubscriptionRow);
		}

		return createSubscriptionFile;
	}

	public static ActivateSubscriptionFile generateActivateSubscriptionFile(CreateSubscriptionFile createSubscriptionFile, ArrayList<LogisticsDTO> packs) {

		// create the activations file
		ActivateSubscriptionFile activationFile = new ActivateSubscriptionFile();
		activationFile.setFilename("activate_subscriptions_" + Timestamp.getUniqueTimestamp() + ".csv");

		// for each row in the creation file, create a corresponding row for the
		// activations file

		for (int i = 0; i < createSubscriptionFile.getRows().size(); i++) {
			ActivateSubscriptionRow activationRow = new ActivateSubscriptionRow(createSubscriptionFile.getRows().get(i), packs.get(i));
			activationFile.addRow(activationRow);
		}

		return activationFile;
	}

	public static MoveSubscriptionsFile generateMoveSubscriptionFile(ArrayList<String> msisdns, int destinationBillingAccountID) {

		// create the base file
		MoveSubscriptionsFile moveSubscriptionsFile = new MoveSubscriptionsFile();

		// for each msisdn passed to the method, create a row in the file
		for (String msisdn : msisdns) {
			MoveSubscriptionsRow row = new MoveSubscriptionsRow(msisdn, Integer.toString(destinationBillingAccountID));
			moveSubscriptionsFile.addRow(row);
		}

		// create the csv file
		String filename = "move_subscriptions_" + Timestamp.getUniqueTimestamp() + ".csv";
		moveSubscriptionsFile.setFilename(filename);

		// return the file object
		return moveSubscriptionsFile;
	}

	public static MoveSubscriptionsFile generateMoveSubscriptionFile(String msisdn, int destinationBillingAccountID) {
		ArrayList<String> msisdns = new ArrayList<String>();
		msisdns.add(msisdn);
		return generateMoveSubscriptionFile(msisdns, destinationBillingAccountID);
	}

	/**
	 * Generate an ADJUSTMENT bulk file to carry out a single adjustment
	 * 
	 * @param billingAccountID
	 * @param reason
	 * @param amount
	 * @return the AdjustmentFile object
	 */
	public static AdjustmentsFile generateAdjustmentsFile(int billingAccountID, AdjustmentReason reason, int amount) {

		// auto generate a unique comment
		String comment = "auto_test_adjustment_" + System.currentTimeMillis();

		// generate the file
		AdjustmentsRow row = new AdjustmentsRow(Integer.toString(billingAccountID), reason, Integer.toString(amount), comment, false);
		AdjustmentsFile file = new AdjustmentsFile();
		file.addRow(row);

		// calculate the filename
		String filename = "adjustment_" + Timestamp.getUniqueTimestamp() + ".csv";
		file.setFilename(filename);

		// return the file
		return file;
	}

	public static ChangeOfferFile generateChangeOfferFile(int billingAccountID, String msisdn, String newOfferCode, String newTariffCode) {

		// generate the file
		ChangeOfferRow row = new ChangeOfferRow(billingAccountID, msisdn, newOfferCode, newTariffCode);
		ChangeOfferFile file = new ChangeOfferFile();
		file.addRow(row);

		// calculate the filename
		String filename = "change_offer_" + Timestamp.getUniqueTimestamp() + ".csv";
		file.setFilename(filename);

		// return the file
		return file;
	}

	public static TerminateSubscriptionsFile generateTerminationFile(int billingAccountID, String msisdn, TerminationReason reason,String terminationDate) {

		// generate the file object
		TerminateSubscriptionsFile file = new TerminateSubscriptionsFile();
		TerminateSubscriptionRow r1 = new TerminateSubscriptionRow(billingAccountID, msisdn, reason, terminationDate);
		file.addRow(r1);
		
		// calculate the filename
		String filename = "terminate_subscriptions_" + Timestamp.getUniqueTimestamp() + ".csv";
		file.setFilename(filename);
		return file;
	}
}

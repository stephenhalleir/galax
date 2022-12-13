package microservices.backend.eir_bulk_backend.bulk_files.bar_subscriptions_file;

import microservices.backend.eir_barring_backend.enums.BarringAction;
import microservices.backend.eir_barring_backend.enums.BarringType;

public class BarSubscriptionsRow {

	private String row;
	private String msisdn;
	private String barPartialSuspend;
	private String barOutgoingService;
	private String barHighValueNumbers;
	private String barFullSuspend;
	private String barFullNetwork;
	private String barPremiumSMS;
	private String barPremiumCalls;
	private String barInternationalCalls;
	private String requestor;

	public BarSubscriptionsRow() {
		row = "";
		msisdn = "";
		barPartialSuspend = "";
		barOutgoingService = "";
		barHighValueNumbers = "";
		barFullSuspend = "";
		barFullNetwork = "";
		barPremiumSMS = "";
		barPremiumCalls = "";
		barInternationalCalls = "";
		requestor = "";
	}
	
	public BarSubscriptionsRow(String msisdn) {
		row = "";
		barPartialSuspend = "";
		barOutgoingService = "";
		barHighValueNumbers = "";
		barFullSuspend = "";
		barFullNetwork = "";
		barPremiumSMS = "";
		barPremiumCalls = "";
		barInternationalCalls = "";
		requestor = "CSR";
		this.msisdn = msisdn;
	}

	public void randomize() {

	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	// set the appropriate bar
	public void setBarValue(BarringType barType, BarringAction action) {

		String value=action.toString();
		
		switch (barType) {
		case BAR_FULL_NETWORK:
			barFullNetwork = value;
			break;
		case BAR_OUTGOING_SERVICE:
			barOutgoingService = value;
			break;
		case BAR_HIGH_VALUE_NUMBERS:
			barHighValueNumbers = value;
			break;
		case BAR_PREMIUM_SMS:
			barPremiumSMS = value;
			break;
		case BAR_FULL_SUSPEND:
			barFullSuspend = value;
			break;
		case BAR_PARTIAL_SUSPEND:
			barPartialSuspend = value;
			break;
		case BAR_PREMIUM_CALLS:
			barPremiumCalls = value;
			break;
		case BAR_INTERNATIONAL_CALLS:
			barInternationalCalls = value;
			break;
		default:

		}
	}

	public String toString() {
		String[] fields = { row, msisdn, barPartialSuspend, barOutgoingService, barHighValueNumbers, barFullSuspend, barFullNetwork, barPremiumSMS,
				barPremiumCalls, barInternationalCalls, requestor };
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}
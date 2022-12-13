package microservices.backend.eir_bulk_backend.bulk_files.activate_subscription_file;

import java.util.ArrayList;

import microservices.backend.eir_bulk_backend.bulk_files.base_file.BulkFile;

public class ActivateSubscriptionFile extends BulkFile {

	private final String header = "ROW,CUSTOMER_ACCOUNT_NUMBER,SUBSCRIPTION_NAME,EIR_TEMPORARY_MSISDN,ICCID,CURRENT_MOBILE_NUMBER,CURRENT_NETWORK_NAME,CURRENT_NETWORK_ACCOUNT_NUMBER,CURRENT_ACCOUNT_TYPE,ACTION,SCHEDULE_PORT,SCHEDULED_PORT_DATE_TIME";
	private ArrayList<ActivateSubscriptionRow> rows;

	public ActivateSubscriptionFile() {
		rows = new ArrayList<ActivateSubscriptionRow>();
	}

	public String toString() {
		String contents = header;

		for (ActivateSubscriptionRow row : rows) {
			contents = contents + "\n" + row;
		}

		return contents;
	}

	public void addRow(ActivateSubscriptionRow row) {
		row.setRow(Integer.toString(rows.size() + 1));
		rows.add(row);
	}

	public ArrayList<String> getMsisdns() {
		ArrayList<String> msisdns = new ArrayList<String>();
		for (ActivateSubscriptionRow row : rows) {
			msisdns.add(row.getEirTemporaryMSISDN());
		}
		return msisdns;
	}
}

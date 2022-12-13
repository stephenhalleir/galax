package microservices.backend.eir_bulk_backend.bulk_files.terminate_subscriptions_file;

import java.util.ArrayList;

import microservices.backend.eir_bulk_backend.bulk_files.base_file.BulkFile;

public class TerminateSubscriptionsFile extends BulkFile {

	private final String header="ROW,CUSTOMER_ACCOUNT_NUMBER,MSISDN,TERMINATION_REASON,TERMINATION_DATE,EARLY_CEASE_CHARGE,APPROVED_BY";
	private ArrayList<TerminateSubscriptionRow> rows;
	
	public TerminateSubscriptionsFile() {
		rows=new ArrayList<TerminateSubscriptionRow>();
	}

	public void addRow(TerminateSubscriptionRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(TerminateSubscriptionRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
}

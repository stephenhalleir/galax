package microservices.backend.eir_bulk_backend.bulk_files.bar_subscriptions_file;

import java.util.ArrayList;

public class BarSubscriptionsFile {

	private final String header="ROW,MSISDN,BAR_PARTIAL_SUSPEND,BAR_OUTGOING_SERVICE,BAR_HIGH_VALUE_NUMBERS,BAR_FULL_SUSPEND,BAR_FULL_NETWORK,BAR_PREMIUM_SMS,BAR_PREMIUM_CALLS,BAR_INTERNATIONAL_CALLS,REQUESTER";
	private ArrayList<BarSubscriptionsRow> rows;
	
	public BarSubscriptionsFile() {
		rows=new ArrayList<BarSubscriptionsRow>();
	}

	
	public void addRow(BarSubscriptionsRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(BarSubscriptionsRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
}

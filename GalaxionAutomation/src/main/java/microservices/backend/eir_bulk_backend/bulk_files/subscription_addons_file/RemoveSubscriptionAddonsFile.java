package microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file;

import java.util.ArrayList;

public class RemoveSubscriptionAddonsFile {

	private final String header="ROW,CUSTOMER_ACCOUNT_NUMBER,MSISDN,ADD_ON_1,ADD_ON_2,ADD_ON_3,ADD_ON_4,ADD_ON_5";
	private ArrayList<RemoveSubscriptionAddonsRow> rows;
	
	public RemoveSubscriptionAddonsFile() {
		rows=new ArrayList<RemoveSubscriptionAddonsRow>();
	}
	
	public void addRow(RemoveSubscriptionAddonsRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(RemoveSubscriptionAddonsRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
	
}

package microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file;

import java.util.ArrayList;

public class AddSubscriptionAddonsFile {

	private final String header="ROW,CUSTOMER_ACCOUNT_NUMBER,MSISDN,ADD_ON_1,ADD_ON_1_CHARGE,ADD_ON_2,ADD_ON_2_CHARGE,ADD_ON_3,ADD_ON_3_CHARGE,ADD_ON_4,ADD_ON_4_CHARGE,ADD_ON_5,ADD_ON_5_CHARGE";
	private ArrayList<AddSubscriptionAddonsRow> rows;
	
	public AddSubscriptionAddonsFile() {
		rows=new ArrayList<AddSubscriptionAddonsRow>();
	}
	
	public void addRow(AddSubscriptionAddonsRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(AddSubscriptionAddonsRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
	
}

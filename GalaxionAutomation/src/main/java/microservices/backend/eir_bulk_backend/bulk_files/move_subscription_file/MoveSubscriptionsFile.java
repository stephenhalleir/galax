package microservices.backend.eir_bulk_backend.bulk_files.move_subscription_file;

import java.util.ArrayList;

import microservices.backend.eir_bulk_backend.bulk_files.base_file.BulkFile;

public class MoveSubscriptionsFile extends BulkFile{

	private final String header="ROW,SOURCE_MSISDN,DESTINATION_CUSTOMER_ACCOUNT_NUMBER";
	private ArrayList<MoveSubscriptionsRow> rows;
	
	public MoveSubscriptionsFile() {
		rows=new ArrayList<MoveSubscriptionsRow>();
	}
	
	public void addRow(MoveSubscriptionsRow row) {
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(MoveSubscriptionsRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}

	public ArrayList<MoveSubscriptionsRow> getRows() {
		return rows;
	}
}

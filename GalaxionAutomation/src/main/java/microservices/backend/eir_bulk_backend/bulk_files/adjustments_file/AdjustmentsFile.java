package microservices.backend.eir_bulk_backend.bulk_files.adjustments_file;

import java.util.ArrayList;

import microservices.backend.eir_bulk_backend.bulk_files.base_file.BulkFile;

public class AdjustmentsFile extends BulkFile {

	private final String header="ROW,CUSTOMER_ACCOUNT_NUMBER,ADJUSTMENT_REASON,ADJUSTMENT_AMOUNT,COMMENT,IS_NOTIFICATION_REQUIRED";
	private ArrayList<AdjustmentsRow> rows;
	
	public AdjustmentsFile() {
		rows=new ArrayList<AdjustmentsRow>();
	}

	
	public void addRow(AdjustmentsRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(AdjustmentsRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
}

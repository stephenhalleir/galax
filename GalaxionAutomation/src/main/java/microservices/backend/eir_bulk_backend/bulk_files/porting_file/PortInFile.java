package microservices.backend.eir_bulk_backend.bulk_files.porting_file;

import java.util.ArrayList;

public class PortInFile {

	private final String header="ROW,CUSTOMER_ACCOUNT_NUMBER,SUBSCRIPTION_NAME,EIR_TEMPORARY_MSISDN,ICCID,CURRENT_MOBILE_NUMBER,CURRENT_NETWORK_NAME,CURRENT_NETWORK_ACCOUNT_NUMBER,CURRENT_ACCOUNT_TYPE,ACTION,SCHEDULE_PORT,SCHEDULED_PORT_DATE_TIME";
	private ArrayList<PortInRow> rows;
	
	public PortInFile() {
		rows=new ArrayList<PortInRow>();
	}
	
	public void addRow(PortInRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(PortInRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
	
}

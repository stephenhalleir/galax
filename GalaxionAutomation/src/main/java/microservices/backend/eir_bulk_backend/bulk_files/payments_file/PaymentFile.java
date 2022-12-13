package microservices.backend.eir_bulk_backend.bulk_files.payments_file;

import java.util.ArrayList;

public class PaymentFile {

	private final String header="CUSTOMER_ACCOUNT_NUMBER,PAYMENT_AMOUNT,PAYMENT_DATE,INVOICE_NUMBER,REMARK,DOCUMENT_TYPE,PAY_ITEM";
	private ArrayList<PaymentRow> rows;
	
	public PaymentFile() {
		rows=new ArrayList<PaymentRow>();
	}
	
	public void addRow(PaymentRow row) {
		
		rows.add(row);
	}
	
	public String toString() {
		String contents = header;
		
		for(PaymentRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
}

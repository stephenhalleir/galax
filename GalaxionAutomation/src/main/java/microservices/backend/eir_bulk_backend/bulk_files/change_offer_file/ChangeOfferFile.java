package microservices.backend.eir_bulk_backend.bulk_files.change_offer_file;

import java.util.ArrayList;

import microservices.backend.eir_bulk_backend.bulk_files.base_file.BulkFile;

public class ChangeOfferFile extends BulkFile {

	private final String header="ROW,CUSTOMER_ACCOUNT_NUMBER,MSISDN,EXTERNAL_ORDER_REF,RE_CONTRACT,CHANGE_TO_OFFER_CODE,CHANGE_TO_OFFER_TARIFF_PLAN_CODE,CHANGE_TO_OFFER_TARIFF_PLAN_CHARGE,EARLY_CEASE_CHARGE,APPROVED_BY,REASON,DEVICE_CODE,DEVICE_CHARGE,DEDUCT_DEV_CHARGE_FROM_HWF,DEVICE_ENROLLMENT,DELIVERY_CONTACT_TYPE,DELIVERY_CONTACT_EMAIL_ADDRESS,DELIVERY_FIRST_NAME,DELIVERY_LAST_NAME,DELIVERY_ADDRESS_LINE_1,DELIVERY_ADDRESS_LINE_2,DELIVERY_ADDRESS_LINE_3,DELIVERY_CITY,DELIVERY_COUNTY,DELIVERY_COUNTRY,POSTAL_CODE,DELIVERY_EMAIL,DELIVERY_PHONE_NUMBER";
	private ArrayList<ChangeOfferRow> rows;
	
	public ChangeOfferFile() {
		rows=new ArrayList<ChangeOfferRow>();
	}

	public void addRow(ChangeOfferRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public ArrayList<ChangeOfferRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<ChangeOfferRow> rows) {
		this.rows = rows;
	}

	public String toString() {
		String contents = header;
		
		for(ChangeOfferRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}
}

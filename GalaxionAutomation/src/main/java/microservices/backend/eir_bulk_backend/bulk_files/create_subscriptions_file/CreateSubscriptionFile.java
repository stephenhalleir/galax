package microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file;

import java.util.ArrayList;

public class CreateSubscriptionFile {

	private String header="ROW,CUSTOMER_ACCOUNT_NUMBER,EXTERNAL_ORDER_REF,SUBSCRIPTION_NAME,SUBSCRIPTION_COST_CENTER,SUBSCRIPTION_VIP,OFFER_CODE,OFFER_TARIFF_PLAN_CODE,OFFER_TARIFF_PLAN_CHARGE,DEVICE_CODE,DEVICE_CHARGE,DEDUCT_DEV_CHARGE_FROM_HWF,DEVICE_ENROLLMENT,DIRECTORY_PREFERENCE,DELIVERY_CONTACT_TYPE,DELIVERY_CONTACT_EMAIL_ADDRESS,ADD_ON_1,ADD_ON_1_CHARGE,ADD_ON_2,ADD_ON_2_CHARGE,ADD_ON_3,ADD_ON_3_CHARGE,ADD_ON_4,ADD_ON_4_CHARGE,ADD_ON_5,ADD_ON_5_CHARGE,DELIVERY_FIRST_NAME,DELIVERY_LAST_NAME,DELIVERY_ADDRESS_LINE_1,DELIVERY_ADDRESS_LINE_2,DELIVERY_ADDRESS_LINE_3,DELIVERY_CITY,DELIVERY_COUNTY,DELIVERY_COUNTRY,POSTAL_CODE,DELIVERY_EMAIL,DELIVERY_PHONE_NUMBER";
	private String filename;
	private ArrayList<CreateSubscriptionRow> rows;
	
	public CreateSubscriptionFile() {
		rows=new ArrayList<CreateSubscriptionRow>();
	}
	
	public void addRow(CreateSubscriptionRow row) {
		
		row.setRow(Integer.toString(rows.size()+1));
		rows.add(row);
	}
	
	public ArrayList<CreateSubscriptionRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<CreateSubscriptionRow> rows) {
		this.rows = rows;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String toString() {
		String contents = header;
		for(CreateSubscriptionRow row:rows) {
			contents = contents + "\n" + row;
		}
		
		return contents;
	}	
}

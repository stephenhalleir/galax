package microservices.backend.eir_bulk_backend.bulk_files.activate_subscription_file;

import framework.test_data.generic.RandomStringGenerator;
import microservices.backend.eir_bulk_backend.bulk_files.create_subscriptions_file.CreateSubscriptionRow;
import microservices.backend.eir_mnp_facade_backend.enums.DonorAccountType;
import microservices.backend.eir_mnp_facade_backend.enums.MobileNetwork;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import testCases.eir.b2b.bulk.create_subscriptions.test_objects.Handset;

public class ActivateSubscriptionRow {

	private String row;
	private String customerAccountNumber;
	private String subscriptionName;
	private String eirTemporaryMSISDN;
	private String iccid;
	private String currentMobileNumber;
	private String currentNetworkName;
	private String currentNetworkAccountNumber;
	private String currentAccountType;
	private String action;
	private String schedulePort;
	private String scheduledPortDateTime;
	

	public ActivateSubscriptionRow(String customerAccountNumber, String subscriptionName,String eirTemporaryMSISDN, String iccid) {
		this.customerAccountNumber=customerAccountNumber;
		this.eirTemporaryMSISDN=eirTemporaryMSISDN;
		this.iccid=iccid;
		this.action="ACT";
		this.subscriptionName = subscriptionName;
		currentMobileNumber = "";
		currentNetworkName = "";
		currentNetworkAccountNumber = "";
		currentAccountType = "";
		schedulePort = "FALSE";
		scheduledPortDateTime = "";
	}
	
	public ActivateSubscriptionRow(String s) {

		// hard fix here
		s = s + " ";

		String[] parts = s.split(",", -1);
		for (int i = 0; i < parts.length; i++) {
			System.err.println(i + " - " + parts[i]);
		}

		row = parts[0].trim();
		customerAccountNumber = parts[1].trim();
		subscriptionName = parts[2].trim();
		eirTemporaryMSISDN = parts[3].trim();
		iccid = parts[4].trim();
		currentMobileNumber = parts[5].trim();
		currentNetworkName = parts[6].trim();
		currentNetworkAccountNumber = parts[7].trim();
		currentAccountType = parts[8].trim();
		action = parts[9].trim();
		schedulePort = parts[10].trim();
		scheduledPortDateTime = parts[11].trim();
	}

	public ActivateSubscriptionRow() {
		row = "";
		customerAccountNumber = "";
		subscriptionName = "";
		eirTemporaryMSISDN = "";
		iccid = "";
		currentMobileNumber = "";
		currentNetworkName = "";
		currentNetworkAccountNumber = "";
		currentAccountType = "";
		action = "";
		schedulePort = "FALSE";
		scheduledPortDateTime = "";
	}
	
	public ActivateSubscriptionRow(CreateSubscriptionRow createSubscriptionRow, LogisticsDTO pack) {
		row = "";
		customerAccountNumber = createSubscriptionRow.getCustomerAccountNumber();
		subscriptionName = createSubscriptionRow.getSubscriptionName();
		eirTemporaryMSISDN = pack.getMsisdn();
		iccid = pack.getIccId();
		currentMobileNumber = "";
		currentNetworkName = "";
		currentNetworkAccountNumber = "";
		currentAccountType = "";
		action = "ACT";
		schedulePort = "FALSE";
		scheduledPortDateTime = "";
	}
	
	public void setPort(String currentMobileNumber, MobileNetwork currentNetworkName, String currentNetworkAccountNumber, DonorAccountType currentAccountType, String scheduledPortDateTime) {
		this.currentMobileNumber=currentMobileNumber;
		this.currentNetworkName=currentNetworkName.toString();
		this.currentNetworkAccountNumber=currentNetworkAccountNumber;
		this.currentAccountType=currentAccountType.toString();
		this.scheduledPortDateTime=scheduledPortDateTime;
		this.schedulePort="TRUE";
		this.action="ACT_PORT";
	}

	public void randomize() {

	}

	
	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	public String getEirTemporaryMSISDN() {
		return eirTemporaryMSISDN;
	}

	public void setEirTemporaryMSISDN(String eirTemporaryMSISDN) {
		this.eirTemporaryMSISDN = eirTemporaryMSISDN;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getCurrentMobileNumber() {
		return currentMobileNumber;
	}

	public void setCurrentMobileNumber(String currentMobileNumber) {
		this.currentMobileNumber = currentMobileNumber;
	}

	public String getCurrentNetworkName() {
		return currentNetworkName;
	}

	public void setCurrentNetworkName(String currentNetworkName) {
		this.currentNetworkName = currentNetworkName;
	}

	public String getCurrentNetworkAccountNumber() {
		return currentNetworkAccountNumber;
	}

	public void setCurrentNetworkAccountNumber(String currentNetworkAccountNumber) {
		this.currentNetworkAccountNumber = currentNetworkAccountNumber;
	}

	public String getCurrentAccountType() {
		return currentAccountType;
	}

	public void setCurrentAccountType(String currentAccountType) {
		this.currentAccountType = currentAccountType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

	public String getSchedulePort() {
		return schedulePort;
	}

	public void setSchedulePort(String schedulePort) {
		this.schedulePort = schedulePort;
	}

	public String getScheduledPortDateTime() {
		return scheduledPortDateTime;
	}

	public void setScheduledPortDateTime(String scheduledPortDateTime) {
		this.scheduledPortDateTime = scheduledPortDateTime;
	}
	

	public String toString() {
		String[] fields = { row, customerAccountNumber, subscriptionName, eirTemporaryMSISDN, iccid, currentMobileNumber, currentNetworkName,
				currentNetworkAccountNumber, currentAccountType, action, schedulePort, scheduledPortDateTime };
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}

}
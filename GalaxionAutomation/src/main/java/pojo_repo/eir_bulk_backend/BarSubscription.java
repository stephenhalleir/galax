package pojo_repo.eir_bulk_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarSubscription {

	private int id;
	private String msisdn;
	private String barPartialSuspend;
	private String barOutgoingCalls;
	private String barHighValueNumbers;
	private String barFullSuspend;
	private String barFullNetwork;
	private String barPremiumSms;
	private String barPremiumCall;
	private String barInternationalCalling;
	private String requester;

	public BarSubscription() {

	}

	public BarSubscription(ResultSet rs) {
		try {
			id = rs.getInt("id");
			msisdn = rs.getString("msisdn");
			barPartialSuspend = rs.getString("bar_partial_suspend");
			barOutgoingCalls = rs.getString("bar_outgoing_calls");
			barHighValueNumbers = rs.getString("bar_high_value_numbers");
			barFullSuspend = rs.getString("bar_full_suspend");
			barFullNetwork = rs.getString("bar_full_network");
			barPremiumSms = rs.getString("bar_premium_sms");
			barPremiumCall = rs.getString("bar_premium_call");
			barInternationalCalling = rs.getString("bar_international_calling");
			requester = rs.getString("requester");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getBarPartialSuspend() {
		return barPartialSuspend;
	}

	public void setBarPartialSuspend(String barPartialSuspend) {
		this.barPartialSuspend = barPartialSuspend;
	}

	public String getBarOutgoingCalls() {
		return barOutgoingCalls;
	}

	public void setBarOutgoingCalls(String barOutgoingCalls) {
		this.barOutgoingCalls = barOutgoingCalls;
	}

	public String getBarHighValueNumbers() {
		return barHighValueNumbers;
	}

	public void setBarHighValueNumbers(String barHighValueNumbers) {
		this.barHighValueNumbers = barHighValueNumbers;
	}

	public String getBarFullSuspend() {
		return barFullSuspend;
	}

	public void setBarFullSuspend(String barFullSuspend) {
		this.barFullSuspend = barFullSuspend;
	}

	public String getBarFullNetwork() {
		return barFullNetwork;
	}

	public void setBarFullNetwork(String barFullNetwork) {
		this.barFullNetwork = barFullNetwork;
	}

	public String getBarPremiumSms() {
		return barPremiumSms;
	}

	public void setBarPremiumSms(String barPremiumSms) {
		this.barPremiumSms = barPremiumSms;
	}

	public String getBarPremiumCall() {
		return barPremiumCall;
	}

	public void setBarPremiumCall(String barPremiumCall) {
		this.barPremiumCall = barPremiumCall;
	}

	public String getBarInternationalCalling() {
		return barInternationalCalling;
	}

	public void setBarInternationalCalling(String barInternationalCalling) {
		this.barInternationalCalling = barInternationalCalling;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

}
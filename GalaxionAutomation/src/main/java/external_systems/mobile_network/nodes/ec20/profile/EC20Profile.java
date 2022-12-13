package external_systems.mobile_network.nodes.ec20.profile;

import java.util.ArrayList;

import external_systems.mobile_network.nodes.ec20.components.OCSDABalance;
import external_systems.mobile_network.nodes.ec20.components.OCSDataBundle;
import external_systems.mobile_network.nodes.ec20.components.OCSOffer;

public class EC20Profile {

	private String msisdn;
	private String status;
	private String tariffPlan;
	private String cfsp;
	private ArrayList<OCSDataBundle> dataBundles;
	private ArrayList<OCSDABalance> daBalances;
	private ArrayList<OCSOffer> offers;
	private boolean retrievalOk;
	private String accountBalance;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	public EC20Profile(String msisdn, String status, String tariffPlan, ArrayList<OCSOffer> offers, ArrayList<OCSDataBundle> balances) {
		super();
		this.cfsp=null;
		this.msisdn = msisdn;
		this.status = status;
		this.tariffPlan = tariffPlan;
		this.offers = offers;
		this.dataBundles = balances;
		this.retrievalOk = false;
	}

	public EC20Profile() {
		super();
		this.msisdn = "";
		this.status = "";
		this.tariffPlan = "";
		this.cfsp=null;
		this.offers = new ArrayList<OCSOffer>();
		this.dataBundles = new ArrayList<OCSDataBundle>();
		this.daBalances = new ArrayList<OCSDABalance>();
		this.offers = new ArrayList<OCSOffer>();
		this.retrievalOk = false;
	}

	public EC20Profile(String msisdn) {
		super();

		this.setMsisdn(msisdn);
		this.msisdn = msisdn;
		this.status = "";
		this.tariffPlan = "";
		this.cfsp=null;
		this.offers = new ArrayList<OCSOffer>();
		this.dataBundles = new ArrayList<OCSDataBundle>();
		this.retrievalOk = false;
	}

	// =================================================================================================================
	// Getters and setters
	// =================================================================================================================
	
	
	
	public String getMsisdn() {
		return msisdn;
	}

	public String getCfsp() {
		return cfsp;
	}

	public void setCfsp(String cfsp) {
		this.cfsp = cfsp;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public ArrayList<OCSDataBundle> getDataBundles() {
		return dataBundles;
	}

	public void setDataBundles(ArrayList<OCSDataBundle> dataBundles) {
		this.dataBundles = dataBundles;
	}

	public boolean isRetrievalOk() {
		return retrievalOk;
	}

	public void setRetrievalOk(boolean retrievalOk) {
		this.retrievalOk = retrievalOk;
	}

	// set the msisdn in +353 format
	public void setMsisdn(String msisdn) {

		// change to +35385 format
		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}

		this.msisdn = msisdn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(String tariffPlan) {
		this.tariffPlan = tariffPlan;
	}

	public ArrayList<OCSOffer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<OCSOffer> offers) {
		this.offers = offers;
	}

	public ArrayList<OCSDataBundle> getBalances() {
		return dataBundles;
	}

	public void setBalances(ArrayList<OCSDataBundle> balances) {
		this.dataBundles = balances;
	}

	public String getMsisdn08x() {
		if (msisdn.startsWith("3538")) {
			return msisdn.replaceFirst("3538", "08");
		}
		return msisdn;
	}

	public ArrayList<OCSDABalance> getDaBalances() {
		return daBalances;
	}

	public void setDaBalances(ArrayList<OCSDABalance> daBalances) {
		this.daBalances = daBalances;
	}

	// determine whether a customer has a specific data bundle on the IN
	public boolean hasDataBundle(String bundleName) {

		for (OCSDataBundle balance : dataBundles) {
			if (balance.getBalanceId().equals(bundleName)) {
				return true;
			}
		}
		return false;
	}

	// =================================================================================================================
	// Methods to retrieve the details from MMW/CS5
	// =================================================================================================================

	public void print() {
		System.out.println("\n***** PRINTING OCS PROFILE DETAILS *****\n");
		System.out.println("MSISDN:" + msisdn);
		System.out.println("Status:" + status);
		System.out.println("Tariff Plan:" + tariffPlan);

		System.out.println("DA Balances:");
		for (OCSDataBundle balance : dataBundles) {
			System.out.println("\t" + balance);
		}

		System.out.println("Offers:");
		for (OCSOffer offer : offers) {
			System.out.println("\t" + offer);
		}

		System.out.println("Data Bundles:");
		for (OCSDataBundle bundle : dataBundles) {
			System.out.println("\t" + bundle);
		}
	}

	
	// check whether the IN profile contains a specified offer
	public boolean hasOffer(int offerID) {
		for (OCSOffer offer : offers) {
			if (offer.getOfferID() == offerID) {
				return true;
			}
		}

		return false;
	}

	// return the profile as a HTML string
	public String asHTML() {

		// main profile attributes
		String htmlString = "<table>\r\n" + "<th colspan=\"3\">OCS Profile</th>\r\n" + "<tbody>\r\n"
				+ "<tr><td class=\"table-heading\">MSISDN</td><td>$msisdn</td></tr>\r\n"
				+ "<tr><td class=\"table-heading\">Tariff Plan</td><td>$tariffPlan</td></tr>\r\n"
				+ "<tr><td class=\"table-heading\">Status</td><td>$status</td></tr>\r\n"
				+ "<tr><td class=\"table-heading\">Main Balance</td><td>€$mainBalance</td></tr>\r\n" + "</tbody>\r\n" + "</table>";
		htmlString = htmlString.replace("$msisdn", msisdn);
		htmlString = htmlString.replace("$tariffPlan", tariffPlan);
		htmlString = htmlString.replace("$status", status);
		htmlString = htmlString.replace("$mainBalance", accountBalance);

		// include a separate table for data Balances
		htmlString = htmlString + "<table>\r\n" + "<th colspan=\"5\">Data Bundles</th>\r\n" + "<tbody>\r\n" + "<tr class=\"table-heading\">\r\n"
				+ "<td>Bundle ID</td>\r\n" + "<td>Event Type</td>\r\n" + "<td>Balance</td><td>Balance Category</td>\r\n" + "<td>Expiry Date</td>\r\n"
				+ "</tr>\r\n";

		System.out.println(dataBundles.size() + " bundles found");

		// for each data bundle
		for (OCSDataBundle balance : dataBundles) {
			String balanceString = "<tr>\r\n" + "<td>$balanceId</td>\r\n" + "<td>$eventType</td>\r\n" + "<td>$balanceAmt</td>\r\n"
					+ "<td>$balanceCategory</td>\r\n" + "<td>$expiryDate</td>\r\n" + "</tr>\r\n";
			balanceString = balanceString.replace("$balanceId", balance.getBalanceId());
			balanceString = balanceString.replace("$eventType", balance.getBalanceType());
			balanceString = balanceString.replace("$balanceAmt", balance.getBalanceAmount());
			balanceString = balanceString.replace("$balanceCategory", balance.getCategory());
			balanceString = balanceString.replace("$expiryDate", balance.getExpiryDate());
			htmlString = htmlString + balanceString;
		}

		htmlString = htmlString + "</tbody></table>";

		// include a separate table for offers
		htmlString = htmlString + "<table>\r\n" + "<th colspan=\"3\">Offers</th>\r\n" + "<tbody>\r\n" + "<tr class=\"table-heading\">\r\n"
				+ "<td>Offer ID</td>\r\n" + "<td>Offer Name</td>\r\n" + "<td>Expiry Date</td>\r\n" + "</tr>";

		for (OCSOffer offer : offers) {
			String offerString = "<tr>\r\n" + "<td>$offerId</td>\r\n" + "<td>$offerName</td>\r\n" + "<td>$endDate</td>\r\n" + "</tr>";
			offerString = offerString.replace("$offerId", Integer.toString(offer.getOfferID()));
			offerString = offerString.replace("$offerName", offer.getOfferName());
			offerString = offerString.replace("$endDate", offer.getExpiryDate());

			htmlString = htmlString + offerString;
		}

		// close out the HTML string
		htmlString = htmlString + "</tbody></table>";

		// include a separate table for DA Balances
		htmlString = htmlString + "<table>\r\n" + "<th colspan=\"4\">DA Balances</th>\r\n" + "<tbody>\r\n" + "<tr class=\"table-heading\">\r\n"
				+ "<td>Event Type</td>\r\n" + "<td>Balance</td><td>Balance Category</td>\r\n" + "<td>Expiry Date</td>\r\n" + "</tr>\r\n";

		for (OCSDABalance balance : daBalances) {
			String balanceString = "<tr>\r\n" + "<td>$eventType</td>\r\n" + "<td>$balanceAmt</td>\r\n" + "<td>$balanceCategory</td>\r\n"
					+ "<td>$expiryDate</td>\r\n" + "</tr>\r\n";
			balanceString = balanceString.replace("$eventType", balance.getEventType());
			balanceString = balanceString.replace("$balanceAmt", balance.getBalance());
			balanceString = balanceString.replace("$balanceCategory", balance.getBalanceCategory());
			balanceString = balanceString.replace("$expiryDate", balance.getExpiryDate());
			htmlString = htmlString + balanceString;
		}

		htmlString = htmlString + "</tbody></table>";

		return htmlString;
	}

	
	public boolean hasDA(String name, String balanceAmount) {
		for(OCSDABalance balance:daBalances) {
			if(balance.getEventType().equals(name) && balance.getBalance().equals(balanceAmount)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasDA(String name) {
		for(OCSDABalance balance:daBalances) {
			if(balance.getEventType().equals(name)) {
				return true;
			}
		}
		 
		return false;
	}
	
	public double getMainBalance() {
		return Double.parseDouble(getMainBalanceStr());
	}
	
	public String getMainBalanceStr() {
		for(OCSDABalance balance:daBalances) {
			if(balance.getEventType().equals("MDInEventTyMoney")) {
				return balance.getBalance();
			}
		}
		return null;
	}
}

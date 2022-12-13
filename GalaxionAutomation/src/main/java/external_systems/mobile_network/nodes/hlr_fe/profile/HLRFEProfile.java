package external_systems.mobile_network.nodes.hlr_fe.profile;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import external_systems.mobile_network.nodes.hlr_fe.components.APN;
import utilities.generic.files.TextReader;

public class HLRFEProfile {

	private String msisdn;
	private String imsi;
	private String schar;
	private String csp;
	private String rsa;
	private boolean retrievalOk;
	private String notFound = "0";
	private ArrayList<Integer> apns;

	// define the services
	private String bs3g, bs26, cat, clip, clir, dbsg, hold, obi, obo, obopre, obopri, obrf, osb2, osb3, osb4, ofa, pwd, socb, socfb, socfrc, socfu, soclip,
			ts11, ts21, ts22, redmch, ocsist, tcsist, ics;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public HLRFEProfile(String msisdn) {

		super();

		// change to +35385 format
		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}

		this.msisdn = msisdn;
		this.imsi = "";
		this.schar = "";
		this.csp = "";
		this.rsa = "";
		this.retrievalOk = false;

		this.bs26 = "";
		this.bs3g = "";
		this.cat = "";
		this.clip = "";
		this.clir = "";
		this.dbsg = "";
		this.hold = "";
		this.obi = "";
		this.obo = "";
		this.obopre = "";
		this.obopri = "";
		this.obrf = "";
		this.osb2 = "";
		this.osb3 = "";
		this.osb4 = "";
		this.ofa = "";
		this.pwd = "";
		this.socb = "";
		this.socfb = "";
		this.socfrc = "";
		this.socfu = "";
		this.soclip = "";
		this.ts11 = "";
		this.ts21 = "";
		this.ts22 = "";
		this.redmch = "";
		this.ocsist = "";
		this.tcsist = "";
		this.ics = "";
		apns = new ArrayList<Integer>();
	}

	// =================================================================================================================
	// Getters and Setters
	// =================================================================================================================

	public String getMsisdn() {
		return msisdn;
	}

	public boolean isRetrievalOk() {
		return retrievalOk;
	}

	public void setRetrievalOk(boolean retrievalOk) {
		this.retrievalOk = retrievalOk;
	}

	public String getBs26() {
		return bs26;
	}

	public void setBs26(String bs26) {
		this.bs26 = bs26;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getClip() {
		return clip;
	}

	public void setClip(String clip) {
		this.clip = clip;
	}

	public String getNotFound() {
		return notFound;
	}

	public void setNotFound(String notFound) {
		this.notFound = notFound;
	}

	public ArrayList<Integer> getApns() {
		return apns;
	}

	public void setApns(ArrayList<Integer> apns) {
		this.apns = apns;
	}


	public String getBs3g() {
		return bs3g;
	}

	public void setBs3g(String bs3g) {
		this.bs3g = bs3g;
	}

	public String getOsb2() {
		return osb2;
	}

	public void setOsb2(String osb2) {
		this.osb2 = osb2;
	}

	public String getOsb3() {
		return osb3;
	}

	public void setOsb3(String osb3) {
		this.osb3 = osb3;
	}

	public String getTs22() {
		return ts22;
	}

	public void setTs22(String ts22) {
		this.ts22 = ts22;
	}

	public String getIcs() {
		return ics;
	}

	public void setIcs(String ics) {
		this.ics = ics;
	}

	public String getClir() {
		return clir;
	}

	public void setClir(String clir) {
		this.clir = clir;
	}

	public String getDbsg() {
		return dbsg;
	}

	public void setDbsg(String dbsg) {
		this.dbsg = dbsg;
	}

	public String getHold() {
		return hold;
	}

	public void setHold(String hold) {
		this.hold = hold;
	}

	public String getObi() {
		return obi;
	}

	public void setObi(String obi) {
		this.obi = obi;
	}

	public String getObo() {
		return obo;
	}

	public void setObo(String obo) {
		this.obo = obo;
	}

	public String getObopre() {
		return obopre;
	}

	public void setObopre(String obopre) {
		this.obopre = obopre;
	}

	public String getObopri() {
		return obopri;
	}

	public void setObopri(String obopri) {
		this.obopri = obopri;
	}

	public String getObrf() {
		return obrf;
	}

	public void setObrf(String obrf) {
		this.obrf = obrf;
	}

	public String getOsb4() {
		return osb4;
	}

	public void setOsb4(String osb4) {
		this.osb4 = osb4;
	}

	public String getOfa() {
		return ofa;
	}

	public void setOfa(String ofa) {
		this.ofa = ofa;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSocb() {
		return socb;
	}

	public void setSocb(String socb) {
		this.socb = socb;
	}

	public String getSocfb() {
		return socfb;
	}

	public void setSocfb(String socfb) {
		this.socfb = socfb;
	}

	public String getSocfrc() {
		return socfrc;
	}

	public void setSocfrc(String socfrc) {
		this.socfrc = socfrc;
	}

	public String getSocfu() {
		return socfu;
	}

	public void setSocfu(String socfu) {
		this.socfu = socfu;
	}

	public String getSoclip() {
		return soclip;
	}

	public void setSoclip(String soclip) {
		this.soclip = soclip;
	}

	public String getTs11() {
		return ts11;
	}

	public void setTs11(String ts11) {
		this.ts11 = ts11;
	}

	public String getTs21() {
		return ts21;
	}

	public void setTs21(String ts21) {
		this.ts21 = ts21;
	}

	public String getRedmch() {
		return redmch;
	}

	public void setRedmch(String redmch) {
		this.redmch = redmch;
	}

	public String getOcsist() {
		return ocsist;
	}

	public void setOcsist(String ocsist) {
		this.ocsist = ocsist;
	}

	public String getTcsist() {
		return tcsist;
	}

	public void setTcsist(String tcsist) {
		this.tcsist = tcsist;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSchar() {
		return schar;
	}

	public void setSchar(String schar) {
		this.schar = schar;
	}

	public String getCsp() {
		return csp;
	}

	public void setCsp(String csp) {
		this.csp = csp;
	}

	public String getRsa() {
		return rsa;
	}

	public void setRsa(String rsa) {
		this.rsa = rsa;
	}

	public String getMsisdn08x() {
		if (msisdn.startsWith("3538")) {
			return msisdn.replaceFirst("3538", "08");
		}
		return msisdn;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	public void print() {
		System.out.println("\n***** PRINTING HLR PROFILE DETAILS *****\n");
		System.out.println("MSISDN: " + msisdn);
		System.out.println("IMSI: " + imsi);
		System.out.println("CSP: " + csp);
		System.out.println("RSA: " + rsa);
		System.out.println("C-Char: " + schar);
	}

	// return the HLR profile as a HTML string
	public String asHTML() {

		// build the APN list
		String apnString = "<ul>";
		for (int apnID : apns) {
			APN apn = new APN(apnID);
			apn.enhanceWithName();
			apnString = apnString + "<li>" + apn.getApnID() + ":" + apn.getApnName() + "</li>";
		}
		apnString = apnString + "</ul>";

		// template string
		String htmlString = TextReader.getContent("templates\\network_extracts\\table_hlr_fe.html");
		htmlString = htmlString.replace("$msisdn", msisdn);
		htmlString = htmlString.replace("$imsi", imsi);
		htmlString = htmlString.replace("$csp", csp);
		htmlString = htmlString.replace("$schar", schar);
		htmlString = htmlString.replace("$rsa", rsa);
		htmlString = htmlString.replace("$cat", cat);
		htmlString = htmlString.replace("$obopre", obopre);
		htmlString = htmlString.replace("$obopri", obopri);
		htmlString = htmlString.replace("$obo", obo);
		htmlString = htmlString.replace("$obi", obi);
		htmlString = htmlString.replace("$osb2", osb2);
		htmlString = htmlString.replace("$osb3", osb3);
		htmlString = htmlString.replace("$osb4", osb4);
		htmlString = htmlString.replace("$ts21", ts21);
		htmlString = htmlString.replace("$ts22", ts22);
		htmlString = htmlString.replace("$obrf", obrf);
		htmlString = htmlString.replace("$bsg3", bs3g);
		htmlString = htmlString.replace("$apns", apnString);

		return htmlString;
	}

	// return the value of a node, or the "not found" string value if it's not
	// applicable
	public String getElement(NodeList list) {
		if (list.item(0) != null) {
			return list.item(0).getFirstChild().getNodeValue();
		}
		return notFound;
	}

	public boolean hasAPN(int apnID) {
		return apns.contains(apnID);
	}

	public boolean hasAPN(String apn) {
		for (int apnID : apns) {
			APN a = new APN(apnID);
			a.enhanceWithName();
			if (a.getApnName().equals(apn)) {
				return true;
			}
		}
		return false;
	}

	// -------------------------------------------------------------
	// Determine barring settings on the account
	// -------------------------------------------------------------
	public boolean isFullSuspendSet() {
		return cat.equals("225") && bs3g.equals("0") && obi.equals("1") && ts21.equals("0") && ts22.equals("0");
	}

	public boolean isFullNetworkBarSet() {
		return rsa.equals("128");
	}

	public boolean isICSEnabled() {
		return ics.equals("1");
	}

	public boolean isInternationalBarSet() {
		return osb2.equals("1") && osb3.equals("1") && !isFullSuspendSet() && !isOutgoingServiceBarSet() && !isHighValueBarSet() && !isFullNetworkBarSet()
				&& !isPartialSuspendSet();
	}

	public boolean isPremiumBarSet() {
		return obopre.equals("1") && obopri.equals("1");
	}

	public boolean isOutgoingServiceBarSet() {
		return bs3g.equals(notFound) && obo.equals("1") && obopre.equals("1") && obopri.equals("1") && obrf.equals("1") && ts22.equals(notFound);
	}

	public boolean isHighValueBarSet() {
		return obo.equals("2") && obopre.equals("1") && obopri.equals("1");
	}

	public boolean isPartialSuspendSet() {
		return bs3g.equals(notFound) && cat.equals("225") && ts22.equals(notFound) && !isFullSuspendSet();
	}

	public boolean checkBarring(String barringType) {

		switch (barringType) {
		case "Roaming":
			break;
		case "FullNetwork":
			System.out.println("isFullNetworkBarSet()" + isFullNetworkBarSet());
			return isFullNetworkBarSet();
		case "AllOutgoingServices":
			return isOutgoingServiceBarSet();
		case "HighValueNumbers":
			return isHighValueBarSet();
		case "FullSuspend":
			return isFullSuspendSet();
		case "PartialSuspend":
			return isPartialSuspendSet();
		case "premiumcalls":
			System.out.println("Checking premium call bar");
			return isPremiumBarSet();
		case "internationalterminated":
			return isInternationalBarSet();

		}
		return false;

	}
}
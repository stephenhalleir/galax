package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefOffer {

	private int id;
	private int pcatOfferId;
	private String cfsp;
	private String tariffClass;
	private int inBalance;

	public RefOffer() {

	}

	public RefOffer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			pcatOfferId = rs.getInt("pcat_offer_id");
			cfsp = rs.getString("cfsp");
			tariffClass = rs.getString("tariff_class");
			inBalance = rs.getInt("in_balance");
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

	public int getPcatOfferId() {
		return pcatOfferId;
	}

	public void setPcatOfferId(int pcatOfferId) {
		this.pcatOfferId = pcatOfferId;
	}

	public String getCfsp() {
		return cfsp;
	}

	public void setCfsp(String cfsp) {
		this.cfsp = cfsp;
	}

	public String getTariffClass() {
		return tariffClass;
	}

	public void setTariffClass(String tariffClass) {
		this.tariffClass = tariffClass;
	}

	public int getInBalance() {
		return inBalance;
	}

	public void setInBalance(int inBalance) {
		this.inBalance = inBalance;
	}

}
package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefChangeOffer {

	private String brandCode;
	private String serviceGroupCode;
	private String offerTypeCode;
	private String customerTypeCode;
	private int inContract;
	private int inGracePeriod;
	private String acquisitionType;
	private int upgrade;
	private int downgrade;
	private int ecc;

	public RefChangeOffer() {

	}

	public RefChangeOffer(ResultSet rs) {
		try {
			brandCode = rs.getString("brand_code");
			serviceGroupCode = rs.getString("service_group_code");
			offerTypeCode = rs.getString("offer_type_code");
			customerTypeCode = rs.getString("customer_type_code");
			inContract = rs.getInt("in_contract");
			inGracePeriod = rs.getInt("in_grace_period");
			acquisitionType = rs.getString("acquisition_type");
			upgrade = rs.getInt("upgrade");
			downgrade = rs.getInt("downgrade");
			ecc = rs.getInt("ecc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getServiceGroupCode() {
		return serviceGroupCode;
	}

	public void setServiceGroupCode(String serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}

	public String getOfferTypeCode() {
		return offerTypeCode;
	}

	public void setOfferTypeCode(String offerTypeCode) {
		this.offerTypeCode = offerTypeCode;
	}

	public String getCustomerTypeCode() {
		return customerTypeCode;
	}

	public void setCustomerTypeCode(String customerTypeCode) {
		this.customerTypeCode = customerTypeCode;
	}

	public int getInContract() {
		return inContract;
	}

	public void setInContract(int inContract) {
		this.inContract = inContract;
	}

	public int getInGracePeriod() {
		return inGracePeriod;
	}

	public void setInGracePeriod(int inGracePeriod) {
		this.inGracePeriod = inGracePeriod;
	}

	public String getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(String acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

	public int getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	public int getDowngrade() {
		return downgrade;
	}

	public void setDowngrade(int downgrade) {
		this.downgrade = downgrade;
	}

	public int getEcc() {
		return ecc;
	}

	public void setEcc(int ecc) {
		this.ecc = ecc;
	}

}
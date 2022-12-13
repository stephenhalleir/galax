package pojo_repo.eir_contact_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {

	private int id;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String town;
	private String county;
	private String code;
	private String poBox;
	private String type;
	private String contactUuid;
	private Date updatedAt;
	private Date createdAt;
	private String area;
	private String country;
	private String street;
	private String streetNumber;
	private String streetQualifier;
	private String buildingType;
	private String buildingBlock;

	public Address() {

	}

	public Address(ResultSet rs) {
		try {
			id = rs.getInt("id");
			addressLine1 = rs.getString("address_line1");
			addressLine2 = rs.getString("address_line2");
			addressLine3 = rs.getString("address_line3");
			town = rs.getString("town");
			county = rs.getString("county");
			code = rs.getString("code");
			poBox = rs.getString("po_box");
			type = rs.getString("type");
			contactUuid = rs.getString("contact_uuid");
			updatedAt = rs.getDate("updated_at");
			createdAt = rs.getDate("created_at");
			area = rs.getString("area");
			country = rs.getString("country");
			street = rs.getString("street");
			streetNumber = rs.getString("street_number");
			streetQualifier = rs.getString("street_qualifier");
			buildingType = rs.getString("building_type");
			buildingBlock = rs.getString("building_block");
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

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactUuid() {
		return contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		this.contactUuid = contactUuid;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetQualifier() {
		return streetQualifier;
	}

	public void setStreetQualifier(String streetQualifier) {
		this.streetQualifier = streetQualifier;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getBuildingBlock() {
		return buildingBlock;
	}

	public void setBuildingBlock(String buildingBlock) {
		this.buildingBlock = buildingBlock;
	}

}
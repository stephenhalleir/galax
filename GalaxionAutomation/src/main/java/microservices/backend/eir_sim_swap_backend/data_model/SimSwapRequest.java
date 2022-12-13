package microservices.backend.eir_sim_swap_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimSwapRequest {

	private int id;
	private String type;
	private String status;
	private int serviceId;
	private int waived;
	private int barServices;
	private String address;
	private Date createdAt;
	private Date updatedAt;
	private String newIccid;
	private String newImsi;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String errorMessage;
	private String catalogAddonCode;

	public SimSwapRequest() {

	}

	public SimSwapRequest(ResultSet rs) {
		try {
			id = rs.getInt("id");
			type = rs.getString("type");
			status = rs.getString("status");
			serviceId = rs.getInt("service_id");
			waived = rs.getInt("waived");
			barServices = rs.getInt("bar_services");
			address = rs.getString("address");
			createdAt = rs.getDate("created_at");
			updatedAt = rs.getDate("updated_at");
			newIccid = rs.getString("new_iccid");
			newImsi = rs.getString("new_imsi");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			phoneNumber = rs.getString("phone_number");
			email = rs.getString("email");
			errorMessage = rs.getString("error_message");
			catalogAddonCode=rs.getString("catalog_addon_code");
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getWaived() {
		return waived;
	}

	public void setWaived(int waived) {
		this.waived = waived;
	}

	public int getBarServices() {
		return barServices;
	}

	public void setBarServices(int barServices) {
		this.barServices = barServices;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getNewIccid() {
		return newIccid;
	}

	public void setNewIccid(String newIccid) {
		this.newIccid = newIccid;
	}

	public String getNewImsi() {
		return newImsi;
	}

	public void setNewImsi(String newImsi) {
		this.newImsi = newImsi;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCatalogAddonCode() {
		return catalogAddonCode;
	}

	public void setCatalogAddonCode(String catalogAddonCode) {
		this.catalogAddonCode = catalogAddonCode;
	}

}
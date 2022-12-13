package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;

public class Company {

	private int id;
	private String name;
	private String registrationNumber;
	private Date startAt;
	
	public Company() {
		id=-1;
		name="";
		registrationNumber="";
		startAt=null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Date getStartAt() {
		return startAt;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}	
}

package testCases.testObjects.orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utilities.generic.database.MariaDBConnection;

public class TestObjectCRMOrder {

	private ArrayList<TestObjectCRMOrderService> services;
	private String orderName;
	private String orderDescription;
	private String channel;
	private String orderReference;
	private boolean registered;

	public TestObjectCRMOrder() {
		services = new ArrayList<TestObjectCRMOrderService>();
		orderName = "";
		orderDescription = "";
		channel = "";
		orderReference = "";
		registered=false;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public ArrayList<TestObjectCRMOrderService> getServices() {
		return services;
	}

	public void setServices(ArrayList<TestObjectCRMOrderService> services) {
		this.services = services;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void add(TestObjectCRMOrderService service) {
		services.add(service);
	}

	public String toString() {
		return "Order " + orderName + orderDescription;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
}

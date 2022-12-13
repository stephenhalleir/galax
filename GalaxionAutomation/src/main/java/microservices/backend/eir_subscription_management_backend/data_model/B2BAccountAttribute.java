package microservices.backend.eir_subscription_management_backend.data_model;

public class B2BAccountAttribute {
	
	private int id;
	private int b2bAccountID;
	private String attributeKey;
	private String attributeValue;
	
	public B2BAccountAttribute() {
		id=-1;
		b2bAccountID=-1;
		attributeKey="";
		attributeValue="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getB2bAccountID() {
		return b2bAccountID;
	}

	public void setB2bAccountID(int b2bAccountID) {
		this.b2bAccountID = b2bAccountID;
	}

	public String getAttributeKey() {
		return attributeKey;
	}

	public void setAttributeKey(String attributeKey) {
		this.attributeKey = attributeKey;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
}

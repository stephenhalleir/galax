package utilities.rabbitmq;

/**
 * This class represents a DTO that can be posted to the RabbitMQ API
 * to publish a message
 * 
 * @author stephenhall
 *
 */
public class PublishMessageDTO {
	private String payload;
	private String payload_encoding;
	private String routing_key;
	private Properties properties;

	/**
	 * Constructor
	 * @param queueName
	 * @param payload
	 */
	public PublishMessageDTO(String queueName, String payload) {
		this.payload_encoding = "string";
		this.routing_key = queueName;
		this.properties = new Properties();
		this.payload = payload;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getPayload_encoding() {
		return payload_encoding;
	}

	public void setPayload_encoding(String payload_encoding) {
		this.payload_encoding = payload_encoding;
	}

	public String getRouting_key() {
		return routing_key;
	}

	public void setRouting_key(String routing_key) {
		this.routing_key = routing_key;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}	
}

/*
 * Subclass for the properties object inside the payload
 */
class Properties {
	private int delivery_mode;
	
	public Properties() {
		delivery_mode=2;
	}

	public int getDelivery_mode() {
		return delivery_mode;
	}

	public void setDelivery_mode(int delivery_mode) {
		this.delivery_mode = delivery_mode;
	}
}

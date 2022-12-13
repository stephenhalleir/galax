/**
 * This utility class will allow the user to send
 * a json message a named ION queue
 *
 * @author Stephen Hall
 * @date   09/02/2021
 * 
 */

package utilities.rabbitmq;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;
import utilities.test.config_readers.JSONRequestManager;

public class RabbitMQUtil {

	/**
	 * Send a message to a named queue in RabbitMQ
	 * @param message
	 * @param queue
	 * @return true/false based on the value of "routed" in the response
	 */
	public static boolean publishMessage(String message, String queue) {

		RestAssured.useRelaxedHTTPSValidation();

		// create a request object
		PublishMessageDTO dto = new PublishMessageDTO(queue, message);

		// read the environment credentials
		String url = EnvironmentDirectory.getRabbitMQURL() + "/api/exchanges/%2F/amq.default/publish";
		LoginCredentials rabbitMQCredentials = EnvironmentDirectory.getRabbitMQCredentials();

		// use the API to post the object to the queue
		Response response = given().log().all().urlEncodingEnabled(false).auth().preemptive().basic(rabbitMQCredentials.getUsername(), rabbitMQCredentials.getPassword()).contentType(ContentType.JSON)
				.body(PojoToJsonConverter.getJSON(dto)).post(url).then().log().all().extract().response();

		// read out the value of "routed" (true/false) from the response
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator.get("routed");
	}

	/**
	 * Retrieve the messages from a particular queue in RabbitMQ
	 * @param queueName
	 * @return the result as a list
	 */
	public static String getMessages(String queueName) {

		RestAssured.useRelaxedHTTPSValidation();

		// read the environment values and build the URL
		String url = EnvironmentDirectory.getRabbitMQURL() + "/api/queues/%2F/" + queueName + "/get";
		LoginCredentials rabbitMQCredentials = EnvironmentDirectory.getRabbitMQCredentials();

		// populate the payload
		String payload = JSONRequestManager.getJSONRequest("requests/json/rabbitmq/get_messages.json");

		// use the API to read from the queue
		Response response = given().log().all().urlEncodingEnabled(false).auth().preemptive().basic(rabbitMQCredentials.getUsername(), rabbitMQCredentials.getPassword()).contentType(ContentType.JSON)
				.body(payload).post(url).then().log().all().extract().response();

		return response.asString();
	}

	public static void main(String[] args) {

		publishMessage("{test payload here}", "order.create");
		System.out.println(getMessages("dead.letter.order.create"));
	}
}

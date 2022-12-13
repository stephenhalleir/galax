package utilities.generic.pojo_generation.pojo_to_json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PojoToJsonConverter {

	/**
	 * Return a JSON string for a java object
	 * @param o
	 * @return
	 */
	public static String getJSON(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
		  return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o).replace("\\r\\n", "");
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
		return null;
	}
	
}

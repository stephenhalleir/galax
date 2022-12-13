package utilities.test.config_readers;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

public class JSONRequestManager {

	public static String getJSONRequest(String location) {

		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		//return TextReader.getContent(location).replace("\r", "");
		
		try (FileReader reader = new FileReader(location)) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);
			return obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

package utilities.generic.pojo_generation.json_to_pojo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class JsonToPojoConverter {

	/**
	 * Generate a POJO based on a json string/response
	 * 
	 * @param jsonFilePath  - part to the json file containing the json to be parsed
	 * @param packageName   - name of the package
	 * @param javaClassName - class name
	 * 
	 *                      Sample usage:
	 *                      JsonToPojoConverter.convertJsonToJavaClass("files\\json\\mygomo_get_contacts.json",
	 *                      "utilities.generated_pojo", "GeneratedContact");
	 */
	public static void convertJsonToJavaClass(String jsonFilePath, String packageName, String javaClassName) {

		String outputJavaClassDirectory = "src\\main\\java\\";

		URL url = null;

		// create the URL object based on the path to the .json file
		try {
			url = new File(jsonFilePath).toURI().toURL();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		JCodeModel jcodeModel = new JCodeModel();

		GenerationConfig config = new DefaultGenerationConfig() {
			@Override
			public boolean isGenerateBuilders() {
				return true;
			}

			@Override
			public SourceType getSourceType() {
				return SourceType.JSON;
			}
		};

		// generate the class(es)
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		mapper.generate(jcodeModel, javaClassName, packageName, url);

		try {
			jcodeModel.build(new File(outputJavaClassDirectory));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

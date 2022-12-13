package utilities.test.config_readers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class IONEmailManager {

	private final String configFileDirectory = System.getProperty("user.dir") + "/maps/GalaxionEmailMapper.xml";

	public IONEmailManager() {
	}

	// return the value from the XML for a specified path
	public String get(String id, String path) {

		Document doc;
		try {
			File file = new File(configFileDirectory);
			doc = new SAXBuilder().build(file);

			String[] pathBits = path.split("\\.");

			List<Element> list = doc.getRootElement().getChild("EMAILS").getChildren();

			for (Element element : list) {
				if (element.getAttribute("id").getValue().equals(id)) {
					for (int i = 0; i < pathBits.length - 1; i++) {
						element = element.getChild(pathBits[i]);
					}
					return element.getChildText(pathBits[pathBits.length - 1]);
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {

		String emailID = "forgotPassword";

		IONEmailManager reader = new IONEmailManager();
		System.out.println(reader.get(emailID, "SUBJECT"));
		System.out.println(reader.get(emailID, "CONTAINS"));
		System.out.println(reader.get(emailID, "BEFORESTRING"));
		System.out.println(reader.get(emailID, "AFTERSTRING"));
	}
}

package external_systems.mobile_network.nodes.ec20.utility;

import external_systems.mobile_network.nodes.hlr_fe.components.EMASession;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.api.RestAssuredUtil;
import utilities.generic.files.TextReader;

public class HLRFEUtil {

	public static EMASession login() {

		// create an EMASession object
		String soapEndpoint = "http://10.144.144.111:8080/CAI3G1.2/services/CAI3G1.2";

		// create the login request body
		String loginXmlRequest = TextReader.getContent("requests/soap/hlr/login");
		System.out.println("EMASession.login() - request: " + loginXmlRequest);

		Response loginResponse2 = RestAssuredUtil.HLRFESoapPost(soapEndpoint, loginXmlRequest,"Login");

		if (loginResponse2.statusCode() != 200) {
			return null;
		} else {
			XmlPath xml = new XmlPath(loginResponse2.asString());
			String sessionId = xml.getString("Envelope.Body.LoginResponse.sessionId");
			String baseSequenceId = xml.getString("Envelope.Body.LoginResponse.sessionId");
			return new EMASession(sessionId, baseSequenceId);
		}
	}
}

package utilities.galaxion.environments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvironmentSnapshot {

	public static ArrayList<String> getReleaseVersions(String webURL) throws IOException {
        URL url = new URL(webURL);
        InputStream is =  url.openStream();
        ArrayList<String> versions = new ArrayList<String>();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String regex="nexus.comhar.eircom.ie:5010([^:]*):\\d*.\\d*.\\d*[\"-SNAPSHOT\"]*";
                Pattern p = Pattern.compile (regex);
                Matcher m = p.matcher (line);
                while (m.find ()) {
                	String version=m.group();
                	if(version.endsWith("</")) {
                		version=version.replace("</", "");
                	}
                	version = version.substring(version.indexOf("/")+1);
                    versions.add(version);
                }
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return versions;
    }
	
}

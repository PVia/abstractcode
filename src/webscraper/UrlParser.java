package webscraper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class UrlParser {

    private URL siteName;
    private String resultString ="";

    //method returns string with source code
    public String getResultString() throws IOException{
        System.out.println("Scanning source...");
        InputStreamReader reader = new InputStreamReader(siteName.openStream());
            while (reader.ready()) {
                int data = reader.read();
                resultString += (char) data;
            }
        return resultString;
    }

    //constructor formats url for URL class
    public UrlParser(String siteName) throws MalformedURLException {
        if (!siteName.matches("http://.*") && !siteName.matches("https://.*"))
            siteName = "http://" + siteName;
        this.siteName = new URL(siteName);
    }

}

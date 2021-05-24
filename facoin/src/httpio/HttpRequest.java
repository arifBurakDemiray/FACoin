package httpio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import enums.ERequestType;

/**
 * This class makes a http request by given endpoint and type
 */
public class HttpRequest {

	private String endpoint;
	private ERequestType type;

	public HttpRequest(String endpoint, ERequestType type) {
		this.endpoint = endpoint;
		this.type = type;
	}

	public HttpRequest() {
	}

	public void setParams(String endpoint, ERequestType type) {
		this.endpoint = endpoint;
		this.type = type;
	}

	/**
	 * This function makes a GET request and returns responded value
	 * 
	 * @return responded value
	 */
	public String getRequest() {
		if (!type.equals(ERequestType.GET)) {
			return "";
		}
		try {
			URL url = new URL(this.endpoint); // open url
			URLConnection urlConnection = url.openConnection(); // open conenction
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			String result = "";
			while ((inputLine = reader.readLine()) != null)
				result += inputLine;
			reader.close();
			return result;
		} catch (IOException e) {
			return "";
		}

	}
}
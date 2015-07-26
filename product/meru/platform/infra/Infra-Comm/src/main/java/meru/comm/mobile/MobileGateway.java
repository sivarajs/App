package meru.comm.mobile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import meru.el.EL;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class MobileGateway {

	private String urlTemplate;
	private Map<String, String> properties;

	public MobileGateway(String urlTemplate) {
		this.urlTemplate = urlTemplate;
		properties = new HashMap<String, String>(2);
	}

	public void sendSMS(String number, String message) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {

			properties.put("number", number);
			properties.put("message", URLEncoder.encode(message, "UTF-8"));
			String url = EL.parseText(urlTemplate, properties);
			System.out.println("executing request " + url);
			HttpGet httpGet = new HttpGet(url);

			response = httpClient.execute(httpGet);

			// Create a response handler
			// ResponseHandler<String> responseHandler = new
			// BasicResponseHandler();
			// String responseBody = httpclient.execute(httpget,
			// responseHandler);
			String responseBody = response.getEntity().toString();
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			System.out.println("----------------------------------------");

		} finally {
			if (response != null) {
				response.close();
			}
			httpClient.close();
		}
	}
}

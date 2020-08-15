package com.qa.apitest;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.Utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class GetApiTest.
 */
public class GetApiTest extends TestBase {

	/** The test base. */
	TestBase testBase;
	
	/** The endpoint url. */
	String endpointUrl;
	
	/** The service url. */
	String serviceUrl;
	
	/** The uri. */
	String uri;

	/**
	 * Sets the up.
	 */
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		restClient = new RestClient();
		System.out.println();
		uri = prop.getProperty("endpointUrl") + prop.getProperty("serviceUrl");
		System.out.println(uri);

	}

	/**
	 * Gets the api test without headers.
	 *
	 * @return the api test without headers
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void getApiTestWithoutHeaders() throws ClientProtocolException, IOException {

		CloseableHttpResponse httpResponseString = restClient.getAPIResponse(uri);

		// status code
		int statusCode = httpResponseString.getStatusLine().getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, STATUS_CODE_200);

		// response string
		String responseString = EntityUtils.toString(httpResponseString.getEntity(), "UTF-8");
		
		// string to json conversion
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println(responseJson);
		
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/total_pages"), "2");
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/data[0]/last_name"), "Bluth");

		// headers
		Header[] headersArray = httpResponseString.getAllHeaders();
		HashMap<String, String> headersMap = new HashMap<String, String>();
		for (Header header : headersArray) {
			headersMap.put(header.getName(), header.getValue());
		}
		System.out.println(headersMap);
	}

	/**
	 * Gets the api test with headers.
	 *
	 * @return the api test with headers
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void getApiTestWithHeaders() throws ClientProtocolException, IOException {
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");

		CloseableHttpResponse httpResponseString = restClient.getAPIResponse(uri, headersMap);

		// status code
		int statusCode = httpResponseString.getStatusLine().getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, STATUS_CODE_200);

		// response string
		String responseString = EntityUtils.toString(httpResponseString.getEntity(), "UTF-8");

		// string to json conversion
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println(responseJson);
		
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/total_pages"),"2");
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/data[0]/first_name"),"George");
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/data[0]/email"),"george.bluth@reqres.in");
	}

	/**
	 * Tear down.
	 */
	@AfterMethod
	public void tearDown() {

	}
}

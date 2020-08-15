package com.qa.apitest;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.util.Utilities;

public class DeleteApiTest extends TestBase {
	/** The test base. */
	TestBase testBase;

	/** The endpoint url. */
	String endpointUrl;

	/** The service url. */
	String serviceUrl;

	/** The uri. */
	String uri;

	Users user;

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

	@Test
	public void deleteApiTest() throws ClientProtocolException, IOException {
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");
		headersMap.put("Authorization", "Bearer OVf-mNKrDKl3upR1UchRUH0qo9-SsjPN7Jhl");
		headersMap.put("Accept", "application/json");

		CloseableHttpResponse closeableHttpResponse = restClient.deleteAPI(uri, headersMap);
		System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(), STATUS_CODE_200);
		System.out.println(closeableHttpResponse);

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		System.out.println(responseString);

		JSONObject responseJson = new JSONObject(responseString);

		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/_meta/code"), "204");

	}
}

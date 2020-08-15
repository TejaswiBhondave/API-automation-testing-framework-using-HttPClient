package com.qa.apitest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.util.Utilities;

public class PutAPiTest extends TestBase{
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
	public void putApiTest() throws JsonGenerationException, JsonMappingException, IOException
	{
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");
		headersMap.put("Authorization", "Bearer OVf-mNKrDKl3upR1UchRUH0qo9-SsjPN7Jhl");
		headersMap.put("Accept", "application/json");

		user = new Users("nituuu", "sable","nitu.s@test.com","male");
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.writeValue(new File(
				"D:\\API automation sample project\\com.qa.restapitest\\src\\main\\java\\com\\qa\\data\\Users.json"),
				user);

		String jsonString = objectMapper.writeValueAsString(user);
		System.out.println(jsonString);
		
		CloseableHttpResponse closeableHttpResponse = restClient.putAPI(uri, headersMap, jsonString);
		
		System.out.println(closeableHttpResponse);

		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, STATUS_CODE_200);
		//sometimes status code comes as 302 Moved Temporarily

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println(responseJson);
		Users actualUserObj = objectMapper.readValue(responseString, Users.class);
		System.out.println(actualUserObj);

		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/result/first_name"), user.getFirst_name());
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/result/last_name"), user.getLast_name());
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/result/gender"), user.getGender());
		Assert.assertEquals(Utilities.getValueByJPath(responseJson, "/result/email"), user.getEmail());

	}

}

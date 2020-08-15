package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
public class RestClient {

	/**
	 * Gets the API response without headers.
	 *
	 * @param url
	 *            the url
	 * @return the API response
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public CloseableHttpResponse getAPIResponse(String url) throws ClientProtocolException, IOException {
		System.out.println(url);
		CloseableHttpClient httpclient = HttpClients.createDefault(); // create a client
		HttpGet httpGet = new HttpGet(url); // hit the get request

		CloseableHttpResponse httpResponseString = httpclient.execute(httpGet);
		return httpResponseString;
	}

	/**
	 * Gets the API response with headers.
	 *
	 * @param url
	 *            the url
	 * @param headersMap
	 *            the headers map
	 * @return the API response
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public CloseableHttpResponse getAPIResponse(String url, HashMap<String, String> headersMap)
			throws ClientProtocolException, IOException {
		// System.out.println(url);
		CloseableHttpClient httpclient = HttpClients.createDefault(); // create a client
		HttpGet httpGet = new HttpGet(url); // hit the get request

		for (Entry<String, String> entry : headersMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse httpResponseString = httpclient.execute(httpGet);
		return httpResponseString;
	}

	/**
	 * Post API.
	 *
	 * @param url
	 *            the url
	 * @param headersMap
	 *            the headers map
	 * @param entityString
	 *            the entity string
	 * @return the closeable http response
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public CloseableHttpResponse postAPI(String url, HashMap<String, String> headersMap, String entityString)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault(); // create a client

		HttpPost httpPost = new HttpPost(url);

		for (Entry<String, String> entry : headersMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		httpPost.setEntity(new StringEntity(entityString));// for payload

		CloseableHttpResponse httpResponseString = httpclient.execute(httpPost);
		System.out.println(httpResponseString);
		return httpResponseString;
	}

	/**
	 * Put API.
	 *
	 * @param url
	 *            the url
	 * @param headersMap
	 *            the headers map
	 * @param entityString
	 *            the entity string
	 * @return the closeable http response
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public CloseableHttpResponse putAPI(String url, HashMap<String, String> headersMap, String entityString)
			throws ClientProtocolException, IOException

	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);

		for (Entry<String, String> entry : headersMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}

		httpPut.setEntity(new StringEntity(entityString));// for payload

		CloseableHttpResponse httpResponseString = httpClient.execute(httpPut);
		return httpResponseString;

	}

	/**
	 * Delete API.
	 *
	 * @param url the url
	 * @param headersMap the headers map
	 * @return the closeable http response
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CloseableHttpResponse deleteAPI(String url, HashMap<String, String> headersMap)
			throws ClientProtocolException, IOException

	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);

		for (Entry<String, String> entry : headersMap.entrySet()) {
			httpDelete.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse httpResponseString = httpClient.execute(httpDelete);
		return httpResponseString;

	}
}

package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.client.RestClient;

public class TestBase {
	public Properties prop;
	public RestClient restClient;
	public int STATUS_CODE_200 = 200;
	public int STATUS_CODE_500 = 500;
	public int STATUS_CODE_400 = 400;
	public int STATUS_CODE_201 = 201;


	public TestBase() {

		try {
			String str = System.getProperty("user.dir") + "//src//main//java//com//qa//config//config.properties";
			/**
			 * D://API automation sample
			 * project//com.qa.restapitest//src//main//java//com//qa//config//config.properties
			 */
			FileInputStream fileInputStream = new FileInputStream(str);
			prop = new Properties();
			prop.load(fileInputStream);
			// {endpointUrl=http://reqres.in, serviceUrl=/api/users/}
			//System.out.println(prop);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

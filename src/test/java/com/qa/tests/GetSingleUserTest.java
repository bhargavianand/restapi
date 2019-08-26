package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetSingleUserTest extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;   // type of this url is a Default => available to any other class in the same package
	//scope of this variable = ?
	RestClient restClient;
	CloseableHttpResponse response;
	String responseString;
	JSONObject responseJSON;
	
	@BeforeTest
	public void setUp() throws ClientProtocolException, IOException {
		System.out.println("In setUp() of GetSingleUserTest class");
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL2");
		url = serviceUrl + apiUrl;
		System.out.println("URL is : " +url);
	}
	
	@Test
	public void getSingleUserTest() throws ClientProtocolException, IOException {
		//System.out.println("In getSingleUserTest() of GetSingleUserTest class");
		restClient = new RestClient();
		response = restClient.get(url);	
		System.out.println("response is " +response);
		System.out.println("================================================================");
		responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		responseJSON = new JSONObject(responseString);	
		System.out.println("ResponseJSON = " + responseJSON);
	}
	
	@Test(enabled=false)
	public void statusCodeTest() {
	System.out.println("In statusCodeTest() of GetSingleUserTest class");
	int statusCode = response.getStatusLine().getStatusCode();
	System.out.println("Status code ----> " +statusCode);	
	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code not equal to 200");
	}
	
	/**
	 * 
	 * This test will help in getting response JSON
	 * @throws ParseException
	 * @throws IOException
	 */
	
	@Test(enabled=false)
	public void getJSONStringTest() throws ParseException, IOException {	
	System.out.println("In getJSONStringTest() of GetSingleUserTest class");
	responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
	responseJSON = new JSONObject(responseString);		
	System.out.println("responseJSON from API -----> " +responseJSON);		
	}

	
	@Test(enabled=false)
	public void getHeadersTest() {	
	System.out.println("In getHeadersTest() of GetSingleUserTest class");
	Header[] headerArray = response.getAllHeaders();			
	HashMap<String,String> allHeaders = new HashMap<String, String>();
	for(Header header : headerArray) {			
		allHeaders.put(header.getName(), header.getValue());
	}
	System.out.println("HeadersArrays====> " + allHeaders);	
	}


}
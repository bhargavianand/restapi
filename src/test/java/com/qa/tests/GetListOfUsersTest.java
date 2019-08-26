package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetListOfUsersTest extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse response;
	String responseString;
	JSONObject responseJSON;
	JSONArray jsonarr;	
	
	@BeforeTest
	public void setUp() throws ClientProtocolException, IOException {
		System.out.println("In BeforeMethod of GetListOfUsersTest class");
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		url = serviceUrl + apiUrl;
		System.out.println("URL is : " +url);
	}
	
	
	@Test
	public void GetResponseOfListOfUsersTest() throws ClientProtocolException, IOException {
		System.out.println("In GetResponseOfListOfUsersTest  of GetListOfUsersTest class");
		restClient = new RestClient();
		response = restClient.get(url);	
		System.out.println("Response is :\n" +response);
		System.out.println("==============================================================================");
		responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		responseJSON = new JSONObject(responseString);	
		System.out.println("ResponseJSON =======> \n" + responseJSON);
	}
	
	@Test(enabled=false)
	public void GetStatusCodeOfListOfUsersTest() {
		System.out.println("In GetStatusCodeOfListOfUsersTest  of GetListOfUsersTest class");
//	System.out.println("response inside ststus code test" + response);
	int statusCode = response.getStatusLine().getStatusCode();
	System.out.println("Status code ----> " +statusCode);	
	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code not equal to 200");
	System.out.println("================================================================");
	}
	
	@Test(enabled=false)
	public void getJSONStringTest() throws ParseException, IOException {
		System.out.println("In getJSONStringTest of GetListOfUsersTest class");
	responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
	responseJSON = new JSONObject(responseString);		
	System.out.println("responseJSON from API -----> " +responseJSON);		
	}
	
	
	@Test(enabled=false)
	public void getHeadersTest() {	
		System.out.println("In  getHeadersTest of GetListOfUsersTest class");
	Header[] headerArray = response.getAllHeaders();			
	HashMap<String,String> allHeaders = new HashMap<String, String>();
	for(Header header : headerArray) {			
		allHeaders.put(header.getName(), header.getValue());
	}
	System.out.println("HeadersArrays====> " + allHeaders);	
	}
	
	
	@Test
	public void readJSONArray() {
		System.out.println("Inside JSONArrayTest");
		
	//	System.out.println("Printing responseJSOn array \n " + responseJSON.get("data"));

		//responseJSON is a JSONObject type ..from that i am extracting JSONarray = data
		JSONArray jsonarr = responseJSON.getJSONArray("data");
		Iterator<Object>iterator = jsonarr.iterator();
		//System.out.println("Items in Data Array are ..........");
		while(iterator.hasNext()){
			JSONObject object = (JSONObject) iterator.next();
			System.out.println("Printing object "+object.get("last_name"));
		}  
		
		 
		
		

	}
		


		
		
		
		
		
		
	
	
}

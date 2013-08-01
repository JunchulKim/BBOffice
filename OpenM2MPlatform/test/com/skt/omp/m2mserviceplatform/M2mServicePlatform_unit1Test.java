package com.skt.omp.m2mserviceplatform;

import static org.junit.Assert.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import android.util.Log;

public class M2mServicePlatform_unit1Test {
	
	public static String UNIT1TEST = "M2mServicePlatform_unit1Test";
	   

	@Test
	public void testM2mServicePlatform_unit1() {
		
		fail("Not yet implemented");
		Log.d( M2mServicePlatform_unit1Test.UNIT1TEST, "testM2mServicePlatform_unit1TEST");		
	}

	@Test
	public void testMultiplier_Unit1() {
		fail("Not yet implemented");
		
		Log.d( M2mServicePlatform_unit1Test.UNIT1TEST, "testMultiplier_Unit1TEST");
    
		try{
			
    	 HttpClient httpClient = new DefaultHttpClient(); 
        HttpGet httpGet = new HttpGet("http://www.naver.com");
       
        HttpResponse httpResponse = httpClient.execute(httpGet); 
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
	
       } catch (Exception e) {
    	   
        e.printStackTrace();
        Log.d(UNIT1TEST, "Error: " + e.getMessage());
        
        }
    
	}

}

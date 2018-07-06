package com.msl.httpclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.msl.httpclient.client.CustomHttpClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomHttpClientTest {

	private static final int NUM_REQUESTS = 100;
	
	@Autowired
	private CustomHttpClient client;
	
	@Test
	public void testRequest() {
		for(int i=0;i<NUM_REQUESTS;i++) {
			client.request(true);
		}
	}

}

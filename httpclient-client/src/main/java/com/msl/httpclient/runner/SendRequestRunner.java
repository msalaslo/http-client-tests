package com.msl.httpclient.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.msl.httpclient.client.IHttpClient;

@ComponentScan("com.msl")
public class SendRequestRunner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(SendRequestRunner.class.getName());
	
	private static final int NUM_REQUESTS = 1000;
	
	@Autowired
	private IHttpClient client;
	
	@Override
	public void run(final String... args) throws Exception {
		int numRequests = NUM_REQUESTS;
		boolean forceCloseConn = true;
		if(args != null && args[0] != null) {
			numRequests = new Integer(args[0]);
		}
		if(args != null && args[1] != null) {
			forceCloseConn = new Boolean(args[1]);
		}
		String msg = "Sending " + numRequests + " requests.";
		logger.info(msg);
		System.out.println(msg);
		sendRequests(numRequests, forceCloseConn);
	}
	
	public void sendRequests(int numRequest, boolean forceCloseConn) {
		for(int i=0;i<numRequest;i++) {
			client.request(forceCloseConn);
		}
	}

}

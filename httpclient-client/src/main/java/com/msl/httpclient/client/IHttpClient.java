package com.msl.httpclient.client;

import org.springframework.stereotype.Service;

@Service
public interface IHttpClient {
	
	public void request(boolean forceCloseConn);

}

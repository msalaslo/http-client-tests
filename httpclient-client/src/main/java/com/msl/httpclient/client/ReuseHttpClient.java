package com.msl.httpclient.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Service;

@Service
public class ReuseHttpClient implements IHttpClient{

	private static int soTimeout = 1000;
	private static int connectionTimeout = 1000;
	private static int port = 8585;
	
	static PoolingHttpClientConnectionManager connManager = null;
	static RequestConfig requestConfig = null;
	static CloseableHttpClient httpclient =  null;
	
	
	private void init() {
//		connManager = new PoolingHttpClientConnectionManager();
//		connManager.setDefaultMaxPerRoute(20);
//		connManager.setMaxTotal(200);		
		requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setConnectionRequestTimeout(connectionTimeout).setSocketTimeout(soTimeout).build();
		httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
//		httpclient =  HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
//		httpclient =  HttpClients.custom().setKeepAliveStrategy(CustomConnectionKeepAliveStrategy.INSTANCE).setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
//		CloseableHttpClient httpclient = HttpClientBuilder.create().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
//		httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		
	}

	public void request(boolean forceCloseConn) {
		init();
		String uri = "http://localhost:" + port +"/test";
		System.out.println("Invocando a " + uri + " force close conn:" + forceCloseConn);
		HttpPost post = new HttpPost(uri);
		if(forceCloseConn) {
			System.out.println("Forzando el cierre de la conexión");
			post.addHeader("Connection", "close");
		}
		StringEntity entity = null;
			try {
			entity = new StringEntity("{name:msl}");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entity.setContentType("application/json");
		post.setEntity(entity);
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(post);
			System.out.println(httpResponse);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
//				if(forceCloseConn) {
//					post.releaseConnection();
//					System.out.println("Forzando el cierre de la conexión");
//					post.addHeader("Connection", "close");
//					connManager.closeExpiredConnections();
//					connManager.closeIdleConnections(0, TimeUnit.MILLISECONDS);
//				}
				if(httpResponse != null) {
					httpResponse.close();
				}
				if(httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

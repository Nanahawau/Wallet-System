package com.fgt.walletsystem.utility.http;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpUtility {
	
	public static CloseableHttpClient getHttpClient(String url) {
		if (url.toLowerCase().startsWith("https")) {
			try {
				javax.net.ssl.SSLContext sslContext = new org.apache.http.ssl.SSLContextBuilder()
						.loadTrustMaterial(null, (certificate, authType) -> true).build();
				return HttpClients.custom().setSslcontext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier())
						.build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return HttpClientBuilder.create().build();
		}
		return null;
	}

}

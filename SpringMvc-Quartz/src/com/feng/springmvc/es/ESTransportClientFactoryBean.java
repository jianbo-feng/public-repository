package com.feng.springmvc.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import java.net.InetAddress;

public class ESTransportClientFactoryBean implements FactoryBean<RestClient>, InitializingBean, DisposableBean {
	
	private String clusterName;
    private String host;
    private int port;
    
    private RestClient client;

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		client = RestClient
	            .builder(new HttpHost("localhost", 9200, "http"))
	            .build();
	
	}

	@Override
	public RestClient getObject() throws Exception {
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void destroy() throws Exception {
		if (client != null) {
			client.close();
		}
		
	}

}

package com.feng.springmvc.config;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
/**
 * ES客户端访问工厂类
 * @author baby
 * @date 2019/11/05
 */
public class TransportClientFactoryBean implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

	private static final Logger logger =LoggerFactory.getLogger(TransportClientFactoryBean.class);
	private String clusterNodes = "127.0.0.1:9300";
	private String clusterName = "elasticsearch";
	private Boolean clientTransportSniff = true;
	private Boolean clientIgnoreClusterName = Boolean.FALSE;
	private String clientPingTimeout = "5s";
	private String clientNodesSamplerInterval = "5s";
	private TransportClient client;
	private Properties properties;
	static final String COLON = ":";
	static final String COMMA = ",";
	
	@Override
	public void destroy() throws Exception {
		try {
			logger.info("Close elasticsearch client");
		}
		catch(final Exception e) {
			logger.error("Error	closing ElasticSearch client:", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		client = new PreBuiltTransportClient(settings());
		for (String clusterNode : clusterNodes.split(COMMA)) {
			String[] _arr = clusterNode.split(COLON);
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(_arr[0]),Integer.valueOf(_arr[1])));
		}
		client.connectedNodes();
	}
	
	private Settings settings() {
		if (properties != null) {
			Settings.Builder builder = Settings.builder();
			Enumeration<Object> en = properties.keys(); 
			while(en.hasMoreElements()) {
				String key = en.nextElement().toString();
				builder.put(key, properties.getProperty(key));
			}
			
			return builder.build();
		}
		return Settings.EMPTY;
	}

	@Override
	public TransportClient getObject() throws Exception {
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public Boolean getClientTransportSniff() {
		return clientTransportSniff;
	}

	public void setClientTransportSniff(Boolean clientTransportSniff) {
		this.clientTransportSniff = clientTransportSniff;
	}

	public Boolean getClientIgnoreClusterName() {
		return clientIgnoreClusterName;
	}

	public void setClientIgnoreClusterName(Boolean clientIgnoreClusterName) {
		this.clientIgnoreClusterName = clientIgnoreClusterName;
	}

	public String getClientPingTimeout() {
		return clientPingTimeout;
	}

	public void setClientPingTimeout(String clientPingTimeout) {
		this.clientPingTimeout = clientPingTimeout;
	}

	public String getClientNodesSamplerInterval() {
		return clientNodesSamplerInterval;
	}

	public void setClientNodesSamplerInterval(String clientNodesSamplerInterval) {
		this.clientNodesSamplerInterval = clientNodesSamplerInterval;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}

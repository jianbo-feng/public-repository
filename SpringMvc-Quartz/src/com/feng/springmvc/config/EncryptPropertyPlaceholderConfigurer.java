package com.feng.springmvc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import com.feng.springmvc.util.StringUtil;

/**
 * 对配置属性进行加密
 * @author baby
 *
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);
	
	private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	
	private String[] encryptProNames = {"jdbc.user", "jdbc.password"};
	
	private String profile = "dev";
	
	protected Properties[] localProperties;
	
	protected boolean localOverride = false;
	
	private Resource[] locations;

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setLocalOverride(boolean localOverride) {
		this.localOverride = localOverride;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptPropertyName(propertyName)) {
			return decodeBase64(propertyValue);
		}
		return propertyValue;
	}

	@Override
	protected void loadProperties(Properties props) throws IOException {
		if (locations != null) {
			profile = StringUtil.trim(profile);
			String suffix = ".properties";
			if (!"".equals(profile)) {
				suffix = "-" + profile + suffix;
			}
			//System.err.println("suffix....>>>" + suffix);
			for (Resource location : locations) {
				InputStream is = null;
				try {
					String fileName = location.getFilename();
					if (fileName.endsWith(suffix)) {
						LOGGER.info("Loading properties file from " + location);
						is = location.getInputStream();
						this.propertiesPersister.load(props, is);
					}
					else {
						LOGGER.error("Properties file ('" + fileName + "') cannot be loaded.");
					}
				}
				catch(Exception e) {
					LOGGER.error("Read properties error ", e);
					throw e;
				}
				finally {
					if (is != null) {
						is.close();
					}
				}
			}
		}
	}
	
	/**
	 * 检测属性名是否为加密属性名
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptPropertyName(String propertyName) {
		propertyName = StringUtil.trim(propertyName);
		for (String encryptName : encryptProNames) {
			if (propertyName.equals(encryptName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Base64解密
	 * @param encodeCode
	 * @return
	 */
	public String decodeBase64(String encodeCode) {
		return encodeCode == null ? null : new String(Base64.decodeBase64(encodeCode.getBytes()));
	}
	
	/**
	 * Base64加密
	 * @param src
	 * @return
	 */
	public String encodeBase64(String src) {
		return src == null ? null : new String(Base64.encodeBase64(src.getBytes()));
	}
	
	public static void main(String... src) {
		EncryptPropertyPlaceholderConfigurer test = new EncryptPropertyPlaceholderConfigurer();
		System.err.println("postgres 加密后=>" + test.encodeBase64("postgres"));	// cG9zdGdyZXM=
		System.err.println("cG9zdGdyZXM= 解密后=>" + test.decodeBase64("cG9zdGdyZXM="));	
	}
	
}

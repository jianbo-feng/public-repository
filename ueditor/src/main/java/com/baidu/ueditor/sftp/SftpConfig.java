package com.baidu.ueditor.sftp;

import java.io.Serializable;

/**
 * SFTP配置
 * @author jianbo.feng
 * @date 2019/11/18
 */
public class SftpConfig implements Serializable {

	private static final long serialVersionUID = -638545526186837994L;

	/**
     * 是否激活
     */
    private Boolean active;
    
    /**
     * 是否加密(以Base64加密)
     */
    private Boolean encrypt;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 密钥文件路径
     */
    private String keyFilePath;

    /**
     * 服务器文件目录(路径)
     */
    private String path;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(Boolean encrypt) {
		this.encrypt = encrypt;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getKeyFilePath() {
        return keyFilePath;
    }

    public void setKeyFilePath(String keyFilePath) {
        this.keyFilePath = keyFilePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SftpConfig() {
    }

    public SftpConfig(Boolean active, Boolean encrypt, String userName, String password, String host, int port,
			String keyFilePath, String path) {
		super();
		this.active = active;
		this.encrypt = encrypt;
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.port = port;
		this.keyFilePath = keyFilePath;
		this.path = path;
	}

	@Override
	public String toString() {
		return "SftpConfig [active=" + active + ", encrypt=" + encrypt + ", userName=" + userName + ", password="
				+ password + ", host=" + host + ", port=" + port + ", keyFilePath=" + keyFilePath + ", path=" + path
				+ "]";
	}
}


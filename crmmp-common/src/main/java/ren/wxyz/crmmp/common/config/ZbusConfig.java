/**
 * @Copyright 
 * @Project crmp-common
 * @Package wxyz.crmp.common.config
 * @File ZbusConfig.java
 * @CreateTime 2015-11-11 上午10:40:01
 */
package ren.wxyz.crmmp.common.config;

import java.util.Properties;

/**
 * <p>
 * zbus的配置文件
 * <br>修改的话，需要注意修改默认值，和构造器
 * </p>
 * @Author wxyz
 * @CreateTime 2015-11-11 上午10:40:01 init class
 */
public class ZbusConfig {
	
	private static final String ZBUS_HOST = "127.0.0.1";
	private static final String ZBUS_PORT = "15555";
	private static final String ZBUS_MQ_STATUS = "MQ_STATUS";
	private static final String ZBUS_MQ_APPLICATION = "MQ_APPLICATION";

	public ZbusConfig() {
		this.host = ZBUS_HOST;
		this.port = ZBUS_PORT;
		
		this.statusMqName = ZBUS_MQ_STATUS;
		this.applicationMqName = ZBUS_MQ_APPLICATION;
	}
	
	public ZbusConfig(Properties props) {
		this();
		if (null == props) {
			return;
		}
		
		this.host = props.getProperty("zbus.host", ZBUS_HOST);
		this.port = props.getProperty("zbus.port", ZBUS_PORT);
		
		this.statusMqName = props.getProperty("zbus.mq.status", ZBUS_MQ_STATUS);
		this.applicationMqName = props.getProperty("zbus.mq.application", ZBUS_MQ_APPLICATION);
	}
	
	/**
	 * 服务器的地址
	 */
	private String host;
	/**
	 * 端口
	 */
	private String port;
	
	/**
	 * 状态mq的名字
	 */
	private String statusMqName;
	/**
	 * 申请mq的名字
	 */
	private String applicationMqName;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getStatusMqName() {
		return statusMqName;
	}

	public void setStatusMqName(String statusMqName) {
		this.statusMqName = statusMqName;
	}

	public String getApplicationMqName() {
		return applicationMqName;
	}

	public void setApplicationMqName(String applicationMqName) {
		this.applicationMqName = applicationMqName;
	}
}

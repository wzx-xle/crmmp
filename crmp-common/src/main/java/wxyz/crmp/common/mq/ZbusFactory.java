/**
 * @Copyright 
 * @Project crmp-common
 * @Package wxyz.crmp.common.mq
 * @File ZbusFactory.java
 * @CreateTime 2015-11-11 上午11:37:55
 */
package wxyz.crmp.common.mq;

import java.io.IOException;
import java.util.Properties;

import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.mq.Consumer;
import org.zbus.mq.MqConfig;
import org.zbus.mq.Producer;

import wxyz.crmp.common.ResourceUtils;
import wxyz.crmp.common.config.ZbusConfig;
import wxyz.crmp.common.exception.MqConnectionException;

/**
 * <p>
 * zbus的生产者消费者工程工具
 * </p>
 * @Author wxyz
 * @CreateTime 2015-11-11 上午11:37:55 init class
 */
public class ZbusFactory {
	private static ZbusFactory zbus = null;
	private static final Object lock = new Object();
	
	/**
	 * <p>
	 * 获取zbus工厂，用来创建zbus的生产者和消费者
	 * </p>
	 * @return zbus工厂
	 * @throws MqConnectionException mq连接异常
	 * @CreateTime 2015-11-11 下午2:43:22 create method
	 */
	public static ZbusFactory getFactory() throws MqConnectionException {
		ZbusFactory zbusFac = null;
		if (null == zbus) {
			synchronized (lock) {
				if (null == zbus) {
					zbusFac = new ZbusFactory("/zbus.properties");
					zbus = zbusFac;
				}
			}
		}
		
		return zbus;
	}
	
	/**
	 * <p>
	 * 初始化zbus工厂，完成初始化
	 * </p>
	 * @param cfgPath
	 * @throws MqConnectionException
	 * @CreateTime 2015-11-11 下午2:44:03 create constructor
	 */
	private ZbusFactory(String cfgPath) throws MqConnectionException {
		Properties config = null;
		try {
			config = ResourceUtils.getProepertiesResource(cfgPath);
		}
		catch (IOException e) {
			throw new MqConnectionException("配置文件读取失败，将使用默认配置！cfgPath=" + cfgPath, e);
		}
		
		// 解析配置文件
		this.cfg = new ZbusConfig(config);
		
		// 创建zbus的公共部分
		BrokerConfig brokerConfig = new BrokerConfig();
		brokerConfig.setServerAddress(this.cfg.getHost() + this.cfg.getPort());
		
		// 创建broker
		try {
			this.broker = new SingleBroker(brokerConfig);
		}
		catch (IOException e) {
			throw new MqConnectionException("zbus 连接异常！", e);
		}
	}
	
	/**
	 * 自定义的zbus配置文件
	 */
	private final ZbusConfig cfg;
	
	/**
	 * broker
	 */
	private final Broker broker;
	
	/**
	 * <p>
	 * 创建消费者连接
	 * </p>
	 * @param mqName mq的名字
	 * @return
	 * @CreateTime 2015-11-11 下午2:52:46 create method
	 */
	public Consumer createConsumer(String mqName) {
		// 添加mq配置
		MqConfig config = new MqConfig();
		config.setBroker(broker);
		config.setMq(mqName);
		
		// 创建消费者
		Consumer c = new Consumer(config);
		return c;
	}
	
	/**
	 * <p>
	 * 创建消费者
	 * </p>
	 * @param mqName mq的名字
	 * @return
	 * @throws MqConnectionException 
	 * @CreateTime 2015-11-11 下午2:56:16 create method
	 */
	public Producer createProducer(String mqName) throws MqConnectionException {
		Producer producer = new Producer(broker, mqName);
		try {
			producer.createMQ();
		}
		catch (IOException | InterruptedException e) {
			throw new MqConnectionException("生产者创建失败！", e);
		}
		
		return producer;
	}
}

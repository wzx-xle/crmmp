/**
 * @Copyright 
 * @Project crmp-common
 * @Package wxyz.crmp.common.mq
 * @CreateTime 2015-11-11 下午6:33:51
 */
package wxyz.crmp.common.mq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.zbus.mq.Consumer;
import org.zbus.mq.Producer;
import org.zbus.net.core.Session;
import org.zbus.net.http.Message;

import wxyz.crmp.common.exception.MqConnectionException;


/**
 * <p>
 * MessageQueue管理器
 * </p>
 * @Author wxyz
 * @CreateTime 2015-11-11 下午6:33:51 init class
 */
public class MessageQueueManager {
	private static MessageQueueManager manager = null;
	private static final Object lock = new Object();
	
	/**
	 * <p>
	 * 获取单例的消息队列管理器
	 * </p>
	 * @return
	 * @CreateTime 2015-11-11 下午6:58:34 create method
	 */
	public static MessageQueueManager getManager() {
		MessageQueueManager mqManager = null;
		if (null == manager) {
			synchronized (lock) {
				if (null == manager) {
					mqManager = new MessageQueueManager();
					manager = mqManager;
				}
			}
		}
		
		return manager;
	}
	
	/**
	 * 生产者消息队列
	 */
	private Map<String, Producer> producerManager = new HashMap<String, Producer>();
	
	/**
	 * 消费者队列
	 */
	private Map<String, Map<MessageHandler, Consumer>> consumerManager = new HashMap<String, Map<MessageHandler, Consumer>>();
	
	private MessageQueueManager() {}
	
	/**
	 * <p>
	 * 同步给消息队列发送消息
	 * </p>
	 * @param mq 消息队列名称
	 * @param data 发送的消息数据
	 * @throws MqConnectionException 
	 * @CreateTime 2015-11-11 下午6:48:29 create method
	 */
	public void sendMessage(String mq, String data) throws MqConnectionException {
		Producer producer = null;
		if (producerManager.containsKey(mq)) {
			producer = producerManager.get(mq);
		}
		else {
			producer = ZbusFactory.getFactory().createProducer(mq);
			producerManager.put(mq, producer);
		}
		
		Message msg = new Message();
		msg.setBody(data);
		try {
			producer.sendSync(msg);
		}
		catch (IOException | InterruptedException e) {
			throw new MqConnectionException("消息发送失败！mq=" + mq + ", data=" + data, e);
		}
	}
	
	/**
	 * <p>
	 * 注册到某个消息队列，异步监听该消息队列
	 * </p>
	 * @param mq 消息队列名称
	 * @param callback 回调接口
	 * @throws MqConnectionException 
	 * @CreateTime 2015-11-11 下午6:48:42 create method
	 */
	public synchronized void registerListener(final String mq, final MessageHandler callback) throws MqConnectionException {
		Consumer consumer = ZbusFactory.getFactory().createConsumer(mq);
		// 监听消息
		try {
			consumer.onMessage(new org.zbus.net.http.Message.MessageHandler() {
				
				@Override
				public void handle(Message msg, Session sess) throws IOException {
					callback.handle(msg.getMq(), msg.getBodyString());
				}
			});
			
			consumer.start();
			
			// 添加到管理集合中
			if (!consumerManager.containsKey(mq)) {
				Map<MessageHandler, Consumer> consumerss = new HashMap<>();
				consumerss.put(callback,consumer);
				consumerManager.put(mq, consumerss);
			}
			else {
				consumerManager.get(mq).put(callback, consumer);
			}
		}
		catch (IOException e) {
			throw new MqConnectionException("注册监听失败！mq=" + mq, e);
		}
	}
	
	/**
	 * <p>
	 * 移除注册的回调接口
	 * </p>
	 * @param mq 消息队列名
	 * @param callback 回调接口
	 * @throws MqConnectionException 
	 * @CreateTime 2015-11-11 下午7:32:38 create method
	 */
	public synchronized void removeListener(final String mq, final MessageHandler callback) throws MqConnectionException {
		if (null == mq) {
			return ;
		}
		
		// 如果mq存在，则取出mq
		if (consumerManager.containsKey(mq)) {
			Map<MessageHandler, Consumer> consumerss = consumerManager.get(mq);
			
			// 如果callback存在，则移除callback，并关闭消费者
			if (consumerss.containsKey(callback)) {
				Consumer consumer = consumerss.remove(callback);
				try {
					consumer.close();
				}
				catch (IOException e) {
					throw new MqConnectionException("移除监听失败！mq=" + mq, e);
				}
				finally {
					// 如果没有监听器关注该mq，则移除mq来 释放资源
					if (consumerss.size() == 0) {
						consumerManager.remove(mq);
					}
				}
			}
		}
	}
}

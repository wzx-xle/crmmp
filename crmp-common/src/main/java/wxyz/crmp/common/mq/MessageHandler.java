/**
 * @Copyright 
 * @Project crmp-common
 * @Package wxyz.crmp.common.mq
 * @CreateTime 2015-11-11 下午6:45:11
 */
package wxyz.crmp.common.mq;

/**
 * <p>
 * 消息处理器
 * </p>
 * @Author wxyz
 * @CreateTime 2015-11-11 下午6:45:11 init class
 */
public interface MessageHandler {

	/**
	 * <p>
	 * 处理消息
	 * </p>
	 * @param mq 消息的来源mq
	 * @param data 消息内容
	 * @CreateTime 2015-11-11 下午6:47:15 create method
	 */
	void handle(String mq, String data);
}

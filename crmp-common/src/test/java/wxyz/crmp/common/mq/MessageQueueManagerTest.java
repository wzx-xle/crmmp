package wxyz.crmp.common.mq;

import static org.junit.Assert.*;

import org.junit.Test;

import wxyz.crmp.common.exception.MqConnectionException;

public class MessageQueueManagerTest {

	@Test
	public void testSendMessage() {
		try {
			MessageQueueManager.instance().sendMessage("TEST", "hello");
		}
		catch (MqConnectionException e) {
			e.printStackTrace();
		}
		
		try {
			MessageQueueManager.instance().sendMessage("TEST1", "{\"id\": 3}");
		}
		catch (MqConnectionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRegisterListener() {
		final Register count  = new Register();
		count.count = 2;
		try {
			MessageQueueManager.instance().registerListener("TEST", new MessageHandler() {
				
				@Override
				public void handle(String mq, String data) {
					System.out.println("data=" + data);
					assertEquals("TEST", mq);
					assertEquals("hello", data);
					count.count--;
				}
			});
			
			MessageQueueManager.instance().registerListener("TEST1", new MessageHandler() {
				
				@Override
				public void handle(String mq, String data) {
					System.out.println("data=" + data);
					assertEquals("TEST1", mq);
					assertEquals("{\"id\": 3}", data);
					count.count--;
				}
			});
		}
		catch (MqConnectionException e) {
			e.printStackTrace();
		}
		
		
		try {
			while (count.count > 0) {
				Thread.sleep(1);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveListener() {
		fail("Not yet implemented");
	}

	/**
	 * <p>
	 * 计数器
	 * </p>
	 * @Author wxyz
	 * @CreateTime 2015-11-14 上午11:54:39 init class
	 */
	static class Register {
		public int count = 0;
	}
}

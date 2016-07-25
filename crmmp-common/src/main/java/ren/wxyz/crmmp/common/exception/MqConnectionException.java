/**
 * @Copyright 
 * @Project crmp-common
 * @Package wxyz.crmp.common.exception
 * @File MqConnectionException.java
 * @CreateTime 2015-11-11 下午2:32:27
 */
package ren.wxyz.crmmp.common.exception;

/**
 * <p>
 * mq连接异常
 * </p>
 * @Author wxyz
 * @CreateTime 2015-11-11 下午2:32:27 init class
 */
public class MqConnectionException extends Exception {
	private static final long serialVersionUID = 7572760706062723870L;

	public MqConnectionException() {
		super();
	}

	public MqConnectionException(String message) {
		super(message);
	}

	public MqConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	protected MqConnectionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

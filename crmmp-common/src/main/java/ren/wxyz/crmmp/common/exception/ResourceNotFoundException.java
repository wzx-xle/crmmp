/**
 * @Copyright 
 * @Timestamp 2015-9-27 下午1:11:31
 */
package ren.wxyz.crmmp.common.exception;

/**
 * <p>
 * 资源找不到异常
 * </p>
 * 
 * @Author wxyz
 * @Timestamp 2015-9-27 下午1:11:31
 */
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8652794162529563680L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	protected ResourceNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

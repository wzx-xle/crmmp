/**
 * @Copyright 
 * @Timestamp 2015-9-27 下午6:24:04
 */
package ren.wxyz.crmmp.common;

/**
 * <p>
 * 字符串工具类
 * </p>
 * @Author wxyz
 * @Timestamp 2015-9-27 下午6:24:04
 */
public class StringUtils {
	
	/**
	 * <p>
	 * 判断字符串是否为null或空，这个的空包括多个空格 <br>
	 * 比如：<br>
	 * StringUtil.isBlank(null) --> true <br>
	 * StringUtil.isBlank("")  --> true <br>
	 * StringUtil.isBlank("  ") --> true <br>
	 * StringUtil.isBlank("abc ") --> false <br>
	 * StringUtil.isBlank("a bc") --> false
	 * <p>
	 * @param str 字符串对象
	 * @return 验证结果
	 */
	public static boolean isBlank(String str) {
		return str == null || str.equals("") || str.trim().equals("");
	}
	
	/**
	 * <p>
	 * 判断字符串是否为null或空 <br>
	 * 比如：<br>
	 * StringUtil.isBlank(null) --> true <br>
	 * StringUtil.isBlank("")  --> true <br>
	 * StringUtil.isBlank("  ") --> false <br>
	 * StringUtil.isBlank("abc ") --> false <br>
	 * StringUtil.isBlank("a bc") --> false
	 * </p>
	 * @param str 字符串对象
	 * @return 验证结果
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}
}

/**
 * @Copyright 
 * @Timestamp 2015-9-27 下午12:54:29
 */
package ren.wxyz.crmmp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import ren.wxyz.crmmp.common.exception.ResourceNotFoundException;

/**
 * <p>
 * 资源读取解析工具类
 * </p>
 * @Author wxyz
 * @Timestamp 2015-9-27 下午12:54:29
 */
public class ResourceUtils {

	/**
	 * <p>
	 * 获取本地资源的流，<br>解决了资源在jar包中时，普通方式无法读取的问题。<br>
	 * 资源文件的路径是从当前工具类的包所在的目录开始计算，比如：/home/xx/test/wxyz/crmp/common/ResourceUtils.class，
	 * 资源文件实际的路径是 /home/xx/test/test.txt，则传入的path参数为：/test.txt
	 * <br>也就是类路径开始算。
	 * </p>
	 * @param path 资源文件的路径，以绝对路径开头
	 * @return 资源文件的输入流
	 * @Author wxyz
	 * @throws ResourceNotFoundException 如果资源文件找不到
	 * @Timestamp 2015-9-27 下午12:56:10
	 */
	public static InputStream getResource(String path) {
		try {
			// 为空的情况，保存异常
			if (StringUtils.isBlank(path)) {
				throw new NullPointerException();
			}
			
			// 找不到的情况，抛出异常
			InputStream is = ResourceUtils.class.getResourceAsStream(path);
			if (null == is) {
				throw new NullPointerException();
			}
			
			return is;
		}
		catch (NullPointerException e) {
			throw new ResourceNotFoundException(path, e);
		}
	}
	
	/**
	 * <p>
	 * 获取本地资源的字符串内容，<br>解决了资源在jar包中时，普通方式无法读取的问题。<br>
	 * 资源文件的路径是从当前工具类的包所在的目录开始计算，比如：/home/xx/test/wxyz/crmp/common/ResourceUtils.class，
	 * 资源文件实际的路径是 /home/xx/test/test.txt，则传入的path参数为：/test.txt
	 * <br>也就是类路径开始算。
	 * </p>
	 * @param path 资源文件的路径
	 * @return 资源内容
	 * @throws ResourceNotFoundException 如果资源文件找不到
	 * @throws IOException 如果资源文件读取不正常
	 * @Author wxyz
	 * @Timestamp 2015-9-27 下午1:03:37
	 */
	public static String getStringResource(String path) throws IOException {
		return getStringResource(path, "utf-8");
	}
	
	/**
	 * <p>
	 * 获取本地资源的字符串内容，<br>解决了资源在jar包中时，普通方式无法读取的问题。<br>
	 * 资源文件的路径是从当前工具类的包所在的目录开始计算，比如：/home/xx/test/wxyz/crmp/common/ResourceUtils.class，
	 * 资源文件实际的路径是 /home/xx/test/test.txt，则传入的path参数为：/test.txt
	 * <br>也就是类路径开始算。
	 * </p>
	 * @param path 资源文件的路径
	 * @param charset 编码方式
	 * @return 资源内容
	 * @throws ResourceNotFoundException 如果资源文件找不到
	 * @throws IOException 如果资源文件读取不正常
	 * @Author wxyz
	 * @Timestamp 2015-9-27 下午1:03:37
	 */
	public static String getStringResource(String path, String charset) throws IOException {
		InputStream is = null;
		BufferedReader br = null;
		try {
			// 创建一个缓冲区字符流，都要关闭
			is = getResource(path);
			br = new BufferedReader(new InputStreamReader(is, charset));
			
			StringBuilder sb = new StringBuilder();
			String tmpStr = null;
			// 读取行到字符串构建器中
			while ((tmpStr = br.readLine()) != null) {
				sb.append(tmpStr);
				sb.append('\n');
			}
			
			return sb.substring(0, sb.length() - 1);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("charset is not supported.", e);
		}
		finally {
			if (null != br) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * <p>
	 * 解析属性文件到属性对象中，<br>解决了资源在jar包中时，普通方式无法读取的问题。<br>
	 * 资源文件的路径是从当前工具类的包所在的目录开始计算，比如：/home/xx/test/wxyz/crmp/common/ResourceUtils.class，
	 * 资源文件实际的路径是 /home/xx/test/test.txt，则传入的path参数为：/test.txt
	 * <br>也就是类路径开始算。
	 * </p>
	 * @param path 资源路径
	 * @return 属性集合对象
	 * @throws ResourceNotFoundException 如果资源文件找不到
	 * @throws IOException 如果资源文件读取异常
	 * @Author wxyz
	 * @Timestamp 2015-9-27 下午3:00:04
	 */
	public static Properties getProepertiesResource(String path) throws IOException {
		return getProepertiesResource(path, "utf-8");
	}
	
	/**
	 * <p>
	 * 解析属性文件到属性对象中，<br>解决了资源在jar包中时，普通方式无法读取的问题。<br>
	 * 资源文件的路径是从当前工具类的包所在的目录开始计算，比如：/home/xx/test/wxyz/crmp/common/ResourceUtils.class，
	 * 资源文件实际的路径是 /home/xx/test/test.txt，则传入的path参数为：/test.txt
	 * <br>也就是类路径开始算。
	 * </p>
	 * @param path 资源路径
	 * @param charset 资源文件编码方式
	 * @return 属性集合对象
	 * @throws ResourceNotFoundException 如果资源文件找不到
	 * @throws IOException 如果资源文件读取异常
	 * @Author wxyz
	 * @Timestamp 2015-9-27 下午3:00:04
	 */
	public static Properties getProepertiesResource(String path, String charset) throws IOException {
		Properties props = new Properties();
		
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = getResource(path);
			br = new BufferedReader(new InputStreamReader(is, charset));
			
			props.load(br);
			
			return props;
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("charset is not supported.", e);
		}
		finally {
			if (null != br) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

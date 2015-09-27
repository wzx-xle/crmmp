package wxyz.crmp.common;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.junit.Test;

import wxyz.crmp.common.exception.ResourceNotFoundException;

public class ResourceUtilsTest {

	@Test
	public void testGetResource() {
		String path = null;
		InputStream is = null;

		// test path = null
		path = null;
		try {
			is = ResourceUtils.getResource(path);

			assertFalse(true);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals(null, e.getMessage());
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// test path = ""
		path = "";
		try {
			is = ResourceUtils.getResource(path);

			assertFalse(true);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals("", e.getMessage());
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// test path = "test.properties"
		path = "test.properties";
		try {
			is = ResourceUtils.getResource(path);

			assertFalse(true);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals("test.properties", e.getMessage());
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// test path = "/test.properties"
		path = "/test.properties";
		try {
			is = ResourceUtils.getResource(path);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// test path = "/test_notfound.properties"
		path = "/test_notfound.properties";
		try {
			is = ResourceUtils.getResource(path);

			assertFalse(true);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals(path, e.getMessage());
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// test path = "/test.properties"，并且准确读取出数据
		path = "/test.properties";
		try {
			is = ResourceUtils.getResource(path);

			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			try {
				assertEquals("k01=val01", br.readLine());
				assertEquals("k02=值", br.readLine());
			}
			catch (IOException e) {
				assertTrue(false);
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
			}

		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		finally {
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

	@Test
	public void testGetStringResource() {
		String path = "";
		try {
			ResourceUtils.getStringResource(path, "utf-8");

			assertTrue(true);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals("", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}

		path = "/test_not_found.properties";
		try {
			ResourceUtils.getStringResource(path, "utf-8");

			assertTrue(false);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals("/test_not_found.properties", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}

		path = "/test.properties";
		try {
			ResourceUtils.getStringResource(path, "gbkfd");

			assertTrue(false);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
			assertEquals("", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(true);
			assertEquals("charset is not supported.", e.getMessage());
		}

		path = "/test.properties";
		try {
			assertEquals("k01=val01\nk02=值", ResourceUtils.getStringResource(path, "utf-8"));
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
			assertEquals("", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}

		path = "/test.properties";
		try {
			assertEquals("k01=val01\nk02=值", ResourceUtils.getStringResource(path));
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}

		path = "/test_gbk.properties";
		try {
			assertEquals("k01=val01\nk02=值", ResourceUtils.getStringResource(path, "gbk"));
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetProepertiesResource() {
		String path = null;
		Properties props = null;

		path = "";
		try {
			props = ResourceUtils.getProepertiesResource(path, "utf-8");

			assertTrue(false);
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals("", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}

		path = "/test_not_found.properties";
		try {
			ResourceUtils.getProepertiesResource(path, "utf-8");
		}
		catch (ResourceNotFoundException e) {
			assertTrue(true);
			assertEquals("/test_not_found.properties", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}

		path = "/test.properties";
		try {
			ResourceUtils.getProepertiesResource(path, "gbkfd");
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(true);
			assertEquals("charset is not supported.", e.getMessage());
		}

		path = "/test.properties";
		try {
			props = ResourceUtils.getProepertiesResource(path, "utf-8");

			assertNotEquals(null, props.containsKey("k01"));
			assertNotEquals(null, props.containsKey("k02"));

			assertEquals("val01", props.get("k01"));
			assertEquals("值", props.get("k02"));
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
			assertEquals("", e.getMessage());
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}

		path = "/test.properties";
		try {
			props = ResourceUtils.getProepertiesResource(path);

			assertNotEquals(null, props.containsKey("k01"));
			assertNotEquals(null, props.containsKey("k02"));

			assertEquals("val01", props.get("k01"));
			assertEquals("值", props.get("k02"));
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}

		path = "/test_gbk.properties";
		try {
			props = ResourceUtils.getProepertiesResource(path, "gbk");

			assertNotEquals(null, props.containsKey("k01"));
			assertNotEquals(null, props.containsKey("k02"));

			assertEquals("val01", props.get("k01"));
			assertEquals("值", props.get("k02"));
		}
		catch (ResourceNotFoundException e) {
			assertTrue(false);
		}
		catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			assertTrue(false);
		}
	}

}

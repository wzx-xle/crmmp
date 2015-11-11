package wxyz.crmp.common.config;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import wxyz.crmp.common.ResourceUtils;

public class ZbusConfigTest {

	@Test
	public void test() {
		Properties props = null;
		try {
			props = ResourceUtils.getProepertiesResource("/zbus.properties");
			assertTrue(true);
		}
		catch (IOException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
		ZbusConfig zbusCfg = new ZbusConfig(props);
		
		assertEquals("127.0.0.1", zbusCfg.getHost());
		assertEquals("15555", zbusCfg.getPort());
		assertEquals("MQ_STATUS", zbusCfg.getStatusMqName());
		assertEquals("MQ_APPLICATION", zbusCfg.getApplicationMqName());
	}

}

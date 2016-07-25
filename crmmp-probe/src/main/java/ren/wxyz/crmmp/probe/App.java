package ren.wxyz.crmmp.probe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Hello world!
 * 
 */
public class App {
	
	public static void main(String[] args) {

		URL resourceDir = App.class.getResource("/");
		if (null != resourceDir) {
			File f = new File(resourceDir.getFile() + "/test.txt");
			if (f.exists()) {
				System.out.println(f.getAbsolutePath());
			}
			else {
				System.out.println("file not exist.");
			}
		}
		else {
			System.out.println("resources directory not exist.");
		}
		
		InputStream is = App.class.getResourceAsStream("/test.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			String tmpStr = br.readLine();
			System.out.println(tmpStr);
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

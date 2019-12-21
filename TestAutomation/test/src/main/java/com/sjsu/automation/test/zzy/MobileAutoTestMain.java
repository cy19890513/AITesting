package com.sjsu.automation.test.zzy;

import java.io.FileReader;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

public class MobileAutoTestMain {
	private static GetAndroidInfo gai = new GetAndroidInfo();
	private static GetIOSInfo gii = new GetIOSInfo();
	private static String platform = "IOS";
	private static TapTapSee ttc = new TapTapSee();
	
	public static void main(String[] args) {
//		if (platform.equals("IOS")) {
//			// System.out.println("Do not support IOS now");
//			ttc.setDeviceName(gii.getDeviceName());
//		} 
//		
//		else if (platform.equals("Android")) {
//			ttc.setDeviceName(gai.getDeviceName());
//			ttc.setUdid(gai.getUdid());
//			ttc.setPlatform(platform);
//			ttc.openTapTapSee();
//		} else {
//			System.out.println("Unknow system!");
//			return;
//		}
		
		
	}
}

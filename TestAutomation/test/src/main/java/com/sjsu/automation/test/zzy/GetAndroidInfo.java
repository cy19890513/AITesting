package com.sjsu.automation.test.zzy;

import java.io.IOException;

public class GetAndroidInfo {
	private GetConsoleInfo gci = new GetConsoleInfo();
	private String consoleInfo;
	private String udid;
	private String deviceName;
	
	public String getUdid() {
		try {
			Process p = Runtime.getRuntime().exec("adb devices");
			consoleInfo = gci.getConsoleInfo(p);
			String[] consoleMessage = consoleInfo.split("\n");
			String[] arr = consoleMessage[1].split("\\s+");
			udid = arr[0];
		} catch (IOException e)  {  
            e.printStackTrace();  
        }
		
		return udid;
	}
	
	public String getDeviceName() {
		getUdid();
		
		String cmd = "adb -s \"" + udid + "\" shell getprop ro.product.model";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			consoleInfo = gci.getConsoleInfo(p);
			deviceName = consoleInfo;
		} catch (IOException e)  {  
            e.printStackTrace();  
        }
		
		return deviceName;
	}
}
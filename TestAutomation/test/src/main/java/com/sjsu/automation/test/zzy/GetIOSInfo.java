package com.sjsu.automation.test.zzy;

import java.io.IOException;

public class GetIOSInfo {
	private GetConsoleInfo gci = new GetConsoleInfo();
	private String consoleInfo;
	private String udid;
	private String deviceName;
	
	public String getUdid() {
		try {
			Process p = Runtime.getRuntime().exec("instruments -s devices");
			consoleInfo = gci.getConsoleInfo(p);
			String[] consoleMessage = consoleInfo.split("\n");
			String[] arr = consoleMessage[2].split("\\s+");
			deviceName = arr[1];
			udid = arr[3].substring(1, arr[3].length()-2);
			System.out.println(deviceName + "    " + udid);
		} catch (IOException e)  {  
            e.printStackTrace();  
        }
		return udid;
	}
	
	public String getDeviceName() {
		getUdid();
		return deviceName;
	}
}
package com.sjsu.automation.test.zzy;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class TapTapSee {
	static int allowNumber = 4;
	private String deviceName;
	private String udid;
	private String platform;
	
	public void setDeviceName (String name) {
		this.deviceName = name;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setUdid (String id) {
		this.udid = id;
	}
	
	public String getUdid() {
		return udid;
	}
	
	public void setPlatform (String platformName) {
		platform = platformName;
	}
	
	public String getPlatform() {
		return platform;
	}
	public void openTapTapSee() throws MalformedURLException, InterruptedException {
		DesiredCapabilities caps = new DesiredCapabilities();
		
		//caps.setCapability("deviceName", "K20 V");
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("udid", udid);
		caps.setCapability("platformName", platform);
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("appPackage", "com.msearcher.taptapsee.android");
		caps.setCapability("appActivity", "com.msearcher.taptapsee.activity.SplashActivity");
		caps.setCapability("�session-override", true);
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url, caps);
		Thread.sleep(5000);
		
		for (int i = 0; i < allowNumber; i++) {
			driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
		}
		driver.findElementById("com.msearcher.taptapsee.android:id/accept_check_box").click();
		driver.findElementById("com.msearcher.taptapsee.android:id/accept_button").click();
		driver.findElementById("android:id/button1").click();
		
		//Gallery
		driver.findElementById("com.msearcher.taptapsee.android:id/library_button").click();
		
		//Go into pics
		driver.findElementById("com.lge:id/resolver_icon").click();
		driver.findElementById("com.lge:id/button_once").click();
		
		//String ans = driver.findElementById("com.google.android.calculator:id/result_final").getText();
		//if (ans.equals("10")) {
			//System.out.println("Test passed ...");
		//} else {
			//System.out.println("Test failed ...");
		//}
	}
}

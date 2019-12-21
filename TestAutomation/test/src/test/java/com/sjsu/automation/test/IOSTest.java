package com.sjsu.automation.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class IOSTest {
	private IOSDriver<MobileElement> driver;
	
	@BeforeTest
	public void setup() throws MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "kk");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2");
		capabilities.setCapability("bundleId", "com.google.chrome.ios");
		capabilities.setCapability(MobileCapabilityType.UDID, "auto");
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoDismissAlerts", true);
		capabilities.setCapability("updatedWDABundleId", "com.sjsu.WebDriverAgentRunner");
	    capabilities.setCapability("xcodeOrgId", "FDDAGV287J");
	    capabilities.setCapability("xcodeSigningId", "iPhone Developer");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new IOSDriver<>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//String addr = "https://cdn.newsapi.com.au/image/v1/67a523605bca40778c6faaad93883a3b";
		String addr = "https://i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg?width=300&quality=85&auto=format&fit=max&s=80a280664b70fa77e4bcb4cde3caf273";
		preparePhoto(addr);
		// switch to TapTapSee
		HashMap<String, Object> args = new HashMap<>();
		args.put("bundleId", "com.NetIdeas.TapTapSee");
		driver.executeScript("mobile: terminateApp", args);
		driver.executeScript("mobile: launchApp", args);
	}
	
	@Test
	public void test() throws InterruptedException {
		driver.findElement(MobileBy.AccessibilityId("Ok")).click();
		driver.findElement(MobileBy.AccessibilityId("Gallery")).click();
		driver.findElement(MobileBy.xpath("//XCUIElementTypeCell[last()]")).click();	
		Thread.sleep(1000);
		MobileElement textEle = (MobileElement)driver.findElement(MobileBy.className("XCUIElementTypeStaticText"));
		String outputText = "";
		outputText = textEle.getText();
		while(outputText.contains("in progress")) {
			Thread.sleep(500);
			outputText = textEle.getText();
		}
		System.out.println(outputText);
	}
	
	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	// save a photo to library through Chrome
	private void preparePhoto(String addr) {
		try {
			driver.findElement(MobileBy.AccessibilityId("NTPHomeFakeOmniboxAccessibilityID")).click();
		} catch(Exception ex) {
			driver.findElement(MobileBy.AccessibilityId("Address and search bar")).click();
		}
		
		driver.findElement(MobileBy.AccessibilityId("Address")).sendKeys(addr);
		driver.findElement(MobileBy.id("Go")).click();
		WebElement ele = driver.findElement(MobileBy.xpath("//XCUIElementTypeImage"));
		TouchAction action = new TouchAction(driver);
		action.longPress(new LongPressOptions().withDuration(Duration.ofSeconds(1)).withElement(new ElementOption().withElement(ele))).release().perform();
		driver.findElement(MobileBy.AccessibilityId("Save Image")).click();
	}
}

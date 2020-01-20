package com.sjsu.automation.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.PushesFiles;
import io.appium.java_client.remote.MobileCapabilityType;


public class AndroidTest_8_0 {

    private AndroidDriver<MobileElement> driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        Path currentRelativePath = Paths.get("../apps/TapTapSee.apk");
        String appPath = currentRelativePath.toAbsolutePath().toString();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2000);
        capabilities.setCapability("appPackage", "com.msearcher.taptapsee.android");
        capabilities.setCapability("appActivity", "com.msearcher.taptapsee.activity.SplashActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @Test
    public void test() {
//      List<String> removePicArgs = Arrays.asList("-rf", "/mnt/sdcard/Automation/*.*");
//      Map<String, Object> removeCmd = ImmutableMap.of("command", "rm", "args", removePicArgs);
//      driver.executeScript("mobile:shell", removeCmd);
//
//      List<String> lsArgs = Arrays.asList("/mnt/sdcard/Automation");
//      Map<String, Object> lsCmd = ImmutableMap.of("command", "ls", "args", lsArgs);
//      String lsOutput = (String) driver.executeScript("mobile: shell", lsCmd);
//      System.out.println(lsOutput);
//
        try {
            pushFile();

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(MobileBy.id("android:id/button1")).click();
            driver.findElement(MobileBy.id("android:id/button1")).click();
            driver.findElement(MobileBy.id("com.msearcher.taptapsee.android:id/library_button")).click();
            driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Image']")).click();
            driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Automation']")).click();
            driver.findElement(MobileBy.xpath("//android.view.ViewGroup[@index=1]")).click();
            //driver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Photo taken on Jan 9, 2020 11:11:48 AM.\"]")).click();
            Thread.sleep(5000);
            MobileElement textEle = (MobileElement)driver.findElement(MobileBy.id("com.msearcher.taptapsee.android:id/image_keytext"));
            Thread.sleep(5000);
            String outputText = "";
            outputText = textEle.getText();
            while(outputText.contains("in progress")) {
                Thread.sleep(500);
                outputText = textEle.getText();
            }
            System.out.println(outputText);
        } catch(Exception ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!!");
            System.out.println(ex.getMessage());
            System.out.println("!!!!!!!!!!!!!!!!!!!");
        }
    }

    public void pushFile() throws InterruptedException, IOException {
        File imageFile = new File("asset/img/demophoto.png");
        driver.pushFile("/mnt/sdcard/Automation/demophoto.png", imageFile);

        Thread.sleep(3000);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

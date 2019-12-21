package com.sjsu.automation.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ScriptGeneratorMain {
	
	public static void main(String[] args) {
		try {
			generateScript_IOS();
			//generateScript();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
  
	
	public static void generateScript() throws Exception {
		IGenerator androidGenerator = new Generator("asset/template/android_7.0.tmp", "src/test/java/android");
		
		// create a json obj 
		// or use the following function to read json input from file
		//JSONObject obj = Generator.parseJsonInput("asset/json/input_android.json");
		
		// construct an input json object
		JSONObject obj = new JSONObject();
		obj.put("@IMAGE_PATH@", "asset/img/test.jpg");
		obj.put("@IMAGE_CONTENT@", "dog");
		androidGenerator.generateScript(obj);
		androidGenerator.setTemplatePath("asset/template/android_10.0.tmp");
		androidGenerator.generateScript(obj);
	}
	
	public static void generateScript_IOS() throws Exception {
		IGenerator iosGenerator = new Generator("asset/template/ios_13.2.tmp", "src/test/java/ios");
		JSONObject obj = Generator.parseJsonInput("asset/json/input_ios_2.json");
		iosGenerator.generateScript(obj);
	}

}

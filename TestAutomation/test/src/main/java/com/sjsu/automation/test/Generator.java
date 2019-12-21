package com.sjsu.automation.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Generator implements IGenerator{

	protected String templatePath;
	protected String outputPath;
	
	public Generator(String tmplPath,String outPath) {
		templatePath = tmplPath;
		outputPath = outPath;
		
	}
	public void setTemplatePath(String path) {
		templatePath = path;
	}
	
	public void setOutputPath(String path) {
		outputPath = path;
	}
	
	public void generateScript(JSONObject jsonObj) throws IOException {
		String templateStr = FileUtils.readFileToString(new File(templatePath), "UTF-8");
		String id = getValidScriptID();
		jsonObj.put("@ID@", id);
		
		// replace key
		for (Iterator keys = jsonObj.keySet().iterator(); keys.hasNext();) {
			String key = (String)keys.next();
			String value = (String)jsonObj.get(key);
			templateStr = templateStr.replaceAll(key, value);
		}
		String path = String.format("%s/Test_%s.java", outputPath, id);
		File outputFile= new File(path);
		FileUtils.writeStringToFile(outputFile, templateStr, "UTF-8");
		System.out.println("Script Generated: " + path);
	}
	
	public static JSONObject parseJsonInput(String path) throws Exception {
		JSONObject obj = null;
		try {
			Reader reader = new FileReader(path);
			obj = (JSONObject) new JSONParser().parse(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return obj;
	}
	
	private String getValidScriptID() {
		boolean fileExist = true;
		long id = 0;
		File file = null;
		while (fileExist) {
			id = System.currentTimeMillis();
			String fileName = String.format("%s/Test%d.java", outputPath, id);
			file = new File(fileName);
			fileExist = file.exists();
		}
		return "" + id;
	}
	
}

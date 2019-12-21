package com.sjsu.automation.test;

import java.io.IOException;

import org.json.simple.JSONObject;

public interface IGenerator {
	// write multiple tests in one script or one test per script?
	
	/**
	 * set path for template file.
	 * @param path template file path
	 */
	public void setTemplatePath(String path);
	
	/**
	 * set path for output file.
	 * @param path output file path
	 */
	public void setOutputPath(String path);
	
	/**
	 * Parse data from json input file.
	 * @throws IOException template file not found exception.
	 */
	public void generateScript(JSONObject jsonObj) throws IOException;

}

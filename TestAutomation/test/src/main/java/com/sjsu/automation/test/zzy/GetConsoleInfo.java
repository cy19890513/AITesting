package com.sjsu.automation.test.zzy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class GetConsoleInfo {
	public String getConsoleInfo(Process p) {
		StringBuffer resStr = new StringBuffer(); 
		
		try {   
			InputStream in = p.getInputStream();  
			Reader reader = new InputStreamReader(in);  
			BufferedReader bReader = new BufferedReader(reader);  
			for (String res = ""; (res = bReader.readLine()) != null;)  {  
				resStr.append(res + "\n");  
			}  
			bReader.close();  
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resStr.toString();
	}
}

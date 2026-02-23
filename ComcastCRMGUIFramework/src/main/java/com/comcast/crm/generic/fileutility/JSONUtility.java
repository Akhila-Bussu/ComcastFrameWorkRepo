package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility {

	public String getDataFromJSONFile(String key) throws IOException, ParseException {
		
		FileReader fr=new FileReader("./configAppData/Appcommondata.json");
		
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(fr);
		
		JSONObject map=(JSONObject)obj;
		String data=(String) map.get(key);
		
		return data;
		
		
	}
}

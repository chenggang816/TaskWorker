package com.worker;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tools.JSONHelper;

public class TaskInfo {
	private String name;
	private String path;
	private String success;
	private String version;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public void parseJSON(String jsonString){
		JSONObject obj = JSONHelper.parse(jsonString);
		if(obj == null) return;
		
		setPath(obj.get("path").toString());
		setSuccess(obj.get("success").toString());
		setVersion(obj.get("version").toString());
	}
}

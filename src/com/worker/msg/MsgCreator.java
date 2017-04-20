package com.worker.msg;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;

import com.tools.JSONHelper;

public class MsgCreator {
	static String[] keys = {"type","content"};
	private static String createMsg(String[] values){
		if(keys.length < values.length) throw new RuntimeException("值数组长度越界");
		JSONObject obj = new JSONObject();
		for(int i = 0; i < values.length; i++){
			obj.put(keys[i],values[i]);
		}
		return JSONHelper.toJSONString(obj);
	}
	
	public static String createReplyMsg(){
		JSONObject obj = new JSONObject();
		obj.put("type", "REPLY");
		return JSONHelper.toJSONString(obj);
	}
	
	public static String createTaskInfoReplyMsg(boolean isTaskNeedToUpdate){
		return createMsg(new String[]{"TASK_INFO_REPLY",String.valueOf(isTaskNeedToUpdate)});
	}
}

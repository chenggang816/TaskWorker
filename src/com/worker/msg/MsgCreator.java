package com.worker.msg;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;

import com.tools.JSONHelper;
import com.worker.Server;

public class MsgCreator {
	private static String[] keys = {"host","port","type","content"};
	private static String createMsg(String[] values){
		if(keys.length < values.length) throw new RuntimeException("ֵ���鳤��Խ��");
		JSONObject obj = new JSONObject();
		obj.put(keys[0], HelloMsgHandler.ip);
		obj.put(keys[1], Server.port);
		for(int i = 0; i < values.length; i++){
			obj.put(keys[i + 2], values[i]);
		}
		return JSONHelper.toJSONString(obj);
	}
	
	public static String createReplyMsg(){
		JSONObject obj = new JSONObject();
		obj.put("type", "REPLY");
		StringWriter out = new StringWriter();
		try {
			obj.writeJSONString(out);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String createTaskInfoReplyMsg(String content){
		return createMsg(new String[]{"TASK_INFO_REPLY",String.valueOf(content)});
	}
}

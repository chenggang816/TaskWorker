package com.worker.msg;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;

public class MsgCreator {
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
}

package com.worker.msg;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;

public class MsgMgr {
	public static String packReplyMsg(){
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

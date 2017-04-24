package com.worker.msg;

import java.io.File;

import org.json.simple.JSONObject;

import com.tools.FileHelper;
import com.worker.FileMgr;

public class TaskClearMsgHandler extends MsgHandler {

	String taskDirName;
	public TaskClearMsgHandler(JSONObject msg) {
		taskDirName = (String)msg.get("content"); 
	}

	@Override
	public String handle() {
		File theTaskDir = new File(FileMgr.getTaskDir(),taskDirName);
		if(theTaskDir.exists()){
			FileHelper.deleteDir(theTaskDir);
		}
		return null;
	}

}

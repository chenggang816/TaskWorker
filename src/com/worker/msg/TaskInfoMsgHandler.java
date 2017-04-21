package com.worker.msg;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tools.FileHelper;
import com.tools.JSONHelper;
import com.worker.FileMgr;
import com.worker.TaskInfo;
import com.worker.msg.MsgCreator;;

public class TaskInfoMsgHandler extends MsgHandler {

	private String content;
	public TaskInfoMsgHandler(String content) {
		this.content = content;
	}
	
	@Override
	public String handle() {
		//获取任务版本信息
		JSONObject obj = JSONHelper.parse(content);
		JSONArray tasks = (JSONArray)(obj.get("tasks"));
		Map<String, String> mapTaskState = new HashMap<String, String>();
		for(Object o:tasks){
			JSONObject task = (JSONObject)o;
			String taskName = (String)task.get("taskname");
			
			File taskConf = FileMgr.getTheTaskConfigFile(taskName);
			if(taskConf == null || !taskConf.exists()) {
				mapTaskState.put(taskName, "NotExist");
				continue;
			}
			String taskConfStr = FileHelper.ReadAllFromFile(taskConf);
			if(taskConfStr == null){
				mapTaskState.put(taskName, "ConfigException");
				continue;
			}
			TaskInfo ti = new TaskInfo();
			ti.parseJSON(taskConfStr);
			mapTaskState.put(taskName, ti.getVersion());
		}
		JSONObject taskState = new JSONObject(mapTaskState);
		return MsgCreator.createTaskInfoReplyMsg(JSONHelper.toJSONString(taskState));
	}

}

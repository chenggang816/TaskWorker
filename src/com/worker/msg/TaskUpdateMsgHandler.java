package com.worker.msg;

import org.json.simple.JSONObject;

public class TaskUpdateMsgHandler extends MsgHandler {
	JSONObject msg;
	public TaskUpdateMsgHandler(JSONObject msg) {
		this.msg = msg;
	}
	
	@Override
	public String handle() {
		/*
		 * 先创建文件夹，如果有文件要传输，则获取ServerSocket对象，接收文件
		 */
		
		/*
		 * 文件md5校验：通过与检验值比较，判断文件是否完整传输(此功能暂不实现)
		 */
		return MsgCreator.createReplyMsg();
	}

}

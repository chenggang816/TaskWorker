package com.worker.msg;

import com.worker.msg.MsgCreator;;

public class TaskInfoMsgHandler extends MsgHandler {

	private String content;
	public TaskInfoMsgHandler(String content) {
		this.content = content;
	}
	
	@Override
	public String handle() {
		//判断任务是否更新
		boolean isTaskNeedToUpdate = true;
		return MsgCreator.createTaskInfoReplyMsg(isTaskNeedToUpdate);
	}

}

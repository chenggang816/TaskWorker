package com.worker.msg;

import com.worker.msg.MsgCreator;;

public class TaskInfoMsgHandler extends MsgHandler {

	private String content;
	public TaskInfoMsgHandler(String content) {
		this.content = content;
	}
	
	@Override
	public String handle() {
		//�ж������Ƿ����
		boolean isTaskNeedToUpdate = true;
		return MsgCreator.createTaskInfoReplyMsg(isTaskNeedToUpdate);
	}

}

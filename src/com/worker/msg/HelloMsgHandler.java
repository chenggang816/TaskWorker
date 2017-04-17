package com.worker.msg;

public class HelloMsgHandler extends MsgHandler{

	@Override
	public String handle() {
		return MsgCreator.createReplyMsg();
	}

}

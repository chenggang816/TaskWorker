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
		 * �ȴ����ļ��У�������ļ�Ҫ���䣬���ȡServerSocket���󣬽����ļ�
		 */
		
		/*
		 * �ļ�md5У�飺ͨ�������ֵ�Ƚϣ��ж��ļ��Ƿ���������(�˹����ݲ�ʵ��)
		 */
		return MsgCreator.createReplyMsg();
	}

}

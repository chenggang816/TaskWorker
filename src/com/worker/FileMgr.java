package com.worker;

import java.io.File;

import com.tools.FileHelper;

public class FileMgr {
	/*
	 * ��ȡconfig�ļ���
	 */
	public static File getConfigDir(){
		return FileHelper.getDir("config");
	}
	/*
	 * ��ȡ�����ļ�
	 */
	public static File getConfigFile(){
		return FileHelper.getFile(getConfigDir(),"server.conf");
	}
}

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
	
	/*
	 * ��ȡdata�ļ��е�File����
	 */
	public static File getDataDir(){
		return FileHelper.getDir("data");
	}
	
	/*
	 * ��ȡdata/taskĿ¼��File����
	 */
	public static File getTaskDir(){
		return FileHelper.getDir(getDataDir(),"task");
	}
	
	public static File getTheTaskDir(String taskName){
		return FileHelper.getDir(getTaskDir(),taskName,false);
	}
	
	public static File getTheTaskConfigFile(String taskName){
		File dir = getTheTaskDir(taskName);
		if(dir == null || dir.exists() == false){
			return null;
		}
		return FileHelper.getFile(dir, "task.conf", false);
	}
}

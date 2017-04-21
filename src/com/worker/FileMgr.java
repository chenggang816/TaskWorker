package com.worker;

import java.io.File;

import com.tools.FileHelper;

public class FileMgr {
	/*
	 * 获取config文件夹
	 */
	public static File getConfigDir(){
		return FileHelper.getDir("config");
	}
	/*
	 * 获取配置文件
	 */
	public static File getConfigFile(){
		return FileHelper.getFile(getConfigDir(),"server.conf");
	}
	
	/*
	 * 获取data文件夹的File对象
	 */
	public static File getDataDir(){
		return FileHelper.getDir("data");
	}
	
	/*
	 * 获取data/task目录的File对象
	 */
	public static File getTaskDir(){
		return FileHelper.getDir(getDataDir(),"task");
	}
	
	public static File getTheTaskDir(String taskName){
		return FileHelper.getDir(getTaskDir(),taskName,false);
	}
	
	public static File getTheTaskConfigFile(String taskName){
		return FileHelper.getFile(getTheTaskDir(taskName), "task.conf", false);
	}
}

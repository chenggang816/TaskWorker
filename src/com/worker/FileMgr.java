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
}

package com.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.naming.ldap.UnsolicitedNotificationEvent;

public class FileHelper {
	/*
	 * ��textд���ļ���
	 */
	public static void WriteToFile(File file,String text){
		WriteToFile(file, text,"utf-8");
	}
	public static void WriteToFile(File file,String text,String encode){
		if(file == null || text == null) return;
		try(BufferedWriter out = 
				new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encode))) {
			out.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void WriteToFile(String path,String text){
		if(path == null || text == null) return;
		WriteToFile(new File(path), text);
	}
	/*
	 * ���ļ��ж�ȡȫ������
	 */
	public static String ReadAllFromFile(File file){
		if(file == null || !file.exists()) return null;
		String text = "";
		String line;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while((line = in.readLine()) != null){
				text += line;
			}
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static File getDir(String dirName){
		File dir = new File(dirName);
		if(!dir.exists()) dir.mkdir();
		if(!dir.isDirectory()) return null;
		return dir;
	}
	
	public static File getDir(File dirBase,String dirName){
		return getDir(dirBase,dirName,true);
	}
	
	public static File getDir(File dirBase,String dirName,boolean createNew){
		if(dirBase == null || dirBase.exists() == false) throw new RuntimeException("��Ŀ¼������");
		File dir = new File(dirBase,dirName);
		if(createNew && !dir.exists()) dir.mkdir();
		return dir;
	}
	/*
	 * ��ȡָ��Ŀ¼�µ��ļ���createNewΪtrueʱ�����ļ�������ʱ�������ļ�
	 */
	public static File getFile(File dirBase, String fileName, boolean createNew){
		if(dirBase == null || dirBase.exists() == false) throw new RuntimeException("��Ŀ¼������");
		File file = new File(dirBase,fileName);
		try {
			if(!file.exists() && createNew)
				file.createNewFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static File getFile(File dirBase, String fileName){
		return getFile(dirBase,fileName,true);
	}
}

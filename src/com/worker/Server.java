package com.worker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
	public static void main(String args[]) throws IOException {
		System.err.println("Server Console");
		//Ϊ�˼���������е��쳣��Ϣ��������  
		
		int port = 8000;  
		//����һ��ServerSocket�����ڶ˿�8899��  
		ServerSocket server = new ServerSocket(port);  
		
		//server���Խ�������Socket����������server��accept����������ʽ��  
		Socket socket = server.accept();  
		
		//���ͻ��˽���������֮�����ǾͿ��Ի�ȡsocket��InputStream�������ж�ȡ�ͻ��˷���������Ϣ�ˡ�  
		Reader reader = new InputStreamReader(socket.getInputStream());  
		char chars[] = new char[64];  
		int len;  
		StringBuffer sb = new StringBuffer();  
		String temp;  
		int index;  
		while ((len=reader.read(chars)) != -1) {  
			temp = new String(chars, 0, len);  
			if ((index = temp.indexOf("eof")) != -1) {  
				sb.append(temp.substring(0, index));  
				break;  
			}  
			sb.append(new String(chars, 0, len));  
		}  
		System.out.println("from Client: " + sb);  
		
		//�����дһ��  
		Writer writer = new OutputStreamWriter(socket.getOutputStream());  
		writer.write("Hello Client.");  
		writer.write("eof");
		writer.flush();  
		writer.close();  
		reader.close();  
		socket.close();  
		server.close();  
	}  
}

package com.worker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tools.FileHelper;
import com.worker.msg.MsgHandler;
import com.worker.msg.MsgHandlerFactory;

public class Server {
	final int port = getPortFromConfig();  
	//����һ��ServerSocket�����ڶ˿�port��  
	ServerSocket server = null;
	//server���Խ�������Socket����������server��accept����������ʽ��  
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;

	public Server() {
		System.err.println("�����ѿ����������˿�Ϊ��" + port);
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\n\t�쳣���޷��ڶ˿�" + port + "������SocketServer,Server Console���˳�");
			System.exit(0);
		}  
	}

	public static void main(String args[]){
		new Server().start();
		System.out.println("Server has exited.");
	}

	private void start() {
		try {
			while(true){
				//����������ֱ�����յ��ͻ�����Ϣ���Ż����ִ��
				socket = server.accept();

				//��socket����
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = "";
				String line = null;
				while((line = in.readLine()) != null){
					System.out.println("Client:" + line);
					msg += line;
				}
				MsgHandler handler = MsgHandlerFactory.getMsgHandler(msg);
				String strReply = handler.handle();
				if(strReply != null){
					send(strReply);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 

	public void send(String strMsg){
		if(socket == null) return;
		try {
			//��socket���
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(strMsg);
		out.print("\n");
	}
	
	private int getPortFromConfig(){
		int port = 30000;
		File conf = FileMgr.getConfigFile();
		if(conf == null) return port;
		String text = FileHelper.ReadAllFromFile(conf);
		try{
			return Integer.parseInt(text);
		}catch(NumberFormatException e){
			return port;
		}
	}
}

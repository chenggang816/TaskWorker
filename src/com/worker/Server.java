package com.worker;

import java.io.BufferedReader;
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

import com.worker.msg.MsgHandler;
import com.worker.msg.MsgHandlerFactory;

public class Server {
	int port = 8000;  
	//����һ��ServerSocket�����ڶ˿�port��  
	ServerSocket server = null;
	//server���Խ�������Socket����������server��accept����������ʽ��  
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;

	public Server() {
		System.err.println("Server Console");
		try {
			server = new ServerSocket(port);



		} catch (IOException e) {
			e.printStackTrace();
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
				String line = null;
				while((line = in.readLine()) != null){
					System.out.println("Client:" + line);
					MsgHandler handler = MsgHandlerFactory.getMsgHandler(line);
					String strReply = handler.handle();
					if(strReply != null){
						send(strReply);
					}
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
}

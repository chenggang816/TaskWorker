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
	public static void main(String args[]) throws IOException {
		System.err.println("Server Console");
		//Ϊ�˼���������е��쳣��Ϣ��������  

		int port = 8000;  
		//����һ��ServerSocket�����ڶ˿�port��  
		ServerSocket server = new ServerSocket(port);  

		//server���Խ�������Socket����������server��accept����������ʽ��  
		Socket socket = server.accept();  

		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = null;
			String strMsg = "";

			while(socket.isConnected() && (line = in.readLine()) != null){
				System.out.println("Client:" + line);
				strMsg += line;
				MsgHandler handler = MsgHandlerFactory.getMsgHandler(line);
				String strReply = handler.handle();
				if(strReply != null){
					MsgSender sender = new MsgSender(socket);
					sender.send(strReply);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}



	} 
}

class MsgSender {
	Socket socket = null;
	PrintWriter out = null;
	public MsgSender(Socket socket) throws IOException{
		this.socket = socket;
		//����������ڷ�����Ϣ���ͻ���
		out = new PrintWriter(socket.getOutputStream(),true);
	}	
	public void send(String strMsg){
		String line = "";
		while (!line.equals("�ټ�")) {			
			out.println(strMsg);
		}
		out.close();
	}

}
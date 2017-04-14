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
		//为了简单起见，所有的异常信息都往外抛  

		int port = 8000;  
		//定义一个ServerSocket监听在端口port上  
		ServerSocket server = new ServerSocket(port);  

		//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
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
		//输出流，用于发送消息给客户端
		out = new PrintWriter(socket.getOutputStream(),true);
	}	
	public void send(String strMsg){
		String line = "";
		while (!line.equals("再见")) {			
			out.println(strMsg);
		}
		out.close();
	}

}
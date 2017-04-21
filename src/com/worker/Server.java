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

public class Server extends Object{
	public static final int port = getPortFromConfig();  
	//定义一个ServerSocket监听在端口port上  
	ServerSocket server = null;
	//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;

	public Server() {
		System.err.println("服务已开启，开启端口为：" + port);
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\n\t异常：无法在端口" + port + "上启动SocketServer,Server Console已退出");
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
				//阻塞方法，直到接收到客户端消息，才会继续执行
				socket = server.accept();

				//从socket读入
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
			//向socket输出
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(strMsg);
		out.print("\n");
	}
	
	private static int getPortFromConfig(){
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

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

public class Server {
	public static void main(String args[]) throws IOException {
		System.err.println("Server Console");
		//为了简单起见，所有的异常信息都往外抛  
		
		int port = 8000;  
		//定义一个ServerSocket监听在端口port上  
		ServerSocket server = new ServerSocket(port);  
		
		//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
		Socket socket = server.accept();  
		
		new MsgReceiver(socket).start();
		
		//输出流，用于发送消息给客户端
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		BufferedReader sin = new  BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while (!line.equals("再见")) {			
			line = sin.readLine();
			out.println(line);
		}
		out.close();
		sin.close();
		
	} 
	
	
	class MsgReceiver extends Thread{
		public MsgReceiver(Socket socket){
			this.socket = socket;
		}
		BufferedReader in = null;
		Socket socket = null;
		@Override
		public void run() {
			if(socket != null){		
				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String line = null;
					while(socket.isConnected() && (line = in.readLine()) != null){
						System.out.println("Client:" + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
}

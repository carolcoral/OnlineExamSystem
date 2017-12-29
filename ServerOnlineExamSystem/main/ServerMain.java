package cn.xdl.exam.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ServerMain {
	/**
	 * 启动类
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		//1.创建一个ServerSocket对象
		ServerSocket ss = new ServerSocket(8888);
		//2.持续获取客户端接入的Socket对象
		System.out.println("服务器已经开启，等待客户端接入... ...");
		while(true){
			Socket s =  ss.accept();//阻塞
			//3.开启线程，交互
			new Thread(new ServerThread(s)).start();
		}
		
	}

}

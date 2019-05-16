package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 顺序化的webserver
 * @author jiuyv
 *
 */
public class SingleThreadWebServer {
public static void main(String[] args) throws IOException {
	ServerSocket server=new ServerSocket(80);
	while (true) {
		Socket socket=server.accept();
		handleRequest(socket);
	}
}

private static void handleRequest(Socket socket) {
	char ch=10;
	
}
}

package test1.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BioSocketServer {
	private static final ExecutorService executor = Executors.newFixedThreadPool(100);//线程池
	
	
	
	public static void main(String[] args) throws InterruptedException  {

		Thread thread=new Thread(()->{
			try(		ServerSocket serverSocket=new ServerSocket(8088);
					)
							{
								while (true) {
									//主线程循环等待新连接的到来
									Socket socket=serverSocket.accept();
									Runnable r=new Runnable() {
										
										@Override
										public void run() {
											while (!Thread.currentThread().isInterrupted()&&!socket.isClosed()) {
												try (BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
														PrintWriter pw = new PrintWriter(socket.getOutputStream())){
													while(true) {
														String s=br.readLine();
														if (s!=null) {
															System.out.println(Thread.currentThread()+s);
															pw.println(s);
															pw.flush();
														}
															
													}
													
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
											
										}
									};
									executor.execute(r);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		});
		thread.start();
		
		Thread.sleep(500);
		for (int j = 0; j < 5; j++) {
			try(Socket cSocket = new Socket(InetAddress.getLocalHost(), 8088);
					PrintWriter pw = new PrintWriter(cSocket.getOutputStream());
					BufferedReader br=new BufferedReader(new InputStreamReader(cSocket.getInputStream()))
					) {
				for (int i = 0; i < 3; i++) {
					pw.println("hello world ");
					pw.flush();
					System.out.println(br.readLine());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
			

	}
	

}

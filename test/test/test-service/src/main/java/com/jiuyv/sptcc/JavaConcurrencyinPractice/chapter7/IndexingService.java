package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter7;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class IndexingService {
private static final File POISON=new File("");
private final IndexerThread consumer=new IndexerThread();
private final CrawlerThread producer=new CrawlerThread();
private final BlockingQueue<File> queue;
private final File root;
public IndexingService(BlockingQueue<File> queue,File root) {
	this.queue=queue;
	this.root=root;
}

public void start() {
	consumer.start();
	producer.start();
}

public void stop() {
	producer.interrupt();

}

public void awaitTermination() throws InterruptedException {
	consumer.join();
}

class IndexerThread extends Thread{
	@Override
	public void run() {
		
		try {
			while (true) {
				File file=queue.take();
				if (file==POISON) {
					break;
				}else {
					indexFile(file);
				}
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
class CrawlerThread extends Thread{
	@Override
	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}finally {
			while (true) {
				try {
					queue.put(POISON);
					break;
				} catch (InterruptedException e2) {
					// TODO: handle exception
				}
			}
		}
	}
}

private void crawl(File file) throws InterruptedException{
	// TODO Auto-generated method stub

}

private void indexFile(File file) {
	// TODO Auto-generated method stub

}
}

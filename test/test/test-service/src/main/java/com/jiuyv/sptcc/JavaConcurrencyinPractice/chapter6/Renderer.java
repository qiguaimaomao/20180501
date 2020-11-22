package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Renderer {
	private final ExecutorService es;
	public Renderer(ExecutorService es) {
		this.es=es;
	}
 void renderPage(CharSequence source) {
	 final List<ImageInfo> imageInfos=scanForImageInfo(source);
	 CompletionService<ImageData> cs=new ExecutorCompletionService<>(es);
	 for (ImageInfo imageInfo : imageInfos) {
		 cs.submit(new Callable<ImageData>() {
			
			@Override
			public ImageData call() throws Exception {
				return imageInfo.downloadImage();
			}
		});
		}

	renderText(source);
try {
	for (int i = 0; i < imageInfos.size(); i++) {
		Future<ImageData> future=cs.take();
		ImageData imageData=future.get();
		renderImage(imageData);
	}
} catch (InterruptedException e) {
	Thread.currentThread().interrupt();
} catch (ExecutionException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

private void renderImage(ImageData imageData2) {
	// TODO Auto-generated method stub
	
}

private List<ImageInfo> scanForImageInfo(CharSequence source) {
	// TODO Auto-generated method stub
	return new ArrayList<>();
}

private void renderText(CharSequence source) {
	// TODO Auto-generated method stub
	
}
}

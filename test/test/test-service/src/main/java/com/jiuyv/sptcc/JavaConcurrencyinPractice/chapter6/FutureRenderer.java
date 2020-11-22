package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureRenderer {
	private final ExecutorService es=Executors.newCachedThreadPool();
 void renderPage(CharSequence source) {
	 final List<ImageInfo> imageInfos=scanForImageInfo(source);
	 Callable<List<ImageData>> task=new Callable<List<ImageData>>() {
		
		@Override
		public List<ImageData> call() throws Exception {
			List<ImageData> imageData=new ArrayList<>();
			for (ImageInfo imageInfo : imageInfos) {
				imageData.add(imageInfo.downloadImage());
			}
			return imageData;
		}
	};

	Future<List<ImageData>> future=es.submit(task);
	renderText(source);
try {
	List<ImageData> data=future.get();
	for (ImageData imageData : data) {
		renderImage(imageData);
	}
} catch (InterruptedException e) {
	Thread.currentThread().interrupt();
	future.cancel(true);
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

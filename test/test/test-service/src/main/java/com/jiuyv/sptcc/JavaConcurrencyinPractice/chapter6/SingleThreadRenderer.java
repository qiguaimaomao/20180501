package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter6;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRenderer {
 void renderPage(CharSequence source) {
	renderText(source);
List<ImageData> imageData=new ArrayList<>();
for (ImageInfo imageInfo : scanForImageInfo(source)) {
	imageData.add(imageInfo.downloadImage());
}
for (ImageData imageData2 : imageData) {
	renderImage(imageData2);
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

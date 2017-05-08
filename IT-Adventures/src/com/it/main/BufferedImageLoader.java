package com.it.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	public BufferedImage loadImage(String path) {
		BufferedImage img=null;
		try {
			img=ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	
	public BufferedImage loadImageFromSS(String ssPath,int ssCol,int ssRow,int width,int height){
		BufferedImage img=null;
		
		img=loadImage(ssPath).getSubimage((ssCol*width)-width, (ssRow*height)-height, width, height);
		
		return img;
	}
	
	public BufferedImage[] getImageSet(BufferedImage ss,int numberOfImg,int width,int height){
		BufferedImage[] imageArray=new BufferedImage[numberOfImg];
		
		
		for(int i=0;i<numberOfImg;i++){
			imageArray[i]=ss.getSubimage((i*width)-width, 0, width, height);
		}
		
		return imageArray;
	}
}

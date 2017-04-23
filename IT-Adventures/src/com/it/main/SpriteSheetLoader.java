package com.it.main;

import java.awt.image.BufferedImage;

public class SpriteSheetLoader {

	private BufferedImageLoader imageLoader;
	
	public SpriteSheetLoader(BufferedImageLoader imageLoader){
		this.imageLoader=imageLoader;
	}
	
	public BufferedImage[] getImageSet(BufferedImage ss,int numberOfImg,int width,int height){
		BufferedImage[] imageArray=new BufferedImage[numberOfImg];
		
		
		for(int i=0;i<numberOfImg;i++){
			imageArray[i]=ss.getSubimage((i*width)-width, 0, width, height);
		}
		
		return imageArray;
	}
}

package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background{

	private BackgroundType backgroundType;
	private BufferedImageLoader imageLoader;
	private BufferedImage[] images;
	private String[] imagePaths;
	
	public Background(BackgroundType backgroundType,BufferedImageLoader imageLoader){
		this.backgroundType=backgroundType;
		this.imageLoader=imageLoader;
		imagePaths=new String[backgroundType.backgroundImageLocations.length];
		for(int i =0;i<backgroundType.backgroundImageLocations.length;i++){
			imagePaths[i]=backgroundType.backgroundImageLocations[i];
		}
		createLevel();
	}
	
	private void createLevel(){
		int numberOfSegments = (int) ((Game.WIDTH/backgroundType.width)+1);
		images=new BufferedImage[numberOfSegments];
		for(int i=0;i<numberOfSegments;i++){
			int index = (Math.round((float)Math.random()*imagePaths.length))-1;
			if(index==-1)index=0;
			images[i]=imageLoader.loadImage(imagePaths[0]);
					
		}
		
	}
	
	public void render(Graphics g){	
		for(int i =0;i<images.length;i++){
			g.drawImage(images[i], (i*(int)backgroundType.width), 0,(int)backgroundType.width,Game.HEIGHT, null);
		}
	}
	
	
	
}

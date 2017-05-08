package com.it.main;

import java.awt.image.BufferedImage;

public class LevelLoader {

	BufferedImageLoader imageLoader;
	GameObjectHandler handler;
	
	public LevelLoader(BufferedImageLoader imageLoader,GameObjectHandler handler){
		this.imageLoader=imageLoader;
		this.handler=handler;
	}
	
	public void loadLevel(String characterName,int level){
		handler.removeAllObjects();
		BufferedImage levelImage = imageLoader.loadImage("/images/levels/"+characterName+"/"+level);
		for(int xx=0;xx<levelImage.getWidth();xx++){
			for(int yy=0;yy<levelImage.getHeight();yy++){
				int pixel = levelImage.getRGB(xx, yy);
				int red  = (pixel>>16) &  0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==0 && green==0 && blue==0)handler.addObject(new Block(xx*64, yy*64, handler, imageLoader, ObjectType.Grass));
				if(red==255 && green==0 && blue==0)handler.addObject(new Player(xx*64,yy*64,imageLoader,handler,ObjectType.Player));
			}
		}
		
	}
}

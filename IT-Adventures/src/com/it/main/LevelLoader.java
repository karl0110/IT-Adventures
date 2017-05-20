package com.it.main;

import java.awt.image.BufferedImage;

public class LevelLoader {

	BufferedImageLoader imageLoader;
	GameObjectHandler handler;
	
	public LevelLoader(BufferedImageLoader imageLoader,GameObjectHandler handler){
		this.imageLoader=imageLoader;
		this.handler=handler;
	}
	
	public Camera loadLevel(String characterName,int level){
		
		handler.removeAllObjects();
		BufferedImage levelImage = imageLoader.loadImage("/levelImages/"+characterName+"_"+level+".png");
		BufferedImage gras = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 2, 1, 32, 32);
		BufferedImage dirt = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 1, 1, 32, 32);
		BufferedImage stone = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 3, 1, 32, 32);
		Player player = null;
		for(int xx=0;xx<levelImage.getWidth();xx++){
			for(int yy=0;yy<levelImage.getHeight();yy++){
				
				int pixel = levelImage.getRGB(xx, yy);
				int red  = (pixel>>16) &  0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==255 && green==0 && blue==0){
					player=new Player(xx*32,yy*32,imageLoader,handler,ObjectType.Player,CharacterType.Jaime,null);
				}
			}
			
		}
		handler.addObject(player);
		for(int xx=0;xx<levelImage.getWidth();xx++){
			for(int yy=0;yy<levelImage.getHeight();yy++){
				int pixel = levelImage.getRGB(xx, yy);
				int red  = (pixel>>16) &  0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==0 && green==0 && blue==0)handler.addObject(new Block(xx*32, yy*32, handler, imageLoader, ObjectType.Dirt,dirt,player));
				else if(red==0&&green==255&&blue==0)handler.addObject(new Block(xx*32,yy*32,handler,imageLoader,ObjectType.Grass,gras,player));
				else if(red==99&&green==99&&blue==99)handler.addObject(new Block(xx*32,yy*32,handler,imageLoader,ObjectType.Stone,stone,player));
			}
		}
		return new Camera(0,0,player);
		
		
	}
}

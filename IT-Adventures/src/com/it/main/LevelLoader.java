package com.it.main;

import java.awt.image.BufferedImage;

public class LevelLoader {

	BufferedImageLoader imageLoader;
	TileHandler handler;
	Game game;
	
	public LevelLoader(BufferedImageLoader imageLoader,TileHandler handler,Game game){
		this.imageLoader=imageLoader;
		this.handler=handler;
		this.game=game;
	}
	
	public Camera loadLevel(String characterName,int level){
		
		handler.removeAllObjects();
		BufferedImage levelImage = imageLoader.loadImage("/levelImages/"+characterName+"_"+level+".png");
		BufferedImage gras = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 2, 1, 32, 32);
		BufferedImage dirt = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 1, 1, 32, 32);
		BufferedImage stone = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 3, 1, 32, 32);
		BufferedImage lava = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 5, 1, 32, 32);
		BufferedImage ice = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 8, 1, 32, 32);
		BufferedImage iceBottom = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 6, 1, 32, 32);
		BufferedImage iceTop = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 7, 1, 32, 32);
		BufferedImage brick = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 9, 1, 32, 32);
		BufferedImage usb = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 1, 2, 32, 32);
		Player player = null;
		for(int xx=0;xx<levelImage.getWidth();xx++){
			for(int yy=0;yy<levelImage.getHeight();yy++){
				
				int pixel = levelImage.getRGB(xx, yy);
				int red  = (pixel>>16) &  0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==255 && green==0 && blue==0){
					player=new Player(xx*32,yy*32,imageLoader,handler,TileType.Player,CharacterType.Jaime,game);
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
				
				if(red==0 && green==0 && blue==0)handler.addObject(new Block(xx*32, yy*32, imageLoader, TileType.Dirt,dirt,player));
				else if(red==0&&green==255&&blue==0)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.Grass,gras,player));
				else if(red==99&&green==99&&blue==99)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.Stone,stone,player));
				else if(red==255&&green==255&&blue==0)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.Lava,lava,player));
				else if(red==208&&green==246&&blue==245)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.Ice,ice,player));	
				else if(red==95&&green==246&&blue==245)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.IceBottom,iceBottom,player));	
				else if(red==0&&green==246&&blue==245)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.IceTop,iceTop,player));
				else if(red==39&&green==63&&blue==39)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.Brick,brick,player));	
				else if(red==0&&green==0&&blue==255)handler.addObject(new Enemy(xx*32,yy*32,imageLoader,TileType.Enemy,handler,EnemyType.Virus,player));
				else if(red==17&&green==0&&blue==119)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.USB,usb,player));
			}
		}
		return new Camera(0,0,player);
		
		
	}
}

package com.it.main;

import java.awt.image.BufferedImage;

public class Level {

	private BufferedImageLoader imageLoader;
	private TileHandler handler;
	private Game game;
	private boolean unlocked;
	private int characterNumber,levelNumber;
	private Player player;
	private Camera camera;
	private Background background;
	
	public Level(BufferedImageLoader imageLoader,TileHandler handler,Game game,boolean unlocked,int characterNumber, int levelNumber){
		this.imageLoader=imageLoader;
		this.handler=handler;
		this.game=game;
		this.unlocked=unlocked;
		this.characterNumber=characterNumber;
		this.levelNumber=levelNumber;
		BackgroundType backgroundType=null;;
		switch(levelNumber){
			case 0:
				backgroundType=BackgroundType.Day;
				break;
			case 1:
				backgroundType=BackgroundType.Night;
				break;
			case 2:
				backgroundType=BackgroundType.Castle;
				break;
			case 3:
				backgroundType=BackgroundType.Snow;
				break;
			
		}
		background=new Background(backgroundType, imageLoader);
	}
	
	public void loadLevel(){
		
		handler.removeAllObjects();
		BufferedImage levelImage = imageLoader.loadImage("/levelImages/"+characterNumber+"_"+levelNumber+".png");
		BufferedImage gras = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 2, 1, 32, 32);
		BufferedImage dirt = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 1, 1, 32, 32);
		BufferedImage stone = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 3, 1, 32, 32);
		BufferedImage lava = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 5, 1, 32, 32);
		BufferedImage ice = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 8, 1, 32, 32);
		BufferedImage iceBottom = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 6, 1, 32, 32);
		BufferedImage iceTop = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 7, 1, 32, 32);
		BufferedImage brick = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 9, 1, 32, 32);
		BufferedImage usb = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 1, 2, 32, 32);
		BufferedImage sign = imageLoader.loadImageFromSS("/images/block_sprite_sheet.png", 1, 10, 32, 32);
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
				else if(red==150&&green==0&&blue==150)handler.addObject(new Block(xx*32,yy*32,imageLoader,TileType.Sign,sign,player));
			}
		}
		camera= new Camera(0,0,player);
		
		
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public int getCharacterNumber() {
		return characterNumber;
	}

	public void setCharacterNumber(int characterNumber) {
		this.characterNumber = characterNumber;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}
	
	
	
}

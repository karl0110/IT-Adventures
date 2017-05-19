package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject{

	public Block(float x,float y,GameObjectHandler handler,BufferedImageLoader imageLoader, ObjectType type,Player player) {
		super(x,y,imageLoader, type,handler,player);
		image = imageLoader.loadImageFromSS(type.imagePath,type.ssCol,type.ssRow,(int)width,(int)height);
		
	}
	
	public Block(float x,float y, GameObjectHandler handler,BufferedImageLoader imageLoader,ObjectType type,BufferedImage image,Player player){
		super(x,y,imageLoader, type,handler,player);
		this.image=image;
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.BLUE);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		if(x>=(player.getX()-Game.WIDTH/2)&&x<=(player.getX()+Game.WIDTH/2)){
			g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getUpperBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	@Override
	public Rectangle getBottomBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	@Override
	public Rectangle getLeftBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	@Override
	public Rectangle getRightBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

}

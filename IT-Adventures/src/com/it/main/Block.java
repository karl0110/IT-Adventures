package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends NonPlayerGameObject{

	public Block(float x,float y,BufferedImageLoader imageLoader, ObjectType type,Player player) {
		super(x,y,imageLoader, type,player);
		image = imageLoader.loadImageFromSS(type.imagePath,type.ssCol,type.ssRow,(int)width,(int)height);
		
	}
	
	public Block(float x,float y,BufferedImageLoader imageLoader,ObjectType type,BufferedImage image,Player player){
		super(x,y,imageLoader, type,player);
		this.image=image;
	}


	public void render(Graphics g) {

		if(player.getX()>x-Game.WIDTH &&player.getX()<x+Game.WIDTH){
			g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		}
		
	}

	public void tick() {

	}

	
	public Rectangle getUpperBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	
	public Rectangle getBottomBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	
	public Rectangle getLeftBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	
	public Rectangle getRightBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

}

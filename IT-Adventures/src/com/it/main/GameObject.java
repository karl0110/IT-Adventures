package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class GameObject {

	protected float x,y,velX,velY,width,height;
	protected boolean falling,jumping,passable;
	protected BufferedImage image;
	
	public GameObject(BufferedImageLoader imageLoader,ObjectType type) {
		
		image = imageLoader.loadImage(type.imagePath);
		this.passable=type.passable;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
		
	public abstract Rectangle getUpperBounds();
	public abstract Rectangle getBottomBounds();
	public abstract Rectangle getLeftBounds();
	public abstract Rectangle getRightBounds();
	
	protected boolean collisionRight(){
		boolean collision=false;
		
		return collision;
	}
	protected boolean collisionLeft(){
		boolean collision=false;
		
		return collision;
	}
	protected boolean collisionBottom(){
		boolean collision=false;
		
		return collision;
	}
	protected boolean collisionUp(){
		boolean collision=false;
		
		return collision;
	}
	
	
}

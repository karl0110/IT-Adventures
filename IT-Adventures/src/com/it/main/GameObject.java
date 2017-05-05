package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class GameObject {

	protected float x,y,velX,velY,width,height;
	protected boolean falling,jumping,passable;
	protected BufferedImage image;
	protected GameObjectHandler handler;
	
	public GameObject(float x,float y,BufferedImageLoader imageLoader,ObjectType type,GameObjectHandler handler) {
		this.x=x;
		this.y=x;
		//image = imageLoader.loadImage(type.imagePath);
		this.passable=type.passable;
		this.handler=handler;
		this.width=type.width;
		this.height=type.height;
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

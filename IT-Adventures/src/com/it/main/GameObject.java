package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 * @author vincent.wiechmann, karl.mattes
 *
 */
public abstract class GameObject {

	
	
	protected float x,y,velX,velY,width,height;
	protected boolean falling,jumping,passable;
	protected BufferedImage image;
	protected GameObjectHandler handler;
	protected ObjectType type;
	
	public GameObject(float x,float y,BufferedImageLoader imageLoader,ObjectType type,GameObjectHandler handler) {
		this.x=x;
		this.y=y;
		
		this.passable=type.passable;
		this.handler=handler;
		this.width=type.width;
		this.height=type.height;
		image = imageLoader.loadImageFromSS(type.imagePath,type.ssCol,type.ssRow,(int)width,(int)height);
		this.type = type;
	}
	
	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public GameObjectHandler getHandler() {
		return handler;
	}

	public void setHandler(GameObjectHandler handler) {
		this.handler = handler;
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

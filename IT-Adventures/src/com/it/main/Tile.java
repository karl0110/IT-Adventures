package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Tile {

	protected float x,y,velX,velY,width,height;
	protected boolean falling,jumping,passable,isTileEntity;
	protected BufferedImage image;
	protected TileType type;
	protected BufferedImageLoader imageLoader;
	
	public Tile(float x,float y,BufferedImageLoader imageLoader,TileType type){
		this.x=x;
		this.y=y;
		this.passable=type.passable;
		this.width=type.width;
		this.height=type.height;
		this.type = type;
		this.imageLoader=imageLoader;
	}
	
	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
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

	
	
	public boolean isTileEntity() {
		return isTileEntity;
	}

	public void setLivingTileEntity(boolean isTileEntity) {
		this.isTileEntity = isTileEntity;
	}

	public abstract void render(Graphics g);
	
	
	

	public Rectangle getUpperBounds() {
		return new Rectangle((int) x +8, (int) y, (int) width -16, (int) height/2);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) x+8, (int) y+(int)(height/2), (int) width -16, (int)height/2 );
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y+2, (int) 5, (int)height-4 );
	}


	public Rectangle getRightBounds() {
		return new Rectangle((int) x+((int)width-5) , (int) y+2, 5, (int)height-4 );
	}
}

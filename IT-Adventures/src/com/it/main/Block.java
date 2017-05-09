package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject{

	public Block(float x,float y,GameObjectHandler handler,BufferedImageLoader imageLoader, ObjectType type) {
		super(x,y,imageLoader, type,handler);
		image = imageLoader.loadImageFromSS(type.imagePath,type.ssCol,type.ssRow,(int)width,(int)height);
		
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.BLUE);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getUpperBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBottomBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getLeftBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRightBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

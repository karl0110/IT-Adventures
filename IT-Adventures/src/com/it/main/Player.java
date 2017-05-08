package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	public Player(float x, float y, BufferedImageLoader imageLoader, GameObjectHandler handler, ObjectType type) {
		super(x, y, imageLoader, type, handler);
		falling=true;
	}

	public void render(Graphics g) {
		//g.setColor(Color.BLUE);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);

	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(falling || jumping){
			
			velY += 0.05f;
			//joa also dann öööhm fällt er halt
		}
		
	}

	public Rectangle getUpperBounds() {
		return null;
	}

	public Rectangle getBottomBounds() {
		return null;
	}

	public Rectangle getLeftBounds() {
		return null;
	}


	public Rectangle getRightBounds() {
		return null;
	}

}

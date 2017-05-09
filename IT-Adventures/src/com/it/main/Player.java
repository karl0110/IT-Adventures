package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	private Animator idleAnimator;
	
	public Player(float x, float y, BufferedImageLoader imageLoader, GameObjectHandler handler, ObjectType type,CharacterType characterType) {
		super(x, y, imageLoader, type, handler);
		falling=true;
		idleAnimator=new Animator(imageLoader.getImageSet("/images/character_idle_ss.png", 3, width, height, characterType.ssCol),10);
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.BLUE);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		//g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		idleAnimator.renderAnimation(g, x, y, width, height);

	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(falling || jumping){
			
			//velY += 0.05f;
			//joa also dann öööhm fällt er halt
		}
		
		if(velX==0&&velY==0)idleAnimator.runAnimation();
		
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

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

	/**
	 * 
	 */
	public void tick() {
		x += velX;
		y += velY;
		
		if(falling || jumping){
			
			velY += 0.05f;
			//joa also dann öööhm fällt er halt
		}
		
		if(velX==0)idleAnimator.runAnimation();
		collision(handler);
		
	}
	
	private void collision(GameObjectHandler handler){
		//hier must die collision hin ja, ja
		//das ist eine blöde situation ja, ja
		//ich bin auf der letzten Ration ja, ja
		//shit das is viel zu viel information ja, ja
		//deutschland is eine geile nation ja, ja
		//GOA GOA GOA ich bin jetz ertig ja, ja
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject=handler.object.get(i);
			if(tempObject.isPassable()==false){
				if(getBottomBounds().intersects(tempObject.getUpperBounds())) {
					velY=0;
					y=tempObject.getY()-(int)height;
					falling = false;
					jumping = false;
				}
				if(getUpperBounds().intersects(tempObject.getBottomBounds())) {
					

				}
				if(getLeftBounds().intersects(tempObject.getRightBounds())) {
					

				}
				if(getRightBounds().intersects(tempObject.getLeftBounds())) {
					

				}
			}
		}
				
	}

	public Rectangle getUpperBounds() {
		return new Rectangle((int) x +1, (int) y, (int) width -2, (int) height/2);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) x+1, (int) y/2, (int) width -2, (int)height/2 );
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y, (int) 2, (int)height );
	}


	public Rectangle getRightBounds() {
		return new Rectangle((int) x+((int)height-2) , (int) y, 2, (int)height );
	}

}

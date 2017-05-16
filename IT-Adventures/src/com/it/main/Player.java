package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;



public class Player extends GameObject {

	private Animator idleAnimator;
	
	public Player(float x, float y, BufferedImageLoader imageLoader, GameObjectHandler handler, ObjectType type,CharacterType characterType) {
		super(x, y, imageLoader, type, handler);
		falling=true;
		idleAnimator=new Animator(imageLoader.getImageSet("/images/character_idle_ss.png", 3, width, height,characterType.ssCol),10);
		
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
		collision();
		
		x += velX;
		y += velY;
		
		if(falling || jumping){
			
			velY += 0.981f;
			//joa also dann öööhm fällt er halt
		}
		
		if(velX==0)idleAnimator.runAnimation();
		
		
		
	}
	
	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject=handler.object.get(i);
			if(tempObject.isPassable()==false){
				if(getBottomBounds().intersects(tempObject.getUpperBounds())) {
					y=tempObject.getY()-(int)height-1;
					//falling = false;
					jumping = false;
					velY=0;
					
					
				}
				if(getUpperBounds().intersects(tempObject.getBottomBounds())){
					y=tempObject.getY()+tempObject.getHeight()+1;
					velY=0;
				}
				if(getLeftBounds().intersects(tempObject.getRightBounds())) {
					x=tempObject.getX()+tempObject.getWidth()+1;
					velX=0;	

				}
				if(getRightBounds().intersects(tempObject.getLeftBounds())) {
					x=tempObject.getX()-(int)width-1;
					velX=0;

				}
			}
		}
				
	}

	public Rectangle getUpperBounds() {
		return new Rectangle((int) x +2, (int) y, (int) width -4, (int) height/2);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) x+2, (int) y+(int)(height/2), (int) width -4, (int)height/2 );
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y, (int) 2, (int)height );
	}


	public Rectangle getRightBounds() {
		return new Rectangle((int) x+((int)width-2) , (int) y, 2, (int)height );
	}

}

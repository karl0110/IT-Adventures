package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;



public class Player extends GameObject {

	private Animator idleAnimator,rightWalkAnimator,leftWalkAnimator;
	
	
	public Player(float x, float y, BufferedImageLoader imageLoader, GameObjectHandler handler, ObjectType type,CharacterType characterType,Player player) {
		super(x, y, imageLoader, type, handler,player);
		falling=true;
		idleAnimator=new Animator(imageLoader.getImageSet("/images/character_idle_ss.png", 3, width, height,characterType.ssCol),15);
		rightWalkAnimator=new Animator(imageLoader.getImageSet("/images/character_walk_right_ss.png", 6, width, height,characterType.ssCol),15);
		leftWalkAnimator=new Animator(imageLoader.getImageSet("/images/character_walk_left_ss.png", 6, width, height,characterType.ssCol),15);
	}

	public void render(Graphics g) {
		//g.setColor(Color.BLUE);
		
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		//g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		
		if(velX>0)rightWalkAnimator.renderAnimation(g, x, y, width, height);
		else if(velX<0)leftWalkAnimator.renderAnimation(g, x, y, width, height);
		else idleAnimator.renderAnimation(g, x, y, width, height);

	}

	/**
	 * 
	 */
	public void tick() {
		
		
		x += velX;
		y += velY;
		
		if(falling || jumping){
			
			velY += 0.981f;
			//joa also dann öööhm fällt er halt
		}
		collision();
		
		
		if(velX==0)idleAnimator.runAnimation();
		else if(velX>0)rightWalkAnimator.runAnimation();
		else if(velX<0)leftWalkAnimator.runAnimation();
		
		
		
	}
	
	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject=handler.object.get(i);
			if(tempObject.isPassable()==false){
				
				if(getBottomBounds().intersects(tempObject.getUpperBounds())) {
					y=(tempObject.getY()-(int)height);
					falling = false;
					if(jumping==true)jumping = false;
					velY=0;
				}
				else{
					falling =true;
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
		return new Rectangle((int) x +5, (int) y, (int) width -10, (int) height/2);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) x+5, (int) y+(int)(height/2), (int) width -10, (int)height/2 );
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y+2, (int) 2, (int)height-4 );
	}


	public Rectangle getRightBounds() {
		return new Rectangle((int) x+((int)width-2) , (int) y+2, 2, (int)height-4 );
	}

}

package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;



public class Player extends TileEntity{

	private Animator idleAnimator,rightWalkAnimator,leftWalkAnimator,rightJumpAnimator,leftJumpAnimator;
	private float health;
	public  final static float MAXHEALTH=100;
	
	public Player(float x, float y, BufferedImageLoader imageLoader, TileHandler handler, ObjectType type,CharacterType characterType) {
		super(x, y, imageLoader, type,handler);
		this.handler=handler;
		falling=true;
		idleAnimator=new Animator(imageLoader.getImageSet("/images/character_idle_ss.png", 3, width, height,characterType.ssCol),15);
		rightWalkAnimator=new Animator(imageLoader.getImageSet("/images/character_walk_right_ss.png", 6, width, height,characterType.ssCol),15);
		leftWalkAnimator=new Animator(imageLoader.getImageSet("/images/character_walk_left_ss.png", 6, width, height,characterType.ssCol),15);
		rightJumpAnimator=new Animator(imageLoader.getImageSet("/images/character_jump_right_ss.png", 3, width, height,characterType.ssCol),15);
		leftJumpAnimator=new Animator(imageLoader.getImageSet("/images/character_jump_left_ss.png", 3, width, height,characterType.ssCol),15);
		health=MAXHEALTH;
	}

	public void render(Graphics g) {

		if(velX>0){
			if(!jumping){
				rightWalkAnimator.renderAnimation(g, x, y, width, height);
			}
			else{
				rightJumpAnimator.renderAnimation(g, x, y, width, height);
			}
		}
		else if(velX<0){
			if(!jumping){
				leftWalkAnimator.renderAnimation(g, x, y, width, height);
			}
			else{
				leftJumpAnimator.renderAnimation(g, x, y, width, height);
			}
		}
		else idleAnimator.renderAnimation(g, x, y, width, height);

	}

	/**
	 * 
	 */
	public void tick() {
		
		
		x += velX;
		y += velY;
		
		// K�nstliche Gravitation f�r den Spieler wenn er f�llt oder springt (also immer)
		if(falling || jumping){ 
			
			velY += 0.981f; // Y-Wert steigt immer (wird durch Kollision unterbrochen)
		}
		collision();
		
		
		if(velX==0)idleAnimator.runAnimation();
		else if(velX>0){
			if(!jumping){
				rightWalkAnimator.runAnimation();
			}
			else{
				rightJumpAnimator.runAnimation();
			}
		}
		else if(velX<0){
			if(!jumping){
				leftWalkAnimator.runAnimation();
			}
			else{
				leftJumpAnimator.runAnimation();
			}
		}
		
		if(y>Game.HEIGHT){
			health=0;
		}
		
		if(health==0){
			Game.State=Game.STATE.GameOver;
		}
		
	}
	
	

	/**
	 *  Vier Kollisionsrechtecke f�r den Spieler (Oben, Unten, Links und Rechts) werden erstellt
	 */

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

	@Override
	public void leftCollisionReaction(Tile tempObject) {
		x=tempObject.getX()+tempObject.getWidth()+1;
		velX=0;
		
	}

	@Override
	public void rightCollisionReaction(Tile tempObject) {
		x=tempObject.getX()-(int)width-1;
		velX=0;
		
	}

}

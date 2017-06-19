package com.it.main;

import java.awt.Graphics;



public class Player extends LivingTileEntity{

	private Animator idleAnimator,rightWalkAnimator,leftWalkAnimator,rightJumpAnimator,leftJumpAnimator;
	
	public  final static float MAXHEALTH=100;
	
	
	
	public Player(float x, float y, BufferedImageLoader imageLoader, TileHandler handler, TileType type,CharacterType characterType) {
		super(x, y, imageLoader, type,handler);
		this.handler=handler;
		falling=true;
		idleAnimator=new Animator(imageLoader.getImageSet("/images/character_idle_ss.png", 3, width, height,characterType.ssCol),15);
		rightWalkAnimator=new Animator(imageLoader.getImageSet("/images/character_walk_right_ss.png", 6, width, height,characterType.ssCol),15);
		leftWalkAnimator=new Animator(imageLoader.getImageSet("/images/character_walk_left_ss.png", 6, width, height,characterType.ssCol),15);
		rightJumpAnimator=new Animator(imageLoader.getImageSet("/images/character_jump_right_ss.png", 3, width, height,characterType.ssCol),15);
		leftJumpAnimator=new Animator(imageLoader.getImageSet("/images/character_jump_left_ss.png", 3, width, height,characterType.ssCol),15);
		health=MAXHEALTH;
		handler.addObject(healthBar=new HealthBar(0,0, imageLoader, TileType.HealthBar, handler, this, this));
		
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
		
		// Künstliche Gravitation für den Spieler wenn er fällt oder springt (also immer)
		if(falling || jumping){ 
			
			velY += 0.981f; // Y-Wert steigt immer (wird durch Kollision unterbrochen)
		}
		collision();
		
		
		healthBar.reloadCoordinates(x, y);
		
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
		
		if(velX>0){
			facingRight=true;
		}
		else if(velX<0){
			facingRight=false;
		}
	}
	
	

	/**
	 *  Vier Kollisionsrechtecke für den Spieler (Oben, Unten, Links und Rechts) werden erstellt
	 */

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

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	@Override
	public void upperCollisionReaction(Tile tempObject) {
		y=tempObject.getY()+tempObject.getHeight()+1;
		velY=0;
		
	}

	@Override
	public void bottomCollisionReaction(Tile tempObject) {
		y=(tempObject.getY()-(int)height);
		falling = false;
		if(jumping==true)jumping = false;
		velY=0;
		
	}

	
	
	
}

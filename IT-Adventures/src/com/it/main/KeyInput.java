package com.it.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author vincent.wiechmann,karl.mattes
 *
 */
public class KeyInput extends KeyAdapter{
	
	private TileHandler handler;
	private BufferedImageLoader imageLoader;
	
	public KeyInput(TileHandler handler,BufferedImageLoader imageLoader) {
		this.handler = handler;
		this.imageLoader=imageLoader;
		}

	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		
		}
		for(int i=0;i<handler.object.size();i++){
			Tile tempObject=handler.object.get(i);
			
			if(Game.State==Game.STATE.Game&&tempObject.getType()==TileType.Player){
			
				if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A )tempObject.setVelX(-3);
			
				if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D)tempObject.setVelX(3);
				
				if(key==KeyEvent.VK_SPACE&&!tempObject.isJumping()){
					tempObject.setVelY(-20);
					tempObject.setFalling(true);
					tempObject.setJumping(true);
				}
				
				if(key==KeyEvent.VK_ENTER){
					int shotX;
					int shotY;
					boolean facingRight;
					if(((Player)tempObject).isFacingRight()){
						shotX=(int) (tempObject.getX()+tempObject.getWidth());
						shotY=(int) (tempObject.getY()+tempObject.getHeight()/2);
					}
					else{
						shotX=(int) tempObject.getX();
						shotY=(int) (tempObject.getY()+tempObject.getHeight()/2);
					}
					facingRight=((Player)tempObject).isFacingRight();
					handler.addObject(new Shot(shotX, shotY, imageLoader, TileType.Shot, handler, 600, 10, facingRight, 1, 6));
				}
			}
			
		}

		
	}
	
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			Tile tempObject=handler.object.get(i);
			
			if(Game.State==Game.STATE.Game&&tempObject.getType()==TileType.Player){
				
				if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A)tempObject.setVelX(0);
			
				if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D)tempObject.setVelX(0);
				
				
			}
		}
	}
	
}

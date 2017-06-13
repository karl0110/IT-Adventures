package com.it.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author vincent.wiechmann,karl.mattes
 *
 */
public class KeyInput extends KeyAdapter{
	
	private TileHandler handler;
	
	public KeyInput(TileHandler handler) {
		this.handler = handler;
		}

	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		
		}
		for(int i=0;i<handler.object.size();i++){
			Tile tempObject=handler.object.get(i);
			
			if(Game.State==Game.STATE.Game&&tempObject.getType()==ObjectType.Player){
			
				if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A )tempObject.setVelX(-3);
			
				if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D)tempObject.setVelX(3);
				
				if(key==KeyEvent.VK_SPACE&&!tempObject.isJumping()){
					tempObject.setVelY(-20);
					tempObject.setFalling(true);
					tempObject.setJumping(true);
				}
				
				
			}
			
		}

		
	}
	
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			Tile tempObject=handler.object.get(i);
			
			if(Game.State==Game.STATE.Game&&tempObject.getType()==ObjectType.Player){
				
				if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A)tempObject.setVelX(0);
			
				if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D)tempObject.setVelX(0);
				
				
			}
		}
	}
	
}

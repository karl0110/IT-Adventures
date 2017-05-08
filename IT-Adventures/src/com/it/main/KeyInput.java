package com.it.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author vincent.wiechmann,karl.mattes
 *
 */
public class KeyInput extends KeyAdapter{
	
	private GameObjectHandler handler;
	
	public KeyInput(GameObjectHandler handler) {
		this.handler = handler;
		}

	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		
		}
		if(key==KeyEvent.VK_LEFT && Game.State==Game.STATE.Game) {
			for(int i=0;i<=handler.object.size();i++)
			{
				if(handler.object.get(i).getType()==ObjectType.Player)
				{
					handler.object.get(i).setVelX(5);
				}
			}
		}
		if(key==KeyEvent.VK_RIGHT && Game.State==Game.STATE.Game) {
			for(int i=0;i<=handler.object.size();i++)
			{
				if(handler.object.get(i).getType()==ObjectType.Player)
				{
					handler.object.get(i).setVelX(-5);
				}
			}
		}

		
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
}

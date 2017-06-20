package com.it.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author vincent.wiechmann,karl.mattes
 * 
 * Die Klasse reagiert auf Tastenschläge, welche bei einem bestimmten Zustand des Spieles bestimmte Reaktionen hervorrufen.
 */
public class KeyInput extends KeyAdapter{
	
	private TileHandler handler;
	private BufferedImageLoader imageLoader;
	
	
	public KeyInput(TileHandler handler,BufferedImageLoader imageLoader) {
		this.handler = handler;
		this.imageLoader=imageLoader;
		}

	/**
	 * Wenn eine Taste gedrückt wird, wird diese Methode aufgerufen.
	 * 
	 * @see KeyAdapter
	 * @param e Die Taste, welche gedrückt wurde
	 */
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();//Der zu der gedrückten Taste zugehöriger Zahlenwert.
		
		if(key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		
		}
		for(int i=0;i<handler.object.size();i++){//Alle Spielobjekte werden nach einander ausgewählt.
			Tile tempObject=handler.object.get(i);
			
			if(Game.State==Game.STATE.Game&&tempObject.getType()==TileType.Player){//Falls das Spielobjekt der Spieler ist,
			
				if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A )tempObject.setVelX(-3);//und die Taste "Links" oder "A" war, bewegt sich der Spieler nach Links
			
				if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D)tempObject.setVelX(3);//und die Taste "Rechts" oder "D" war, bewegt sich der Spieler nach Rechts
				
				if(key==KeyEvent.VK_SPACE&&!tempObject.isJumping()){//Falls die Leertaste gedrückt wurde und der Spieler nicht bereits am springen ist,
					tempObject.setVelY(-20);//wird er nach oben bewegt,
					tempObject.setFalling(true);//als fallend eingestuft
					tempObject.setJumping(true);//und als springend festgelegt.
				}
				
				if(key==KeyEvent.VK_ENTER && ((Player)tempObject).getCooldown()==0){//Falls Enter gedrückt wurde, und der Spieler wieder bereit ist zu schießen (zur Vermeidung vom gedrückt halten der Taste),
					int shotX;//x-Koordinate des Schusses, welcher erstellt wird.
					int shotY;//y-Koordinate des Schusses, welcher erstellt wird.
					boolean facingRight;//in welche Richtung der Spieler guckt, also auch die Richtung des Schusses.
					if(((Player)tempObject).isFacingRight()){//Falls der Spieler nach rechts guckt, muss der Schuss rechts von ihm platziert werden.
						shotX=(int) (tempObject.getX()+tempObject.getWidth());
						shotY=(int) (tempObject.getY()+tempObject.getHeight()/2);
					}
					else{//Falls der Spieler nach links guckt, muss der Schuss links von ihm platziert werden.
						shotX= (int)( tempObject.getX()-TileType.Shot.width);
						shotY=(int) (tempObject.getY()+tempObject.getHeight()/2);
					}
					facingRight=((Player)tempObject).isFacingRight();
					handler.addObject(new Shot(shotX, shotY, imageLoader, TileType.Shot, handler, 600, 10, facingRight, 1, 6));
					((Player)tempObject).setCooldown(30);//Der Aktions-Cooldown des Spielers wird auf eine halbe Sekunde gesetzt.
				}
			}
			
		}

		
	}
	
	/**
	 * Wenn eine Taste losgelassen wird, wird diese Methode aufgerufen.
	 * 
	 * @see KeyAdapter
	 * @param e Die Taste, welche losgelassen wurde
	 */
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			Tile tempObject=handler.object.get(i);
			
			if(Game.State==Game.STATE.Game&&tempObject.getType()==TileType.Player){
				
				if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A)tempObject.setVelX(0);//Die Bewegung des Spielers wird gestoppt.
			
				if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D)tempObject.setVelX(0);
				
				
			}
		}
	}
	
}

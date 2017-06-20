package com.it.main;

/**
 * 
 * @author KarlMatt, Filip
 *
 *Dies ist die Cameraklasse der Anwendung "IT-Adventure
 *Die Cameraklasse sollte bewirken, dass der zu sehende Bildschirmausschnitt
 *sich mit der Instanz eines Spielers mitbewegt.
 *
 *Dazu wird die X-Koordinate der X-Koordinate der Instanz des Spielers angeglichen.
 */
public class Camera {

	private float x,y;
	private Player player;
	
	public Camera(float x, float y,Player player){
		this.x = x;
		this.y = y;
		this.player=player;
	}
	
	/**
	 * setze die X-Koordinate durch Prüfen der X-Koordinate der Instanz des Spielers.
	 */
	public void tick(){
		x+= ((-player.getX() + (Game.WIDTH/2))-x)*0.1;
		
	}
	
	/**
	 * get()- und set()-Methoden der Attribute der Cameraklasse
	 */

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}

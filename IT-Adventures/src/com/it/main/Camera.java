package com.it.main;

public class Camera {

	private float x,y;
	private Player player;
	
	public Camera(float x, float y,Player player){
		this.x = x;
		this.y = y;
		this.player=player;
	}
	
	public void tick(){
		x+= ((-player.getX() + (Game.WIDTH/2))-x)*0.15;
		
	}

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

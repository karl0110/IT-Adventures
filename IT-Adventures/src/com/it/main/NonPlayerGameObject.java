package com.it.main;

public abstract class NonPlayerGameObject extends GameObject {

	protected Player player;
	
	public NonPlayerGameObject(float x, float y, BufferedImageLoader imageLoader, ObjectType type, Player player) {
		super(x, y, imageLoader, type);
		this.player=player;
		
	}

}

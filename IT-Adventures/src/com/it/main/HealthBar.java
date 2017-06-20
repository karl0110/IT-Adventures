package com.it.main;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends TileEntity {

	private float health;
	private float healthPixel;
	private LivingTileEntity entity;
	
	public HealthBar(float x, float y, BufferedImageLoader imageLoader, TileType type, TileHandler handler,LivingTileEntity entity) {
		super(x, y, imageLoader, type,handler);
		this.entity=entity;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.green);
		g.fillRect((int)x+1, (int)y+1, (int)healthPixel-2, (int)height-2);
		

	}

	@Override
	public void tick() {
		
		health= entity.getHealth();
		healthPixel=(health/Player.MAXHEALTH)*(int)width;
	}
	
	public void reloadCoordinates(float entityX, float entityY){
		x=entityX;
		y=entityY-height-5;
	}

	@Override
	public void leftCollisionReaction(Tile tempObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightCollisionReaction(Tile tempObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upperCollisionReaction(Tile tempObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bottomCollisionReaction(Tile tempObject) {
		// TODO Auto-generated method stub
		
	}
	

}

package com.it.main;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends TileObject {

	private float health;
	private float healthPixel;
	private LivingTileEntity entity;
	
	public HealthBar(float x, float y, BufferedImageLoader imageLoader, TileType type, TileHandler handler,LivingTileEntity entity,Player player) {
		super(x, y, imageLoader, type,player);
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

}

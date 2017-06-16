package com.it.main;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends TileObject {

	private float health;
	private float healthPixel;
	private Player player;
	
	public HealthBar(float x, float y, BufferedImageLoader imageLoader, TileType type, TileHandler handler,Player player) {
		super(x, y, imageLoader, type,player);
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, Game.HEIGHT-(int)height, (int)width, (int)height);
		g.setColor(Color.green);
		g.fillRect(0, Game.HEIGHT-(int)height, (int)healthPixel, (int)height);
		

	}

	@Override
	public void tick() {
		health= 60;
		healthPixel=(health/Player.MAXHEALTH)*(int)width;
	}

}

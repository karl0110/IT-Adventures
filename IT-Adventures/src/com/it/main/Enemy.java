package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends TileEntity{

	public Enemy(float x, float y, BufferedImageLoader imageLoader, ObjectType type,EnemyType enemyType) {
		super(x, y, imageLoader, type);
		image=imageLoader.loadImageFromSS("images/enemy_sprite_sheet.png",  enemyType.ssCol,enemyType.ssRow, 32, 32);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getUpperBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBottomBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getLeftBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRightBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

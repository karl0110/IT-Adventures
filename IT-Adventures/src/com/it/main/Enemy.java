package com.it.main;

import java.awt.Graphics;

public class Enemy extends TileEntity{

	public Enemy(float x, float y, BufferedImageLoader imageLoader, TileType type,TileHandler handler,EnemyType enemyType) {
		super(x, y, imageLoader, type,handler);
		image=imageLoader.loadImageFromSS("images/enemy_sprite_sheet.png",  enemyType.ssCol,enemyType.ssRow, 32, 32);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x, (int)y,(int)width,(int)height, null);
		
	}

	@Override
	public void tick() {
		
		
	}


	@Override
	public void leftCollisionReaction(Tile tempObject) {
		
		
	}

	@Override
	public void rightCollisionReaction(Tile tempObject) {
		
		
	}

}

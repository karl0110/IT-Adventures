package com.it.main;

import java.awt.Graphics;

public class Enemy extends TileEntity{

	private float leftPatrolCoordinate,rightPatrolCoordinate;
	private boolean isWalkingRight;
	
	public Enemy(float x, float y, BufferedImageLoader imageLoader, TileType type,TileHandler handler,EnemyType enemyType) {
		super(x, y, imageLoader, type,handler);
		//image=imageLoader.loadImageFromSS("images/enemy_sprite_sheet.png",  enemyType.ssCol,enemyType.ssRow, 32, 32);
		image=imageLoader.loadImage("/images/enemy1.png");
		leftPatrolCoordinate=x;
		rightPatrolCoordinate=x+600;
		isWalkingRight=true;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x, (int)y,(int)width,(int)height, null);
		
	}

	@Override
	public void tick() {
		
		x+=velX;
		y+=velY;
		
		if(falling || jumping){ 
			
			velY += 0.981f; // Y-Wert steigt immer (wird durch Kollision unterbrochen)
		}	
		
		if(isWalkingRight==true){
			if(x<rightPatrolCoordinate){
				velX=2;
			}
			else{
				isWalkingRight=false;
			}
		}
		else{
			if(x>leftPatrolCoordinate){
				velX=-2;
			}
			else{
				isWalkingRight=true;
			}
		}
		collision();
	}


	@Override
	public void leftCollisionReaction(Tile tempObject) {
		velY=(-10);
		falling=true;
		jumping=true;
		
	}

	@Override
	public void rightCollisionReaction(Tile tempObject) {
		velY=(-10);
		falling=true;
		jumping=true;
		
	}

}

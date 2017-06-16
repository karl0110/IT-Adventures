package com.it.main;

import java.awt.Graphics;

public class Shot extends TileEntity {

	private int range;
	private int damage;
	private boolean goingRight;
	private float startingCoordinate;
	private float speed;
	
	
	public Shot(float x, float y, BufferedImageLoader imageLoader, TileType type, TileHandler handler,int range, int damage, boolean goingRight, int ssRow, float speed) {
		super(x, y, imageLoader, type, handler);
		this.range=range;
		this.damage=damage;
		this.goingRight=goingRight;
		image=imageLoader.loadImageFromSS("/images/shot_spite_sheet.png", 1, ssRow, 32, 32);
		this.speed=speed;
		startingCoordinate= x;
		
	}

	@Override
	public void leftCollisionReaction(Tile tempObject) {
		if(tempObject.getType()==TileType.Player){
			((Player) tempObject).removeHealth(damage);
		}
		else{
			handler.removeObject(this);
		}

	}

	@Override
	public void rightCollisionReaction(Tile tempObject) {
		if(tempObject.getType()==TileType.Player){
			((Player) tempObject).removeHealth(damage);
		}
		else{
			handler.removeObject(this);
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x,(int)y,(int)width,(int)height,null);

	}

	@Override
	public void tick() {
		if(goingRight){
			x+=speed;
			if(x-startingCoordinate>range){
				handler.removeObject(this);
			}
		}
		else{
			x-=speed;
			if(startingCoordinate-x>range){
				handler.removeObject(this);
			}
		}
		
		collision();

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

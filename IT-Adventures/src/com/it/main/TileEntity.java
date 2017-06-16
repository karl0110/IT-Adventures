package com.it.main;

import java.awt.Graphics;

public abstract class TileEntity extends Tile{

	protected TileHandler handler;
	
	public TileEntity(float x, float y, BufferedImageLoader imageLoader, TileType type,TileHandler handler) {
		super(x, y, imageLoader, type);
		this.handler=handler;
	}

	protected void collision(){
		for(int i=0;i<handler.object.size();i++){
			Tile tempObject=handler.object.get(i);
			if(tempObject.isPassable()==false){
				
				if(getBottomBounds().intersects(tempObject.getUpperBounds())) {
					bottomCollisionReaction(tempObject);
				}
				else{
					falling =true;
				}
				
				if(getUpperBounds().intersects(tempObject.getBottomBounds())){
					upperCollisionReaction(tempObject);
				}
				if(getLeftBounds().intersects(tempObject.getRightBounds())) {
					leftCollisionReaction(tempObject);

				}
				if(getRightBounds().intersects(tempObject.getLeftBounds())) {
					rightCollisionReaction(tempObject);

				}
			}
		}
	}
	public abstract void leftCollisionReaction(Tile tempObject);
	public abstract void rightCollisionReaction(Tile tempObject);
	public abstract void upperCollisionReaction(Tile tempObject);
	public abstract void bottomCollisionReaction(Tile tempObject);
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	

}

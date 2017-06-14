package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;

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
					y=(tempObject.getY()-(int)height);
					falling = false;
					if(jumping==true)jumping = false;
					velY=0;
				}
				else{
					falling =true;
				}
				
				if(getUpperBounds().intersects(tempObject.getBottomBounds())){
					y=tempObject.getY()+tempObject.getHeight()+1;
					velY=0;
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
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	
	public Rectangle getUpperBounds() {
		return new Rectangle((int) x +5, (int) y, (int) width -10, (int) height/2);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle((int) x+5, (int) y+(int)(height/2), (int) width -10, (int)height/2 );
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y+2, (int) 2, (int)height-4 );
	}


	public Rectangle getRightBounds() {
		return new Rectangle((int) x+((int)width-2) , (int) y+2, 2, (int)height-4 );
	}
}

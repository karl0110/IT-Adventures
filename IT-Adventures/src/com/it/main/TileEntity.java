package com.it.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class TileEntity extends Tile{

	public TileEntity(float x, float y, BufferedImageLoader imageLoader, ObjectType type) {
		super(x, y, imageLoader, type);
		// TODO Auto-generated constructor stub
	}


public abstract void render(Graphics g);
	
	public abstract void tick();
	
	
	public abstract Rectangle getUpperBounds();
	public abstract Rectangle getBottomBounds();
	public abstract Rectangle getLeftBounds();
	public abstract Rectangle getRightBounds();
}

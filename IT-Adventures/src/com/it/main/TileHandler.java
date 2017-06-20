
package com.it.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class TileHandler {

	public LinkedList<Tile> object = new LinkedList<Tile>();
	public LinkedList<TileEntity> tickObject = new LinkedList<TileEntity>();

	public void tick() {
		for (int i = 0; i < tickObject.size(); i++) {
			TileEntity tempObject = tickObject.get(i);

			tempObject.tick();

		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			Tile tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void addObject(Tile object) {
		this.object.add(object);
		if(object.isTileEntity()){
			this.tickObject.add((TileEntity)object);
		}
	}

	public void removeObject(Tile object) {
		this.object.remove(object);
		if(object.isTileEntity()){
			this.tickObject.remove((TileEntity)object);
		}
	}

	public void removeAllObjects() {
		object = new LinkedList<Tile>();
		tickObject= new LinkedList<TileEntity>();
	}
}
